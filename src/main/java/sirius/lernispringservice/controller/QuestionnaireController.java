package sirius.lernispringservice.controller;

import jakarta.validation.Valid;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sirius.lernispringservice.dto.AnswerRequestDTO;
import sirius.lernispringservice.dto.QuestionnaireProgressDTO;
import sirius.lernispringservice.service.QuestionnaireService;

import java.io.IOException;

@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {

    private final QuestionnaireService questionnaireService;
    @Autowired
    public QuestionnaireController(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @PostMapping("/answer")
    public ResponseEntity<QuestionnaireProgressDTO> answerQuestionnaire(@Valid @RequestBody AnswerRequestDTO answerRequestDTO) throws ParseException, IOException {
        return new ResponseEntity<>(questionnaireService.answerQuestionnaire(answerRequestDTO), HttpStatus.OK);
    }
}
