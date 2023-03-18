package fi.tuni.prog3.junitattainment;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AttainmentTest {

    @BeforeAll
    public static void setUpClass() throws Exception {
    }


    @BeforeEach void setUp() throws Exception {        
    }


    @Test
    public void testGetters() {       
        Attainment att = new Attainment("COMP.CS.140", "230523906", 5); 
        Assertions.assertEquals("COMP.CS.140", att.getCourseCode());
        Assertions.assertEquals("230523906", att.getStudentNumber());
        Assertions.assertEquals(5, att.getGrade());
    }


    @Test
    public void testToString() {
        Attainment att = new Attainment("COMP.CS.140", "230523906", 5);
        Assertions.assertEquals("COMP.CS.140 230523906 5", att.toString());

    }


    @Test
    public void testCompareTo() {
        System.out.println("CompareTo");
        Attainment a1 = new Attainment("COMP.CS.140", "230523906", 5);
        Attainment a2 = new Attainment("COMP.CS.140", "230523907", 4);
        Attainment a3 = new Attainment("ACC.010", "230523906", 4);

        assertTrue(a1.compareTo(a2) < 0, "a1 should be less than a2");
        assertTrue(a1.compareTo(a3) > 0, "a1 should be greater than a3");
        assertTrue(a2.compareTo(a3) > 0, "a2 should be greater than a3");
        assertTrue(a1.compareTo(a1) == 0, "a1 should be equal to a1");
    }


    @Test
    public void testIllegalValues() {
        Assertions.assertThrows(IllegalArgumentException.class, ()->{ 
            new Attainment(null, "dadaa", 2);
        });

        Assertions.assertThrows(IllegalArgumentException.class,()->{ 
            new Attainment("Testi", null, 3);
        });

        Assertions.assertThrows(IllegalArgumentException.class, ()->{ 
            new Attainment("Testi", "195358417", 6);
        });

        Assertions.assertThrows(IllegalArgumentException.class, ()->{ 
            new Attainment("Testi", "195358417", -256);
        });

        Assertions.assertThrows(IllegalArgumentException.class, ()->{ 
            new Attainment("adaw","H299168", -24523);
        });   
    }
}