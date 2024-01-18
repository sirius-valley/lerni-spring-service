package sirius.lernispringservice.controller;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sirius.lernispringservice.dto.PillNodeDTO;
import sirius.lernispringservice.dto.PillRequestDTO;
import sirius.lernispringservice.service.PillService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/pill")
public class PillController {

    private final PillService pillService;

    @Autowired
    PillController(PillService pillService){
        this.pillService = pillService;
    }

    @GetMapping("/progress")
    public ResponseEntity<List<PillNodeDTO>> getProgress(@RequestBody PillRequestDTO pillRequestDTO) throws ParseException, IOException {
        return new ResponseEntity<>(pillService.getPillProgress(pillRequestDTO), HttpStatus.OK);
    }
}
