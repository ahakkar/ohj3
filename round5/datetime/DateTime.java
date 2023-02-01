/*
 * COMP.CS.140 Ohjelmointi 3
 * H5 Date
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

public class DateTime extends Date {

    int hh;
    int min;
    int sec;

    public DateTime(int day, int month, int year, int hh, int min, int sec) throws DateException {
        super (day, month, year);

        if ((hh < 0 || hh > 23) || (min < 0 || min > 59) || (sec < 0 || sec > 59)) {
            throw new DateException(String.format("Illegal time %02d:%02d:%02d", hh, min, sec));
        } 

        this.hh = hh;
        this.min = min;
        this.sec = sec;
    }

    public int getHour() { 
        return hh;
    }
    public int getMinute() {
        return min;

    } 
    public int getSecond() {
        return sec;
    }

    public String toString() {
        return String.format("%s %02d:%02d:%02d", super.toString(), hh, min, sec);
    }
    
}
