package zt.assignment.service.assessor;

import zt.assignment.representation.Decision;
import zt.assignment.representation.Transaction;
import zt.assignment.resource.RiskAssessor;

public class RiskyTransactionAmountAssessor implements RiskAssessor {
    @Override
    public Decision assess(Transaction transaction) {
        if(transaction.getAmount() > 1000) {
            return Decision.reject("amount");
        }
        return null;
    }
}
