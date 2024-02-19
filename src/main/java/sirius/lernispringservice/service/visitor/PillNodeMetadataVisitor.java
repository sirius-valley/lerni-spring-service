package sirius.lernispringservice.service.visitor;

import form.node.implementations.ActionNode;
import form.node.implementations.QuestionNode;
import visitor.Visitor;

import java.util.Map;

public class PillNodeMetadataVisitor implements Visitor<Map<String,Object>> {

    @Override
    public Map<String, Object> visit(QuestionNode questionNode) {
        return (Map<String, Object>) questionNode.getElement().getMetadata().getMetadata();
    }

    @Override
    public Map<String, Object> visit(ActionNode actionNode) {
        return null;
    }
}
