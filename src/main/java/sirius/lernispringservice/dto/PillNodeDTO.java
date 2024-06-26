package sirius.lernispringservice.dto;

import form.node.FormNodeType;
import metadata.Metadata;

import java.util.List;


public class PillNodeDTO {

    private String nodeId;
    private FormNodeType type;
    private NodeContentDTO nodeContent;
    private Object answer;

    public PillNodeDTO(String nodeId, FormNodeType type, NodeContentDTO nodeContent, Object answer){
        this.nodeId = nodeId;
        this.type = type;
        this.answer = answer;
        this.nodeContent = nodeContent;
    }


    public String getNodeId() {
        return nodeId;
    }

    public FormNodeType getType() {
        return type;
    }

    public Object getAnswer() {
        return answer;
    }

    public NodeContentDTO getNodeContent() {
        return nodeContent;
    }
}
