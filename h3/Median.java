/*
 * COMP.CS.140 Ohjelmointi 3
 * H3 T2 Median
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi
 */
package h3;

import java.util.ArrayList;
import java.util.Collections;

public class Median {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {       
        ArrayList<Double> vals = new ArrayList<Double>();
        double median = 0.0;
        int i = 0;
        
        // add strings to double array
        for (var param : args) { 
            vals.add(i, Double.parseDouble(param));
            i++;
        }

        // sort array...
        Collections.sort(vals);

/*         i = 0;
        for (var param : vals) {                  
            System.out.printf("%d: %f\n", i, param);
            i++;
        } */

        // choose and print median
        if (vals.size() % 2 == 0) {
            median = (vals.get(vals.size()/2-1)
                     + vals.get(vals.size()/2))
                     / 2;
/*             System.out.printf("%f, %f\n", 
                              vals.get(vals.size()/2-1), vals.get(vals.size()/2)); */
        } else {
            median = vals.get(vals.size()/2);
        }

        System.out.print("Median: " + median + "\n");
    }
}