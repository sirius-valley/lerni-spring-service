package sirius.lernispringservice.dto;

import org.json.simple.JSONObject;

import java.util.List;

public class PillRequestDTO {

    //stack instead
    private List<PillAnswerDTO> answers;
    private JSONObject pillForm;

    public PillRequestDTO (List<PillAnswerDTO> answers, JSONObject pillForm) {
        this.answers = answers;
        this.pillForm = pillForm;
    }

    public List<PillAnswerDTO> getAnswers() {
        return answers;
    }

    public JSONObject getPillForm() {
        return pillForm;
    }
}
