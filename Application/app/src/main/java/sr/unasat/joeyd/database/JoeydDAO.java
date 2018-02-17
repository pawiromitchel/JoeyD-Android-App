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
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";

    private static final String SQL_USER_TABLE_QUERY = "create table user (id INTEGER PRIMARY KEY, first_name STRING, last_name STRING, username STRING NOT NULL UNIQUE, password STRING NOT NULL)";
    private static final String SQL_DISH_TABLE_QUERY = "create table dish (id INTEGER PRIMARY KEY, name STRING NOT NULL UNIQUE, price STRING NOT NULL, special INTEGER NOT NULL)";
    private static final String SQL_ORDER_ITEM_TABLE_QUERY = "create table order_item (id INTEGER PRIMARY KEY, dish_id INTEGER, quantity DOUBLE, user_id INTEGER, datetime TIMESTAMP, FOREIGN KEY(dish_id) REFERENCES dish(id), FOREIGN KEY(user_id) REFERENCES user(id))";
    private static final String SQL_RECEIPT_TABLE_QUERY = "create table receipt (id INTEGER PRIMARY KEY, total_price DOUBLE)";
    private static final String SQL_ORDER_TABLE_QUERY = "create table `order` (id INTEGER PRIMARY KEY, order_item_id INTEGER, receipt_id INTEGER, FOREIGN KEY(order_item_id) REFERENCES order_item(id), FOREIGN KEY(receipt_id) REFERENCES receipt(id))";
    private static final String SQL_SHIFT_TABLE_QUERY = "create table shift (id INTEGER PRIMARY KEY, name STRING, time TIME)";
    private static final String SQL_ORDER_MENU_TABLE_QUERY = "create table order_menu (id INTEGER PRIMARY KEY, dish_id INTEGER, shift_id INTEGER, FOREIGN KEY(dish_id) REFERENCES dish(id), FOREIGN KEY(shift_id) REFERENCES shift(id))";
    private static final String SQL_DAY_TABLE_QUERY = "create table day (id INTEGER PRIMARY KEY, day STRING, order_menu_id INTEGER, FOREIGN KEY(order_menu_id) REFERENCES order_menu(id))";

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
        contentValues.put(FIRST_NAME, "admin");
        contentValues.put(USER_USERNAME, "admin");
        contentValues.put(LAST_NAME, "admin");
        contentValues.put(USER_PASSWORD, "admin");
        insertOneRecord(USER_TABLE, contentValues);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_USER_TABLE_QUERY);
        db.execSQL(SQL_DISH_TABLE_QUERY);
        db.execSQL(SQL_ORDER_ITEM_TABLE_QUERY);
        db.execSQL(SQL_RECEIPT_TABLE_QUERY);
        db.execSQL(SQL_ORDER_TABLE_QUERY);
        db.execSQL(SQL_SHIFT_TABLE_QUERY);
        db.execSQL(SQL_ORDER_MENU_TABLE_QUERY);
        db.execSQL(SQL_DAY_TABLE_QUERY);
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
            user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
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
            user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        }
        db.close();
        return user;
    }

}
