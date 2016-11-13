package com.mrexray.www.weather.Utilities;

/**
 * Created by Ray on 11/14/2016.
 */

public class UnitConverter {

    public String convertFarToCel(String tempInString)
    {
        double temp=Double.parseDouble(tempInString);
        temp=(temp - 32)*0.555;
        long returnTemp=Math.round(temp);
        return String.valueOf(returnTemp);
    }

    public String convertCelToFar(String tempInString)
    {
        double temp=Double.parseDouble(tempInString);
        temp = temp*1.8 + 32;
        long returnTemp=Math.round(temp);
        return String.valueOf(returnTemp);
    }


}
