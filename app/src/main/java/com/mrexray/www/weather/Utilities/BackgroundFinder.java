package com.mrexray.www.weather.Utilities;

import android.content.Intent;

import com.mrexray.www.weather.R;

/**
 * Created by Ray on 11/13/2016.
 */

public class BackgroundFinder {
    int drawableId;


    public int findBackground(int weatherCode) {


        if (weatherCode == 0 || weatherCode == 1
                || weatherCode == 2 || weatherCode == 3 || weatherCode == 4
                || weatherCode == 37 || weatherCode == 38 || weatherCode == 39) {

            drawableId = R.drawable.thunderstorm;
        }
        if (weatherCode == 23 || weatherCode == 24) {

            drawableId = R.drawable.breezy;
        }
        if (weatherCode == 5 || weatherCode == 6
                || weatherCode == 8 || weatherCode == 9 || weatherCode == 10
                || weatherCode == 11 || weatherCode == 12 || weatherCode == 17 || weatherCode == 18) {

            drawableId = R.drawable.rainy;
        }
        if (weatherCode == 28 || weatherCode == 30
                || weatherCode == 44 || weatherCode == 26) {

            drawableId = R.drawable.cloud;
        }
        if (weatherCode == 32 || weatherCode == 34) {

            drawableId = R.drawable.sunny;
        }
        if (weatherCode == 47) {

            drawableId = R.drawable.thunder;
        }

        if (weatherCode == 15 || weatherCode == 16
                || weatherCode == 43 || weatherCode == 46 || weatherCode == 41
                || weatherCode == 14 || weatherCode == 7 || weatherCode == 13
                || weatherCode == 42 || weatherCode == 25|| weatherCode == 19
                || weatherCode == 20 || weatherCode == 21 ||weatherCode==22) {

            drawableId = R.drawable.snow;
        }
        if (weatherCode == 36) {

            drawableId = R.drawable.hot;
        }
        if (weatherCode == 31||weatherCode == 33) {

            drawableId = R.drawable.clear_night;
        }

        if (weatherCode == 27||weatherCode == 29) {

            drawableId = R.drawable.cloudy_night;
        }
        else drawableId=R.drawable.mostly_sunny;






        return drawableId;
    }
}
