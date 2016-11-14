package com.mrexray.www.weather.Data.Remote;

import com.mrexray.www.weather.Data.Weather;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Ray on 11/12/2016.
 */
public interface WeatherApi {

    String BASE_URL = "https://query.yahooapis.com/v1/public/";

    @GET
    Call<Weather> getWeather(@Url String url);

    class Factory {
        private static WeatherApi service;

        public static WeatherApi getInstance() {

            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder().
                        addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
                service = retrofit.create(WeatherApi.class);

                return service;
            } else return service;


        }


    }
}

