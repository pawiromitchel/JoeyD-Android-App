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

    private TodaysMenuAdapter adapter;
    private TodaysMenuAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_menu);

        adapter = new TodaysMenuAdapter(this, getSpecialData());

        RecyclerView recyclerView_special = (RecyclerView) findViewById(R.id.special_menu_list);
        recyclerView_special.setAdapter(adapter);
        recyclerView_special.setLayoutManager(new LinearLayoutManager(this));

        adapter2 = new TodaysMenuAdapter(this, getDailyData());

        RecyclerView recyclerView_daily = (RecyclerView) findViewById((R.id.daily_menu_list));
        recyclerView_daily.setAdapter((adapter2));
        recyclerView_daily.setLayoutManager(new LinearLayoutManager(this));

    }

    private List<Dish> getSpecialData() {
        List<Dish> c = new ArrayList<>();
        c.add(new Dish(1, "dish1", "SRD1,-", R.drawable.joeyds_logoimage));
        c.add(new Dish(2, "dish2", "SRD2,-", R.drawable.joeyds_logoimage));
        c.add(new Dish(3, "dish3", "SRD3,-", R.drawable.joeyds_logoimage));
        return c;
    }

    private List<Dish> getDailyData() {
        List<Dish> d = new ArrayList<>();
        d.add(new Dish(1, "dish4", "SRD4,-", R.drawable.joeyds_logoimage));
        d.add(new Dish(2, "dish5", "SRD5,-", R.drawable.joeyds_logoimage));
        d.add(new Dish(3, "dish6", "SRD6,-", R.drawable.joeyds_logoimage));
        return d;
    }

}
