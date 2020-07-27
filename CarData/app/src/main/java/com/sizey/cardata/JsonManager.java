package com.sizey.cardata;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class JsonManager {

    public ArrayList<Car> getCarData(String json) {
        ArrayList<Car> cars = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                int carId = jsonArray.getJSONObject(i).getInt("id");
                String carModel1 = jsonArray.getJSONObject(i).getString("CarModel1");
                String carModel2 = jsonArray.getJSONObject(i).getString("CarModel2");
                int year = jsonArray.getJSONObject(i).getInt("Year");
                cars.add(new Car(carId,carModel1,carModel2,year,false));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
            return cars;
    }
}
