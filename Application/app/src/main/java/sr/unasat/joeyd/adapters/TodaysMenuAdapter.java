package sr.unasat.joeyd.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import sr.unasat.joeyd.DishDescriptionActivity;
import sr.unasat.joeyd.R;
import sr.unasat.joeyd.entity.Dish;

/**
 * Created by Sniffle on 3/6/2018.
 */

public class TodaysMenuAdapter extends RecyclerView.Adapter<TodaysMenuAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    List<Dish> data = Collections.emptyList();

    public TodaysMenuAdapter(Context context, List<Dish> data) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row_todays_menu, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Dish current = data.get(position);
        holder.dishName.setText(current.getDish_name());
        holder.dishPrice.setText(current.getDish_price());
        holder.dishImage.setImageDrawable(context.getDrawable(current.getDish_img_id()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //Represents the item
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView dishName;
        TextView dishPrice;
        ImageView dishImage;

        public ViewHolder(View itemView) {
            super(itemView);
            dishName = itemView.findViewById(R.id.dishName);
            dishPrice = itemView.findViewById(R.id.dishPrice);
            dishImage = itemView.findViewById(R.id.dishImage);
            dishImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Dish dish = data.get(getAdapterPosition()); //Position for passing objext data
            Intent intent = new Intent(context, DishDescriptionActivity.class);
            //intent.putExtra("1", dish);
            Bundle bundle = new Bundle();
            bundle.putSerializable("1", dish);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }

    }
}
