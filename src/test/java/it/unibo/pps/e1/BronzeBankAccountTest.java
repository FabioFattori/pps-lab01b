package it.unibo.pps.e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BronzeBankAccountTest extends SilverBankAccountTest {

    @Override
    @BeforeEach
    public void init() {
        super.init();
        this.account = new BronzeBankAccount(new CoreBankAccount());
    }

    @ParameterizedTest
    @CsvSource({"50, 30, 20", "150, 50, 99"})
    public void testWithdrawConditionalFee(int amountToDeposit, int amountToWithdraw, int expectedRemainingAmount) {
        this.account.deposit(amountToDeposit);
        this.account.withdraw(amountToWithdraw);
        assertEquals(expectedRemainingAmount, this.account.getBalance());
    }

}
