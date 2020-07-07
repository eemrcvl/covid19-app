package com.Covid19.Tracker.controller;

import com.Covid19.Tracker.model.Country;
import com.Covid19.Tracker.model.Global;
import com.Covid19.Tracker.repository.CountryRepository;
import com.Covid19.Tracker.repository.GlobalRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@EnableScheduling
@RestController
@RequestMapping("/api/data")
public class DataApiController {
    private static final String ApiURL = "https://api.covid19api.com/summary";
    private static JSONArray dataCountries;
    private static JSONObject dataGlobal;
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private final CountryRepository countryRepository;
    private final GlobalRepository globalRepository;

    public DataApiController(CountryRepository countryRepository, GlobalRepository globalRepository) {
        this.countryRepository = countryRepository;
        this.globalRepository = globalRepository;
    }

    public void getDataFromAPI() throws IOException {
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
        JSONObject jsonObject = new JSONObject(response.toString());
        if (jsonObject.length() != 0) {
            dataCountries = jsonObject.getJSONArray("Countries");
            dataGlobal = jsonObject.getJSONObject("Global");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Scheduled(cron = "* 0 0 * * *",zone = "Europe/Istanbul")
    public void saveData() {
        //fetch country & global stats and save to db
        try {
            getDataFromAPI();
        } catch (IOException exception) {
            exception.getMessage();
        }

        LocalDate localDate = getLocalDate();
        Country[] countries = new Country[dataCountries.length()];
        for (int i = 0; i < dataCountries.length(); i++){
            countries[i] = new Country();
            countries[i].setDate(localDate);
            countries[i].setCountryName(dataCountries.getJSONObject(i).getString("Country"));
            countries[i].setCountryCode(dataCountries.getJSONObject(i).getString("CountryCode"));
            countries[i].setSlug(dataCountries.getJSONObject(i).getString("Slug"));
            countries[i].setNewConfirmed(dataCountries.getJSONObject(i).getInt("NewConfirmed"));
            countries[i].setTotalConfirmed(dataCountries.getJSONObject(i).getInt("TotalConfirmed"));
            countries[i].setNewDeaths(dataCountries.getJSONObject(i).getInt("NewDeaths"));
            countries[i].setTotalDeaths(dataCountries.getJSONObject(i).getInt("TotalDeaths"));
            countries[i].setNewRecovered(dataCountries.getJSONObject(i).getInt("NewRecovered"));
            countries[i].setTotalRecovered(dataCountries.getJSONObject(i).getInt("TotalRecovered"));
        }

        for (Country country : countries) {
            countryRepository.save(country);
        }

        Global global = new Global();
        global.setTotalConfirmed(dataGlobal.getInt("TotalConfirmed"));
        global.setNewConfirmed(dataGlobal.getInt("NewConfirmed"));
        global.setNewDeaths(dataGlobal.getInt("NewDeaths"));
        global.setTotalDeaths(dataGlobal.getInt("TotalDeaths"));
        global.setNewRecovered(dataGlobal.getInt("NewRecovered"));
        global.setTotalRecovered(dataGlobal.getInt("TotalRecovered"));
        global.setDate(localDate);
        globalRepository.save(global);
    }
    public static LocalDate getLocalDate(){
        return LocalDate.now();
    }
}
