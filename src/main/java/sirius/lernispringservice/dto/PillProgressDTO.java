package sirius.lernispringservice.dto;

import java.util.List;

public class PillProgressDTO {

    private boolean completed;
    private int progress;
    private List<PillNodeDTO> nodes;

    public PillProgressDTO(boolean completed, int progress, List<PillNodeDTO> nodes){
        this.completed = completed;
        this.progress = progress;
        this.nodes = nodes;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getProgress() {
        return progress;
    }

    public List<PillNodeDTO> getNodes() {
        return nodes;
    }
}
