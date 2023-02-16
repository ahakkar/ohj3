import java.util.Comparator;

/*
 * COMP.CS.140 Ohjelmointi 3
 * H4 StudentRegister
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

public class Attainment implements Comparable<Attainment> {

    private String courseCode;
    private String studentNumber;
    private int grade;

    public static final Comparator<Attainment> CODE_STUDENT_CMP = new Comparator<Attainment>() {
        @Override
        public int compare(Attainment a1, Attainment a2) {
            int result = a1.getCourseCode().compareTo(a2.getCourseCode());
            if (result == 0) {
                result = a1.getStudentNumber().compareTo(a2.getStudentNumber());
            }
            return result;
        }
    };

    public static final Comparator<Attainment> CODE_GRADE_CMP = new Comparator<Attainment>() {
        @Override
        public int compare(Attainment a1, Attainment a2) {
            int result = a1.getCourseCode().compareTo(a2.getCourseCode());
            if (result == 0) {
                result = a1.getGrade() - a2.getGrade();
            }
            return result;
        }
    };

    @Override
    public int compareTo(Attainment o) {
        int result = studentNumber.compareTo(o.studentNumber);
        if(result == 0) {
            result = courseCode.compareTo(o.courseCode);
        }
        return result;
    }

    public String toString() {
        return String.format("%s %s %d", courseCode, studentNumber, grade);
    }

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