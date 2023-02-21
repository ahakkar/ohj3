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

        for (var key : json.keySet()) {
            JsonObject obj = json.get(key);
            // does not work. how to get all fields?
            JsonArray recordArray = obj.getAsJsonObject("Root")
                                       .getAsJsonObject("data")
                                       .getAsJsonArray("record");

            for (var rec : recordArray) {
                System.out.println(rec);
            }

            JsonObject firstRecord = recordArray.get(0).getAsJsonObject();
            JsonArray fieldArray = firstRecord.getAsJsonArray("field");
                        
            for (var i = 0; i < fieldArray.size(); i++) {
                var result = fieldArray.get(i).getAsJsonObject().get("name");
                if (result != null) {
                    System.out.println(result.getAsString());
                }
   
/*                 JsonObject country = arr.get(i).getAsJsonObject();
                JsonObject attributes = country.getAsJsonObject("attributes");
                String name = attributes.get("name").getAsString();
                double area = attributes.get("area").getAsDouble();
                long population = attributes.get("population").getAsLong();
                double gdp = attributes.get("gdp").getAsDouble(); */
                //Country c = new Country(name, area, population, gdp);
                //info.add(c);
            }
        }

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
