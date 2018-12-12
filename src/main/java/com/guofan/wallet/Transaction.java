package com.guofan.wallet;

import java.sql.Timestamp;
import java.util.UUID;

public class Transaction {
    private UUID ID;
    private Timestamp timestamp;
    private double balance;

    public Transaction(double balance){

        ID = UUID.randomUUID();
        timestamp = new Timestamp(System.currentTimeMillis());
        this.balance = balance;

    }
}
