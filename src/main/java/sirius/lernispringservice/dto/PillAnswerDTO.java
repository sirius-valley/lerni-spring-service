package sirius.lernispringservice.dto;

import java.util.Map;

public class PillAnswerDTO {

    private String questionId;
    private String value;
    private Map<String, Object> params;

    public PillAnswerDTO (String questionId, String value, Map<String, Object> params){
        this.questionId = questionId;
        this.value = value;
        this.params = params;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getValue() {
        return value;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}
