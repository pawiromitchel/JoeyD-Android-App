package sr.unasat.joeyd.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import sr.unasat.joeyd.entity.User;

/**
 * Created by mpawirodinomo on 2/17/2018.
 */

public class JoeydDAO extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "joeyd.db";
    private static final int DATABASE_VERSION = 1;

    public static final String USER_TABLE = "user";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";

    private static final String SQL_USER_TABLE_QUERY = "create table user (id INTEGER PRIMARY KEY, username STRING NOT NULL UNIQUE, password STRING NOT NULL)";

    public JoeydDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setDefaultCredentials();
    }

    private void setDefaultCredentials() {
        User user = findOneRecordByUsername("admin");
        if (user != null) {
            return;
        }
        //Set default username and password
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_USERNAME, "admin");
        contentValues.put(USER_PASSWORD, "admin");
        insertOneRecord(USER_TABLE, contentValues);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_USER_TABLE_QUERY);
        // db.execSQL(SQL_TRANSACTION_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertOneRecord(String tableName, ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();
        long rowId = db.insert(tableName, null, contentValues);
        db.close();
        //return the row ID of the newly inserted row, or -1 if an error occurred
        return rowId;
    }

    public boolean insertMultipleRecord(String tableName, List<ContentValues> contentValuesList) {
        SQLiteDatabase db = getWritableDatabase();
        long countOnSucces = 0;
        long rowId = 0;
        for (ContentValues contentValues : contentValuesList) {
            rowId = db.insert(tableName, null, contentValues);
            countOnSucces = (rowId == 1 ? countOnSucces++ : countOnSucces);
        }
        boolean isSuccess = (countOnSucces > 0 && contentValuesList.size() == countOnSucces);
        db.close();
        //return the true id all inserts where succesfull
        return isSuccess;
    }

    public User findOneRecordByUsername(String username) {
        User user = null;
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("select * from %s", USER_TABLE);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        }
        db.close();
        return user;
    }

    public User authenticateUser(String username, String password) {
        User user = null;
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("select * from %s where username = '%s' AND password = '%s'", USER_TABLE, username, password);
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        }
        db.close();
        return user;
    }

}
