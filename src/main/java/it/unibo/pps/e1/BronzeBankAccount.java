package it.unibo.pps.e1;

public class BronzeBankAccount implements BankAccount {

    private final BankAccount account;

    public BronzeBankAccount(BankAccount baseAccount) {
        account = baseAccount;
    }

    @Override
    public int getBalance() {
        return account.getBalance();
    }

    @Override
    public void deposit(int amount) {
        account.deposit(amount);
    }

    @Override
    public void withdraw(int amount) {
        if(cannotWithdraw(amount)){
            throw new IllegalStateException();
        }

        account.withdraw(amount + getWithdrawFee());
    }

    @Override
    public int getWithdrawFee() {
        if (getBalance() >= 100) {
            return 1;
        }

        return 0;
    }

    @Override
    public boolean cannotWithdraw(int amount) {
        return getBalance() < amount + getWithdrawFee();
    }

    @Override
    public int getAccountOverdraft() {
        return 0;
    }
}
