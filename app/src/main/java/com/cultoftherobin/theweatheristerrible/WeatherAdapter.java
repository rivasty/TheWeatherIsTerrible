package com.cultoftherobin.theweatheristerrible;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherHolder> {
    private ArrayList<String> mWeathers;
    public ArrayList<String> mExtendedWeathers;
    private onWeatherListener mListener;
    public interface onWeatherListener {
        void onClickWeather(String mExtendedWeather);
    }
    public WeatherAdapter(onWeatherListener listener) {
        mListener          = listener;
        Weather();
    }

    @NonNull
    @Override
    public WeatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.weather_with_toast,parent,false);
        return new WeatherHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherHolder holder, int position) {
        String WeatherText = mWeathers.get(position);
        holder.bind(WeatherText);
    }

    @Override
    public int getItemCount() {
        return mWeathers.size();
    }

    public void addWeather(String Weather, String ExtendedWeather) {
        mWeathers.add(0,Weather);
        mExtendedWeathers.add(0,ExtendedWeather);
        notifyItemInserted(0);
    }

    class WeatherHolder extends RecyclerView.ViewHolder {
        private TextView mWeatherTV;
        public WeatherHolder(@NonNull View itemView) {
            super(itemView);
            mWeatherTV = itemView.findViewById(R.id.tv_weather_text);
            mWeatherTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mWeatherText = mExtendedWeathers.get(getAdapterPosition());
                    mListener.onClickWeather(mWeatherText);
                }
            });
        }
        void bind(String newWeatherText){
            mWeatherTV.setText(newWeatherText);
        }
    }
    public void Weather() {
        mWeathers          = new ArrayList<>(15);
        mExtendedWeathers   = new ArrayList<>(15);
        int mDay = 6;
        int max = -20;
        int min = -240;
        String[] mWeaTyp = new String[] {
                "Cold",
                "Freezing",
                "'Chilly'",
                "Snowing",
                "Ice Raining",
                "Unnaturally Heavy Snow"
        };
        String[] mWeaAdj = new String[] {
                "Eww.",
                "Hope you brought your ice gear.",
                "There is no hope of good things.",
                "This is why we cant have nice things. They all just freeze.",
                "What was that about never having lost limbs to frostbite again?",
                "Why is the weather like this?",
                "Holy shit its cold.",
                "Did that guy over there just freeze to death?",
                "Maybe don't go more than 2m from the fire.",
                "Did the steam generator just die out?",
                "So. Cold.",
                "I wish it was -20C, at least that was warm.",
                "The inside of your house is -60C. That's standing next to the fire.",
                "Welp. No more fire I guess.",
                "Its Dark af"
        };
        String[] mWeaDay = {
                "Sat",
                "Fri",
                "Thu",
                "Wed",
                "Tue",
                "Mon",
                "Sun"
        };
        for(int i = 0;i < 15;i++) {

            int mHighRange = (int)(Math.random() * ((5 - 3) + 1)) + 3;
            int mLowRange = (int)(Math.random() * ((5 - 3) + 1)) + 3;
            int temperature = (int)(Math.random() * ((max - min) + 1)) + min;
            int visibility = (int)(Math.random() * (100 - 5) + 1) + 5;
            int day = i+15;
            mHighRange += temperature;
            mLowRange -= temperature;
            int type = (int)(Math.random() * 5 + 1);
            String mWeather;
            String mExtendedWeather;
            mWeather = mWeaDay[mDay] + " March " + day + " " + mWeaTyp[type] + " " + temperature +"C.";
            mExtendedWeather = mWeaDay[mDay] + " March " + day + " High of " + mHighRange + "C. Low of " + mLowRange + "C. Visibility " + visibility + "m. " + mWeaAdj[i];
            mDay--;
            if(mDay < 0) {
                mDay = 6;
            }
            mWeathers.add(mWeather);
            mExtendedWeathers.add(mExtendedWeather);
        }
    }
}
