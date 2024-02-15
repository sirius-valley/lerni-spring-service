package sirius.lernispringservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.json.simple.JSONObject;

public class AnswerRequestDTO {

    @Valid
    private PillAnswerDTO answer;
    @NotNull
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
