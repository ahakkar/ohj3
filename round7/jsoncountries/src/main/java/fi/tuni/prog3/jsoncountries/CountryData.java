package fi.tuni.prog3.jsoncountries;


import java.io.FileReader;

import java.io.Reader;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class CountryData {

    // accepts variable amount of json files as strings
    public static List<Country> readFromJsons(String... files) {

        List<Country> info = new ArrayList<Country>();
        HashMap<String, JsonObject> json = new HashMap<String, JsonObject>();
        Gson gson = new Gson();        

        // read json files to hashmap as JsonObjects
        for (var filename : files) { 
            try {
                Reader reader = new FileReader(filename);
                JsonReader jsonReader = new JsonReader(reader);        
                JsonObject obj = gson.fromJson(jsonReader, JsonObject.class);    
                
                json.put(filename, obj);
                jsonReader.close();    
            } 
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }       

        var country = new Country("ok", 1.0, 1, 1.0);

        info.add(country);

        return info;
    }

    public static void writeToJson(
        List<Country> countries, 
        String countryFile)
        {
    }
  
    public static void main(String[] args) {
        readFromJsons("area1.json", "population1.json", "gdp1.json");
    }
}
