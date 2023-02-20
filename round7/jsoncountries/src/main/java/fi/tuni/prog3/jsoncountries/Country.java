package fi.tuni.prog3.jsoncountries;

public class Country implements Comparable<Country>
{
    private String name;
    private double area;
    private long population;
    private double gdp;

    public Country(String name, double area, long population, double gdp) {
        this.name = name;
        this.area = area;
        this.population = population;
        this.gdp = gdp;
    }

    @Override
    public int compareTo(Country c) {
        return this.name.compareTo(c.name);
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
        return "";
    }

    public double getArea() {
        return 0.0;
    }
    
    public long getPopulation() {
        return 0;
    }

    public double getGdp() {
        return 0.0;
    }

    public static void main(String[] args) 
    {
        System.out.println("Hello World!");
    }
}
