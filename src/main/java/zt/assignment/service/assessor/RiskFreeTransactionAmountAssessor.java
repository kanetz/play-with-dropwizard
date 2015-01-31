package zt.assignment.service.assessor;

import zt.assignment.representation.Decision;
import zt.assignment.representation.Transaction;
import zt.assignment.resource.RiskAssessor;

public class RiskFreeTransactionAmountAssessor implements RiskAssessor {
    @Override
    public Decision assess(Transaction transaction) {
        if(transaction.getAmount() < 10) {
            return Decision.accept();
        }
        return null;
    }
}
