package zt.assignment.resource;

import zt.assignment.representation.Transaction;

public interface RiskEvaluator {
    public Judgement judge(Transaction transaction);
}
