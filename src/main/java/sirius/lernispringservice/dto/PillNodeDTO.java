package sirius.lernispringservice.dto;

import form.node.FormNodeType;
import metadata.Metadata;


public class PillNodeDTO {

    private String nodeId;
    private FormNodeType type;
    private String content;
    private Metadata metadata;

    public PillNodeDTO(String nodeId, FormNodeType type, String content){
        this.nodeId = nodeId;
        this.type = type;
        this.content = content;
    }

    public PillNodeDTO(String nodeId, FormNodeType type, String content, Metadata metadata){
        this.nodeId = nodeId;
        this.type = type;
        this.content = content;
        this.metadata = metadata;
    }

    public String getNodeId() {
        return nodeId;
    }

    public FormNodeType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public Metadata getMetadata() {
        return metadata;
    }
}
