package com.sizey.cardata;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
interface CarDao {

    // onConflict = OnConflictStrategy.REPLACE은 수정을 가능하게 함
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCar(Car car);

    @Query("SELECT * FROM car ORDER BY id DESC")
    LiveData<List<Car>> getAllCars();
}
