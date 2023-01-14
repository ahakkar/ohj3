/*
 * COMP.CS.140 Ohjelmointi 3
 * H3 T3 Parameters
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi
 */

package h3;

import java.util.Arrays;

public class Parameters {

    static int ROW_PADDING = 6;

    public static void main(String args[]) {

        int col0_width = Integer.toString(args.length+1).length() + 1;
        int col1_width = 0;
        int i = 1;

        for (var param : args) {
            if (param.length() > col1_width) {
                col1_width = param.length();
            }
        }

        Arrays.sort(args);

        // print the table
        // ps java's formatter just sucks compared to ANSI C
        System.out.println("#".repeat(col0_width + col1_width + ROW_PADDING));
        for (var param : args) {                  
            System.out.printf("#%" + col0_width + "d | %-" + col1_width +"s #\n", i, param);
            i++;
            // avoid printing extra dividers
            if (i <= args.length) {
                System.out.printf("#%s+%s#\n",
                            "-".repeat(col0_width+1),
                            "-".repeat(col1_width+2));
            }
        }
        System.out.println("#".repeat(col0_width + col1_width + ROW_PADDING));
    }    
}
