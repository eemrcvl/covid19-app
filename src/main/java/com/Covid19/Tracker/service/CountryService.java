package com.Covid19.Tracker.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class CountryService {
    private static final String ApiURL = "https://api.covid19api.com/summary";
    private JSONArray data;
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
        System.out.println(data);
    }
}
