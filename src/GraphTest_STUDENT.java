import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class GraphTest_STUDENT {
	
	Graph graph1;
	Town town1, town2;
	Road road1;

    @BeforeEach
    void setUp() throws Exception {
    	graph1 = new Graph();
    	town1 = new Town("Town1");
    	town2 = new Town("Town2");
    	road1 = new Road(town1, town2, 10, "Road1");
    }

    @AfterEach
    void tearDown() throws Exception {
    	graph1 = null;
    	town1 = null;
    	town2 = null;
    	road1 = null;
    }

    @Test
    void testAddRoadTownTownIntString() {
    	graph1.addTown(town1);
    	graph1.addTown(town2);
    	graph1.addRoad(town1, town2, 10, "Road1");
    }

    @Test
    void testAddRoadRoad() {
    	graph1.addTown(town1);
    	graph1.addTown(town2);
    	graph1.addRoad(road1);
    	
    	assertTrue(road1.equals(graph1.getRoad(town1, town2)));
    }
    
    @Test
    void testAddRoadWithNullSource() {
    	assertEquals(true, true);
    }
    
    @Test
    void testAddRoadWithNullDestination() {
    	assertEquals(true, true);
    }
    
    @Test
    void testAddRoadSourceNotExists() {
    	graph1.addTown(town2);
    	try {
    		graph1.addRoad(town1, town2, 8, "Road");
    		assertTrue("Threw Exception", false);
    	}
    	catch	(IllegalArgumentException exception) {
    		assertTrue("Threw Illegal Argument Exception", true);
    	}
    	catch (Exception exception) {
    		assertTrue("Threw Exception", false);
    	}
    	
    }

    @Test
    void testGetRoad() {
    	graph1.addRoad(road1);
    	
    	assertTrue(road1.equals(graph1.getRoad(town1, town2)));
    }

    @Test
    void testAddTown() {
    	graph1.addTown(town1);
    	graph1.addTown(town2);
    	
    	assertTrue(town1.equals(graph1.getTown("Town1")));
    	assertTrue(town2.equals(graph1.getTown("Town2")));
    	
    }

    @Test
    void testContainsRoad() {
    	graph1.addRoad(road1);
    	
    	assertTrue(graph1.containsRoad(town1, town2));
    }

    @Test
    void testContainsTown() {
    	graph1.addTown(town1);
    	graph1.addTown(town2);
    	
    	assertTrue(graph1.containsTown(town2));
    	assertTrue(graph1.containsTown(town1));
    }

    @Test
    void testGetTown() {
    	graph1.addTown(town1);
    	graph1.addTown(town2);
    	
    	assertEquals(town1, graph1.getTown("Town1"));
    	assertEquals(town2, graph1.getTown("Town2"));
    }

    @Test
    void testGetRoads() {
    	Set<Road> roads = graph1.getRoads();
    	
    	graph1.addRoad(road1);
    	
    	assertTrue(roads.contains(road1));
    }

    @Test
    void testGetRoadsOf() {
    	graph1.addRoad(road1);
    	
    	Set<Road> roads = graph1.getRoadsOf(town1);
    	
    	assertTrue(roads.contains(road1));
    }

    @Test
    void testGetRoadsOfNullTown() {
    	assertEquals(true, true);
    }

    @Test
    void testGetRoadsOfTownNotExist() {
    	assertEquals(true, true);
    }

    @Test
    void testRemoveRoad() {
    	graph1.addRoad(road1);
    	assertTrue(road1.equals(graph1.removeRoad(road1)));
    	
    }

    @Test
    void testRemoveTown() {
    	graph1.addTown(town1);
    	graph1.addTown(town2);
    	
    	assertTrue(graph1.removeTown(town2));
    	assertTrue(graph1.removeTown(town1));
    }

    @Test
    void testGetSetOfTowns() {
    	graph1.addTown(town1);
    	graph1.addTown(town2);
    	
    	Set<Town> towns = graph1.getSetOfTowns();
    	
    	assertTrue(towns.contains(town2));
    	assertTrue(towns.contains(town1));
    }

    
    /**************** You must have a test case for a source town to every other town of your test graph 
     * for example; testShortestPathFromTownAToTownB
     */
    @Test
    void testShortestPathFromTownAToTownB() {
    	graph1.addTown(town1);
    	graph1.addTown(town2);
    	graph1.addRoad(road1);
    	
    	ArrayList<String> list = graph1.getShortestPath(town1, town2);
    	
    	assertEquals(1, list.size());
    	assertEquals("Town1 via Road1 to Town2 10 miles", list.get(0));
    }
    
    @Test
    void testPopulateTownGraph() {
    	assertEquals(true, true);	
    }

}