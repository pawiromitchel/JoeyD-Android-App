package sr.unasat.joeyd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import sr.unasat.joeyd.database.JoeydDAO;
import sr.unasat.joeyd.entity.Dish;

public class OrderActivity extends AppCompatActivity {

    private Dish dish;
    private TextView dishNameTextView;
    private Spinner quantitySpinner;

    private SQLiteDatabase db;
    private Cursor getUserPendingReceipt;
    private Cursor receiptCursor;

    // TODO: get User Object from IntentExtra
    private int UserID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        dishNameTextView = (TextView) findViewById(R.id.dishNameTextView);
        quantitySpinner = (Spinner) findViewById(R.id.quantitySpinner);

        dish = (Dish)getIntent().getSerializableExtra("dishObject");
        dishNameTextView.setText(dish.getName());
    }

    public void addToOrder(View view) {
        System.out.println("test");
//        int quantity = (Integer) quantitySpinner.getSelectedItem();
//
//        // TODO: Make new receipt
        SQLiteOpenHelper joeyDDatabaseHelper = new JoeydDAO(this);
        db = joeyDDatabaseHelper.getReadableDatabase();
//
//        // create an empty receipt
//        ContentValues receipt = new ContentValues();
        getUserPendingReceipt = db.rawQuery(String.format("select id from receipt where user_id = %s", UserID), null);
        int userReceipt = 0;
        if (getUserPendingReceipt.moveToFirst()) {
            userReceipt = getUserPendingReceipt.getInt(0);
        }

        if(userReceipt == 0){
            
        }
        db.close();

        // check if user has pending receipt
        // if not -> create a new receipt
        // if exist -> get the receipt id and work with that

        // create order tussen table order_item + receipt_id

        // insert into order_item
//        ContentValues orderItem = new ContentValues();
//        orderItem.put("dish_id", dish.getId());
//        orderItem.put("quantity", quantity);
//        orderItem.put("user_id", 1);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        orderItem.put("datetime", dateFormat.format(date));
//        long rowid = db.insert("order_item", null, orderItem);

    }
}
