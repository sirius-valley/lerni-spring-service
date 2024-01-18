package sirius.lernispringservice.dto;

import java.util.Map;

public class PillAnswerDTO {

    private String questionId;
    private Object value;
    private Map<String, Object> params;

    public PillAnswerDTO (String questionId, Object value, Map<String, Object> params){
        this.questionId = questionId;
        this.value = value;
        this.params = params;
    }

    public String getQuestionId() {
        return questionId;
    }

    public Object getValue() {
        return value;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}
