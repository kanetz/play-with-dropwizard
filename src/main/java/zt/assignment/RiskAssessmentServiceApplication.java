package zt.assignment;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import zt.assignment.resource.DecisionResource;

public class RiskAssessmentServiceApplication extends Application<RiskAssessmentServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new RiskAssessmentServiceApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<RiskAssessmentServiceConfiguration> bootstrap) {

    }

    @Override
    public void run(RiskAssessmentServiceConfiguration riskAssessmentServiceConfiguration, Environment environment) throws Exception {
        environment.healthChecks().register("RAS", new RiskAssessmentServiceHealthCheck());
        environment.jersey().register(DecisionResource.class);
    }
}
