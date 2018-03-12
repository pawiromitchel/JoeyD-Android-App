package sr.unasat.joeyd;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
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

    // get userID from loggedUserStorage
    private long UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        dishNameTextView = (TextView) findViewById(R.id.dish_desc_text);
        quantitySpinner = (Spinner) findViewById(R.id.quantitySpinner);

        dish = (Dish)getIntent().getSerializableExtra("dishObject");
        dishNameTextView.setText(dish.getName());

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        UserID = preferences.getLong("LoggedUserID", 0);
    }

    public void addToOrder(View view) {
        SQLiteOpenHelper joeyDDatabaseHelper = new JoeydDAO(this);
        db = joeyDDatabaseHelper.getReadableDatabase();

        getUserPendingReceipt = db.rawQuery(String.format("select id from receipt where user_id = %s AND status = 'new'", UserID), null);
        int userReceipt = 0;
        if (getUserPendingReceipt.moveToFirst()) {
            userReceipt = getUserPendingReceipt.getInt(0);
        }

        // no pending receipt found
        if(userReceipt == 0){

            Integer nextInt = 1;
            Cursor checkInt = db.rawQuery("select id from receipt order by id desc limit 1", null);
            if (checkInt.moveToFirst()) {
                nextInt = (checkInt.getInt(0) + 1);
            }

            // create new empty receipt
            ContentValues newReceipt = new ContentValues();
            newReceipt.put("receipt_number", nextInt);
            newReceipt.put("user_id", UserID);
            newReceipt.put("total_price", 0);
            newReceipt.put("status", "new");
            long newUserReceiptID = db.insert("receipt", null, newReceipt);

            // insert into order_item
            int quantity = Integer.parseInt(quantitySpinner.getSelectedItem().toString());
            ContentValues orderItem = new ContentValues();
            orderItem.put("dish_id", dish.getId());
            orderItem.put("quantity", quantity);
            orderItem.put("user_id", UserID);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            orderItem.put("datetime", dateFormat.format(date));
            long orderItemID = db.insert("order_item", null, orderItem);

            // insert into order tussentable
            ContentValues orderTableValues = new ContentValues();
            orderTableValues.put("order_item_id", orderItemID);
            orderTableValues.put("receipt_id", newUserReceiptID);
            long orderTableID = db.insert("`order`", null, orderTableValues);
            db.close();

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

            db.close();

            // navigate user to Main Menu
            Intent intent = new Intent(OrderActivity.this, MainMenuActivity.class);
            startActivity(intent);
        }
        db.close();
    }
}
