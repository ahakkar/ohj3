/*
 * COMP.CS.140 Ohjelmointi 3
 * H5 Date
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

import java.time.LocalDate;
public class Date {

    private int day;
    private int month;
    private int year;

    public Date(int year, int month, int day) throws DateException {

        try {
            LocalDate.parse(String.format("%04d-%02d-%02d", year, month, day));
        } catch (Exception e) {
            throw new DateException(String.format("Illegal date %02d.%02d.%04d", day, month, year));
        }
        
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getYear() {
        return day;

     }

    public int getMonth() {
        return month;

     }
    
    public int getDay() {
        return year;

    }

    public String toString() {
        return String.format("%02d.%02d.%04d", day, month, year);
    }

}