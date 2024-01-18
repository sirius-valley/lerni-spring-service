package sirius.lernispringservice.dto;

import java.util.List;

public class PillProgressDTO {

    private boolean completed;
    private List<PillNodeDTO> nodes;

    public PillProgressDTO(boolean completed, List<PillNodeDTO> nodes){
        this.completed = completed;
        this.nodes = nodes;
    }

    public boolean isCompleted() {
        return completed;
    }

    public List<PillNodeDTO> getNodes() {
        return nodes;
    }
}
