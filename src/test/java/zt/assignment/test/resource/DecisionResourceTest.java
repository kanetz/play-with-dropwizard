package zt.assignment.test.resource;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Rule;
import org.junit.Test;
import zt.assignment.representation.Decision;
import zt.assignment.representation.Transaction;
import zt.assignment.resource.DecisionResource;
import zt.assignment.service.CustomerDebtService;

import javax.ws.rs.core.MediaType;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class DecisionResourceTest {
    private final String email = "my@email.com";
    private final CustomerDebtService mockedCustomerDebtService = mock(CustomerDebtService.class);

    @Rule
    public final ResourceTestRule RULE = ResourceTestRule.builder()
            .addResource(new DecisionResource(mockedCustomerDebtService))
            .build();

    @Test
    public void should_always_accept_transaction_when_amount_is_less_than_10_and_debt_is_0() {
        givenExistingCustomerDebt(0);
        Transaction transactionWithAmountLessThan10 = applyForTransaction(9);
        Decision result = makeDecision(transactionWithAmountLessThan10);
        assertTransactionAccepted(result, transactionWithAmountLessThan10);
    }

    @Test
    public void should_always_accept_transaction_when_amount_is_less_than_10_and_debt_is_higher_than_1000() {
        givenExistingCustomerDebt(1001);
        Transaction transactionWithAmountLessThan10 = applyForTransaction(9);
        Decision result = makeDecision(transactionWithAmountLessThan10);
        assertTransactionAccepted(result, transactionWithAmountLessThan10);
    }

    @Test
    public void should_always_reject_transaction_when_amount_is_higher_than_1000() {
        givenExistingCustomerDebt(0);
        Transaction transactionWithAmountHigherThan1000 = applyForTransaction(1001);
        Decision result = makeDecision(transactionWithAmountHigherThan1000);
        assertTransactionRejected(result, "amount");
    }

    @Test
    public void should_reject_transaction_when_debt_is_larger_than_1000() {
        givenExistingCustomerDebt(1001);
        Transaction transaction = applyForTransaction(100);
        Decision result = makeDecision(transaction);
        assertTransactionRejected(result, "debt");
    }

    @Test
    public void should_reject_transaction_when_debt_plus_amount_is_larger_than_1000() {
        givenExistingCustomerDebt(901);
        Transaction transaction = applyForTransaction(100);
        Decision result = makeDecision(transaction);
        assertTransactionRejected(result, "debt");
    }

    @Test
    public void should_accept_transaction_when_debt_plus_amount_is_not_larger_than_1000() {
        givenExistingCustomerDebt(900);
        Transaction transaction = applyForTransaction(100);
        Decision result = makeDecision(transaction);
        assertTransactionAccepted(result, transaction);
    }

    private Transaction applyForTransaction(int amount) {
        return new Transaction(email, "firstName", "lastName", amount);
    }

    private Decision makeDecision(Transaction transaction) {
        return RULE.client().resource("/decision")
                .entity(transaction, MediaType.APPLICATION_JSON_TYPE)
                .post(Decision.class);
    }

    private void assertTransactionAccepted(Decision result, Transaction transaction) {
        assertThat(result.isAccepted()).isTrue();
        assertThat(result.getReason()).isEqualTo("ok");
        verify(mockedCustomerDebtService).increaseDebt(transaction.getEmail(), transaction.getAmount());
    }

    private void assertTransactionRejected(Decision result, String reason) {
        assertThat(result.isAccepted()).isFalse();
        assertThat(result.getReason()).isEqualTo(reason);
    }

    private void givenExistingCustomerDebt(int amount) {
        when(mockedCustomerDebtService.getDebtAmount(eq(email)))
                .thenReturn(amount);
    }
}
