package sr.unasat.joeyd.entity;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Sniffle on 3/5/2018.
 */

public class Dish implements Serializable{

    private long id;
    private String dish_name;
    private String dish_price;
    //private Integer dish_img_id;
    private Drawable dish_img;
    private int dish_img_id;

    public Dish(long id, String dish_name, String dish_price, int dish_img) {
        this.id = id;
        this.dish_name = dish_name;
        this.dish_price = dish_price;
//        this.special = special;
        this.dish_img_id = dish_img;
    }

    public int getDish_img_id() {
        return this.dish_img_id;
    }

    public Drawable getDish_img() {
        return dish_img;
    }

    public void setDish_img(Drawable dish_img) {
        this.dish_img = dish_img;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public String getDish_price() {
        return dish_price;
    }

    public void setDish_price(String dish_price) {
        this.dish_price = dish_price;
    }

//    public Integer getSpecial() {
//        return special;
//    }
//
//    public void setSpecial(Integer special) {
//        this.special = special;
//    }

//    public Integer getDish_imag_id() {
//        return dish_img_id;
//    }
//
//    public void setDish_imag_id(Integer dish_imag_id) {
//        this.dish_img_id = dish_imag_id;
//    }

}