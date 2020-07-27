package com.sizey.cardata;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    interface AlertDialogListner {
        void alertDialogCar(Car car);
    }

    private Context mContext;
    private ArrayList<Car> carList;
    AlertDialogListner listner;

    public CarAdapter(Context mContext, ArrayList<Car> carList) {
        this.mContext = mContext;
        this.carList = carList;
        listner = (AlertDialogListner) mContext;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.tv_carModel1.setText(car.getCarModel1());
        holder.tv_carModel2.setText(car.getCarModel2());
        if (!car.isFavorite())
        holder.iv_favorite.setImageResource(R.drawable.unlike);
        else
            holder.iv_favorite.setImageResource(R.drawable.like);

    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    class CarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_carModel1, tv_carModel2;
        ImageView iv_favorite;
        public CarViewHolder(View itemView) {
            super(itemView);

            tv_carModel1 = (TextView) itemView.findViewById(R.id.tv_car_model1);
            tv_carModel2 = (TextView) itemView.findViewById(R.id.tv_car_model2);
            iv_favorite = (ImageView) itemView.findViewById(R.id.iv_favorite);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final Car car = carList.get(getAdapterPosition());
            new AlertDialog.Builder(mContext)
                    .setTitle("Favorite City?")
                    .setMessage("Are you sure you want to insert " + car.getCarModel1() + " to Database?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
//                            DatabaseClient.insertFavoraitCity(city);

                            listner.alertDialogCar(new Car(car.getId(),car.getCarModel1(),car.getCarModel2(),car.getYear(),true));

                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }
    }


}
