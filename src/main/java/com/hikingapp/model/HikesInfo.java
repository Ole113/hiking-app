package com.hikingapp.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

/**
 * Gets the information from the API by first converting the address, postal code, city, and state into longitude and latitude.
 * Next the longitude and latitude is queried into the hiking API to get the hiking information.
 */
public class HikesInfo {
    
    private final String ADDRESS;
    private final String POSTAL_CODE;
    private final String CITY;
    private final String STATE;
    
    /**
     * Sets the instance variables with the user's input.
     * @param address The address the user input.
     * @param postalCode The postal code the user input.
     * @param city The city the user input.
     * @param state The state the user input.
     */
    public HikesInfo(String address, String postalCode, String city, String state) {
        this.ADDRESS = address;
        this.POSTAL_CODE = postalCode;
        this.CITY = city;
        this.STATE = state;
    }

    /**
     * Gets the position of the hikes using the longitude and latitude from the users address input.
     * @return The string that is returned from the API.
     * @throws JSONException The getHikePosition() method throws this exception.
     */
    public String getHikeInfo() throws JSONException {
        String apiKey = "200955643-5409210a90c1c5739821d1efae87d2bb";
        String hikeInfo = getApiInfo("https://www.hikingproject.com/data/get-trails?lat=" + getHikePosition()[0] + "&lon=" + getHikePosition()[1] + "&maxResults=5&key=" + apiKey);
        return hikeInfo;
    }
    
    /**
     * Finds the latitude and longitude from the getApiInfo method call result.
     * @return A double array where [0] is the latitude and [1] is the longitude.
     * @throws JSONException Exception that is thrown by JSONObject's.
     */
    private double[] getHikePosition() throws JSONException {
        String apiKey = "PDjIk1CuAfcFT0Bcr2w4Ep9uPRMH5T89";
        String hikePosition = getApiInfo("http://open.mapquestapi.com/geocoding/v1/address?key=" + apiKey + "&location=" + this.ADDRESS.replace(" ", "+") + "," + this.STATE + "," + this.CITY + "," + this.POSTAL_CODE);
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
     * @param url The URL of the API to get the info from.
     * @return A string of the API result.
     */
    private String getApiInfo(String url)  {
        try {
            URL apiUrl = new URL(url);
            BufferedReader in = new BufferedReader(
            new InputStreamReader(apiUrl.openStream()));

            String output = "";
            String inputLine;
            while ((inputLine = in.readLine()) != null) output += inputLine;
            in.close();
            return output;
        } catch(IOException e) {
            return "An error occurred in HikesFrame.getApiInfo() " + e;
        }
    }
    
    /**
     * Gets the closest five hikes that are returned by the hike API result.
     * @return A String array of the five hikes.
     */
    public String[] getHikesNames() {
        String[] hikesNames = new String[5];
        
        try {
            JSONObject hikeApiResult = new JSONObject(getHikeInfo());
            JSONArray hikesArray = hikeApiResult.getJSONArray("trails");
            
            for (int i = 0; i < hikesArray.length(); i++) {
                String hikeName = hikesArray.getJSONObject(i).getString("name");
                hikesNames[i] = hikeName;
            }
            
            return hikesNames;
        } catch(JSONException e) {
            System.out.println("A JSONException has occured in getHikesNames()." + e);
            return hikesNames;
        }
    }
    
    /**
     * Puts all the hike information into a HashMap<String, String> so all the hike information can easily be accessed by other classes.
     * @param chosenHike The name of the hike that was chosen.
     * @return The HashMap<String, String> that has all the hike information.
     */
    public HashMap<String, String> getChosenHikeInfo(String chosenHike) {
        HashMap<String, String> hikeInfo = new HashMap<>();

        try {
            JSONObject hikeApiResult = new JSONObject(getHikeInfo());
            JSONArray hikesArray = hikeApiResult.getJSONArray("trails");
            
            for (int i = 0; i < hikesArray.length(); i++) {
                JSONObject hike = hikesArray.getJSONObject(i);
                if(hike.getString("name").equals(chosenHike)) {
                    hikeInfo.put("name", hike.getString("name"));
                    hikeInfo.put("summary", hike.getString("summary"));
                    hikeInfo.put("difficulty", hike.getString("difficulty"));
                    hikeInfo.put("stars", hike.getString("stars"));
                    hikeInfo.put("starVotes", hike.getString("starVotes"));
                    hikeInfo.put("location", hike.getString("location"));
                    hikeInfo.put("imgUrl", hike.getString("imgMedium"));
                    hikeInfo.put("length", hike.getString("length"));
                    hikeInfo.put("ascent", hike.getString("ascent"));
                    hikeInfo.put("descent", hike.getString("descent"));
                    hikeInfo.put("high", hike.getString("high"));
                    hikeInfo.put("low", hike.getString("low"));
                    hikeInfo.put("conditionStatus", hike.getString("conditionStatus"));
                    hikeInfo.put("conditionDetails", hike.getString("conditionDetails"));
                    hikeInfo.put("conditionDate", hike.getString("conditionDate"));
                    hikeInfo.put("url", hike.getString("url"));
                }
            }
            
            return hikeInfo;
        } catch(JSONException e) {
            System.out.println("A JSONException has occured in getChosenHikeInfo(..)" + e);
            return hikeInfo;
        }
    }
}