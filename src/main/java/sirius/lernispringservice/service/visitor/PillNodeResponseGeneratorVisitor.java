package sirius.lernispringservice.service.visitor;

import form.node.FormNodeType;
import form.node.implementations.ActionNode;
import form.node.implementations.QuestionNode;
import sirius.lernispringservice.dto.PillNodeDTO;
import visitor.Visitor;

public class PillNodeResponseGeneratorVisitor implements Visitor<PillNodeDTO> {
    @Override
    public PillNodeDTO visit(QuestionNode questionNode) {
        return new PillNodeDTO(questionNode.getId(), FormNodeType.QUESTION, questionNode.getElement().getQuestionContent(), questionNode.getElement().getMetadata());
    }

    @Override
    public PillNodeDTO visit(ActionNode actionNode) {
        return new PillNodeDTO(actionNode.getId(), FormNodeType.ACTION, actionNode.getElement().getAction());
    }
}
