package com.mrexray.www.weather;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrexray.www.weather.Data.Remote.WeatherApi;
import com.mrexray.www.weather.Data.Weather;
import com.mrexray.www.weather.Utilities.BackgroundFinder;
import com.mrexray.www.weather.Utilities.BuildUrl;
import com.mrexray.www.weather.Utilities.IconFinder;
import com.mrexray.www.weather.Utilities.SharedPreferenceManager;
import com.mrexray.www.weather.Utilities.UnitConverter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private TextView tvLocation;
    private TextView tvUnit;
    private TextView tvDate;
    private TextView tvTemp;
    private TextView tvHum;
    private TextView tvWindSp;
    private TextView tvDescription;
    private TextView tvPressure;
    private TextView tvSunRise;
    private TextView tvSunSet;

    private ImageView imgIcon1;
    private ImageView imgIcon2;
    private ImageView imgIcon3;
    private ImageView imgIcon4;
    private ImageView imgIcon5;
    private ImageView imgIcon6;
    private ImageView imgIcon7;

    private TextView tvDay1;
    private TextView tvDay2;
    private TextView tvDay3;
    private TextView tvDay4;
    private TextView tvDay5;
    private TextView tvDay6;
    private TextView tvDay7;

    private TextView tvCast1;
    private TextView tvCast2;
    private TextView tvCast3;
    private TextView tvCast4;
    private TextView tvCast5;
    private TextView tvCast6;
    private TextView tvCast7;


    private View layout;
    private AlertDialog.Builder alert;
    private String newCity;
    private String url;
    BuildUrl buildUrl;
    SharedPreferenceManager shManager;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        alert = new AlertDialog.Builder(this);
        shManager = new SharedPreferenceManager(this);
        buildUrl = new BuildUrl();

        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Getting latest weather information");
        progress.setCancelable(false);

        tvLocation = (TextView) findViewById(R.id.tvLocation);
        tvUnit = (TextView) findViewById(R.id.tvUnit);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvPressure = (TextView) findViewById(R.id.tvPressure);
        tvTemp = (TextView) findViewById(R.id.tvTemp);
        tvHum = (TextView) findViewById(R.id.tvHumidity);
        tvWindSp = (TextView) findViewById(R.id.tvWindSp);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvSunRise = (TextView) findViewById(R.id.tvSunRise);
        tvSunSet = (TextView) findViewById(R.id.tvSunSet);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/gotham_light.ttf");
        Typeface typefaceReg = Typeface.createFromAsset(getAssets(), "fonts/gotham_pro.ttf");

        tvLocation.setTypeface(typefaceReg);
        tvDate.setTypeface(typeface);
        tvTemp.setTypeface(typefaceReg);
        tvPressure.setTypeface(typeface);
        tvSunRise.setTypeface(typeface);
        tvSunSet.setTypeface(typeface);
        tvDescription.setTypeface(typeface);
        tvWindSp.setTypeface(typeface);
        tvHum.setTypeface(typeface);
        tvUnit.setTypeface(typefaceReg);

        tvDay1 = (TextView) findViewById(R.id.tvDay1);
        tvDay2 = (TextView) findViewById(R.id.tvday2);
        tvDay3 = (TextView) findViewById(R.id.tvDay3);
        tvDay4 = (TextView) findViewById(R.id.tvDay4);
        tvDay5 = (TextView) findViewById(R.id.tvDay5);
        tvDay6 = (TextView) findViewById(R.id.tvDay6);
        tvDay7 = (TextView) findViewById(R.id.tvDay7);

        tvDay1.setTypeface(typeface);
        tvDay2.setTypeface(typeface);
        tvDay3.setTypeface(typeface);
        tvDay4.setTypeface(typeface);
        tvDay5.setTypeface(typeface);
        tvDay6.setTypeface(typeface);
        tvDay7.setTypeface(typeface);

        tvCast1 = (TextView) findViewById(R.id.tvCast1);
        tvCast2 = (TextView) findViewById(R.id.tvCast2);
        tvCast3 = (TextView) findViewById(R.id.tvCast3);
        tvCast4 = (TextView) findViewById(R.id.tvcast4);
        tvCast5 = (TextView) findViewById(R.id.tvCast5);
        tvCast6 = (TextView) findViewById(R.id.tvCast6);
        tvCast7 = (TextView) findViewById(R.id.tvCast7);

        tvCast1.setTypeface(typeface);
        tvCast2.setTypeface(typeface);
        tvCast3.setTypeface(typeface);
        tvCast4.setTypeface(typeface);
        tvCast5.setTypeface(typeface);
        tvCast6.setTypeface(typeface);
        tvCast7.setTypeface(typeface);


        imgIcon1 = (ImageView) findViewById(R.id.imgIcon1);
        imgIcon2 = (ImageView) findViewById(R.id.imgIcon2);
        imgIcon3 = (ImageView) findViewById(R.id.imgIcon3);
        imgIcon4 = (ImageView) findViewById(R.id.imgIcon4);
        imgIcon5 = (ImageView) findViewById(R.id.imgIcon5);
        imgIcon6 = (ImageView) findViewById(R.id.imgIcon6);
        imgIcon7 = (ImageView) findViewById(R.id.imgIcon7);


    }

    @Override
    protected void onResume() {
        progress.show();
        doAll();
        progress.dismiss();
        super.onResume();
    }

    public void doAll() {
        if (isNetworkConnected()) {

            newCity = shManager.getFromSharedPreference();

            if (!newCity.isEmpty()) {
                progress.show();
                url = buildUrl.buildUrl(newCity);
                setView(url);
                progress.dismiss();
            } else {
                final EditText editText = new EditText(this);
                alert.setTitle("Enter City")
                        .setMessage("")
                        .setView(editText)
                        .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                shManager.setInSharedPreferences(editText.getText().toString());
                                doAll();
                            }
                        });
                alert.show();
            }

        } else {
            alert.setTitle(R.string.noInternet)
                    .setMessage(R.string.Internet)
                    .setPositiveButton(R.string.tryAgain, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            doAll();
                        }
                    });
            alert.show();
        }
    }

    public void setView(String url) {
        WeatherApi.Factory.getInstance().getWeather(url).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                BackgroundFinder backgroundFinder = new BackgroundFinder();
                if (response.body().getQuery().getCount() == 1) {

                    String a = response.body().getQuery().getResults().getChannel().getItem().getCondition().getCode();
                    int weatherCode = Integer.parseInt(a);

                    setBackground(backgroundFinder.findBackground(weatherCode));

                    tvLocation.setText(response.body().getQuery().getResults().getChannel().getLocation().getCity() + ", " +
                            response.body().getQuery().getResults().getChannel().getLocation().getCountry());
                    tvDate.setText(response.body().getQuery().getResults().getChannel().getItem().getForecast().get(0).getDate());
                    tvTemp.setText(response.body().getQuery().getResults().getChannel().getItem().getCondition().getTemp());
                    tvDescription.setText(response.body().getQuery().getResults().getChannel().getItem().getCondition().getText());
                    tvPressure.setText("Pressure: " + response.body().getQuery().getResults().getChannel().getAtmosphere().getPressure() + " ppsi");
                    tvHum.setText("Humidity: " + response.body().getQuery().getResults().getChannel().getAtmosphere().getHumidity() + " %");
                    tvWindSp.setText("Wind Speed: " + response.body().getQuery().getResults().getChannel().getWind().getSpeed() + " mph");
                    tvSunRise.setText("Sunrise: " + response.body().getQuery().getResults().getChannel().getAstronomy().getSunrise());
                    tvSunSet.setText("Sunset: " + response.body().getQuery().getResults().getChannel().getAstronomy().getSunset());

                    tvDay1.setText(response.body().getQuery().getResults().getChannel().getItem().getForecast().get(0).getDay());
                    tvDay2.setText(response.body().getQuery().getResults().getChannel().getItem().getForecast().get(1).getDay());
                    tvDay3.setText(response.body().getQuery().getResults().getChannel().getItem().getForecast().get(2).getDay());
                    tvDay4.setText(response.body().getQuery().getResults().getChannel().getItem().getForecast().get(3).getDay());
                    tvDay5.setText(response.body().getQuery().getResults().getChannel().getItem().getForecast().get(4).getDay());
                    tvDay6.setText(response.body().getQuery().getResults().getChannel().getItem().getForecast().get(5).getDay());
                    tvDay7.setText(response.body().getQuery().getResults().getChannel().getItem().getForecast().get(6).getDay());

                    tvCast1.setText(response.body().getQuery().getResults().getChannel().getItem().getForecast().get(0).getLow());
                    tvCast2.setText(response.body().getQuery().getResults().getChannel().getItem().getForecast().get(1).getLow());
                    tvCast3.setText(response.body().getQuery().getResults().getChannel().getItem().getForecast().get(2).getLow());
                    tvCast4.setText(response.body().getQuery().getResults().getChannel().getItem().getForecast().get(3).getLow());
                    tvCast5.setText(response.body().getQuery().getResults().getChannel().getItem().getForecast().get(4).getLow());
                    tvCast6.setText(response.body().getQuery().getResults().getChannel().getItem().getForecast().get(5).getLow());
                    tvCast7.setText(response.body().getQuery().getResults().getChannel().getItem().getForecast().get(6).getLow());

                    String i0 = response.body().getQuery().getResults().getChannel().getItem().getForecast().get(0).getCode();
                    String i1 = response.body().getQuery().getResults().getChannel().getItem().getForecast().get(0).getCode();
                    String i2 = response.body().getQuery().getResults().getChannel().getItem().getForecast().get(0).getCode();
                    String i3 = response.body().getQuery().getResults().getChannel().getItem().getForecast().get(0).getCode();
                    String i4 = response.body().getQuery().getResults().getChannel().getItem().getForecast().get(0).getCode();
                    String i5 = response.body().getQuery().getResults().getChannel().getItem().getForecast().get(0).getCode();
                    String i6 = response.body().getQuery().getResults().getChannel().getItem().getForecast().get(0).getCode();

                    IconFinder iconFinder = new IconFinder();

                    imgIcon1.setImageResource(iconFinder.findIcon(i0));
                    imgIcon2.setImageResource(iconFinder.findIcon(i1));
                    imgIcon3.setImageResource(iconFinder.findIcon(i2));
                    imgIcon4.setImageResource(iconFinder.findIcon(i3));
                    imgIcon5.setImageResource(iconFinder.findIcon(i4));
                    imgIcon6.setImageResource(iconFinder.findIcon(i5));
                    imgIcon7.setImageResource(iconFinder.findIcon(i6));


                } else {
                    shManager.setInSharedPreferences("");
                    doAll();

                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
            }
        });
    }

    private void setBackground(int drawableId) {
        layout = findViewById(R.id.activity_home);
        Animation anim = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.anim);
        layout.setAnimation(anim);
        anim.start();
        layout.setBackground(ResourcesCompat.getDrawable(getResources(), drawableId, null));
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(HomeActivity.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void edit(final View view) {

        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_actions, popup.getMenu());
        popup.show();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.changeLocation) {
                    final EditText editText = new EditText(HomeActivity.this);
                    alert.setTitle("Enter City");
                    alert.setMessage("");
                    alert.setView(editText);

                    alert.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            newCity = editText.getText().toString();
                            shManager.setInSharedPreferences(newCity);
                            doAll();
                        }
                    });

                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    alert.show();
                }

                return false;
            }
        });
    }


    public void convertUnit(View view) {

        String unit = tvUnit.getText().toString();
        String temp = tvTemp.getText().toString();

        String t1 = tvCast1.getText().toString();
        String t2 = tvCast2.getText().toString();
        String t3 = tvCast3.getText().toString();
        String t4 = tvCast4.getText().toString();
        String t5 = tvCast5.getText().toString();
        String t6 = tvCast6.getText().toString();
        String t7 = tvCast7.getText().toString();

        UnitConverter converter = new UnitConverter();

        if (unit.equals("F")) {
            tvTemp.setText(converter.convertFarToCel(temp));
            tvUnit.setText("C");

            tvCast1.setText(converter.convertFarToCel(t1));
            tvCast2.setText(converter.convertFarToCel(t2));
            tvCast3.setText(converter.convertFarToCel(t3));
            tvCast4.setText(converter.convertFarToCel(t4));
            tvCast5.setText(converter.convertFarToCel(t5));
            tvCast6.setText(converter.convertFarToCel(t6));
            tvCast7.setText(converter.convertFarToCel(t7));

        }
        if (unit.equals("C")) {
            tvTemp.setText(converter.convertCelToFar(temp));
            tvUnit.setText("F");

            tvCast1.setText(converter.convertCelToFar(t1));
            tvCast2.setText(converter.convertCelToFar(t2));
            tvCast3.setText(converter.convertCelToFar(t3));
            tvCast4.setText(converter.convertCelToFar(t4));
            tvCast5.setText(converter.convertCelToFar(t5));
            tvCast6.setText(converter.convertCelToFar(t6));
            tvCast7.setText(converter.convertCelToFar(t7));

        }
    }


}
