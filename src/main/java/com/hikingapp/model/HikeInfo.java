/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hikingapp.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author alex
 */
public class HikeInfo {
    
    private String address;
    private String postalCode;
    private String city;
    private String state;
    
    /**
     * 
     * @param address
     * @param postalCode
     * @param city
     * @param state 
     */
    public HikeInfo(String address, String postalCode, String city, String state) {
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.state = state;
    }
    
    public String getHikeInfo() throws JSONException {
        String apiKey = "200955643-5409210a90c1c5739821d1efae87d2bb";
        String hikeInfo = getApiInfo("https://www.hikingproject.com/data/get-trails?lat=" + getHikePosition()[0] + "&lon=" + getHikePosition()[1] + "&maxResults=5&key=" + apiKey);
        return hikeInfo;
    }
    
    /**
     * Finds the latitude and longitude from the getApiInfo method call result.
     * @return a double array where [0] is the latitude and [1] is the longitude.
     * @throws JSONException 
     */
    private double[] getHikePosition() throws JSONException {
        String apiKey = "PDjIk1CuAfcFT0Bcr2w4Ep9uPRMH5T89";
        String hikePosition = getApiInfo("http://open.mapquestapi.com/geocoding/v1/address?key=" + apiKey + "&location=" + this.address.replace(" ", "+") + "," + this.state + "," + this.city + "," + this.postalCode);
        JSONObject apiJSON = new JSONObject(hikePosition);

        double[] hikeLatLng = new double[2];

        JSONArray hikeResultArray = apiJSON.getJSONArray("results");
        for (int i = 0; i < hikeResultArray.length(); i++) {

            JSONArray hikeLocationArray = hikeResultArray.getJSONObject(i).getJSONArray("locations");

            for(int j = 0; i < hikeLocationArray.length(); i++) {
                JSONObject displayLatLng = new JSONObject(hikeLocationArray.getJSONObject(j).getString("displayLatLng"));
                hikeLatLng[0] = Double.parseDouble(displayLatLng.getString("lat"));
                hikeLatLng[1] = Double.parseDouble(displayLatLng.getString("lng"));
            }
        }

        return hikeLatLng;

    }
    
    /**
     * Gets the API info from a specified URL. Since the result might not be JSON the method returns a String.
     * @param url The url of the API to get the info from.
     * @return A string of the API result.
     */
    private String getApiInfo(String url)  {
        try {
            URL oracle = new URL(url);
            BufferedReader in = new BufferedReader(
            new InputStreamReader(oracle.openStream()));

            String output = "";
            String inputLine;
            while ((inputLine = in.readLine()) != null) output += inputLine;
            in.close();
            return output;
        } catch(IOException e) {
            return "An error occurred in HikesFrame.getApiInfo() " + e;
        }
    }
    
    public String[] getHikesNames() {
        String[] hikesNames = new String[5];
        
        try {
            JSONObject hikeApiResult = new JSONObject(getHikeInfo());
            JSONArray hikesArray = hikeApiResult.getJSONArray("trails");
            
            for (int i = 0; i < hikesArray.length(); i++) {
                String hikeName = hikesArray.getJSONObject(i).getString("name");
                hikesNames[i] = hikeName;
                //System.out.println(hikeName);
            }
            return hikesNames;
        } catch(JSONException e) {
            System.out.println("A JSONException has occured in getHikesNames()." + e);
            return hikesNames;
        }

    }
}