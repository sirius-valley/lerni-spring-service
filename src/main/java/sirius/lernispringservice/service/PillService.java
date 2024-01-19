package sirius.lernispringservice.service;

import factory.FormFactory;
import form.Form;
import form.node.FormNode;
import form.node.FormNodeType;
import form.result.FormTransitionResult;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import sirius.lernispringservice.dto.*;
import sirius.lernispringservice.service.visitor.PillNodeResponseGeneratorVisitor;
import visitor.Visitable;
import visitor.Visitor;

import java.io.IOException;
import java.util.*;

@Service
public class PillService {

    public PillProgressDTO getPillProgress(PillRequestDTO pillRequestDTO) throws ParseException, IOException {
        //create form from json object
        JSONObject pillJson = pillRequestDTO.getPillForm();
        Form pillForm = FormFactory.createFormFromJsonObject(pillJson);

        //add user's answers to a stack
        Stack<PillAnswerDTO> stack = getAnswerStack(pillRequestDTO);

        List<String> visitedIds = new ArrayList<>();
        visitedIds.add(pillForm.getInitial().getId());
        List<PillNodeDTO> pillProgress = new ArrayList<>();

        FormNode current;
        do {
            current = pillForm.getCurrent(visitedIds);
            FormTransitionResult result;

            pillProgress.add(getPillNodeDTO(current, stack.isEmpty() ? "" : stack.peek().getValue()));
            if (current.getType() == FormNodeType.QUESTION) {
                if (stack.isEmpty() || !current.hasNext())
                    return new PillProgressDTO(!stack.isEmpty(), calculateProgress(pillJson, current.getId()), pillProgress);
                PillAnswerDTO answer = stack.pop();
                result = pillForm.getNext(answer.getValue(), visitedIds);
            } else {
                if (!current.hasNext())
                    return new PillProgressDTO(true, calculateProgress(pillJson, current.getId()), pillProgress);
                result = pillForm.getNext("answer", visitedIds);
            }
            visitedIds.add(result.getValue().getId());
        } while (current.hasNext());

        return new PillProgressDTO(false, calculateProgress(pillJson, current.getId()), pillProgress);
    }


    public PillProgressDTO answerQuestion(AnswerRequestDTO answerRequestDTO) throws ParseException, IOException {
        //create form from json object
        JSONObject pillJson = answerRequestDTO.getPillForm();
        Form pillForm = FormFactory.createFormFromJsonObject(pillJson);

        List<String> visitedIds = new ArrayList<>();
        visitedIds.add(answerRequestDTO.getAnswer().getQuestionId());
        List<PillNodeDTO> pillProgress = new ArrayList<>();

        if (!pillForm.hasNext(visitedIds)) {
            return new PillProgressDTO(true, calculateProgress(pillJson, pillForm.getCurrent(visitedIds).getId()), pillProgress);
        }

        FormTransitionResult answerResult = pillForm.getNext(answerRequestDTO.getAnswer().getValue(), visitedIds);
        visitedIds.add(answerResult.getValue().getId());
        answerResult.getValue();
        FormNode current;

        do {
            current = pillForm.getCurrent(visitedIds);
            FormTransitionResult result;
            pillProgress.add(getPillNodeDTO(current, ""));
            if (current.getType() == FormNodeType.QUESTION) {
                return new PillProgressDTO(false, calculateProgress(pillJson, current.getId()), pillProgress);
            } else {
                if (!current.hasNext())
                    return new PillProgressDTO(true, calculateProgress(pillJson, current.getId()), pillProgress);
                result = pillForm.getNext("answer", visitedIds);
            }
            visitedIds.add(result.getValue().getId());
        } while (current.hasNext());

        return new PillProgressDTO(true, calculateProgress(pillJson, current.getId()), pillProgress);
    }


    private Stack<PillAnswerDTO> getAnswerStack(PillRequestDTO pillRequestDTO) {
        List<PillAnswerDTO> answers = pillRequestDTO.getAnswers();
        Collections.reverse(answers);
        Stack<PillAnswerDTO> stack = new Stack<>();
        stack.addAll(answers);
        return stack;
    }

    private int calculateProgress(JSONObject form, String currentId) {
        List<LinkedHashMap<String, String>> elements = (List<LinkedHashMap<String, String>>) form.get("elements");
        List<String> ids = elements.stream().map(map -> map.get("id")).toList();
        return Math.round((float) ids.indexOf(currentId) / (ids.size() - 1) * 100);
    }


    private NodeContentDTO getResponseNodeDTOWithVisitor(FormNode value) {
        Visitor<NodeContentDTO> visitor = new PillNodeResponseGeneratorVisitor();
        Visitable visitable = (Visitable) value;
        return visitable.accept(visitor);
    }

    private PillNodeDTO getPillNodeDTO(FormNode value, Object answer) {
        NodeContentDTO nodeContentDTO = getResponseNodeDTOWithVisitor(value);
        return new PillNodeDTO(value.getId(), value.getType(), nodeContentDTO, value.getType() == FormNodeType.QUESTION ? answer : "");
    }
}
