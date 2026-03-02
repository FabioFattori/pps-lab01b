package it.unibo.pps.e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GoldBankAccountTest extends SilverBankAccountTest {
    @Override
    @BeforeEach
    public void init() {
        super.init();
        account = new GoldBankAccount(new CoreBankAccount());
    }

    @Test
    public void testAccountCanOverdraft() {
        this.account.withdraw(this.account.getAccountOverdraft());
    }

    @Test
    public void testCannotWithdrawMoreThanAvailable() {
        final int amountThatCannotBeWithdrawn = 1700;
        this.account.deposit(DEFAULT_DEPOSIT_AMOUNT);
        assertThrows(IllegalStateException.class, () -> this.account.withdraw(amountThatCannotBeWithdrawn));
    }
}
