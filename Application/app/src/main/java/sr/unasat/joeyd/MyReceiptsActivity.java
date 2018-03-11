package sr.unasat.joeyd;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sr.unasat.joeyd.database.JoeydDAO;
import sr.unasat.joeyd.entity.Receipt;
import sr.unasat.joeyd.entity.User;

public class MyReceiptsActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor userReceipts;
    private long UserID;

    private ArrayAdapter<Receipt> adapter;

    private ListView myReceiptsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_receipts);

        myReceiptsListView = (ListView) findViewById(R.id.myReceiptsListView);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        UserID = preferences.getLong("LoggedUserID", 0);

        SQLiteOpenHelper joeyDDatabaseHelper = new JoeydDAO(this);
        db = joeyDDatabaseHelper.getReadableDatabase();

        List<Receipt> myReceiptsList = new ArrayList<>();

        userReceipts = db.rawQuery(String.format("select * from receipt inner join user on receipt.user_id = user.id where user_id = %s", UserID), null);
        while(userReceipts.moveToNext()){
            User user = null;
            Cursor receiptUser = db.rawQuery(String.format("select * from user where id = %s", UserID), null);
            if(receiptUser.moveToFirst()){
                user = new User(receiptUser.getInt(0), receiptUser.getString(1), receiptUser.getString(2), receiptUser.getString(3), receiptUser.getString(4), receiptUser.getString(5));
            }
            myReceiptsList.add(new Receipt(userReceipts.getInt(0), userReceipts.getInt(2), user, userReceipts.getString(3), userReceipts.getString(4)));
        }

        // convert List to ArrayAdapter
        adapter = new ArrayAdapter<>(MyReceiptsActivity.this, android.R.layout.simple_list_item_1, myReceiptsList);

        // convert de ArrayAdapter van de CountryDto naar list
        myReceiptsListView.setAdapter(adapter);

        db.close();
    }
}
