package com.guofan.wallet;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class TestAccount {
    @Test
    public void ZeroBalanceWithDrawFail() throws Exception {
        Account account = new Account();
        double amount = 10;
        boolean actual = account.WithDraw(amount);
        boolean expected = false;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void DepositSucceed() throws Exception {
        Account account = new Account();
        double amount = 10;
        boolean actual = account.Deposit(amount);
        boolean expected = true;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void WithDrawSucceed() throws Exception {
        Account account = new Account();
        double amount = 10;
        account.Deposit(amount);
        boolean actual = account.WithDraw(amount);
        boolean expected = true;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void TestGetBalance() throws Exception {
        Account account = new Account();
        double amount = 10;
        account.Deposit(amount);
        double actual = account.GetBalance();
        assertEquals(actual, amount);
    }

    @Test
    public void ConcurrentWithdrawFailBalanceLessThanZero() throws Exception {
        Account account = new Account();
        double depositAmount = 10;
        double withdrawAmount = 5;
        account.Deposit(depositAmount);

        class WithdrawThread extends Thread {
            Account account;

            public WithdrawThread(Account account) {
                this.account = account;
            }

            public void run() {
                account.NonConcurrentWithDraw(withdrawAmount);
            }
        }

        int numOfThread = 10;
        ArrayList<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < numOfThread; i++) {
            threads.add(new WithdrawThread(account));
        }

        for (int i = 0; i < numOfThread; i++) {
            threads.get(i).start();
        }

        for (int i = 0; i < numOfThread; i++) {
            threads.get(i).join();
        }

        System.out.println("account.GetBalance(): " + account.GetBalance());
        Assert.assertTrue(account.GetBalance() < 0);
    }

    @Test
    public void ConcurrentWithdrawSucceed() throws Exception {
        Account account = new Account();
        double depositAmount = 10;
        double withdrawAmount = 5;
        account.Deposit(depositAmount);

        class WithdrawThread extends Thread {
            Account account;

            public WithdrawThread(Account account) {
                this.account = account;
            }

            public void run() {
                account.WithDraw(withdrawAmount);
            }
        }

        int numOfThread = 10;
        ArrayList<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < numOfThread; i++) {
            threads.add(new WithdrawThread(account));
        }

        for (int i = 0; i < numOfThread; i++) {
            threads.get(i).start();
        }

        for (int i = 0; i < numOfThread; i++) {
            threads.get(i).join();
        }

        System.out.println("account.GetBalance(): " + account.GetBalance());
        Assert.assertTrue(account.GetBalance() >= 0);
    }
}
