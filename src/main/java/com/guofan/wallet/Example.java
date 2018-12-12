/*
    The main interface for this library is Wallet,
    and there are two auxiliary classes, which are
    Account and Transaction.

    There are three major functions of the Wallet class,
    which are related to the core functionality of this library.
        1) public boolean Withdraw(Account account, double amount):
            Withdraw money from an account, if the account does not exist,
            an AccountNotExistException exception will be thrown;
        2) public boolean Deposit(Account account, double amount):
            Deposit money into an account, if the account does not exist,
            an AccountNotExistException exception will be thrown;
        3) public boolean Transfer(Account account1, Account account2, double amount):
            Transfer money from one account to another account, if any of the accounts does not exist,
            an AccountNotExistException exception will be thrown;;

    Other functions of the Wallet class:
        1) public Wallet():
            This constructor creates a Wallet class,
            which has an ID as the identifier, and an account;
        2) public Account CreateNewAccount()
            Create another account for the current Wallet class;
        3) public ArrayList<Account> ListAccounts():
            List all accounts for the current Wallet class;
        4) public boolean AccountExist(Account account):
            Check if the specified account exists within the current Wallet class;
        5) public double GetBalance(Account account):
            Return the balance of the specified account, if the account does not exist,
            an AccountNotExistException exception will be thrown.
        6) public ArrayList<Transaction> ReturnTransactions(Account account):
            Return all transactions of the specified account, if the account does not exist,
            an AccountNotExistException exception will be thrown.

    In terms of the Account and Transaction classes,
    all their interfaces can be called through the Wallet class,
    so we do not discuss them here.
*/

// The following class serves as an exmaple about how to use the library

package com.guofan.wallet;

import java.util.ArrayList;

public class Example {
    public static void main(String[] args) throws AccountNotExistException {
//      Create a new wallet class
        Wallet wallet = new Wallet();

//      After creating a wallet, an account is added automatically.
        Account account = wallet.ListAccounts().get(0);

//      Deposit money into the account.
        double amountToDeposit = 10;
        wallet.Deposit(account, amountToDeposit);
        System.out.println("Deposit " + amountToDeposit + " into " + account + ".");
        System.out.println(
                "The " + account + " account balance is: " + wallet.GetBalance(account) + ".\n"
        );

//      Withdraw money from the account.
        double amountToWithdraw = 10;
        wallet.Withdraw(account, amountToWithdraw);
        System.out.println("Withdraw " + amountToDeposit + " from " + account + " .");
        System.out.println(
                "The " + account + " account balance is: " + wallet.GetBalance(account) + ".\n"
        );

//      Create another account within the same wallet
        wallet.CreateNewAccount();
        Account anotherAccount = wallet.ListAccounts().get(1);
        wallet.Deposit(anotherAccount, amountToDeposit);

//      Transfer money from one account to another.
        System.out.println(
                "Transfer " + amountToDeposit + " from " + anotherAccount + " to " + account + "."
        );
        System.out.println(
                "The " + anotherAccount + " account balance is: " + wallet.GetBalance(anotherAccount) + "."
        );
        System.out.println(
                "The " + account + " account balance is: " + wallet.GetBalance(account) + "."
        );
        wallet.Transfer(anotherAccount, account, amountToDeposit);
        System.out.println(
                "The " + anotherAccount + " account balance is: " + wallet.GetBalance(anotherAccount) + "."
        );
        System.out.println(
                "The " + account + " account balance is: " + wallet.GetBalance(account) + ".\n"
        );

//      Retrieve transactions.
        System.out.println("Retrieve transactions from " + account + ".");
        ArrayList<Transaction> transactions = wallet.ReturnTransactions(account);
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println(transactions.get(i));
        }
    }
}