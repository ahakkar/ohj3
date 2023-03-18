package fi.tuni.prog3.junitattainment;

import java.util.Comparator;

public class Attainment implements Comparable<Attainment> {

    private String courseCode;
    private String studentNumber;
    private Integer grade;

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

    @Override
    public int compareTo(Attainment other) {
        int res = this.studentNumber.compareTo(other.studentNumber);
        if (res != 0) {
            return res;
        }
        else {
            return this.courseCode.compareTo(other.courseCode);
        }
    }

    public static final Comparator<Attainment> CODE_STUDENT_CMP = new Comparator<Attainment>() {
        @Override
        public int compare(Attainment a1, Attainment a2) {
            int res = a1.courseCode.compareTo(a2.courseCode);
            if (res != 0) {
                return res;
            } 
            else {
                return a1.studentNumber.compareTo(a2.studentNumber);
            }
        }
    };

    public static final Comparator<Attainment> CODE_GRADE_CMP = new Comparator<Attainment>() {
        @Override
        public int compare(Attainment a1, Attainment a2) {
            int res = a1.courseCode.compareTo(a2.courseCode);
            if (res != 0) {
                return res;
            } 
            else {
                return Integer.compare(a2.grade, a1.grade);
            }
        }
    };

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public Integer getGrade() {
        return grade;
    }

    public String toString() {
        return courseCode + ", " + studentNumber + ", " + grade;
    }
    
}
