package com.mrexray.www.weather.Utilities;

import com.mrexray.www.weather.R;

/**
 * Created by Ray on 11/13/2016.
 */

public class IconFinder {

    int iconId;


    public int findIcon(String code) {


        int weatherCode=Integer.parseInt(code);

        if (weatherCode == 0 || weatherCode == 1
                || weatherCode == 2 || weatherCode == 3 || weatherCode == 4
                || weatherCode == 37 || weatherCode == 38 || weatherCode == 39) {

            iconId = R.drawable.i_storm;
        }
        if (weatherCode == 23 || weatherCode == 24) {

            iconId = R.drawable.i_breezy;
        }
        if (weatherCode == 5 || weatherCode == 6
                || weatherCode == 8 || weatherCode == 9 || weatherCode == 10
                || weatherCode == 11 || weatherCode == 12 || weatherCode == 17 || weatherCode == 18) {

            iconId = R.drawable.i_rainy;
        }
        if (weatherCode == 28 || weatherCode == 30
                || weatherCode == 44 || weatherCode == 26) {

            iconId = R.drawable.i_cloud;
        }
        if (weatherCode == 32 || weatherCode == 34) {

            iconId = R.drawable.i_sunny;
        }
        if (weatherCode == 47) {

            iconId = R.drawable.i_thunder;
        }

        if (weatherCode == 15 || weatherCode == 16
                || weatherCode == 43 || weatherCode == 46 || weatherCode == 41
                || weatherCode == 14 || weatherCode == 7 || weatherCode == 13
                || weatherCode == 42 || weatherCode == 25|| weatherCode == 19
                || weatherCode == 20 || weatherCode == 21 ||weatherCode==22) {

            iconId = R.drawable.i_snow;
        }
        if (weatherCode == 36) {

            iconId = R.drawable.i_sunny;
        }




        return iconId;
    }
}
