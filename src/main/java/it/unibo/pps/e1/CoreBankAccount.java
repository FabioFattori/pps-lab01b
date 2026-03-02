package it.unibo.pps.e1;

class CoreBankAccount implements BankAccount {

    private int balance = 0;

    public int getBalance() {
        return this.balance;
    }

    public void deposit(int amount) {
        this.balance = this.balance + amount;
    }

    public void withdraw(int amount) {
        this.balance = this.balance - amount;
    }

    public int getWithdrawFee() {
        return 0;
    }

    @Override
    public boolean cannotWithdraw(int amount) {
        return getBalance() <= amount;
    }

    @Override
    public int getAccountOverdraft() {
        return 0;
    }
}
