package sirius.lernispringservice.dto;

import metadata.Metadata;

public class NodeContentDTO {

    private String content;
    private Metadata metadata;

    public NodeContentDTO(String content){
        this.content = content;
    }

    public NodeContentDTO(String content, Metadata metadata){
        this.content = content;
        this.metadata = metadata;
    }

    public String getContent() {
        return content;
    }

    public Metadata getMetadata() {
        return metadata;
    }
}
