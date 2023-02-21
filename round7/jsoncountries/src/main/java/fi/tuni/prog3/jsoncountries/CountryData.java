package fi.tuni.prog3.jsoncountries;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class CountryData {

    private class CustomJsonparser {
        // store parsed json countries in a map, with a hashmap containing
        // area, gdp and population   
        private Map<String, Country> countries = new HashMap<String, Country>(); 

        // store data from multiple json files in a hashmap
        private HashMap<String, JsonObject> json = new HashMap<String, JsonObject>();

        private Gson gson;

        private CustomJsonparser(String... files) {
            this.gson = new Gson();
            this.json = actually_read_from_jsons(files, json, gson); 
            this.parseJsonData();
        }

        private static HashMap<String, JsonObject> actually_read_from_jsons(
            String[] files, 
            HashMap<String, JsonObject> json, 
            Gson gson) 
        {
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
    
            return json;
        }

        private void parseJsonData() {
            // iterate over the hashmap and get the data from the json files
            for (var key : json.keySet()) {
                // climb over a mountain of garbage indexes
                JsonObject obj = json.get(key);            
                JsonArray recordArray = obj.getAsJsonObject("Root")
                                        .getAsJsonObject("data")
                                        .getAsJsonArray("record");

                for (var rec : recordArray) {
                    // get the actual fields containing the data we want
                    JsonObject record = rec.getAsJsonObject();
                    JsonArray fieldArray = record.getAsJsonArray("field");

                    countries = populate_map(countries, fieldArray);
                }           
            }
        }

        private Map<String, Country> populate_map(Map<String, Country>countries, JsonArray fieldArray) {
            // create a country object with country name if it doesn't exist
            String country_name = fieldArray.get(0).getAsJsonObject().get("value").getAsString();                
    
            if (!countries.containsKey(country_name)) {
                Country country = new Country(country_name);
                countries.put(country_name, country);
            }   
    
            // get key, value pairs from the json file
            String country_data_key = fieldArray.get(1).getAsJsonObject().get("value").getAsString();
            String country_data_value = fieldArray.get(2).getAsJsonObject().get("value").getAsString();
    
            // set the data to the country object depending on key
            switch (country_data_key) {
                case "Surface area (sq. km)":
                    countries.get(country_name).setArea(Double.parseDouble(country_data_value));
                    break;
                case "Population, total":
                    countries.get(country_name).setPopulation(Long.parseLong(country_data_value));
                    break;
                case "GDP (constant 2015 US$)":
                    countries.get(country_name).setGdp(Double.parseDouble(country_data_value));
                    break;
                default:
                    break;
            }
    
            return countries;
        }
    }
    

    // accepts variable amount of json files as strings
    public static List<Country> readFromJsons(String... files) {
        // collect and return the complete list of countries to a list
        List<Country> info = new ArrayList<Country>(); 
        
        // use a separate parser class to untangle the json files
        CountryData data = new CountryData();
        CustomJsonparser parser = data.new CustomJsonparser(files); 

        info = parser.countries.values().stream().collect(Collectors.toList());       

        return info;
    }


    public static void writeToJson(
        List<Country> countries, 
        String countryFile)
    {
        // use prettyprinter to uhh add newlines and indentations
        Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .create();
        String json = gson.toJson(countries);

        try {
            FileWriter writer = new FileWriter(countryFile);
            writer.write(json);
            writer.close();

        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
