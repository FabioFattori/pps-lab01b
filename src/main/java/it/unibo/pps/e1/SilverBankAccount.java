package it.unibo.pps.e1;

public class SilverBankAccount implements BankAccount {

    private final BankAccount base;

    public SilverBankAccount(BankAccount baseAccount) {
        base = baseAccount;
    }

    public int getBalance() {
        return base.getBalance();
    }

    public void deposit(int amount) {
        base.deposit(amount);
    }

    public void withdraw(int amount) {
        if (cannotWithdraw(amount)) {
            throw new IllegalStateException();
        }

        base.withdraw(amount + getWithdrawFee());
    }

    @Override
    public int getWithdrawFee() {
        return 1;
    }

    @Override
    public boolean cannotWithdraw(int amount) {
        return this.getBalance() <= amount;
    }

    @Override
    public int getAccountOverdraft() {
        return 0;
    }
}
