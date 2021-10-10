package root.account;

public interface Account {
    int BALANCE_CONST = 0;
    int MAX_CREDIT_CONST = 1000;
    boolean BLOCKED_CONST = false;

    boolean deposit(int sum);

    boolean withdraw(int sum);

    int getBalance();

    int getMaxCredit();

    boolean isBlocked();

    void block();

    boolean unblock();

    boolean setMaxCredit(int mc);
}
