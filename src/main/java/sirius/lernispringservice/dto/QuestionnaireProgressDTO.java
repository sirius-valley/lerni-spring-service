package sirius.lernispringservice.dto;

import java.util.List;

public class QuestionnaireProgressDTO {

    private boolean completed;
    private int progress;
    private boolean isCorrect;
    private List<PillNodeDTO> nodes;

    public QuestionnaireProgressDTO(boolean completed, int progress, boolean isCorrect, List<PillNodeDTO> nodes) {
        this.completed = completed;
        this.progress = progress;
        this.isCorrect = isCorrect;
        this.nodes = nodes;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getProgress() {
        return progress;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public List<PillNodeDTO> getNodes() {
        return nodes;
    }
}
