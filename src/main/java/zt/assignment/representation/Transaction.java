package zt.assignment.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {
    private String email;
    private String firstName;
    private String lastName;
    private int amount;

    public Transaction() {
    }

    public Transaction(String email, String firstName, String lastName, int amount) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.amount = amount;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty
    public String getLastName() {
        return lastName;
    }

    @JsonProperty
    public int getAmount() {
        return amount;
    }
}
