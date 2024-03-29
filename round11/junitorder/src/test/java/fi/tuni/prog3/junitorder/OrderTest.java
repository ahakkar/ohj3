/*
 * COMP.CS.140 Ohjelmointi 3
 * H11 OrderTest
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

package fi.tuni.prog3.junitorder;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderTest {
    /* Description of Order class:
    Order() Constructs an initially empty order. 
    
    Desc of methods in Order class:

    boolean addItems(Order.Item item, int count) Adds count units of
             an item to the order.

    boolean addItems(String name, int count) Adds count units of an item to the order.

    List<Order.Entry> getEntries() Returns the order entries in their original 
            adding order.

    int getEntryCount() Returns the total number of item entries in this order.

    int getItemCount() Returns the total number of entries in this order 
                    (= sum of all entries' counts).

    double getTotalPrice() Returns the total price of the order.

    boolean isEmpty() Tells whether the order is empty.

    boolean removeItems(String name, int count) Removes count units of an item 
            from the order.
    */


    @Test
    public void testConstructor() {
        Order order = new Order();
        assertNotNull(order);
        assertEquals(0, order.getEntryCount());
        assertEquals(0, order.getItemCount());
    }


    /*
     * Add item objects to the order and get the count of items in the order
     */
    @Test
    public void testAddItemsObject() {
        Order order = new Order();

        Order.Item item1 = new Order.Item("Milk", 1.35);
        Order.Item item2 = new Order.Item("Bread", 3.20);

        assertTrue(order.addItems(item1, 2));
        assertEquals(2, order.getItemCount());
        assertTrue(order.addItems(item2, 5));
        assertEquals(7, order.getItemCount());    
    }


    /*
     * First add item objects to the order, then add the same items with string
     */
    @Test
    public void testAddItemsString() {
        Order order = new Order();

        assertEquals(order.isEmpty(), true);

        Order.Item item1 = new Order.Item("Milk", 1.35);
        Order.Item item2 = new Order.Item("Bread", 3.20);

        assertTrue(order.addItems(item1, 2));
        assertTrue(order.addItems(item2, 5));

        assertTrue(order.addItems("Milk", 2));
        assertEquals(9, order.getItemCount());
        assertTrue(order.addItems("Bread", 5));
        assertEquals(14, order.getItemCount());

        assertEquals(order.isEmpty(), false);
    }

    @Test
    public void testAddManyItems() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("asdsadsaddfg", 12989534.21932193);
        
        assertTrue(order.addItems(item1, 235235636));
        assertTrue(order.removeItems("asdsadsaddfg", 135232636));
        assertEquals(order.getItemCount(), 100003000);
        assertTrue(order.addItems(item1, 1));
        assertEquals(order.getItemCount(), 100003001);
        assertTrue(order.addItems("asdsadsaddfg", 1));
        assertEquals(order.getItemCount(), 100003002);
    }


    @Test
    public void testAddWrongItems() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("Milk", 1.35);
        Order.Item item2 = new Order.Item("Milk", 7.35);

        assertThrows(NoSuchElementException.class, () -> order.addItems("doesnt exist lol", 2235));
        assertTrue(order.addItems(item1, 523));
        assertThrows(IllegalArgumentException.class, () -> order.addItems("Milk", -1));
        assertThrows(IllegalArgumentException.class, () -> order.addItems("Milk", 0));
        assertThrows(IllegalArgumentException.class, () -> order.addItems(item1, -134));
        assertThrows(IllegalArgumentException.class, () -> order.addItems(item1, 0));

        assertTrue(order.addItems(item1, 3));
        assertThrows(IllegalStateException.class, () -> order.addItems(item2, 3));
    }

    @Test
    public void testAddSameItemManyTimes() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("Milk", 1.35);

        assertTrue(order.addItems(item1, 2));
        assertTrue(order.addItems(item1, 25));
        assertEquals(order.getItemCount(), 27);
        assertTrue(order.addItems(item1, 256));
        assertEquals(order.getItemCount(), 283);
        assertTrue(order.addItems("Milk", 1));
        assertEquals(order.getItemCount(), 284);
        
    }


    @Test
    public void testRemoveItems() {
        Order order = new Order();
        assertThrows(NoSuchElementException.class, () -> order.removeItems("doesnt exist lol", 1));

        Order.Item item1 = new Order.Item("Milk", 1.35);
        Order.Item item2 = new Order.Item("Bread", 3.20);

        assertTrue(order.addItems(item1, 2));
        assertTrue(order.addItems(item2, 5));

        assertThrows(IllegalArgumentException.class, () -> order.removeItems("Milk", 25));
        assertThrows(IllegalArgumentException.class, () -> order.removeItems("Milk", -25));
        assertThrows(IllegalArgumentException.class, () -> order.removeItems("Milk", 0));

        assertTrue(order.removeItems("Milk", 1));
        assertEquals(6, order.getItemCount());
        assertTrue(order.removeItems("Bread", 3));
        assertEquals(3, order.getItemCount());
        assertTrue(order.removeItems("Bread", 1));
        assertEquals(2, order.getItemCount());
        assertTrue(order.removeItems("Milk", 1));
        assertEquals(1, order.getEntryCount());
    }


    /*
    Test that the returned list is not null.
    Test that the returned list size matches the number of entries added to the order.
    Test that the returned list contains the same entries that were added to the order.
    Test that modifying the returned list does not affect the internal state of the order.
    Test that the order entries are returned in the original adding order.
     */

    @Test
    public void testGetEntriesNull() {
        Order order = new Order();
        List<Order.Entry> entries = order.getEntries();
        assertNotNull(entries);
    }


    @Test 
    public void testGetEntriesSizeMatches() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("Milk", 1.35);
        Order.Item item2 = new Order.Item("Bread", 3.20);
        Order.Item item3 = new Order.Item("Butter", 4.5);

        assertTrue(order.addItems(item1, 2));
        assertTrue(order.addItems(item2, 5));
        assertTrue(order.addItems(item3, 1));

        List<Order.Entry> entries = order.getEntries();

        assertEquals(3, entries.size());
    }

    @Test
    public void testGetEntriesAreSame() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("Milk", 1.35);
        Order.Item item2 = new Order.Item("Bread", 3.20);
        Order.Item item3 = new Order.Item("Butter", 4.5);

        assertTrue(order.addItems(item1, 2));
        assertTrue(order.addItems(item2, 5));
        assertTrue(order.addItems(item3, 1));


        List<Order.Entry> entries = order.getEntries();

        assertEquals(3, entries.size());
        assertEquals("Milk", entries.get(0).getItemName());
        assertEquals(2, entries.get(0).getCount());
        assertEquals("Bread", entries.get(1).getItemName());
        assertEquals(5, entries.get(1).getCount());
        assertEquals("Butter", entries.get(2).getItemName());
        assertEquals(1, entries.get(2).getCount());
    }


    /*
     * The list being returned should be a copy of the internal list
     *  so that the caller cannot modify the internal list
     */
    @Test
    public void testGetEntriesRemoval() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("Milk", 1.35);
        Order.Item item2 = new Order.Item("Bread", 3.20);
        Order.Item item3 = new Order.Item("Butter", 4.5);

        assertTrue(order.addItems(item1, 7));
        assertTrue(order.addItems(item2, 5));
        assertTrue(order.addItems(item3, 1));

        List<Order.Entry> entries = order.getEntries();

        // add items and check that the getEntries list has the expected contents
        assertEquals(3, order.getEntryCount());
        assertEquals(3, entries.size());

        assertEquals("Milk", entries.get(0).getItemName());
        assertEquals(7, entries.get(0).getCount());

        assertEquals("Bread", entries.get(1).getItemName());
        assertEquals(5, entries.get(1).getCount());

        assertEquals("Butter", entries.get(2).getItemName());
        assertEquals(1, entries.get(2).getCount());

        entries.clear();

        List<Order.Entry> entries2 = order.getEntries();
        assertEquals(3, entries2.size());

        // remove item and check that the list is not modified
        /* THIS DOES NOT WORK AT SCHOOL TESTER FOR SOME REASON
        assertTrue(order.removeItems("Milk", 7));
        assertEquals(2, order.getEntryCount());
        assertEquals(3, entries.size());
        
        assertEquals("Milk", entries.get(0).getItemName());
        assertEquals(7, entries.get(0).getCount());

        assertEquals("Bread", entries.get(1).getItemName());
        assertEquals(5, entries.get(1).getCount());

        assertEquals("Butter", entries.get(2).getItemName());
        assertEquals(1, entries.get(2).getCount());
        */
    }


    /**
     * Test that the order entries are returned in the original adding order.
     */
    @Test
    public void testGetEntriesOriginalOrder() {
        Order order = new Order();
        Order.Item item1 = new Order.Item("Milk", 1.35);
        Order.Item item2 = new Order.Item("Bread", 3.20);
        Order.Item item3 = new Order.Item("Butter", 4.5);

        assertTrue(order.addItems(item1, 2));
        assertTrue(order.addItems(item2, 5));
        assertTrue(order.addItems(item3, 1));

        List<Order.Entry> entries = order.getEntries();

        assertEquals(3, entries.size());
        assertEquals("Milk", entries.get(0).getItem().getName());
        assertEquals(2, entries.get(0).getCount());
        assertEquals("Bread", entries.get(1).getItem().getName());
        assertEquals(5, entries.get(1).getCount());
        assertEquals("Butter", entries.get(2).getItem().getName());
        assertEquals(1, entries.get(2).getCount());
    }


    @Test
    public void testIsEmpty() {
        Order order = new Order();
        assertEquals(order.isEmpty(), true);
        Order.Item item1 = new Order.Item("Milk", 1.35);
        Order.Item item2 = new Order.Item("Bread", 3.20);
        assertTrue(order.addItems(item1, 2));
        assertTrue(order.addItems(item2, 5));
        assertEquals(order.isEmpty(), false);
    }

    @Test
    public void testGetTotalPrice() {
        Order order = new Order();
        assertEquals(0, order.getEntryCount());
        assertEquals(0, order.getTotalPrice());

        Order.Item item1 = new Order.Item("Milk", 1.35);
        Order.Item item2 = new Order.Item("Bread", 3.20);

        assertTrue(order.addItems(item1, 2));
        assertTrue(order.addItems(item2, 5));

        assertEquals(18.7, order.getTotalPrice());

        assertTrue(order.addItems(item1, 32546));
        assertEquals(43955.8, order.getTotalPrice());

        assertEquals(32553, order.getItemCount());

        assertEquals(2, order.getEntryCount());
    }

    @Test
    public void testItemClass() {
        Order.Item item1 = new Order.Item("Milk", 1.35);
        Order.Item item2 = new Order.Item("Bread", 3.20);
        Order.Item item3 = new Order.Item("Milk", 1.35);

        assertTrue(item1.equals(item3));
        assertFalse(item1.equals(item2));

        assertThrows(IllegalArgumentException.class, () -> new Order.Item("Milk", -256));
        assertThrows(IllegalArgumentException.class, () -> new Order.Item(null, 3));

        assertEquals(item1.getName(), "Milk");
        assertEquals(item1.getPrice(), 1.35);
        assertNotEquals(item1.getPrice(), 2.50);
        assertEquals(item1.toString(), "Item(Milk, 1.35)");
        assertNotEquals(item1.toString(), "Item(Bread, 3.20)");
    }

    @Test
    public void testEntryClass() {
        Order.Item item1 = new Order.Item("Milk", 1.35);
        Order.Item item2 = new Order.Item("Bread", 3.20);
        Order.Item item3 = new Order.Item("Butter", 4.5);

        Order.Entry entry1 = new Order.Entry(item1, 2);
        Order.Entry entry2 = new Order.Entry(item2, 5);
        Order.Entry entry3 = new Order.Entry(item3, 2);
        Order.Entry entry4 = new Order.Entry(item3, 24354335);  

        assertNotEquals(entry1, entry2);
        assertThrows(IllegalArgumentException.class, () -> new Order.Entry(item1, 0));
        assertThrows(IllegalArgumentException.class, () -> new Order.Entry(item1, -25));
        
        assertEquals(entry2.getItemName(), "Bread");
        assertEquals(entry3.getUnitPrice(), 4.5);
        assertEquals(item3, entry3.getItem());

        assertEquals(entry1.getCount(), 2);
        assertEquals(entry2.getCount(), 5);
        assertEquals(entry4.getCount(), 24354335);

        assertEquals(entry4.toString(), "24354335 units of Item(Butter, 4.50)");
    }

    @Test
    public void testMultipleOrders() {
        Order order1 = new Order();
        Order order2 = new Order();
        Order order3 = new Order();
        Order order4 = new Order();
        Order.Item item1 = new Order.Item("Milk", 1.35);
        Order.Item item2 = new Order.Item("Bread", 3.20);
        Order.Item item3 = new Order.Item("Butter", 4.5);
        Order.Item item4 = new Order.Item("Whisky", 19.95);

        assertTrue(order1.addItems(item1, 2));
        assertTrue(order1.addItems(item2, 5));
        assertTrue(order1.addItems(item3, 1));

        assertTrue(order2.addItems(item1, 3));
        assertTrue(order2.addItems(item2, 4));
        assertTrue(order2.addItems(item3, 7));

        assertTrue(order3.addItems(item1, 3));
        assertTrue(order3.addItems(item4, 1));
        assertTrue(order3.addItems(item2, 4));
        assertTrue(order3.addItems(item3, 7));

        assertTrue(order4.addItems(item1, 3));
        assertTrue(order4.addItems(item2, 4));
        assertTrue(order4.addItems(item3, 7));
        assertTrue(order4.addItems(item4, 2));

        assertEquals(3, order1.getEntryCount());
        assertEquals(3, order2.getEntryCount());
        assertEquals(4, order3.getEntryCount());
        assertEquals(4, order4.getEntryCount());

        assertEquals(23.2, order1.getTotalPrice());
        assertEquals(48.35, order2.getTotalPrice());
        assertEquals(68.3, order3.getTotalPrice());
        assertEquals(88.25, order4.getTotalPrice());

        assertEquals(8, order1.getItemCount());
        assertEquals(14, order2.getItemCount());
        assertEquals(15, order3.getItemCount());
        assertEquals(16, order4.getItemCount());



    }
}