package hust.soict.itep.aims.entity.order;

import hust.soict.itep.aims.entity.db.AIMSDB;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    @Test
    public void testGetAmount() {
        Order order = new Order();
        AIMSDB db = new AIMSDB();

        // Assuming OrderMedia constructor takes Media, quantity, and price
        OrderMedia media1 = new OrderMedia(db.media.get(0), 1, db.media.get(0).getPrice());
        OrderMedia media2 = new OrderMedia(db.media.get(1), 1, db.media.get(1).getPrice());

        order.addOrderMedia(media1);
        order.addOrderMedia(media2);

        int expectedAmount = (int) ((media1.getPrice() + media2.getPrice()) + (10.0/100)*(media1.getPrice() + media2.getPrice()));
        assertEquals(expectedAmount, order.getAmount(), "Total amount should be calculated correctly");
    }
}