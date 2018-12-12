package com.guofan.wallet;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;

public class TestAccount {
    @Test
    public void ZeroBalanceWithDrawFail() throws Exception{
        Account account = new Account();
        double amount = 10;
        boolean actual = account.WithDraw(amount);
        boolean expected = false;
        Assert.assertEquals(actual, expected);
    }
    @Test
    public void DepositSucceed() throws Exception{
        Account account = new Account();
        double amount = 10;
        boolean actual = account.Deposit(amount);
        boolean expected = true;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void WithDrawSucceed() throws Exception{
        Account account = new Account();
        double amount = 10;
        account.Deposit(amount);
        boolean actual = account.WithDraw(amount);
        boolean expected = true;
        Assert.assertEquals(actual, expected);
    }
    @Test
    public void TestGetBalance() throws Exception{
        Account account = new Account();
        double amount = 10;
        account.Deposit(amount);
        double actual = account.GetBalance();
        assertEquals(actual, amount);
    }
    @Test
    public void ConcurrentWithDrawFail() throws Exception{
//        Account account = new Account();
//        double amount = 10;
//        account.Deposit(amount);
//        boolean actual = account.WithDraw(amount);
//        boolean expected = true;
//        Assert.assertEquals(actual, expected);
    }
}
