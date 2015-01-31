package zt.assignment.test.unit;

import org.junit.Before;
import org.junit.Test;
import zt.assignment.service.CustomerDebtService;

import static org.fest.assertions.api.Assertions.*;

public class CustomerDebtServiceTest {

    private CustomerDebtService customerDebtService;

    @Before
    public void setup() {
        customerDebtService = new CustomerDebtService();
    }

    @Test
    public void should_return_0_if_customer_has_no_debt() {
        int debtAmount = customerDebtService.getDebtAmount("nodebt@email.com");
        assertThat(debtAmount).isEqualTo(0);
    }

    @Test
    public void should_return_previous_debt_for_customer() {
        CustomerDebtService customerDebtService = new CustomerDebtService();
        customerDebtService.increaseDebt("user@email.com", 100);
        int debtAmount = customerDebtService.getDebtAmount("user@email.com");
        assertThat(debtAmount).isEqualTo(100);
    }
}
