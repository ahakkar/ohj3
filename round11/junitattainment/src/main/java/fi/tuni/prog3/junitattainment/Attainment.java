package fi.tuni.prog3.junitattainment;

import java.util.Comparator;

/*
 * COMP.CS.140 Ohjelmointi 3
 * H6 StudentRegister evolved
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
                result = a2.getGrade() - a1.getGrade();
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

    public Attainment(String courseCode, String studentNumber, Integer grade) throws IllegalArgumentException{
        if (courseCode == null || studentNumber == null || grade == null) {
            throw new IllegalArgumentException("Null values not allowed");
        } else if (grade < 0 || grade > 5) {
            throw new IllegalArgumentException("Grade must be between 0 and 5");
        }
        this.courseCode = courseCode;
        this.studentNumber = studentNumber;
        this.grade = grade;
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