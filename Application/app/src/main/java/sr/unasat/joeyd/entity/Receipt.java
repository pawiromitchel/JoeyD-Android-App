package sr.unasat.joeyd.entity;

/**
 * Created by mpawirodinomo on 3/8/2018.
 */

public class Receipt {
    private long id;
    private String totalPrice;
    private String status;

    public Receipt(long id, String totalPrice, String status) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
