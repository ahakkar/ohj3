/*
 * COMP.CS.140 Ohjelmointi 3
 * H3 T4 Currencies
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi
 */

package h3;

import java.util.Scanner;
import java.util.TreeMap;

public class Currencies {

    TreeMap<String, Double> xc_rates = new TreeMap<String, Double>();
    Integer PRECISION = 3; // too lazy to actually use this

    private void rate(String currency, Double rate) {        
        xc_rates.put(currency, rate);
        System.out.printf("Stored the rate 1 EUR = %.3f %s\n", rate, currency);
    }
    private void rates() {
        System.out.println("Stored euro rates:");
        for (var set : xc_rates.entrySet()) {
            System.out.printf("  %s %.3f\n", set.getKey(), set.getValue());

        }
    }
    private void convert(Double amount, String currency) {
        if (xc_rates.containsKey(currency)) {
            System.out.printf("%.3f %s = %.3f EUR\n", amount, currency, amount*xc_rates.get(currency));
        } else {
            System.out.printf("No rate for %s has been stored!\n", currency);
        }
    }
    public static void main(String args[])
    {
        Currencies cur = new Currencies();
        Scanner input = new Scanner(System.in);        

        loop: while (true) {
            // input
            System.out.println("Enter command: ");  
            String user_input = input.nextLine();   
            String[] params = user_input.split(" ", 0);   
            String cmd = params[0];   
            
            switch (cmd) {
                // quit
                case "quit": 
                    System.out.println("Quit-command received, exiting...");
                    break loop;   
                    
                // add rate
                case "rate":
                    cur.rate(params[1].strip().toUpperCase(), Double.parseDouble(params[2]));
                    break;

                // print rates
                    case "rates":
                    cur.rates();
                    break;
                
                // convert currency
                case "convert":
                    cur.convert(Double.parseDouble(params[1]), params[2].strip().toUpperCase());
                    break;

                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }

        input.close();
    }  
}
