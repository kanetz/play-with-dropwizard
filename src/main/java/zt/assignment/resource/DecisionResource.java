package zt.assignment.resource;

import zt.assignment.representation.Decision;
import zt.assignment.representation.Transaction;
import zt.assignment.service.CustomerDebtService;
import zt.assignment.service.assessor.CustomerDebtAssessor;
import zt.assignment.service.assessor.RiskFreeTransactionAmountAssessor;
import zt.assignment.service.assessor.RiskyTransactionAmountAssessor;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/decision")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DecisionResource {
    private CustomerDebtService customerDebtService;
    private List<RiskAssessor> riskAssessors;

    public DecisionResource(final CustomerDebtService customerDebtService) {
        this.customerDebtService = customerDebtService;
        this.riskAssessors = new ArrayList<RiskAssessor>() {{
            add(new RiskFreeTransactionAmountAssessor());
            add(new RiskyTransactionAmountAssessor());
            add(new CustomerDebtAssessor(customerDebtService));
        }};
    }

    @POST
    public Decision decide(Transaction transaction) {
        Decision decision = getDecisionFromAssessors(transaction);
        if(decision.isAccepted()) {
            accept(transaction);
        }
        return decision;
    }

    private Decision getDecisionFromAssessors(Transaction transaction) {
        for (RiskAssessor riskAssessor : riskAssessors) {
            Decision decision = riskAssessor.assess(transaction);
            if (decision != null) {
                return decision;
            }
        }
        return Decision.accept();
    }

    private void accept(Transaction transaction) {
        customerDebtService.increaseDebt(transaction.getEmail(), transaction.getAmount());
    }
}
