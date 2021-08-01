package core;

import model.HardwareOrder;
import model.Order;
import org.junit.Test;
import shared.Shop;

import static org.junit.Assert.*;

public class OnlineShopTest {

    @Test
    public void testContainsShouldReturnTrue() {
        Shop shop = new OnlineShop();
        for (int i = 0; i < 20; i++) {
            shop.add(new HardwareOrder(i, "hardware_order"));
        }
        Boolean isPresent = shop.contains(new HardwareOrder(13, "hardware_order"));
        assertNotNull(isPresent);
        assertTrue(isPresent);
    }

    @Test
    public void testIndexOfShouldReturnCorrectValue() {
        Shop shop = new OnlineShop();
        for (int i = 0; i < 20; i++) {
            shop.add(new HardwareOrder(i, "hardware_order"));
        }
        int index = shop.indexOf(new HardwareOrder(13, "hardware_order"));
        assertEquals(13, index);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetByIndexShouldThrow() {
        Shop shop = new OnlineShop();
        for (int i = 0; i < 20; i++) {
            shop.add(new HardwareOrder(i, "hardware_order"));
        }
        shop.get(20);
    }

    @Test
    public void testGetByIndex() {
        Shop shop = new OnlineShop();
        for (int i = 0; i < 20; i++) {
            shop.add(new HardwareOrder(i, "hardware_order"));
        }
        Order order = shop.get(10);
        assertNotNull(order);
        assertEquals(10, order.getId());
    }

    @Test
    public void testAddMultipleElements() {
        Shop shop = new OnlineShop();
        assertEquals(0, shop.size());

        for (int i = 0; i < 100; i++) {
            shop.add(new HardwareOrder(i, "hardware_order"));
        }

        assertEquals(100, shop.size());
    }

    @Test
    public void testAddSingleElement() {
        Shop shop = new OnlineShop();

        Order order = new HardwareOrder(13, "hardware_order");

        assertEquals(0, shop.size());
        shop.add(order);

        assertEquals(1, shop.size());
        assertTrue(shop.contains(order));
    }
}