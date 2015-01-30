package zt.assignment;

import com.codahale.metrics.health.HealthCheck;

public class RiskAssessmentHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
