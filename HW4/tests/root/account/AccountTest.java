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
    public void depositBlockedAccountTest() {
        Account account = getBlockedAccount();
        assertTrue(account.isBlocked());

        // очень мало значений, проверка на то, что withdraw возвращает false для заблокироваанного
        for (int i = 1; i <= 1_000_000; i *= 10) {
            assertFalse(account.deposit(i));
            assertFalse(account.deposit(-i));
        }

        assertEquals(1000, account.getBalance());
    }

    @Test
    public void depositNotInBoundsTest() {
        Account account = getInstanceOfAccount();
        assertTrue(account.deposit(1000));
        assertFalse(account.isBlocked());

        for (int i = 1; i <= 100_000_000; i *= 10) {
            assertFalse(account.deposit(-i));
        }
        for (int i = 1_000_000 + 1; i <= 1_000_000 + 100; ++i) {
            assertFalse(account.deposit(i));
        }
    }

    @Test
    public void depositInBoundsNonBlockedAccountTest() {
        Account account = getInstanceOfAccount();
        assertFalse(account.isBlocked());

        assertEquals(0, account.getBalance());
        assertTrue(account.deposit(1221));
        assertEquals(1221, account.getBalance());
    }

    @Test
    public void withdrawBlockedAccountTest() {
        Account account = getBlockedAccount();
        assertTrue(account.isBlocked());

        // очень мало значений, проверка на то, что withdraw возвращает false для заблокироваанного
        for (int i = 1; i <= 1_000_000; i *= 10) {
            assertFalse(account.withdraw(i));
            assertFalse(account.withdraw(-i));
        }

        assertEquals(1000, account.getBalance());
    }

    @Test
    public void withdrawNotInBoundsTest() {
        Account account = getInstanceOfAccount();
        assertTrue(account.deposit(1000));
        assertFalse(account.isBlocked());

        for (int i = 1; i <= 100_000_000; i *= 10) {
            assertFalse(account.withdraw(-i));
        }
        for (int i = 1_000_000 + 1; i <= 1_000_000 + 100; ++i) {
            assertFalse(account.withdraw(i));
        }
    }

    @Test
    public void withdrawFalseInBoundsNonBlockedAccountTest() {
        Account account = getInstanceOfAccount();
        assertFalse(account.isBlocked());

        assertEquals(0, account.getBalance());
        assertTrue(account.deposit(1000));
        assertEquals(1000, account.getBalance());
        assertFalse(account.withdraw(1));
        assertEquals(1000, account.getBalance());
    }

    @Test
    public void withdrawTrueInBoundsNonBlockedAccountTest() {
        Account account = getInstanceOfAccount();
        assertFalse(account.isBlocked());

        assertEquals(0, account.getBalance());
        assertTrue(account.deposit(1221));
        assertEquals(1221, account.getBalance());
        assertTrue(account.withdraw(221));
        assertEquals(1000, account.getBalance());
    }

    @Test
    public void getBalanceTest() {
        Account account = getInstanceOfAccount();
        assertFalse(account.isBlocked());

        int sum = 0;
        for (int i = 0; i < 100; ++i) {
            assertEquals(sum, account.getBalance());
            assertTrue(account.deposit(i));
            sum += i;
        }
    }

    @Test
    public void getMaxCreditTest() {
        Account account = getInstanceOfAccount();
        assertFalse(account.isBlocked());

        for (int i = 0; i < 100; ++i) {
            assertEquals(-1000, account.getMaxCredit());
            assertTrue(account.deposit(i));
        }
    }

    @Test
    public void blockAndIsBlockedTest() {
        Account account = getInstanceOfAccount();
        assertFalse(account.isBlocked());
        account.block();
        assertTrue(account.isBlocked());
    }

    @Test
    public void unblockBalanceLessNegativeMaxCreditTest() {
        Account account = getBlockedAccount();
        assertTrue(account.isBlocked());

        for (int i = 1001; i <= 1011; ++i) {
            assertTrue(account.setMaxCredit(-i));
            assertFalse(account.unblock());
        }
    }

    @Test
    public void unblockBalanceMoreEqNegativeMaxCreditTest() {
        Account account = getBlockedAccount();
        assertTrue(account.isBlocked());

        for (int i = 1001; i <= 1011; ++i) {
            assertTrue(account.setMaxCredit(i));
            assertTrue(account.unblock());
            account.block();
            assertTrue(account.isBlocked());
        }
    }

    @Test
    public void setMaxCreditNonBlockedAccountTest() {
        Account account = getInstanceOfAccount();
        assertFalse(account.isBlocked());

        for (int i = 1; i < 100_000_000; i *= 10) {
            assertFalse(account.setMaxCredit(i));
        }
    }

    @Test
    public void setMaxCreditNotInBoundsBlockedAccountTest() {
        Account account = getBlockedAccount();
        assertTrue(account.isBlocked());

        for (int i = 1_000_000 + 1; i <= 1_000_000 + 10; ++i) {
            assertFalse(account.setMaxCredit(i));
            assertFalse(account.setMaxCredit(-i));
        }
    }

    @Test
    public void setMaxCreditInBoundsBlockedAccountTest() {
        Account account = getBlockedAccount();
        assertTrue(account.isBlocked());

        for (int i = 1; i <= 1_000_000; i *= 10) {
            assertTrue(account.setMaxCredit(i));
            assertTrue(account.setMaxCredit(-i));
        }
    }

    private Account getBlockedAccount() {
        Account account = getInstanceOfAccount();

        account.deposit(1000);
        account.block();

        return account;
    }
}
