package zt.assignment.resource;

import zt.assignment.representation.Decision;
import zt.assignment.representation.Transaction;

public interface RiskAssessor {
    public Decision assess(Transaction transaction);
}

