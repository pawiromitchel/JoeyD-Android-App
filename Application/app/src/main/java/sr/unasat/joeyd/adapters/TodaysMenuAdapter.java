package sr.unasat.joeyd.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import sr.unasat.joeyd.R;

/**
 * Created by Sniffle on 3/6/2018.
 */

public class TodaysMenuAdapter extends RecyclerView.Adapter<TodaysMenuAdapter.ViewHolder> {
    int dish_img_id;
    String dish_name;
    String dish_price;
    private LayoutInflater inflater;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row_todays_menu, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView dishName;
        TextView dishPrice;
        ImageView dishImage;
        public ViewHolder(View itemView) {
            super(itemView);
            dishName=itemView.findViewById(R.id.dishName);
            dishPrice=itemView.findViewById(R.id.dishPrice);
            dishImage=itemView.findViewById(R.id.dishImage);
        }
    }

}
