package com.guofan.wallet;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

public class Account {
    private double balance = 0;
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private int maxNumOfTransactions = 10;
    private UUID ID;

    public int GetMaxNumOfTransactions() {
        return maxNumOfTransactions;
    }

    public Account() {
        ID = UUID.randomUUID();
    }

    public String toString() {
        return ID.toString();
    }

    public double GetBalance() {
        return balance;
    }

    //    This function is concurrency safe.
    public synchronized boolean WithDraw(double amount) {
        if (GetBalance() < amount)
            return false;

        balance -= amount;
        PutIntoTransactions(-amount);
        return true;
    }

//    This function is concurrency unsafe,
//    which is only for testing purposes.
//
//    The two differences between this function and
//    the last function is:
//      1) This function does not use the synchronized keyword,
//          which imposes concurrency safety;
//      2) This function has delays inside, which can
//          lead to concurrency errors more easily;
//
    public boolean NonConcurrentWithDraw(double amount) {
        if (GetBalance() < amount)
            return false;

//        A delay is added, so there is a higher chance
//        that concurrency errors are more easily generated.
        try {
            System.out.println(new Timestamp(System.currentTimeMillis()));
            System.out.println("start sleeping.");
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.out.println("got interrupted!");
        }

        balance -= amount;
        PutIntoTransactions(-amount);
        return true;
    }

    public boolean Deposit(double amount) {
        balance += amount;
        PutIntoTransactions(amount);
        return true;
    }

    public boolean PutIntoTransactions(double balance) {
        if (transactions.size() >= maxNumOfTransactions) {
            transactions.remove(0);
        }

        transactions.add(new Transaction(balance));
        return true;
    }

    public ArrayList<Transaction> GetTransactions() {
        return transactions;
    }
}