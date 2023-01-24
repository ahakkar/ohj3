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

package h4.Sudoku;

import java.util.ArrayList;
import java.util.Collections;

public class Sudoku {

    Integer ROWS = 9;
    Integer COLS = 9;
    Integer CELL_WIDTH = 3;
    Integer CELL_HEIGHT = 3;
    Integer PADDING = 10;
    String EMPTY_CELL = "   ";
    String ROW_DIVIDER_1 = "#---+---+---#---+---+---#---+---+---#";
    String ROW_DIVIDER_2 = "#".repeat(COLS * CELL_WIDTH + PADDING);

    ArrayList<Integer> data;

    /*
     * constructor: create a new list to store sudoku values
     */
    public Sudoku() {
        data = new ArrayList<Integer>(Collections.nCopies(ROWS*COLS, 0));  
    }

    public void set(int i, int j, char c) {
        if ((0 <= i && i < ROWS) && (0 <= j && j < COLS)) {
            System.out.println("jees");
        } else {
            System.out.printf("Trying to access illegal cell (%s, %s)!\n", i, j);
        }

    }

    public boolean check() {
        boolean result = true;

        return result;
    }

    private void print_row_divider(Character type) {
        if (type.equals('|')) {
            System.out.println(ROW_DIVIDER_1);
        } else {
            System.out.println(ROW_DIVIDER_2);
        }
    }

    private void print_col_divider(int i) {
        if (i == 2 || i == 5) {
            print_row_divider('#');
        }
        else if (i < ROWS-1) {
            print_row_divider('|');
        }
    }

    private void print_row(int i) {
        System.out.print("#");

        for (int j = 0; j < COLS; j++)
        {     
            // print data           
            if(data.get(i*COLS + j).equals(0)) {
                System.out.print(EMPTY_CELL);
            } else {
                System.out.printf("%" + CELL_WIDTH + "d", data.get(i*COLS + j));
            }  
            // print dividers 
            if (j == 2 || j ==  5 ) {
                System.out.print("#");
            } 
            else if (j < COLS-1) {
                System.out.print("|");
            }
                               
        }
        System.out.println("#");
        
        print_col_divider(i);
    }

    public void print()
    {
        print_row_divider('#');

        for (int i = 0; i < ROWS; i++) {
            print_row(i);
        }

        print_row_divider('#');
    }
    public static void main(String args[])
    {
        Sudoku su = new Sudoku();
        su.print();
        su.set(5,4, '3');
        su.set(-3, 25, '5');
    }
}
