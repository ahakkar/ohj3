package round4.studentregister;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyStudentRegisterTest {
    private StudentRegister r;

    private void generateThreeStudents() {
        Student student1 = new Student("Matti Meikäläinen", "123456");
        Student student2 = new Student("Maija Meikäläinen", "00000");
        Student student3 = new Student("Timo", "987654");
        r.addStudent(student1);
        r.addStudent(student2);
        r.addStudent(student3);
    }

    private void generateThreeCourses() {
        Course course1 = new Course("COMP.CS.140", "Ohjelmointi 3", 5);
        Course course2 = new Course("COMP.CS.120", "Turha kurssi", 2);
        Course course3 = new Course("COMP.CS.110", "Kiva kurssi", 4);
        r.addCourse(course1);
        r.addCourse(course2);
        r.addCourse(course3);
    }

    // private void generateThreeCoursesAndStudents() {
    // generateThreeCourses();
    // generateThreeStudents();
    // }

    public String reduceStudentList(ArrayList<Student> list) {
        String result = "";
        for (Student student : list) {
            result += student.getName() + "; ";
            result += student.getStudentNumber() + "\n";
        }
        return result;

    }

    public String reduceCourseList(ArrayList<Course> list) {
        String result = "";
        for (Course course : list) {
            result += course.getCode() + "; ";
            result += course.getName() + "; ";
            result += course.getCredits() + "\n";
        }
        return result;
    }

    @BeforeEach
    void setup() {
        StudentRegister register = new StudentRegister();
        r = register;
    }

    @Test
    void testGetStudentsEmpty() {
        ArrayList<Student> list = new ArrayList<>();
        assertEquals(r.getStudents(), list);
    }

    @Test
    void testGetStudentsOne() {
        ArrayList<Student> list = new ArrayList<>();
        assertEquals(r.getStudents(), list);
    }

    @Test
    void testGetStudentsThreeForOrder() {
        generateThreeStudents();

        Student student1 = new Student("Matti Meikäläinen", "123456");
        Student student2 = new Student("Maija Meikäläinen", "00000");
        Student student3 = new Student("Timo", "987654");
        ArrayList<Student> list = new ArrayList<>();

        // Lisätään aakkosjärjestyksessä
        list.add(student2);
        list.add(student1);
        list.add(student3);

        String reducedR = reduceStudentList(r.getStudents());
        String reducedList = reduceStudentList(list);

        assertEquals(reducedR, reducedList);
    }

    @Test
    void testGetCoursesEmpty() {
        ArrayList<Course> list = new ArrayList<>();
        assertEquals(r.getCourses(), list);
    }

    @Test
    void testGetCoursesThreeForOrder() {
        generateThreeCourses();

        Course course1 = new Course("COMP.CS.140", "Ohjelmointi 3", 5);
        Course course2 = new Course("COMP.CS.120", "Turha kurssi", 2);
        Course course3 = new Course("COMP.CS.110", "Kiva kurssi", 4);
        ArrayList<Course> list = new ArrayList<>();

        // Lisätään nimen mukaisessa aakkosjärjestyksessä
        list.add(course3);
        list.add(course1);
        list.add(course2);

        String reducedR = reduceCourseList(r.getCourses());
        String reducedList = reduceCourseList(list);

        assertEquals(reducedList, reducedR);
    }

    @Test
    void testAddStudentListGrowsInSize() {
        Integer startSize = r.getStudents().size();
        Student student = new Student("Matti Meikäläinen", "123456");
        r.addStudent(student);
        Integer endSize = r.getStudents().size();
        assertTrue(endSize - 1 == startSize);
    }

    @Test
    void testAddStudentCorrectNameAndNumber() {
        Student student = new Student("Matti Meikäläinen", "123456");
        r.addStudent(student);
        Student fromRegister = r.getStudents().get(0);
        assertEquals((fromRegister.getName() + fromRegister.getStudentNumber()), "Matti Meikäläinen123456");
    }

    @Test
    void testAddStudentMultiple() {
        Student student1 = new Student("Matti Meikäläinen", "123456");
        Student student2 = new Student("Maija Meikäläinen", "00000");
        Student student3 = new Student("Timo", "987654");
        r.addStudent(student1);
        r.addStudent(student2);
        r.addStudent(student3);
        assertEquals(r.getStudents().size(), 3);
    }

    @Test
    void testAddCourseListGrowsInSize() {
        Integer startSize = r.getCourses().size();
        Course course = new Course("COMP.CS.140", "Ohjelmointi 3", 5);
        r.addCourse(course);
        Integer endSize = r.getCourses().size();
        assertTrue(endSize - 1 == startSize);
    }

    @Test
    void testAddCourseCorrectInformationAdded() {
        Course course = new Course("COMP.CS.140", "Ohjelmointi 3", 5);
        r.addCourse(course);
        Course fromRegister = r.getCourses().get(0);
        String sFromRegister = fromRegister.getCode() + fromRegister.getName() + fromRegister.getCredits();
        assertEquals(sFromRegister, "COMP.CS.140Ohjelmointi 35");
    }

    @Test
    void testAddCourseMultiple() {
        Course course1 = new Course("COMP.CS.140", "Ohjelmointi 3", 5);
        Course course2 = new Course("COMP.CS.120", "Turha kurssi", 2);
        Course course3 = new Course("COMP.CS.110", "Kiva kurssi", 4);
        r.addCourse(course1);
        r.addCourse(course2);
        r.addCourse(course3);
        assertEquals(r.getCourses().size(), 3);
    }

    @Test
    void testExampleOutput() {
        // Hae oikeat tiedostopolut
        URL studentsURL = getClass().getResource("students.txt");
        File studentsFile = new File(studentsURL.getFile());
        String studentsPath = studentsFile.getAbsolutePath();

        URL coursesURL = getClass().getResource("courses.txt");
        File coursesFile = new File(coursesURL.getFile());
        String coursesPath = coursesFile.getAbsolutePath();

        URL attainmentsURL = getClass().getResource("attainments.txt");
        File attainmentsFile = new File(attainmentsURL.getFile());
        String attainmentsPath = attainmentsFile.getAbsolutePath();

        String[] args = { studentsPath, coursesPath, attainmentsPath };

        // Aloita streamin lukeminen
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // Aloita koulun laatima testi
        try {
            StudentRegisterTest.main(args);
        } catch (IOException e) {
            fail("Testiä ei voitu suorittaa.");
        }
        // Koulun testi loppuu

        URL outputURL = getClass().getResource("output.txt");
        File outputFile = new File(outputURL.getFile());
        String outputPath = outputFile.getAbsolutePath();

        String correct_answer = "";

        try {
            correct_answer = Files.readString(Paths.get(outputPath));
        } catch (IOException e) {
            fail("Mallitiedostoa ei voitu lukea.");
        }

        correct_answer = correct_answer.replace("\r\n", "\n");
        var tested_answer = outContent.toString().replace("\r\n", "\n");

        assertEquals(correct_answer, tested_answer);
    }

}
