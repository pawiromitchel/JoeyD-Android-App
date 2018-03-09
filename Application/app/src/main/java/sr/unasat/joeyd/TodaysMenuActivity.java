package sr.unasat.joeyd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sr.unasat.joeyd.adapters.TodaysMenuAdapter;
import sr.unasat.joeyd.entity.Dish;

public class TodaysMenuActivity extends AppCompatActivity {

    private TodaysMenuAdapter specialAdapter;
    private TodaysMenuAdapter dailyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_menu);

        specialAdapter = new TodaysMenuAdapter(this, getSpecialData());

        RecyclerView recyclerView_special = (RecyclerView) findViewById(R.id.special_menu_list);
        recyclerView_special.setAdapter(specialAdapter);
        recyclerView_special.setLayoutManager(new LinearLayoutManager(this));

        dailyAdapter = new TodaysMenuAdapter(this, getDailyData());

        RecyclerView recyclerView_daily = (RecyclerView) findViewById((R.id.daily_menu_list));
        recyclerView_daily.setAdapter((dailyAdapter));
        recyclerView_daily.setLayoutManager(new LinearLayoutManager(this));

    }

    private List<Dish> getSpecialData() {
        List<Dish> special = new ArrayList<>();
        special.add(new Dish(1, "dish1", "SRD1,-", R.drawable.joeyds_logoimage, "special", "monday"));
             return special;
    }

    private List<Dish> getDailyData() {
        List<Dish> daily = new ArrayList<>();
        daily.add(new Dish(1, "Beef Burger", "SRD30,-", R.drawable.joeyds_logoimage, "daily", "everyday"));
        daily.add(new Dish(2, "Chicken Burger", "SRD30,-", R.drawable.joeyds_logoimage, "daily", "everyday"));
        daily.add(new Dish(3, "Fried Rice", "SRD25,-", R.drawable.joeyds_logoimage, "daily", "everyday"));
        daily.add(new Dish(3, "Fried Noodles", "SRD25,-", R.drawable.joeyds_logoimage, "daily", "everyday"));
        return daily;
    }

}
