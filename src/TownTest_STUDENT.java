import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TownTest_STUDENT {
	Town town1, town2;
    @BeforeEach
    void setUp() throws Exception {
    	town1 = new Town("Town1");
    	town2 = new Town("Town2");
    }

    @AfterEach
    void tearDown() throws Exception {
    	town1 = null;
    	town2 = null;
    }

    @Test
    void testGetName() {
    	assertEquals("Town1", town1.getName());
    	assertEquals("Town2", town2.getName());
    }

    @Test
    void testAddAdjTown() {
    	town1.addAdjTown(town2);
    	assertEquals("Town2", town1.getAdjacentTowns().get(0).getName());
    }

    @Test
    void testGetAdjTowns() {
    	town1.addAdjTown(town2);
    	assertEquals("Town2", town1.getAdjacentTowns().get(0).getName());
    }

    @Test
    void testEqualsTowns() {
    	assertEquals(false, town1.equals(town2));
    }

}
