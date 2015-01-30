package zt.assignment.resource;

import zt.assignment.representation.Decision;
import zt.assignment.representation.Transaction;
import zt.assignment.service.CustomerDebtService;

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

    public DecisionResource(CustomerDebtService customerDebtService) {
        this.customerDebtService = customerDebtService;
    }

    @POST
    public Decision decide(Transaction transaction) {
        if(transaction.getAmount() < 10) {
            return accept(transaction);
        }

        if(transaction.getAmount() > 1000) {
            return Decision.reject("amount");
        }

        if(customerDebtService.getDebtAmount(transaction.getEmail()) + transaction.getAmount() > 1000) {
            return Decision.reject("debt");
        }

        return accept(transaction);
    }

    private Decision accept(Transaction transaction) {
        customerDebtService.increaseDebt(transaction.getEmail(), transaction.getAmount());
        return Decision.accept();
    }
}
