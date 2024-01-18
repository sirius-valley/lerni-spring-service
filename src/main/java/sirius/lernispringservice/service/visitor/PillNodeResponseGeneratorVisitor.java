package sirius.lernispringservice.service.visitor;

import form.node.implementations.ActionNode;
import form.node.implementations.QuestionNode;
import sirius.lernispringservice.dto.NodeContentDTO;
import visitor.Visitor;

public class PillNodeResponseGeneratorVisitor implements Visitor<NodeContentDTO> {
    @Override
    public NodeContentDTO visit(QuestionNode questionNode) {
        return new NodeContentDTO(questionNode.getElement().getQuestionContent(), questionNode.getElement().getMetadata());
    }

    @Override
    public NodeContentDTO visit(ActionNode actionNode) {
        return new NodeContentDTO(actionNode.getElement().getAction());
    }
}
