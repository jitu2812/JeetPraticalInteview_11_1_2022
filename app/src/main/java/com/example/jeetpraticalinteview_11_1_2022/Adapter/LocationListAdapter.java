package com.example.jeetpraticalinteview_11_1_2022.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jeetpraticalinteview_11_1_2022.Activity.MapsActivity;
import com.example.jeetpraticalinteview_11_1_2022.Model.LocationListModel;
import com.example.jeetpraticalinteview_11_1_2022.R;

import java.util.ArrayList;

public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<LocationListModel> locationListModelArrayList;

    public LocationListAdapter(Context context, ArrayList<LocationListModel> locationListModelArrayList) {
        this.context = context;
        this.locationListModelArrayList = locationListModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_location_name.setText(locationListModelArrayList.get(position).getLocation_name());
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Are your sure delete this current location");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int newPosition = holder.getAdapterPosition();
                        locationListModelArrayList.remove(newPosition);
                        notifyItemRemoved(newPosition);
                        notifyItemRangeChanged(newPosition, locationListModelArrayList.size());
                        notifyDataSetChanged();
//                        SharedPreferences sharedPreferences = context.getSharedPreferences("locationss", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.remove(String.valueOf(position));
//                        editor.clear();
//                        editor.commit();


                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapsActivity.class);
                intent.putExtra("lats", locationListModelArrayList.get(position).getLat());
                intent.putExtra("longs", locationListModelArrayList.get(position).getLongs());
                intent.putExtra("location_name", locationListModelArrayList.get(position).getLocation_name());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return locationListModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_location_name;
        ImageView img_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_location_name = itemView.findViewById(R.id.txt_location_name);
            img_delete = itemView.findViewById(R.id.img_delete);
        }
    }
}
