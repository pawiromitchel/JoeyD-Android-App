package sr.unasat.joeyd.entity;

/**
 * Created by Sniffle on 3/5/2018.
 */

public class Dish {

    private long id;
    private String dish_name;
    private Integer dish_price;
    private Integer special;
    private Integer dish_img_id;

    public Dish(long id, String dish_name, Integer dish_price, Integer special, Integer dish_imag_id) {
        this.id = id;
        this.dish_name = dish_name;
        this.dish_price = dish_price;
        this.special = special;
        this.dish_img_id = dish_img_id;
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

    public Integer getDish_price() {
        return dish_price;
    }

    public void setDish_price(Integer dish_price) {
        this.dish_price = dish_price;
    }

    public Integer getSpecial() {
        return special;
    }

    public void setSpecial(Integer special) {
        this.special = special;
    }

    public Integer getDish_imag_id() {
        return dish_img_id;
    }

    public void setDish_imag_id(Integer dish_imag_id) {
        this.dish_img_id = dish_imag_id;
    }
}