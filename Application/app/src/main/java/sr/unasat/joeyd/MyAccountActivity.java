package sr.unasat.joeyd;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import sr.unasat.joeyd.database.JoeydDAO;
import sr.unasat.joeyd.entity.User;

public class MyAccountActivity extends Activity {

    private TextView userName;
    private TextView firstName;
    private TextView lastName;
    private TextView phoneNumber;
    private JoeydDAO joeydDAO;
    private SQLiteDatabase db;
    private Cursor getUserCreds;
    private long UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        UserID = preferences.getLong("LoggedUserID", 0);

        SQLiteOpenHelper joeyDDatabaseHelper = new JoeydDAO(this);
        db = joeyDDatabaseHelper.getReadableDatabase();
        getUserCreds = db.rawQuery(String.format("select * from user where 'id' = '%s'", UserID),null);

        if(getUserCreds.moveToFirst()){
            userName = (TextView) findViewById(R.id.userUserName);
            userName.setText(getUserCreds.getString(3));

            firstName = (TextView) findViewById(R.id.userFirstName);
            firstName.setText(getUserCreds.getString(1));

            lastName = (TextView) findViewById(R.id.userLastName);
            lastName.setText(getUserCreds.getString(2));

            phoneNumber = (TextView) findViewById(R.id.userPhoneNumber);
            phoneNumber.setText(getUserCreds.getString(5));

        }



    }
}
