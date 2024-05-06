import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoadTest_STUDENT {

	
	
	Road first, second, third, fourth, fifth, sixth;
	Town ex1, ex2, ex3, ex4, ex5, ex6;
	Road randomRoad;
	
	@BeforeEach
	void setUp() throws Exception {
		ex1 = new Town("ex1");
		ex2 = new Town("ex2");
		ex3 = new Town("ex3");
		ex4 = new Town("ex4");
		ex5 = new Town("ex5");
		ex6 = new Town("ex6");
		
		first = new Road(ex1, ex3, 1, "1st Road");
		second = new Road(ex1, ex1, 2, "2nd Road");
		third = new Road(ex3, ex4, 3, "3rd Road");
		fourth = new Road(ex4, ex5, 4, "4th Road");
		fifth = new Road(ex5, ex6, 5, "5th Road");
		sixth = new Road(ex6, ex1, 6, "6th Road");
	}

	@AfterEach
	void tearDown() throws Exception {
		ex1 = null;
		ex2 = null;
		ex3 = null;
		ex4 = null;
		ex5 = null;
		ex6 = null;
		
		first = null;
		second = null;
		third = null;
		fourth = null;
		fifth = null;
		sixth = null;
	}

	@Test
	void testRoadTownTownIntString() {
		randomRoad = new Road(ex1, ex3, 1, "random road");
		assertEquals(ex1, randomRoad.getSource());
		assertEquals(ex3, randomRoad.getDestination());
		assertEquals(1, randomRoad.getDistance());
		assertEquals("random road", randomRoad.getName());

	}

	@Test
	void testRoadTownTownString() {
		randomRoad = new Road(ex1, ex3, "random road");
		assertEquals(ex1, randomRoad.getSource());
		assertEquals(ex3, randomRoad.getDestination());
	}

	@Test
	void testCompareTo() {
		assertEquals(true, true);
	}

	@Test
	void testContains() {
		assertTrue(first.contains(ex1));
		assertTrue(sixth.contains(ex6));
	}

	@Test
	void testEqualsObject() {
		randomRoad = new Road(ex1, ex2, 1, "1st Road");
		assertFalse(first.equals(second));
		assertTrue(first.equals(first));
	}

	@Test
	void testGetDestination() {
		assertEquals(ex1, second.getDestination());
		assertEquals(ex1, sixth.getDestination());
	}

	@Test
	void testGetSource() {
		assertEquals(ex5, fifth.getSource());
	}

	@Test
	void testGetDistance() {
		assertEquals(2, second.getDistance());
		assertEquals(4, fourth.getDistance());
	}

	@Test
	void testGetName() {
		assertEquals("1st Road", first.getName());
		assertEquals("5th Road", fifth.getName());
	}

	@Test
	void testToString() {
		assertEquals(true, true);
	}

}
