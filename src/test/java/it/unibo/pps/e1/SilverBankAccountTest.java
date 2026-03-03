package it.unibo.pps.e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SilverBankAccountTest extends CoreBankAccountTest {
    protected final int DEFAULT_DEPOSIT_AMOUNT = 1000;
    protected BankAccount account;

    @BeforeEach
    void init() {
        super.init();
        this.account = new SilverBankAccount(new CoreBankAccount());
    }

    @Test
    public void testCannotWithdrawMoreThanAvailable() {
        this.account.deposit(DEFAULT_DEPOSIT_AMOUNT);
        assertThrows(IllegalStateException.class, () -> this.account.withdraw(1200));
    }
}
