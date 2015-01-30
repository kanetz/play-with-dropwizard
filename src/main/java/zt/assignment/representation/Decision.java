package zt.assignment.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Decision {
    private boolean accepted;
    private String reason;

    public Decision() {
    }

    private Decision(boolean accepted, String reason) {
        this.accepted = accepted;
        this.reason = reason;
    }

    public static Decision reject(String reason) {
        return new Decision(false, reason);
    }

    public static Decision accept() {
        return new Decision(true, "ok");
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
