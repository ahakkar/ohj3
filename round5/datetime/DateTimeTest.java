/*
 * COMP.CS.140 Ohjelmointi 3
 * H5 Date
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

import java.util.ArrayList;

public class DateTimeTest {
    public static void main(String args[]) {
        args = new String[] {"1998 01 24", "1999 02 03 17 60 17", "2010 01 20 6 55 12", "2012 07 28", "2021 02 02" };
        ArrayList<Date> dates = new ArrayList<>();
        for (String arg : args) {
            try {
                String[] parts = arg.split(" ");
                if (parts.length == 3) {
                    dates.add(new Date(
                        Integer.parseInt(parts[0]), 
                        Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2])
                        )
                    );
                } else if (parts.length == 6) {
                    dates.add(new DateTime(
                        Integer.parseInt(parts[0]),
                        Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[4]), 
                        Integer.parseInt(parts[5])));
                }
            } catch (DateException e) {
                System.out.println(e);
            }
        }
        for (int i = 0; i < dates.size(); i++) {
            System.out.format("Date #%d: %s%n", i + 1, dates.get(i));
        }
    }
}