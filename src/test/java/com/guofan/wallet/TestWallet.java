package com.guofan.wallet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.assertj.core.api.Assertions.*;


public class TestWallet {
    @Test
    public void TestAccountExistSucceed() throws Exception{
        Wallet wallet = new Wallet();
        Account account = wallet.ListAccounts().get(0);

        boolean actual = wallet.AccountExist(account);
        boolean expected = true;
        Assert.assertEquals(actual, expected);
    }
    @Test
    public void TestAccountExistFail() throws Exception{
        Wallet wallet = new Wallet();
        Account anotherAccount = new Account();

        boolean actual = wallet.AccountExist(anotherAccount);
        boolean expected = false;
        Assert.assertEquals(actual, expected);
    }
    @Test
    public void TestWithdrawFail() throws Exception{
        Wallet wallet = new Wallet();
        Account account = wallet.ListAccounts().get(0);
        double amount = 10;

        boolean actual = wallet.Withdraw(account, amount);
        boolean expected = false;
        Assert.assertEquals(actual, expected);
    }
    @Test
    public void TestDepositSucceed() throws Exception{
        Wallet wallet = new Wallet();
        Account account = wallet.ListAccounts().get(0);
        double amount = 10;

        boolean actual = wallet.Deposit(account, amount);
        boolean expected = true;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void TestWithdrawException() throws Exception{
        Wallet wallet = new Wallet();
        Account anotherAccount = new Account();
        double amount = 10;

        assertThatThrownBy(() -> wallet.Withdraw(anotherAccount, amount))
                .isInstanceOf(AccountNotExistException.class);
    }

    @Test
    public void TestTransferSucceed() throws Exception{
        Wallet wallet = new Wallet();
        Account account1 = wallet.ListAccounts().get(0);
        Account account2 = wallet.CreateNewAccount();

        double amount = 10;
        wallet.Deposit(account1, amount);
        boolean actual = wallet.Transfer(account1, account2, amount);
        boolean expected = true;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void TestTransferException() throws Exception{
        Wallet wallet = new Wallet();
        Account account1 = wallet.ListAccounts().get(0);
        Account account2 = new Account();

        double amount = 10;
        wallet.Deposit(account1, amount);

        assertThatThrownBy(() -> wallet.Transfer(account1, account2, amount))
                .isInstanceOf(AccountNotExistException.class);
    }
}
