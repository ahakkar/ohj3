/*
 * COMP.CS.140 Ohjelmointi 3
 * H4 StudentRegister
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

package round4.StudentRegister;

public class Student {

    private String name;
    private String studentNumber;


    public Student(String n, String sn) {
        name = n;
        studentNumber = sn;

    }

    public String getName() {
        return name;
    }

    public String getStudentNumber() {
        return studentNumber;
    }
}