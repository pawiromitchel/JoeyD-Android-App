package sr.unasat.joeyd.entity;

import java.io.Serializable;

/**
 * Created by Sniffle on 3/5/2018.
 */

public class Dish implements Serializable{

    private long id;
    private String name;
    private String price;
    private int img_id;
    private String type;
    private String day;

    public Dish(long id, String name, String price, int img_id, String type, String day) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img_id = img_id;
        this.type = type;
        this.day = day;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}