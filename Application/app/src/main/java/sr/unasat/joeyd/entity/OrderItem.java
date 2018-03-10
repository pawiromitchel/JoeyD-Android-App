package sr.unasat.joeyd.entity;

/**
 * Created by mpawirodinomo on 3/8/2018.
 */

public class OrderItem {
    private long id;
    private Dish dish;
    private int quantity;
    private User user;
    private String datetime;

    public OrderItem(long id, Dish dish, int quantity, User user, String datetime, String portion_size) {
        this.id = id;
        this.dish = dish;
        this.quantity = quantity;
        this.user = user;
        this.datetime = datetime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
