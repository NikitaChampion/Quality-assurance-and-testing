package root.account;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    boolean isCorrect = true;

    private Account getInstanceOfAccount() {
        if (isCorrect) {
            return new CorrectAccount();
        }
        return new OldAccount();
    }

    @Test
    public void nonBlockedWithdrawTest() {
        Account account = getInstanceOfAccount();
        assertFalse(account.isBlocked());

        assertEquals(0, account.getBalance());
        assertTrue(account.deposit(1221));
        assertEquals(1221, account.getBalance());
        assertTrue(account.withdraw(220));
        assertEquals(1001, account.getBalance());

        assertFalse(account.withdraw(1));
        assertEquals(1001, account.getBalance());
    }

    @Test
    public void maxCreditTest() {
        Account account = getInstanceOfAccount();
        assertFalse(account.isBlocked());

        assertFalse(account.setMaxCredit(10000));
        assertFalse(account.setMaxCredit(-10000));

        account.block();
        assertTrue(account.isBlocked());
        assertFalse(account.setMaxCredit(Integer.MAX_VALUE));
        assertFalse(account.setMaxCredit(Integer.MIN_VALUE));

        assertEquals(account.getMaxCredit(), 1000);
    }

    @Test
    public void unblockBalanceMoreMaxCreditTest() {
        Account account = blockAccount();
        assertTrue(account.isBlocked());

        account.setMaxCredit(1001);
        assertFalse(account.unblock());
    }

    @Test
    public void unblockBalanceLessEqMaxCreditTest() {
        Account account1 = blockAccount();
        account1.setMaxCredit(999);
        assertTrue(account1.unblock());
        assertFalse(account1.isBlocked());

        Account account2 = blockAccount();
        account2.setMaxCredit(1000);
        assertTrue(account2.unblock());
        assertFalse(account2.isBlocked());
    }

    @Test
    public void depositAbsValueMoreBoundTest() {
        Account account1 = getInstanceOfAccount();
        int previousBalance = account1.getBalance();
        assertFalse(account1.deposit(Integer.MIN_VALUE));
        assertEquals(previousBalance, account1.getBalance());

        Account account2 = getInstanceOfAccount();
        previousBalance = account2.getBalance();
        assertFalse(account2.deposit(Integer.MAX_VALUE));
        assertEquals(previousBalance, account2.getBalance());
    }

    @Test
    public void withdrawAbsValueMoreBoundTest() {
        Account account1 = getInstanceOfAccount();
        int previousBalance = account1.getBalance();
        assertFalse(account1.withdraw(Integer.MIN_VALUE));
        assertEquals(previousBalance, account1.getBalance());

        Account account2 = getInstanceOfAccount();
        previousBalance = account2.getBalance();
        assertFalse(account2.withdraw(Integer.MAX_VALUE));
        assertEquals(previousBalance, account2.getBalance());
    }

    @Test
    public void depositBlockedAccountTest() {
        Account account = getInstanceOfAccount();
        int balance = account.getBalance();
        account.block();

        assertFalse(account.deposit(-1000));
        assertFalse(account.deposit(1000));

        assertEquals(balance, account.getBalance());
    }

    @Test
    public void withdrawBlockedAccountTest() {
        Account account = getInstanceOfAccount();
        int balance = account.getBalance();
        account.block();

        assertFalse(account.withdraw(-1000));
        assertFalse(account.withdraw(1000));

        assertEquals(balance, account.getBalance());
    }

    private Account blockAccount() {
        Account account = getInstanceOfAccount();

        account.deposit(1000);
        account.block();

        return account;
    }
}
