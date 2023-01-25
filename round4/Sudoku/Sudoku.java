/*
 * COMP.CS.140 Ohjelmointi 3
 * H3 T4 Currencies
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi
 * 
 * Tarkemmin sanoen tehtävänä on toteuttaa luokka Sudoku, jolla
 * on seuraavat julkiset ominaisuudet:

Parametriton rakennin Sudoku(), joka alustaa Sudoku-olion tallettamaan
9×9-kokoisen Sudoku-ruudukon, jonka kaikki ruudut ovat tyhjiä (esitetty
välilyöntimerkeillä ' '). Voit sinänsä vapaasti päättää, millaisessa muodossa
olio ylläpitää ruudukon sisällään. Mutta tässä tulee luonnollisesti huomioida 
alla kuvattujen jäsenfunktioiden ominaisuudet.

Jäsenfunktio set(int i, int j, char c): asettaa Sudoku-ruudukon rivin i
sarakkeen j ruutuun merkin c. Rivit numeroidaan 0…8 ja sarakkeet 0…8. Funktion
tule tarkistaa, että i ja j vastaavat laillista riviä ja saraketta. Ellei näin
ole, tulostetaan muotoa “Trying to access illegal cell (i, j)!” oleva viesti.

Sudoku-ruudukkoon saa tallettaa ainoastaan välilyöntejä ' ' (tarkoittaa tyhjää 
ruutua) sekä numeromerkkejä '1', '2', '3', '4', '5', '6', '7', '8' ja '9'. Jos
annettu merkki c on jokin muu merkki, ei sitä aseteta ja tulostetaan muotoa 
“Trying to set illegal character c to (i, j)!” oleva viesti.

Jäsenfunktio check(): tutkii, onko Sudoku-ruudukon nykyinen sisältö laillinen. 
Palauttaa true, jos on, ja muuten false.Sudoku on laillinen, ellei mikään rivi,
sarake tai 3x3-kokoinen alilohko sisällä kahta keskenään samaa numeromerkkiä
(tyhjiä ruutuja luonnollisesti saa olla monta). Tarkista ensin rivit, 
sitten sarakkeet, ja lopuksi 3x3 lohkot vasemmasta yläkulmasta riveittäin edeten.

Jos tarkituksen aikana löytyy kaksi samaa numeromerkkiä sisältävä rivi, sarake 
tai alilohko, otetaan talteen pienin numeromerkki c, joka esiintyi kaksi kertaa.
Tämä yksikäsitteisyyden vuoksi, jos useampikin eri numeromerkki sattuisi 
esiintymään kaksi kertaa. Tämän jälkeen tulostetaan jokin alla kuvatuista 
viesteistä ja tarkistus päättyy palauttaen arvon false. Tarkistus siis lopetetaan
heti, kun ensimmäinen laiton kohta löytyy.

    - Rivi: “Row i has multiple c's!”, missä i on rivin indeksi.
    - Sarake: “Column j has multiple c's!”, missä j on rivin indeksi.
    - Alilohko: “Block at (x, y) has multiple c's!”, missä x ja y ovat alilohkon
    vasemman yläkulman ruudun rivi ja sarake.

Jäsenfunktio print(): tulostaa Sudoku-ruudukon. Ruudukon rajat esitetään merkkien 
'#', '-', '|' ja '+' avulla ja ruudun arvon molemmin puolin on yksi välilyönti.
Katso tarkempi muoto tehtävän yhteydessä annetusta esimerkkitulosteesta.
*/

package round4.Sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Sudoku {

    private static final Integer SIZE = 9;
    private static final Integer BLOCK_WIDTH = 3;
    private static final Integer BLOCK_HEIGHT = 3;
    private static final Integer PADDING = 10;
    private static final String EMPTY_CELL = "   ";
    private static final String ROW_DIVIDER_1 = "#---+---+---#---+---+---#---+---+---#";
    private static final String ROW_DIVIDER_2 = "#".repeat(SIZE * BLOCK_WIDTH + PADDING);
    private static final ArrayList<String> CELL_COORDS = new ArrayList<String>(
        Arrays.asList("0, 0", "0, 3", "0, 6", "3, 0", "3, 3", "3, 6", "6, 0", "6, 3", "6, 6")
        );

    private ArrayList<Integer> data;

    /*
     * constructor: create a new list to store sudoku values
     */
    public Sudoku() {
        data = new ArrayList<Integer>(Collections.nCopies(SIZE*SIZE, 0));  
    }

    public void set(int i, int j, char c) {
        if (i < 0 || i >= SIZE || j < 0 || j >= SIZE) {
            System.out.printf(
                "Trying to access illegal cell (%s, %s)!\n", i, j
                );
        }
        else if (c != ' ' && (c < '1' || c > '9')) {
            System.out.printf(
                "Trying to set illegal character %s to (%d, %d)!\n", c, i, j
                );
        } else {
            if (c == (char) ' ') {
                data.set(i*SIZE + j, 0);
            } else {
                data.set(i*SIZE + j, Character.getNumericValue(c));
            }
        } 
    }

    /*
    * create sublist, sort asc, if next number != i+1, return false
    */
    private boolean check_list(String type, int num)
    {    
        boolean error = false;
        ArrayList<Integer> digits = new ArrayList<Integer>();

        // populate digits list depending on the type
        switch (type) {
            case "row":
                digits = new ArrayList<Integer>(data.subList(num*SIZE, num*SIZE+9));
                break;
            case "col":    
                for (int j = 0 ; j < SIZE; j++) {
                    digits.add(data.get(j*SIZE + num));
                }
                break;
            case "block":  
                for(int row = 0; row < BLOCK_HEIGHT; row++) {
                    for(int col = 0; col < BLOCK_WIDTH; col++) {
                        digits.add(data.get((row + (num / 3) * 3) * SIZE + col + (num % 3) * 3));
                    }
                }
                break;
            default:
                System.out.println("Invalid command.");
                return false;
        }

        // sort the digits list to ascending order
        Collections.sort(digits);

        // check through the list if there are any duplicates
        int i = 0;
        while (i < SIZE-1) {
            if ((digits.get(i) != 0)) {
                if (digits.get(i) == digits.get(i+1)) {
                    error = true;
                    break;
                }
            }
            i++;
        }  

        // in case of dupicates, print the error message and the location
        if (error) {
            switch (type) {
                case "row":
                    System.out.printf("Row %d has multiple %d's!\n", num, digits.get(i));
                    break;
                case "col":
                    System.out.printf("Column %d has multiple %d's!\n", num, digits.get(i));
                    break;
                case "block":  
                    System.out.printf("Block at (%s) has multiple %d's!\n", CELL_COORDS.get(num), digits.get(i));   
                    break;
    
                default:
                    System.out.println("Invalid command.");
                    break;
            }
            return false;
        }
        return true;
    }  

    // these must be checked in this absurd order, type by type
    public boolean check() {        
        for (int i = 0; i < SIZE; i++) {
            if (!check_list("row", i)) {
                return false;
            }
        }        
        for (int i = 0; i < SIZE; i++) {
            if (!check_list("col", i)) {
                return false;
            }
        }        
        for (int i = 0; i < SIZE; i++) {
            if (!check_list("block", i)) {
                return false;
            }
        }

        return true;
    }

    private void print_row_divider(Character type) {
        if (type.equals('|')) {
            System.out.println(ROW_DIVIDER_1);
            return;
        } 
        System.out.println(ROW_DIVIDER_2);
        
    }

    private void print_col_divider(int i) {
        if (i == 2 || i == 5) {
            print_row_divider('#');            
        }
        else if (i < SIZE-1) {
            print_row_divider('|');
        }
    }

    private void print_row(int i) {
        System.out.print("#");

        for (int j = 0; j < SIZE; j++)
        {     
            // print data           
            if(data.get(i*SIZE + j).equals(0)) {
                System.out.print(EMPTY_CELL);
            } else {
                System.out.printf(" %d ", data.get(i*SIZE + j));
            }  
            // print dividers 
            if (j == 2 || j ==  5 ) {
                System.out.print("#");
            } 
            else if (j < SIZE-1) {
                System.out.print("|");
            }                               
        }
        System.out.println("#");        
        print_col_divider(i);
    }

    public void print()
    {
        print_row_divider('#');
        for (int i = 0; i < SIZE; i++) {
            print_row(i);
        }
        print_row_divider('#');
    }
}
