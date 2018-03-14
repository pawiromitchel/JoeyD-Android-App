package sr.unasat.joeyd;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sr.unasat.joeyd.adapters.TodaysMenuAdapter;
import sr.unasat.joeyd.database.JoeydDAO;
import sr.unasat.joeyd.entity.Dish;

public class MainMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String NORMAL_DISH = "daily";
    private static final String SPECIAL_DISH = "special";

    private TodaysMenuAdapter specialAdapter;
    private TodaysMenuAdapter dailyAdapter;

    private SQLiteDatabase db;
    private Cursor specialDishesCursor;
    private Cursor normalDishesCursor;

    private List<Dish> specialList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // navigate user to his orders
                Intent intent = new Intent(MainMenuActivity.this, MyOrderActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Today's Menu
        specialAdapter = new TodaysMenuAdapter(MainMenuActivity.this, getSpecialDishesData());

        RecyclerView recyclerView_special = (RecyclerView) findViewById(R.id.special_menu_list);
        recyclerView_special.setAdapter(specialAdapter);
        recyclerView_special.setLayoutManager(new LinearLayoutManager(MainMenuActivity.this));

        dailyAdapter = new TodaysMenuAdapter(this, getDailyDishesData());

        RecyclerView recyclerView_daily = (RecyclerView) findViewById((R.id.daily_menu_list));
        recyclerView_daily.setAdapter((dailyAdapter));
        recyclerView_daily.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_myAccount) {
            Intent myAccount = new Intent(this, MyAccountActivity.class);
            startActivity(myAccount);
        } else if (id == R.id.nav_myReceipts) {
            Intent myReceipts = new Intent(this, MyReceiptsActivity.class);
            startActivity(myReceipts);
        } else if (id == R.id.nav_About) {
//            Intent pendingOrder = new Intent(this, PendingOrderActivity.class);
//            startActivity(pendingOrder);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private List<Dish> getSpecialDishesData() {
        try{
            SQLiteOpenHelper joeyDDatabaseHelper = new JoeydDAO(this);
            db = joeyDDatabaseHelper.getReadableDatabase();

            // get day of the week for example monday, tuesday, wednesday, etc
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
            Date date = new Date();
            String dayOfTheWeek = simpleDateFormat.format(date);

            specialDishesCursor = db.rawQuery(String.format("select * from dish where type = '%s' AND day = '%s'", SPECIAL_DISH, dayOfTheWeek.toLowerCase()), null);
            List<Dish> specialDishes = new ArrayList<>();
            while(specialDishesCursor.moveToNext()){
                specialDishes.add(new Dish(specialDishesCursor.getInt(0), specialDishesCursor.getString(1), specialDishesCursor.getString(2),
                        specialDishesCursor.getInt(3), specialDishesCursor.getString(4), specialDishesCursor.getString(5)));
            }
            db.close();
            return specialDishes;
        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        return null;
    }

    private List<Dish> getDailyDishesData() {
        try{
            SQLiteOpenHelper joeyDDatabaseHelper = new JoeydDAO(this);
            db = joeyDDatabaseHelper.getReadableDatabase();

            normalDishesCursor = db.rawQuery(String.format("select * from dish where type = '%s'", NORMAL_DISH), null);
            List<Dish> dailyDishes = new ArrayList<>();
            while(normalDishesCursor.moveToNext()){
                dailyDishes.add(new Dish(normalDishesCursor.getInt(0), normalDishesCursor.getString(1), normalDishesCursor.getString(2),
                        normalDishesCursor.getInt(3), normalDishesCursor.getString(4), normalDishesCursor.getString(5)));
            }
            db.close();
            return dailyDishes;
        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        return null;
    }
}
