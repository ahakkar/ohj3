/*
 * COMP.CS.140 Ohjelmointi 3
 * H4 StudentRegister
 *
 * Antti Hakkarainen / K79735
 * antti.i.hakkarainen@tuni.fi 
 */

import java.util.ArrayList;

public class StudentRegister {

    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Course> courses = new ArrayList<Course>();
    private ArrayList<Attainment> attainments = new ArrayList<Attainment>();

    public StudentRegister() {}

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
        courses.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        return courses;
    }

    public ArrayList<Student> getStudents() {
        students.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        return students;        
    }   

    private Student find_student(String sn) {
        for(Student std : this.students) {
            if (std.getStudentNumber().equals(sn)) {
                return std;                
            }
        }
        return null;        
    }

    private String find_course_name(String cc) {
        for(Course cr : this.getCourses()) {
            if (cr.getCode().equals(cc)) {
                return cr.getName();
            }
        }
        return null;        
    }

    private ArrayList<String> find_attainments(String sn, String order)
    {
        ArrayList<Attainment> search = new ArrayList<Attainment>();
        ArrayList<String> result = new ArrayList<String>();
        for(Attainment att : this.attainments) {
            if (att.getStudentNumber().equals(sn)) {
                search.add(att);
            }
        }

        // sort the results to desired order or leave as is
        switch (order) {
            case "by name":
                search.sort(
                    (o1, o2) -> 
                    find_course_name(o1.getCourseCode())
                    .compareTo(find_course_name(o2.getCourseCode())) );
                break;
            case "by code":
                search.sort(
                    (o1, o2) ->
                    o1.getCourseCode()
                    .compareTo(o2.getCourseCode()) );
                break;
            case "null":
                break;
            default:
                System.out.printf("Error in find_attainments: unknown order: %s",
                                  order);
                break;           
        }

        for(Attainment att : search) {
            result.add(String.format("%s %s: %d",
                att.getCourseCode(),                 
                find_course_name(att.getCourseCode()),
                att.getGrade()
                ));            
        }   
        return result;
    }

    public void printStudentAttainments(String sn) {
        printStudentAttainments(sn, "null");
    }

    public void printStudentAttainments(String sn, String order) {
        Student result = find_student(sn);
        if (result == null) {
            System.out.printf("Unknown student number: %s\n", sn);           
            return;
        }

        System.out.printf("%s (%s):\n", result.getName(), result.getStudentNumber());
        for (String att : find_attainments(sn, order)) {
            System.out.printf("  %s\n", att);
        }
    }    
}