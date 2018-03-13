package sr.unasat.joeyd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import sr.unasat.joeyd.entity.Dish;

public class DishDescriptionActivity extends AppCompatActivity {

    //TODO: place description in activity with price and possibly order immediately

    private Dish dish;
    private Button order_button;
    private TextView dish_name;
    private TextView dish_desc;
    private ImageView dish_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_description);

        dish_name = (TextView) findViewById(R.id.dish_desc_name);
        dish_img = (ImageView) findViewById(R.id.dish_desc_img);
        dish_desc = (TextView) findViewById(R.id.dish_desc_text);

        Intent intent = this.getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null){
            dish = (Dish)getIntent().getSerializableExtra("dishObject");//obtaining data from selected dish
        }

        dish_name.setText(dish.getName());
        dish_img.setImageDrawable(this.getDrawable(dish.getImg_id()));
    }

    public void goToOrder(View view) {
        Intent intent = new Intent(DishDescriptionActivity.this, OrderActivity.class);
        intent.putExtra("dishObject", dish);
        startActivity(intent);
    }
}
