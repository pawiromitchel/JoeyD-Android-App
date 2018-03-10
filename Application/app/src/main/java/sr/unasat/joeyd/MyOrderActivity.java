package sr.unasat.joeyd;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import sr.unasat.joeyd.database.JoeydDAO;

public class MyOrderActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor getUserOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        SQLiteOpenHelper joeyDDatabaseHelper = new JoeydDAO(this);
        db = joeyDDatabaseHelper.getReadableDatabase();

        // getUserOrders = db.rawQuery("select * from ")
    }
}
