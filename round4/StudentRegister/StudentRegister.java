/*
 * COMP.CS.140 Ohjelmointi 3
 * H4 StudentRegister
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

package round4.StudentRegister;

import java.util.ArrayList;

public class StudentRegister {

    private StudentRegister sr;
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Course> courses = new ArrayList<Course>();
    private ArrayList<Attainment> attainments = new ArrayList<Attainment>();

    public StudentRegister() {
        sr = new StudentRegister();
    }

    public void addAttainment(Attainment attainment) {
        attainments.add(attainment);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
    
    public ArrayList<Student> getStudents() {
        return students;
    }

    public void printStudentAttainments(String sn) {

    }

    public void printStudentAttainments(String sn, String order) {

    }





    
}


