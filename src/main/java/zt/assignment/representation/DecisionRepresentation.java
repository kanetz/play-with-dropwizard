package zt.assignment.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DecisionRepresentation {
    private boolean accepted;
    private String reason;

    public DecisionRepresentation() {
    }

    public DecisionRepresentation(boolean accepted, String reason) {
        this.accepted = accepted;
        this.reason = reason;
    }

    @JsonProperty
    public boolean isAccepted() {
        return accepted;
    }

    @JsonProperty
    public String getReason() {
        return reason;
    }
}
