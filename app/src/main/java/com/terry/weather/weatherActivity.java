package com.terry.weather;

import android.app.Activity;
import android.os.Bundle;

import com.yaomei.widget.WeatherView;

public class weatherActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    WeatherView mWeatherView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mWeatherView = (WeatherView) findViewById(R.id.weatherview);
        mWeatherView.updateWeather(getResources().getDrawable(R.drawable.ic_weather_overcast),"26℃","雷阵雨","呼和浩特市");
    }
}