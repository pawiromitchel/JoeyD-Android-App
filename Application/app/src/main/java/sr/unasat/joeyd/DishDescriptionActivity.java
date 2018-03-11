package sr.unasat.joeyd;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sr.unasat.joeyd.database.JoeydDAO;
import sr.unasat.joeyd.entity.Dish;

public class DishDescriptionActivity extends AppCompatActivity {

    private Dish dish;
    private Button order_button;
    private TextView dish_desc;
    private ImageView dish_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_description);

        dish_desc = (TextView) findViewById(R.id.dish_desc_text);
        dish_img = (ImageView) findViewById(R.id.dish_desc_img);

        Intent intent = this.getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null){
            dish = (Dish)getIntent().getSerializableExtra("dishObject");//obtaining data from selected dish
        }

        dish_desc.setText(dish.getName());
        dish_img.setImageDrawable(this.getDrawable(dish.getImg_id()));
    }

    public void goToOrder(View view) {
        Intent intent = new Intent(DishDescriptionActivity.this, OrderActivity.class);
        intent.putExtra("dishObject", dish);
        startActivity(intent);
    }
}
