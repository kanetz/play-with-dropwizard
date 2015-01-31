package zt.assignment.service.assessor;

import zt.assignment.representation.Decision;
import zt.assignment.representation.Transaction;
import zt.assignment.resource.RiskAssessor;
import zt.assignment.service.CustomerDebtService;

public class CustomerDebtAssessor implements RiskAssessor {
    private CustomerDebtService customerDebtService;

    public CustomerDebtAssessor(CustomerDebtService customerDebtService) {
        this.customerDebtService = customerDebtService;
    }

    @Override
    public Decision assess(Transaction transaction) {
        if (customerDebtService.getDebtAmount(transaction.getEmail()) + transaction.getAmount() > 1000) {
            return Decision.reject("debt");
        }
        return null;
    }
}
