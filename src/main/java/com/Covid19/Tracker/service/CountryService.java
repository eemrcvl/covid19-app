package com.Covid19.Tracker.service;

import com.Covid19.Tracker.model.Country;
import com.Covid19.Tracker.repository.CountryRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CountryService {
    private static final String ApiURL = "https://api.covid19api.com/summary";
    private static JSONArray data;
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public String getDataFromAPI() throws IOException {
        URL url = new URL(ApiURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String input;
        StringBuffer response = new StringBuffer();
        while ((input = bufferedReader.readLine()) != null){
            response.append(input);
        }
        bufferedReader.close();
        return response.toString();
    }
    public void saveToDatabase(){
        try {
            String response = getDataFromAPI();
            JSONObject jsonObject = new JSONObject(response);
            if(jsonObject.length() != 0){
                data = jsonObject.getJSONArray("Countries");
            }
        }
        catch (IOException exception) {
            exception.getMessage();
        }
        Country[] countries = new Country[data.length()];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        for (int i = 0; i < data.length(); i++){
            countries[i] = new Country();
            countries[i].setCountryName(data.getJSONObject(i).getString("Country"));
            countries[i].setCountryCode(data.getJSONObject(i).getString("CountryCode"));
            countries[i].setSlug(data.getJSONObject(i).getString("Slug"));
            countries[i].setNewConfirmed(data.getJSONObject(i).getInt("NewConfirmed"));
            countries[i].setTotalConfirmed(data.getJSONObject(i).getInt("TotalConfirmed"));
            countries[i].setNewDeaths(data.getJSONObject(i).getInt("NewDeaths"));
            countries[i].setTotalDeaths(data.getJSONObject(i).getInt("TotalDeaths"));
            countries[i].setNewRecovered(data.getJSONObject(i).getInt("NewRecovered"));
            countries[i].setTotalRecovered(data.getJSONObject(i).getInt("TotalRecovered"));
            countries[i].setDate(data.getJSONObject(i).getString("Date"));
            countryRepository.save(countries[i]);
        }
        //TODO: Check multithreading for loop above
    }
}
