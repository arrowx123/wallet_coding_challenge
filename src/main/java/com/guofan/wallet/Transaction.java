package com.guofan.wallet;

import java.sql.Timestamp;
import java.util.UUID;

public class Transaction {
    private UUID ID;
    private Timestamp timestamp;
    private double balance;

    public Transaction(double balance) {

        ID = UUID.randomUUID();
        timestamp = new Timestamp(System.currentTimeMillis());
        this.balance = balance;

    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public double getBalance() {
        return balance;
    }

    public String toString() {
        return ("Transaction " + ID + " happened at " + timestamp + ", with balance " + balance + ".");
    }
}
