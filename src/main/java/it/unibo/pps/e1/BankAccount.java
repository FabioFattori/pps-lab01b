package it.unibo.pps.e1;

public interface BankAccount {
    int getBalance();

    void deposit(int amount);

    void withdraw(int amount);

    int getWithdrawFee();

    boolean cannotWithdraw(int amount);

    int getAccountOverdraft();
}
