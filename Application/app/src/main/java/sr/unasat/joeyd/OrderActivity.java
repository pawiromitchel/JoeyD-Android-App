package sr.unasat.joeyd;

import android.content.ContentValues;
import android.content.Intent;
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
        SQLiteOpenHelper joeyDDatabaseHelper = new JoeydDAO(this);
        db = joeyDDatabaseHelper.getReadableDatabase();

        getUserPendingReceipt = db.rawQuery(String.format("select id from receipt where user_id = %s AND status = 'pending'", UserID), null);
        int userReceipt = 0;
        if (getUserPendingReceipt.moveToFirst()) {
            userReceipt = getUserPendingReceipt.getInt(0);
        }

        // no pending receipt found
        if(userReceipt == 0){
            // create new empty receipt
            ContentValues newReceipt = new ContentValues();
            newReceipt.put("receipt_number", Math.random());
            newReceipt.put("user_id", UserID);
            newReceipt.put("total_price", 0);
            newReceipt.put("status", "pending");
            long newUserReceiptID = db.insert("receipt", null, newReceipt);

            // insert into order_item
            int quantity = Integer.parseInt(quantitySpinner.getSelectedItem().toString());
            ContentValues orderItem = new ContentValues();
            orderItem.put("dish_id", dish.getId());
            orderItem.put("quantity", quantity);
            orderItem.put("user_id", 1);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            orderItem.put("datetime", dateFormat.format(date));
            long orderItemID = db.insert("order_item", null, orderItem);

            // insert into order tussentable
            ContentValues orderTableValues = new ContentValues();
            orderTableValues.put("order_item_id", orderItemID);
            orderTableValues.put("receipt_id", newUserReceiptID);
            long orderTableID = db.insert("`order`", null, orderTableValues);

            Toast toast = Toast.makeText(this, "Successfully added " + dish.getName() + " to your orders", Toast.LENGTH_SHORT);
            toast.show();

            // navigate user to Main Menu
            Intent intent = new Intent(OrderActivity.this, MainMenuActivity.class);
            startActivity(intent);

        } else {
            // insert into order_item
            int quantity = Integer.parseInt(quantitySpinner.getSelectedItem().toString());
            ContentValues orderItem = new ContentValues();
            orderItem.put("dish_id", dish.getId());
            orderItem.put("quantity", quantity);
            orderItem.put("user_id", 1);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            orderItem.put("datetime", dateFormat.format(date));
            long orderItemID = db.insert("order_item", null, orderItem);

            // insert into order tussentable
            ContentValues orderTableValues = new ContentValues();
            orderTableValues.put("order_item_id", orderItemID);
            orderTableValues.put("receipt_id", userReceipt);
            long orderTableID = db.insert("`order`", null, orderTableValues);

            Toast toast = Toast.makeText(this, "Successfully added " + dish.getName() + " to your orders", Toast.LENGTH_SHORT);
            toast.show();

            // navigate user to Main Menu
            Intent intent = new Intent(OrderActivity.this, MainMenuActivity.class);
            startActivity(intent);
        }
        db.close();
    }
}
