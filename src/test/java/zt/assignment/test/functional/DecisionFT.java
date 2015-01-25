package zt.assignment.test.functional;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.fest.assertions.api.Assertions;
import org.junit.ClassRule;
import org.junit.Test;
import zt.assignment.RiskAssessmentServiceApplication;
import zt.assignment.RiskAssessmentServiceConfiguration;
import zt.assignment.representation.DecisionRepresentation;

public class DecisionFT {

    @ClassRule
    public static final DropwizardAppRule<RiskAssessmentServiceConfiguration> rule = new DropwizardAppRule<RiskAssessmentServiceConfiguration>(RiskAssessmentServiceApplication.class, null);

    @Test
    public void should_return_200_OK_as_status_code() {
        Client client = new Client();
        WebResource resource = client.resource(String.format("http://localhost:%d/decision", rule.getLocalPort()));
        DecisionRepresentation response = resource.post(DecisionRepresentation.class);
        Assertions.assertThat(response.isAccepted()).isTrue();
        Assertions.assertThat(response.getReason()).isEqualTo("ok");
    }
}
