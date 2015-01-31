package zt.assignment.resource;

public class Judgement {
    public static final Judgement AFFIRMATIVE = new Judgement("ok");
    public static final Judgement NEGATIVE = new Judgement("ok");

    private String comment;

    private Judgement(String comment) {
        this.comment = comment;
    }


}
