package sr.unasat.joeyd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sr.unasat.joeyd.database.JoeydDAO;
import sr.unasat.joeyd.entity.Dish;
import sr.unasat.joeyd.entity.OrderItem;
import sr.unasat.joeyd.entity.User;

public class MyOrderActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor getMyOrders;
    private Cursor getMyOrdersDish;
    private Cursor getMyOrdersUser;

    private ListView myOrdersListView;
    private ArrayAdapter<OrderItem> adapter;

    private int UserID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        myOrdersListView = (ListView) findViewById(R.id.myOrdersListView);

        SQLiteOpenHelper joeyDDatabaseHelper = new JoeydDAO(this);
        db = joeyDDatabaseHelper.getReadableDatabase();

        List<OrderItem> myOrdersList = new ArrayList<>();
        getMyOrders = db.rawQuery(String.format("select * from `order` AS o INNER JOIN order_item as oi on o.order_item_id = oi.id INNER JOIN receipt as r on o.receipt_id = r.id WHERE r.user_id = %s AND r.status = 'new'", UserID), null);
        while(getMyOrders.moveToNext()){

            Dish myOrderDish = null;
            getMyOrdersDish = db.rawQuery(String.format("select * from dish where id = %s", getMyOrders.getInt(4)), null);
            if(getMyOrdersDish.moveToFirst()){
                myOrderDish = new Dish(getMyOrdersDish.getInt(0), getMyOrdersDish.getString(1), getMyOrdersDish.getString(2), getMyOrdersDish.getInt(3), getMyOrdersDish.getString(4), getMyOrdersDish.getString(5));
            }

            User myOrderUser = null;
            getMyOrdersUser = db.rawQuery(String.format("select * from user where id = %s", UserID), null);
            if(getMyOrdersUser.moveToFirst()){
                myOrderUser = new User(getMyOrdersUser.getInt(0), getMyOrdersUser.getString(1), getMyOrdersUser.getString(2), getMyOrdersUser.getString(3), getMyOrdersUser.getString(4), getMyOrdersUser.getString(5));
            }

            myOrdersList.add(new OrderItem(getMyOrders.getInt(1), myOrderDish, getMyOrders.getInt(5), myOrderUser, getMyOrders.getString(7), getMyOrders.getString(8)));
        }

        // convert List to ArrayAdapter
        adapter = new ArrayAdapter<>(MyOrderActivity.this, android.R.layout.simple_list_item_1, myOrdersList);

        // convert de ArrayAdapter van de CountryDto naar list
        myOrdersListView.setAdapter(adapter);

        db.close();
    }

    public void onPlaceOrder(View view) {
        SQLiteOpenHelper joeyDDatabaseHelper = new JoeydDAO(this);
        db = joeyDDatabaseHelper.getReadableDatabase();

        ContentValues receiptValueToUpdate = new ContentValues();
        receiptValueToUpdate.put("status", "ordered");

        Cursor updateReceipt;
        updateReceipt = db.rawQuery(String.format("update receipt set status = 'ordered' where user_id = %s", UserID), null);
        if(updateReceipt.moveToFirst()){
            System.out.println(updateReceipt.getInt(0));
        }
        Toast.makeText(this, "Your order has been placed", Toast.LENGTH_LONG).show();
    }
}
