package com.guofan.wallet;

import java.util.ArrayList;

public class Wallet   {
    private ArrayList<Account> accountList = new ArrayList<Account>();

    public Wallet(){
        Account account = new Account();
        accountList.add(account);
    }

    public Account CreateNewAccount(){
        Account account = new Account();
        accountList.add(account);
        return account;
    }

    public ArrayList<Account> ListAccounts(){
        return accountList;
    }

    public boolean AccountExist(Account account){
        for(int i = 0; i < accountList.size(); i ++){
            if(account == accountList.get(i))
                return true;
        }
        return false;
    }

    public boolean Withdraw (Account account, double amount) throws AccountNotExistException{
        if(!AccountExist(account))
            throw new AccountNotExistException(account + " does not exist");

        return account.WithDraw(amount);
    }

    public boolean Deposit(Account account, double amount) throws AccountNotExistException{
        if(!AccountExist(account))
            throw new AccountNotExistException(account + " does not exist");

        return account.Deposit(amount);
    }

    public boolean Transfer(Account account1, Account account2, double amount)throws AccountNotExistException{
        if(!AccountExist(account1))
            throw new AccountNotExistException(account1 + " does not exist");
        if(!AccountExist(account2))
            throw new AccountNotExistException(account2 + " does not exist");

        if(account1.WithDraw(amount)) {
            account2.Deposit(amount);
            return true;
        }

        return false;
    }

    public ArrayList<Transaction> ReturnTransactions(Account account){
        ArrayList<Transaction> arrayList = new ArrayList<Transaction>();
        return arrayList;
    }

}
