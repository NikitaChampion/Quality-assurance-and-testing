package root.vending;

import org.junit.Test;

import static org.junit.Assert.*;

public class VendingMachineTest {
    VendingMachine machine;
    static final long id = 117345294655382L;

    public VendingMachine getNewMachine() {
        return new VendingMachine();
    }

    public void setCoinval1Default() {
        VendingMachine.coinval1 = 1;
    }

    public void setCoinval2Default() {
        VendingMachine.coinval2 = 2;
    }

    @Test
    public void getCurrentSumTest() {
        machine = getNewMachine();

        machine.enterAdminMode(id);
        machine.fillCoins1(25);
        machine.fillCoins2(30);

        assertEquals(machine.getCurrentSum(), 85);

        machine.exitAdminMode();
        assertEquals(machine.getCurrentSum(), 0);
    }

    @Test
    public void getCoinsTest1() {
        machine = getNewMachine();

        machine.enterAdminMode(id);
        machine.fillCoins1(25);
        machine.fillCoins2(30);

        assertEquals(machine.getCoins1(), 25);

        machine.exitAdminMode();
        assertEquals(machine.getCoins1(), 0);
    }

    @Test
    public void getCoinsTest2() {
        machine = getNewMachine();

        machine.enterAdminMode(id);
        machine.fillCoins1(25);
        machine.fillCoins2(30);

        assertEquals(machine.getCoins2(), 30);

        machine.exitAdminMode();
        assertEquals(machine.getCoins2(), 0);
    }

    @Test
    public void getPriceTest1() {
        machine = getNewMachine();

        machine.enterAdminMode(id);
        assertEquals(machine.getPrice1(), 8);

        machine.setPrice1(450);
        assertEquals(machine.getPrice1(), 450);
        machine.setPrice2(500);
        assertEquals(machine.getPrice1(), 450);

        machine.exitAdminMode();
        assertEquals(machine.getPrice1(), 450);
    }

    @Test
    public void getPriceTest2() {
        machine = getNewMachine();

        machine.enterAdminMode(id);
        assertEquals(machine.getPrice2(), 5);

        machine.setPrice2(500);
        assertEquals(machine.getPrice2(), 500);
        machine.setPrice1(450);
        assertEquals(machine.getPrice2(), 500);

        machine.exitAdminMode();
        assertEquals(machine.getPrice2(), 500);
    }

    @Test
    public void fillCoinsTest1() {
        machine = getNewMachine();

        assertEquals(machine.fillCoins1(40), VendingMachine.Response.ILLEGAL_OPERATION);

        machine.enterAdminMode(id);

        assertEquals(machine.fillCoins1(40), VendingMachine.Response.OK);
        assertEquals(machine.getCoins1(), 40);

        assertEquals(machine.fillCoins1(5), VendingMachine.Response.OK);
        assertEquals(machine.getCoins1(), 5);

        assertEquals(machine.fillCoins1(0), VendingMachine.Response.INVALID_PARAM);
        assertEquals(machine.getCoins1(), 5);

        assertEquals(machine.fillCoins1(51), VendingMachine.Response.INVALID_PARAM);
        assertEquals(machine.fillCoins1(-1), VendingMachine.Response.INVALID_PARAM);
    }

    @Test
    public void fillCoinsTest2() {
        machine = getNewMachine();

        assertEquals(machine.fillCoins2(40), VendingMachine.Response.ILLEGAL_OPERATION);

        machine.enterAdminMode(id);

        assertEquals(machine.fillCoins2(40), VendingMachine.Response.OK);
        assertEquals(machine.getCoins2(), 40);

        assertEquals(machine.fillCoins2(5), VendingMachine.Response.OK);
        assertEquals(machine.getCoins2(), 5);

        assertEquals(machine.fillCoins2(0), VendingMachine.Response.INVALID_PARAM);
        assertEquals(machine.getCoins2(), 5);

        assertEquals(machine.fillCoins2(51), VendingMachine.Response.INVALID_PARAM);
        assertEquals(machine.fillCoins2(-1), VendingMachine.Response.INVALID_PARAM);
    }

    @Test
    public void putCoinTest1() {
        machine = getNewMachine();

        assertEquals(machine.putCoin1(), VendingMachine.Response.OK);
        assertEquals(machine.getCurrentBalance(), 1);

        machine.returnMoney();

        machine.enterAdminMode(id);

        assertEquals(machine.putCoin1(), VendingMachine.Response.ILLEGAL_OPERATION);
        machine.fillCoins1(50);
        machine.exitAdminMode();

        assertEquals(machine.putCoin1(), VendingMachine.Response.CANNOT_PERFORM);
    }

    @Test
    public void putCoinTest2() {
        machine = getNewMachine();

        assertEquals(machine.putCoin2(), VendingMachine.Response.OK);
        assertEquals(machine.getCurrentBalance(), 2);

        machine.returnMoney();

        machine.enterAdminMode(id);

        assertEquals(machine.putCoin2(), VendingMachine.Response.ILLEGAL_OPERATION);
        machine.fillCoins2(50);
        machine.exitAdminMode();

        assertEquals(machine.putCoin2(), VendingMachine.Response.CANNOT_PERFORM);
    }

    @Test
    public void fillProductTest1() {
        machine = getNewMachine();

        assertEquals(machine.giveProduct1(10), VendingMachine.Response.INSUFFICIENT_PRODUCT);
        assertEquals(machine.fillProduct1(), VendingMachine.Response.ILLEGAL_OPERATION);

        machine.enterAdminMode(id);
        assertEquals(machine.fillProduct1(), VendingMachine.Response.OK);
        assertEquals(machine.getNumberOfProduct1(), 30);
        machine.exitAdminMode();

        assertEquals(machine.giveProduct1(30), VendingMachine.Response.INSUFFICIENT_MONEY);
    }

    @Test
    public void fillProductTest2() {
        machine = getNewMachine();

        assertEquals(machine.giveProduct2(10), VendingMachine.Response.INSUFFICIENT_PRODUCT);
        assertEquals(machine.fillProduct2(), VendingMachine.Response.ILLEGAL_OPERATION);

        machine.enterAdminMode(id);
        assertEquals(machine.fillProduct2(), VendingMachine.Response.OK);
        assertEquals(machine.getNumberOfProduct2(), 40);
        machine.exitAdminMode();

        assertEquals(machine.giveProduct2(40), VendingMachine.Response.INSUFFICIENT_MONEY);
    }


    @Test
    public void setPriceTest1() {
        machine = getNewMachine();

        assertEquals(machine.setPrice1(10), VendingMachine.Response.ILLEGAL_OPERATION);

        machine.enterAdminMode(id);

        assertEquals(machine.setPrice1(0), VendingMachine.Response.INVALID_PARAM);

        assertEquals(machine.setPrice1(10), VendingMachine.Response.OK);
        assertEquals(machine.getPrice1(), 10);

        assertEquals(machine.setPrice1(8), VendingMachine.Response.OK);
        assertEquals(machine.getPrice1(), 8);
    }

    @Test
    public void setPriceTest2() {
        machine = getNewMachine();

        assertEquals(machine.setPrice2(10), VendingMachine.Response.ILLEGAL_OPERATION);

        machine.enterAdminMode(id);

        assertEquals(machine.setPrice2(0), VendingMachine.Response.INVALID_PARAM);

        assertEquals(machine.setPrice2(10), VendingMachine.Response.OK);
        assertEquals(machine.getPrice2(), 10);

        assertEquals(machine.setPrice2(5), VendingMachine.Response.OK);
        assertEquals(machine.getPrice2(), 5);
    }

    @Test
    public void modeTest1() {
        machine = getNewMachine();

        assertEquals(machine.getCurrentMode(), VendingMachine.Mode.OPERATION);
        assertEquals(machine.enterAdminMode(999L), VendingMachine.Response.INVALID_PARAM);

        machine.putCoin1();
        assertEquals(machine.enterAdminMode(id), VendingMachine.Response.CANNOT_PERFORM);
    }

    @Test
    public void modeTest2() {
        machine = getNewMachine();

        assertEquals(machine.getCurrentMode(), VendingMachine.Mode.OPERATION);
        assertEquals(machine.enterAdminMode(id), VendingMachine.Response.OK);
        assertEquals(machine.getCurrentMode(), VendingMachine.Mode.ADMINISTERING);
    }

    @Test
    public void giveProductTest1_1() {
        machine = getNewMachine();

        machine.enterAdminMode(id);
        machine.fillProduct1();
        assertEquals(machine.giveProduct1(1), VendingMachine.Response.ILLEGAL_OPERATION);
        machine.exitAdminMode();

        assertEquals(machine.giveProduct1(0), VendingMachine.Response.INVALID_PARAM);

        for (int i = 0; i < 4; ++i)
            machine.putCoin2();

        assertEquals(machine.giveProduct1(1), VendingMachine.Response.OK);
        assertEquals(machine.getNumberOfProduct1(), 29);
    }

    @Test
    public void giveProductTest1_2() {
        machine = getNewMachine();

        VendingMachine.coinval1 = 5;

        machine.enterAdminMode(id);
        machine.fillProduct1();
        machine.exitAdminMode();

        for (int i = 0; i < 2; i++)
            machine.putCoin1();

        assertEquals(machine.giveProduct1(1), VendingMachine.Response.OK);

        setCoinval1Default();
    }

    @Test
    public void giveProductTest1_3() {
        machine = getNewMachine();

        machine.enterAdminMode(id);
        machine.fillProduct1();
        machine.fillCoins2(3);
        machine.exitAdminMode();

        for (int i = 0; i < 13; ++i)
            machine.putCoin1();

        assertEquals(machine.giveProduct1(1), VendingMachine.Response.OK);
        assertEquals(machine.getNumberOfProduct1(), 29);

        machine.enterAdminMode(id);
        assertEquals(machine.getCoins1(), 12);
        assertEquals(machine.getCoins2(), 1);
        machine.exitAdminMode();
    }

    @Test
    public void giveProductTest1_4() {
        machine = getNewMachine();

        VendingMachine.coinval2 = 5;

        machine.enterAdminMode(id);
        machine.fillProduct1();
        machine.exitAdminMode();

        for (int i = 0; i < 2; i++)
            machine.putCoin2();

        assertEquals(machine.giveProduct1(1), VendingMachine.Response.UNSUITABLE_CHANGE);

        setCoinval2Default();
    }

    @Test
    public void giveProductTest1_5() {
        machine = getNewMachine();

        machine.enterAdminMode(id);
        machine.fillProduct1();
        machine.fillCoins2(1);
        machine.exitAdminMode();

        for (int i = 0; i < 12; ++i)
            machine.putCoin1();

        assertEquals(machine.giveProduct1(1), VendingMachine.Response.OK);
        assertEquals(machine.getNumberOfProduct1(), 29);

        machine.enterAdminMode(id);
        assertEquals(machine.getCoins1(), 10);
        machine.exitAdminMode();
    }

    @Test
    public void giveProductTest2_1() {
        machine = getNewMachine();

        assertEquals(machine.giveProduct2(40), VendingMachine.Response.INSUFFICIENT_PRODUCT);
    }

    @Test
    public void giveProductTest2_2() {
        machine = getNewMachine();

        machine.enterAdminMode(id);
        machine.fillProduct2();
        assertEquals(machine.giveProduct2(1), VendingMachine.Response.ILLEGAL_OPERATION);
        machine.exitAdminMode();

        assertEquals(machine.giveProduct2(0), VendingMachine.Response.INVALID_PARAM);

        for (int i = 0; i < 5; ++i)
            machine.putCoin1();

        assertEquals(machine.giveProduct2(1), VendingMachine.Response.OK);
        assertEquals(machine.getNumberOfProduct2(), 39);
    }

    @Test
    public void giveProductTest2_3() {
        machine = getNewMachine();

        VendingMachine.coinval1 = 5;

        machine.enterAdminMode(id);
        machine.fillProduct2();
        machine.exitAdminMode();

        for (int i = 0; i < 2; i++)
            machine.putCoin1();

        assertEquals(machine.giveProduct2(1), VendingMachine.Response.OK);

        setCoinval1Default();
    }


    @Test
    public void giveProductTest2_4() {
        machine = getNewMachine();

        machine.enterAdminMode(id);
        machine.setPrice2(8);
        machine.fillProduct2();
        machine.fillCoins2(4);
        machine.exitAdminMode();

        for (int i = 0; i < 13; ++i)
            machine.putCoin1();

        assertEquals(machine.giveProduct2(1), VendingMachine.Response.OK);
        assertEquals(machine.getNumberOfProduct2(), 39);

        machine.enterAdminMode(id);
        assertEquals(machine.getCoins1(), 12);
        assertEquals(machine.getCoins2(), 2);
        machine.exitAdminMode();
    }

    @Test
    public void giveProductTest2_5() {
        machine = getNewMachine();

        VendingMachine.coinval2 = 5;

        machine.enterAdminMode(id);
        machine.fillProduct2();
        machine.setPrice2(7);
        machine.exitAdminMode();

        for (int i = 0; i < 2; i++)
            machine.putCoin2();

        assertEquals(machine.giveProduct2(1), VendingMachine.Response.UNSUITABLE_CHANGE);

        setCoinval2Default();
    }

    @Test
    public void giveProductTest2_6() {
        machine = getNewMachine();

        machine.enterAdminMode(id);
        machine.setPrice2(7);
        machine.fillProduct2();
        machine.fillCoins2(2);
        machine.exitAdminMode();

        for (int i = 0; i < 13; ++i)
            machine.putCoin1();

        assertEquals(machine.giveProduct2(1), VendingMachine.Response.OK);
        assertEquals(machine.getNumberOfProduct2(), 39);

        machine.enterAdminMode(id);
        assertEquals(machine.getCoins1(), 11);
        machine.exitAdminMode();
    }

    @Test
    public void returnMoneyTest1() {
        machine = getNewMachine();

        machine.enterAdminMode(id);
        assertEquals(machine.returnMoney(), VendingMachine.Response.ILLEGAL_OPERATION);

        machine.exitAdminMode();
        assertEquals(machine.returnMoney(), VendingMachine.Response.OK);
    }

    @Test
    public void returnMoneyTest2() {
        machine = getNewMachine();

        machine.enterAdminMode(id);
        machine.fillCoins2(3);
        machine.exitAdminMode();

        for (int i = 0; i < 7; ++i)
            machine.putCoin1();

        assertEquals(machine.returnMoney(), VendingMachine.Response.OK);

        machine.enterAdminMode(id);
        assertEquals(machine.getCoins1(), 6);
        assertEquals(machine.getCoins2(), 0);
        machine.exitAdminMode();
    }

    @Test
    public void returnMoneyTest3() {
        machine = getNewMachine();

        VendingMachine.coinval1 = 5;

        machine.enterAdminMode(id);
        machine.fillCoins2(6);
        machine.exitAdminMode();

        for (int i = 0; i < 7; ++i)
            machine.putCoin1();

        assertEquals(machine.returnMoney(), VendingMachine.Response.OK);

        setCoinval1Default();
    }

    @Test
    public void returnMoneyTest4() {
        machine = getNewMachine();

        machine.enterAdminMode(id);
        machine.fillCoins2(6);
        machine.exitAdminMode();

        for (int i = 0; i < 7; ++i)
            machine.putCoin1();

        assertEquals(machine.returnMoney(), VendingMachine.Response.OK);

        machine.enterAdminMode(id);
        assertEquals(machine.getCoins1(), 6);
        assertEquals(machine.getCoins2(), 3);
        machine.exitAdminMode();
    }

    @Test
    public void returnMoneyTest5() {
        machine = getNewMachine();

        machine.enterAdminMode(id);
        machine.fillCoins2(7);
        machine.exitAdminMode();
        machine.putCoin2();

        for (int i = 0; i < 8; ++i)
            machine.putCoin1();

        assertEquals(machine.returnMoney(), VendingMachine.Response.OK);

        machine.enterAdminMode(id);
        assertEquals(machine.getCoins1(), 8);
        assertEquals(machine.getCoins2(), 3);
        machine.exitAdminMode();
    }
}
