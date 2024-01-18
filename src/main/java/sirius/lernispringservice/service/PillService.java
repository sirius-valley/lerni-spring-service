package sirius.lernispringservice.service;

import factory.FormFactory;
import form.Form;
import form.node.FormNode;
import form.node.FormNodeType;
import form.result.FormTransitionResult;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import sirius.lernispringservice.dto.*;
import sirius.lernispringservice.service.visitor.PillNodeResponseGeneratorVisitor;
import visitor.Visitable;
import visitor.Visitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

@Service
public class PillService {

    public PillProgressDTO getPillProgress (PillRequestDTO pillRequestDTO) throws ParseException, IOException {
        //create form from json object
        Form pillForm = FormFactory.createFormFromJsonObject(pillRequestDTO.getPillForm());

        //add user's answers to a stack
        List<PillAnswerDTO> answers = pillRequestDTO.getAnswers();
        Collections.reverse(answers);
        Stack<PillAnswerDTO> stack = new Stack<>();
        stack.addAll(answers);

        List<String> visitedIds = new ArrayList<>();
        visitedIds.add(pillForm.getInitial().getId());
        List<PillNodeDTO> pillProgress = new ArrayList<>();

        FormNode current;
        do{
            current = pillForm.getCurrent(visitedIds);
            FormTransitionResult result;

            pillProgress.add(getPillNodeDTO(current, stack.isEmpty() ? "" : stack.peek().getValue()));
            if (current.getType() == FormNodeType.QUESTION){
                if(stack.isEmpty() || !current.hasNext()) return new PillProgressDTO(!stack.isEmpty(), pillProgress);
                PillAnswerDTO answer = stack.pop();
                result = pillForm.getNext(answer.getValue(), visitedIds);
            } else {
                if (!current.hasNext()) return new PillProgressDTO(true, pillProgress);
                result = pillForm.getNext("answer", visitedIds);
            }
            visitedIds.add(result.getValue().getId());
        } while (current.hasNext());

        return new PillProgressDTO(false, pillProgress);
    }

    private NodeContentDTO getResponseNodeDTOWithVisitor(FormNode value) {
        Visitor<NodeContentDTO> visitor = new PillNodeResponseGeneratorVisitor();
        Visitable visitable = (Visitable) value;
        return visitable.accept(visitor);
    }

    private PillNodeDTO getPillNodeDTO(FormNode value, String answer) {
        NodeContentDTO nodeContentDTO = getResponseNodeDTOWithVisitor(value);
        return new PillNodeDTO(value.getId(), value.getType(), nodeContentDTO, answer);
    }
}
