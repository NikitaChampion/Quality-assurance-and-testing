package root.account;

public class CorrectAccount implements Account {
    private final int MAX_BOUND = 1_000_000;

    private boolean blocked = BLOCKED_CONST;
    private int balance = BALANCE_CONST;
    private int maxCredit = MAX_CREDIT_CONST;

    public int getBalance() {
        return balance;
    }

    public int getMaxCredit() {
        return maxCredit;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void block() {
        blocked = true;
    }

    public boolean unblock() {
        if (balance < maxCredit) {
            return false;
        }

        blocked = false;
        return true;
    }

    public boolean setMaxCredit(int maxCredit) {
        if (!isBlocked()) {
            return false;
        }

        if (maxCredit < -MAX_BOUND || maxCredit > MAX_BOUND) {
            return false;
        }

        this.maxCredit = maxCredit;
        return true;
    }

    public boolean deposit(int sum) {
        if (blocked) {
            return false;
        } else if (sum < 0 || sum > MAX_BOUND) {
            return false;
        } else {
            balance += sum;
            return true;
        }
    }

    public boolean withdraw(int sum) {
        if (isBlocked()) {
            return false;
        } else if (sum < 0 || sum > MAX_BOUND) {
            return false;
        } else if (balance <= maxCredit + sum) {
            return false;
        } else {
            balance -= sum;
            return true;
        }
    }

}
