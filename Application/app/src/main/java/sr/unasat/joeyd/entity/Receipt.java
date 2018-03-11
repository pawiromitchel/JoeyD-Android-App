package sr.unasat.joeyd.entity;

/**
 * Created by mpawirodinomo on 3/8/2018.
 */

public class Receipt {
    private long id;
    private int receiptNumber;
    private User user;
    private String totalPrice;
    private String status;

    public Receipt(long id, int receiptNumber, User user, String totalPrice, String status) {
        this.id = id;
        this.receiptNumber = receiptNumber;
        this.user = user;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(int receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Receiptnumber: " + receiptNumber + "\n" + "Status: " + getStatus();
    }
}
