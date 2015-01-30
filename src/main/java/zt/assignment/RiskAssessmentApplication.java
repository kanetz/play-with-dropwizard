package zt.assignment;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import zt.assignment.resource.DecisionResource;

public class RiskAssessmentApplication extends Application<RiskAssessmentConfiguration> {

    public static void main(String[] args) throws Exception {
        new RiskAssessmentApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<RiskAssessmentConfiguration> bootstrap) {
    }

    @Override
    public void run(RiskAssessmentConfiguration riskAssessmentConfiguration, Environment environment) throws Exception {
        environment.healthChecks().register("Risk Assessment Service", new RiskAssessmentHealthCheck());
        environment.jersey().register(DecisionResource.class);
    }
}
