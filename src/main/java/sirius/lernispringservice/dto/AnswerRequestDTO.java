package sirius.lernispringservice.dto;

import org.json.simple.JSONObject;

public class AnswerRequestDTO {

    private PillAnswerDTO answer;
    private JSONObject pillForm;


    public AnswerRequestDTO (PillAnswerDTO answer, JSONObject pillForm) {
        this.answer = answer;
        this.pillForm = pillForm;
    }

    public PillAnswerDTO getAnswer() {
        return answer;
    }

    public JSONObject getPillForm() {
        return pillForm;
    }
}
