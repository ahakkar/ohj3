package fi.tuni.prog3.jsoncountries;

public class Country implements Comparable<Country>
{
    private String name;
    private double area;
    private long population;
    private double gdp;

    public Country(String name) {
        this.name = name;
    }

    public Country(String name, double area, long population, double gdp) {
        this.name = name;
        this.area = area;
        this.population = population;
        this.gdp = gdp;
    }

    @Override
    public int compareTo(Country o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString(){
        // ensin maan nimi, ja sen alla kahdella välilyönnillä sisennettyinä 
        // omilla riveillään pinta-ala, väkiluku ja bruttokansantuote. 
        // Double-arvot yhden desimaalin tarkkuudella.
        String fmt = String.format(
            "%s\n  Area: %.1f km2\n  Population: %d\n  GDP: %.1f (2015 USD)\n",
            this.name, 
            this.area, 
            this.population, 
            this.gdp
            );

        return fmt;
    }

    public String getName() {
        return this.name;
    }

    public double getArea() {
        return this.area;
    }
    
    public long getPopulation() {
        return this.population;
    }

    public double getGdp() {
        return this.gdp;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public void setGdp(double gdp) {
        this.gdp = gdp;
    }
}
