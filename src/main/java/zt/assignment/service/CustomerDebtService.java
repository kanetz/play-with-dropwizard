package zt.assignment.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomerDebtService {
    private ConcurrentHashMap<String, AtomicInteger> customerDebts = new ConcurrentHashMap<String, AtomicInteger>();

    public int getDebtAmount(String email) {
        return ensureDebtEntry(email).intValue();
    }

    public int increaseDebt(String email, int amount) {
        AtomicInteger debtEntry = ensureDebtEntry(email);
        return debtEntry.addAndGet(amount);
    }

    private AtomicInteger ensureDebtEntry(String email) {
        AtomicInteger defaultEntry = new AtomicInteger(0);
        AtomicInteger existingEntry = customerDebts.putIfAbsent(email, defaultEntry);
        return existingEntry != null ? existingEntry : defaultEntry;
    }
}
