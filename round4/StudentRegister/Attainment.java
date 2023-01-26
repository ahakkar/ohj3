/*
 * COMP.CS.140 Ohjelmointi 3
 * H4 StudentRegister
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

package round4.studentregister;

public class Attainment {

    private String courseCode;
    private String studentNumber;
    private int grade;

    public Attainment(String cc, String sn, int g) {
        courseCode = cc;
        studentNumber = sn;
        grade = g;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getStudentNumber() {
        return studentNumber;
    }
    
    public int getGrade() {
        return grade;
    }
}