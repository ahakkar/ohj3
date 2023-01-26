/*
 * COMP.CS.140 Ohjelmointi 3
 * H4 StudentRegister
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

public class Course {

    private String code;
    private String name;
    private int credits;    

    public Course(String c, String n, int cr) {
        code = c;
        name = n;
        credits = cr;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
        }
}