package com.sizey.cardata;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkingClass {

    APIDataListner activity;
    Context mainActivityContext;
    String finalData = "";

    public interface APIDataListner {
        void returnAPIData(String data);
    }

    NetworkingClass(APIDataListner listner, Context context) {
        this.activity = listner;
        mainActivityContext = context;
    }

    // call network connect API
    void getCars() {
        final String url = "https://";
        connectAnAPI(url);
    }

    // network connect API
    void connectAnAPI(final String url) {
        try {

            Thread thread = new Thread() {

                public void run() {

                    Looper.prepare();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String data = "";
                            HttpURLConnection httpURLConnection = null;
                            try {

                                // http연결을 위한 객체 생성
                                httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
                                httpURLConnection.setRequestMethod("GET");
                                httpURLConnection.setRequestProperty("Content-Type", "application/json");

                                int status = httpURLConnection.getResponseCode();
                                Log.d("GET RX", " status=> " + status);

                                try {

                                    InputStream in = httpURLConnection.getInputStream();
                                    InputStreamReader inputStreamReader = new InputStreamReader(in);
                                    int inputStreamData = inputStreamReader.read();
                                    while (inputStreamData != -1) {
                                        char current = (char) inputStreamData;
                                        inputStreamData = inputStreamReader.read();
                                        data += current;
                                    }
                                    finalData = data;
                                    // 핸들러를 통한 스레드 접근
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        @Override
                                        public void run() {
                                            activity.returnAPIData(finalData);
                                        }
                                    });

                                } catch (Exception exx) {
                                    Log.d("error", exx.toString());
                                }
                            } catch (Exception e) {
                                Log.e("TX", " error => " + e.getMessage());
                                e.printStackTrace();
                            } finally {
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                            }
                            handler.removeCallbacks(this);
                            Looper.myLooper().quit();
                        }
                    }, 1000);
                    Looper.loop();
                }
            };
            thread.start();
        } catch (Exception ex) {
            Log.e("ERROR =>", "" + ex.getMessage());
            ex.printStackTrace();
        }

    }


}

