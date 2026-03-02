package it.unibo.pps.e1;

public class GoldBankAccount implements BankAccount {
    private final BankAccount baseAccount;

    public GoldBankAccount(BankAccount baseAccount) {
        this.baseAccount = baseAccount;
    }

    @Override
    public int getBalance() {
        return baseAccount.getBalance();
    }

    @Override
    public void deposit(int amount) {
        this.baseAccount.deposit(amount);
    }

    @Override
    public void withdraw(int amount) {
        if (cannotWithdraw(amount)) {
            throw new IllegalStateException();
        }

        baseAccount.withdraw(amount);
    }

    @Override
    public int getWithdrawFee() {
        return 0;
    }

    @Override
    public boolean cannotWithdraw(int amount) {
        return getBalance() + getAccountOverdraft() < amount;
    }

    @Override
    public int getAccountOverdraft() {
        return 500;
    }
}
