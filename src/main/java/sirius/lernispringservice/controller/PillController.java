package sirius.lernispringservice.controller;

import jakarta.validation.Valid;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sirius.lernispringservice.dto.AnswerRequestDTO;
import sirius.lernispringservice.dto.PillProgressDTO;
import sirius.lernispringservice.dto.PillRequestDTO;
import sirius.lernispringservice.service.PillService;

import java.io.IOException;

@RestController
@RequestMapping("/pill")
public class PillController {

    private final PillService pillService;

    @Autowired
    PillController(PillService pillService){
        this.pillService = pillService;
    }

    @GetMapping("/progress")
    public ResponseEntity<PillProgressDTO> getProgress(@Valid @RequestBody PillRequestDTO pillRequestDTO) throws ParseException, IOException {
        return new ResponseEntity<>(pillService.getPillProgress(pillRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/answer")
    public ResponseEntity<PillProgressDTO> answerQuestion(@Valid @RequestBody AnswerRequestDTO answerRequestDTO) throws ParseException, IOException {
        return new ResponseEntity<>(pillService.answerQuestion(answerRequestDTO), HttpStatus.OK);
    }
}
