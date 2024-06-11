package sirius.lernispringservice.service;

import factory.FormFactory;
import form.Form;
import form.node.FormNode;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirius.lernispringservice.dto.AnswerRequestDTO;
import sirius.lernispringservice.dto.PillProgressDTO;
import sirius.lernispringservice.dto.QuestionnaireProgressDTO;
import sirius.lernispringservice.service.visitor.PillNodeMetadataVisitor;
import visitor.Visitable;
import visitor.Visitor;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class QuestionnaireService {

    private final PillService pillService;

    @Autowired
    public QuestionnaireService(PillService pillService) {
        this.pillService = pillService;
    }


    public QuestionnaireProgressDTO answerQuestionnaire(AnswerRequestDTO answerRequestDTO) throws ParseException, IOException {
        PillProgressDTO pillProgressDTO = pillService.answerQuestion(answerRequestDTO);
        Form pillForm = FormFactory.createFormFromJsonObject(answerRequestDTO.getPillForm());

        FormNode current = pillForm.getCurrent(List.of(answerRequestDTO.getAnswer().getQuestionId()));
        boolean isCorrect = isCorrectAnswer(current, answerRequestDTO.getAnswer().getValue());
        return new QuestionnaireProgressDTO(pillProgressDTO.isCompleted(), pillProgressDTO.getProgress(), isCorrect, pillProgressDTO.getNodes());
    }

    private boolean isCorrectAnswer(FormNode currentNode, Object answer) {
        Map<String, Object> metadata = this.getMetadata(currentNode);
        if (metadata == null || !metadata.containsKey("correct_answer")) return true;
        if (metadata.get("lerni_question_type") != "multiple-choice")
            return metadata.get("correct_answer").equals(answer);

        List<String> correctAnswers = (List<String>) metadata.get("correct_answer");
        List<String> answers = (List<String>) answer;

        Set<String> correctAnswersSet = new HashSet<>(correctAnswers);
        Set<String> answersSet = new HashSet<>(answers);
        return correctAnswersSet.equals(answersSet);
    }

    private Map<String, Object> getMetadata(FormNode value) {
        Visitor<Map<String, Object>> visitor = new PillNodeMetadataVisitor();
        Visitable visitable = (Visitable) value;
        return visitable.accept(visitor);
    }
}
