import java.util.Collections;
import java.util.List;
import fi.tuni.prog3.jsoncountries.Country;
import fi.tuni.prog3.jsoncountries.CountryData;

public class CountryTest {
  public static void main(String[] args)
          throws Exception {
    String areaFile = args[0];
    String populationFile = args[1];
    String gdpFile = args[2];
    String countryFile = args[3];

/*     String areaFile = "area1.json";
    String populationFile = "population1.json";
    String gdpFile = "gdp1.json";
    String countryFile = "countries1.json"; */

    List<Country> countries = CountryData.readFromJsons(areaFile, populationFile, gdpFile);

    Collections.sort(countries);
    for(Country country : countries) {
        System.out.println(country);
    }

    CountryData.writeToJson(countries, countryFile);
  }

}
