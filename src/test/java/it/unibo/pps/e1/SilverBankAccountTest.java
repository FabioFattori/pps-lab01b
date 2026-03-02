package it.unibo.pps.e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SilverBankAccountTest {
    protected final int DEFAULT_DEPOSIT_AMOUNT = 1000;
    protected BankAccount account;

    @BeforeEach
    void init() {
        this.account = new SilverBankAccount(new CoreBankAccount());
    }

    @Test
    public void testInitiallyEmpty() {
        final int initialBalanceAmount = 0;
        assertEquals(initialBalanceAmount, this.account.getBalance());
    }

    @Test
    public void testCanDeposit() {
        this.account.deposit(DEFAULT_DEPOSIT_AMOUNT);
        assertEquals(DEFAULT_DEPOSIT_AMOUNT, this.account.getBalance());
    }

    @Test
    public void testCanWithdrawExactAmount() {
        final int expectedBalanceAfterWithdraw = 0;
        this.account.deposit(DEFAULT_DEPOSIT_AMOUNT);
        final int exactAmountToWithdraw = DEFAULT_DEPOSIT_AMOUNT - this.account.getWithdrawFee();
        this.account.withdraw(exactAmountToWithdraw);
        assertEquals(expectedBalanceAfterWithdraw, this.account.getBalance());
    }

    @Test
    public void testCanWithdraw() {
        final int amountToWithdraw = 200;
        this.account.deposit(DEFAULT_DEPOSIT_AMOUNT);
        final int expectedBalanceAfterWithdraw = DEFAULT_DEPOSIT_AMOUNT - amountToWithdraw - this.account.getWithdrawFee();
        this.account.withdraw(amountToWithdraw);
        assertEquals(expectedBalanceAfterWithdraw, this.account.getBalance());
    }

    @Test
    public void testCannotWithdrawMoreThanAvailable() {
        this.account.deposit(DEFAULT_DEPOSIT_AMOUNT);
        assertThrows(IllegalStateException.class, () -> this.account.withdraw(1200));
    }
}
