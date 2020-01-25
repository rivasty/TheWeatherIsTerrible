package com.cultoftherobin.theweatheristerrible;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity implements WeatherAdapter.onWeatherListener {
    private ArrayList<String> mWeathers;
    private ArrayList<String> mExtendedWeathers;
    private WeatherAdapter mWeatherAdapter;
    private RecyclerView mWeatherListRV;
    private Toast mToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWeatherListRV = findViewById(R.id.rv_weather_list);
        mWeatherListRV.setLayoutManager(new LinearLayoutManager(this));
        mWeatherListRV.setHasFixedSize(true);
        mWeatherAdapter = new WeatherAdapter(this);
        mWeatherListRV.setAdapter(mWeatherAdapter);
        mToast = null;
    }
    @Override
    public void onClickWeather(String mExtendedWeather) {
        if(mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this,mExtendedWeather,Toast.LENGTH_LONG);
        mToast.show();
    }
}
