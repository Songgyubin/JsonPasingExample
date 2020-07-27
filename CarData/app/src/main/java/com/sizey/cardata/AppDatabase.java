package com.sizey.cardata;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// Room DB 객체
@Database(entities = {Car.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CarDao carDao();

    // Room DB 싱글톤
    private static AppDatabase INSTANCE;


    // Room DB를 사용하기 위해
    // Room DB의 인스턴스 생성
    public static AppDatabase getInstance(Context context){

        if (INSTANCE==null){
            synchronized (AppDatabase.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class,"car.db")
                        .build();
            }
        }
        return INSTANCE;
    }
    public static void destroyInstance(){
        INSTANCE = null;
    }
}
