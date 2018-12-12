package com.guofan.wallet;

import java.util.ArrayList;
import java.util.UUID;

public class Account {
    private double balance = 0;
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private int N = 10;
    private UUID ID;

    public Account(){
        ID = UUID.randomUUID();
    }

    public String toString(){

        return ID.toString();

    }

    public double GetBalance(){
        return balance;
    }
    public synchronized boolean WithDraw(double amount){
        if(GetBalance() < amount)
            return false;

        balance -= amount;
        PutIntoTransactions(-amount);
        return true;
    }
    public boolean Deposit(double amount){
        balance += amount;
        PutIntoTransactions(amount);
        return true;
    }

    public boolean PutIntoTransactions(double balance){
        if(transactions.size() >= N){
            transactions.remove(0);
        }

        transactions.add(new Transaction(balance));
        return true;
    }
}