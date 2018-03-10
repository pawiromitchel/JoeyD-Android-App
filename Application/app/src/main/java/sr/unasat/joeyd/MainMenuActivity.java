package sr.unasat.joeyd;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import java.util.ArrayList;
import java.util.List;

import sr.unasat.joeyd.adapters.TodaysMenuAdapter;
import sr.unasat.joeyd.database.JoeydDAO;
import sr.unasat.joeyd.entity.Dish;

public class MainMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TodaysMenuAdapter specialAdapter;
    private TodaysMenuAdapter dailyAdapter;

    private SQLiteDatabase db;
    private Cursor dishesCursor;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
        specialAdapter = new TodaysMenuAdapter(this, getSpecialData());

        RecyclerView recyclerView_special = (RecyclerView) findViewById(R.id.special_menu_list);
        recyclerView_special.setAdapter(specialAdapter);
        recyclerView_special.setLayoutManager(new LinearLayoutManager(this));

        dailyAdapter = new TodaysMenuAdapter(this, getDailyData());

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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Today's Menu Data
    private List<Dish> getSpecialData() {
        List<Dish> special = new ArrayList<>();
        special.add(new Dish(1, "dish1", "SRD1,-", R.drawable.joeyds_logoimage, "special", "monday"));
        return special;
    }

    private List<Dish> getDailyData() {
        try{
            SQLiteOpenHelper joeyDDatabaseHelper = new JoeydDAO(this);
            db = joeyDDatabaseHelper.getReadableDatabase();
            dishesCursor = db.rawQuery("select * from dish", null);
            List<Dish> dailyDishes = new ArrayList<>();
            while(dishesCursor.moveToNext()){
                dailyDishes.add(new Dish(dishesCursor.getInt(0), dishesCursor.getString(1), dishesCursor.getString(2),
                        dishesCursor.getInt(3), dishesCursor.getString(4), dishesCursor.getString(5)));
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
