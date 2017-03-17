package com.coolweather.android.util;

import android.text.TextUtils;
import android.widget.EditText;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;
import com.coolweather.android.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/3/16 0016.
 */

public class Utility {

    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces = new JSONArray(response);
                for(int i=0;i<allProvinces.length();i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProviceCode(provinceObject.getInt("id"));
                    province.setProviceName(provinceObject.getString("name"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
     public static boolean handleCityResponse(String response,int provinceId){
         if(!TextUtils.isEmpty(response)){
             try{
                 JSONArray allCities = new JSONArray(response);
                 for (int i=0;i<allCities.length();i++){
                     JSONObject cityObject = allCities.getJSONObject(i);
                     City city = new City();
                     city.setCityCode(cityObject.getInt("id"));
                     city.setCityName(cityObject.getString("name"));
                     city.setProvinceId(provinceId);
                     city.save();
                 }
                 return true;
             }catch (JSONException e){
                 e.printStackTrace();
             }
         }
         return false;
     }

    public static boolean handleCountryResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCountries = new JSONArray(response);
                for(int i=0;i<allCountries.length();i++){
                    JSONObject countryObject = allCountries.getJSONObject(i);
                    County county = new County();
                    county.setCityId(cityId);
                    county.setCountyName(countryObject.getString("name"));
                    county.setWeatherId(countryObject.getString("weather_id"));
                    county.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static Weather handleWeatherResponse(String respons){
        try {
            JSONObject jsonObject = new JSONObject(respons);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather data service 3.0");
            String weahterContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weahterContent, Weather.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
