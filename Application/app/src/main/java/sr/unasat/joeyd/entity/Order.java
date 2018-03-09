package sr.unasat.joeyd.entity;

/**
 * Created by mpawirodinomo on 3/8/2018.
 */

public class Order {
    private long id;
    private OrderItem orderItem;
    private Receipt receipt;

    public Order(long id, OrderItem orderItem, Receipt receipt) {
        this.id = id;
        this.orderItem = orderItem;
        this.receipt = receipt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
