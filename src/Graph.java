import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class Graph implements GraphInterface<Town, Road> {
	private Set<Town> towns;
	private Set<Road> roads;
	private Map<Town, Road> shortestMap;
	
	public Graph()
	{
		towns = new HashSet<Town>();
		roads = new HashSet<Road>();
	}
	public Road addRoad(Road road) {
        if (roads.contains(road)) {
            return null;
        }
		roads.add(road);
        towns.add(road.getSource());
        towns.add(road.getDestination());
		return road;
	}
	public Road addRoad(Town sourceTown, Town destinationTown, int distance, String name) {
		if (sourceTown == null || destinationTown == null) {
			throw new NullPointerException();
		}
		if (!towns.contains(sourceTown) || !towns.contains(destinationTown)) {
			throw new IllegalArgumentException();
		}
		Road road = new Road(sourceTown, destinationTown, distance, name);
		if (containsRoad(sourceTown, destinationTown)) {
			return null;
		}
		roads.add(road);
		return road;
	}
	public boolean addTown(Town town) {
		int size = towns.size();
		towns.add(town);
		return size != towns.size();
	}
	public boolean containsRoad(Town sourceTown, Town destinationTown) {
		for (Road road : roads)
		{
			if (road.equals(new Road(sourceTown, destinationTown, ""))) {
				return true;
			}
		}
		return false;
	}
	public boolean containsTown(Town town) {
        Iterator<Town> townIterator = towns.iterator();
        
        while (townIterator.hasNext()) {
            Town currentTown = townIterator.next();
            if (currentTown.equals(town)){
                return true;
            }
        }
        return false;
    }
	public void dijkstraShortestPath(Town sourceTown) {
		shortestMap = new HashMap<Town, Road>();
		
		for (Town t : towns)
		{
			shortestMap.put(t, new Road(sourceTown, t, Integer.MAX_VALUE, "example"));
		}
		
		Queue<Town> queue = new LinkedList<Town>();
		Set<Town> searched = new HashSet<Town>();
		shortestMap.put(sourceTown, new Road(sourceTown, sourceTown, 0, "reflexive"));
		queue.add(sourceTown);
		searched.add(sourceTown);

		while (queue.size() > 0)
		{
			Town cur = queue.remove();
			
			for (Road r : roads)
			{
				if (r.contains(cur))
				{
					Town source = cur;
					Town destination;

					if (r.getSource().equals(cur))
					{
						destination = r.getDestination();
					}
					else 
					{
						destination = r.getSource();
					}
					if (!searched.contains(destination))
					{
						queue.add(destination);
						searched.add(destination);
					}
					
					int upto = r.getDistance();
					Town temp = source;
					while (!temp.equals(sourceTown))
					{
						upto += shortestMap.get(temp).getDistance();
						temp = shortestMap.get(temp).getSource();
					}
					
					int prev = 0;
					temp = destination;
					while (!temp.equals(sourceTown))
					{
						prev += shortestMap.get(temp).getDistance();
						temp = shortestMap.get(temp).getSource();
					}
					if (upto < prev)
					{
						shortestMap.put(destination, new Road(source, destination, r.getDistance(), r.getName()));
					}
				}
			}
		}
	}
	public Road getRoad(Town sourceTown, Town destinationTown) {	
		for (Road road : roads)
		{
			if (road.contains(sourceTown) && road.contains(destinationTown)) {
				return new Road(sourceTown, destinationTown, road.getDistance(), road.getName());
			}
		}
		return null;
	}
	public Set<Road> getRoads() {
		return roads;
	}
	public Set<Road> getRoadsOf(Town town) {
		if (town == null) {
			throw new NullPointerException();
		}
		if (!towns.contains(town)) {
			throw new IllegalArgumentException();
		}
		Set<Road> adj = new HashSet<Road>();
		for (Road road : roads)
		{
			if (road.contains(town))
			{
				adj.add(road);
			}
		}
		return adj;
	}
	public Set<Town> getSetOfTowns() {
		return towns;
	}
	public ArrayList<String> getShortestPath(Town sourceTown, Town destinationTown) {
		dijkstraShortestPath(sourceTown);
		ArrayList<String> shortestArr = new ArrayList<String>();
		Town current = destinationTown;
		Road example;
		
		while (!current.equals(sourceTown))
		{
			example = shortestMap.get(current);
			shortestArr.add(0, example.getSource().getName() + " via " + example.getName() + " to " + current.getName() + " " + example.getDistance() + " miles");
			current = example.getSource();
		}
		return shortestArr;
	}
    public ArrayList<String> getSortedListOfRoads(){
        Iterator<Road> roadIterator = roads.iterator();
        ArrayList<String> list = new ArrayList<String>();

        while (roadIterator.hasNext()) {
            Road road = roadIterator.next();
            list.add(road.getName());
        }
        Collections.sort(list);
        return list;
    }
	public ArrayList<String> getSortedListOfTowns(){
        Iterator<Town> townIterator = towns.iterator();
        ArrayList<String> list = new ArrayList<String>();

        while (townIterator.hasNext()) {
            Town town = townIterator.next();
            list.add(town.getName());
        }
        Collections.sort(list);
        return list;
    }
    public Town getTown(String townName) {

        Iterator<Town> iterator = towns.iterator();

        while (iterator.hasNext()) {

            Town currentTown = iterator.next();
            if (currentTown.getName().equals(townName)){
                return currentTown;
            }

        }

        return null;

    }
    public void populateTownGraph(File file) throws FileNotFoundException {
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String words[] = line.split(";");
                String road[] = words[0].split(",");
                Town sourceTown = new Town(words[1]);
                Town destinationTown = new Town(words[2]);

                addTown(sourceTown);
                addTown(destinationTown);
                addRoad(sourceTown, destinationTown, Integer.parseInt(road[1]), road[0]);
            }
            scanner.close();
        }
        catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }
    public Road removeRoad(Road road) {

        if (roads.contains(road)) {
            roads.remove(road);
            return road;
        }
        else {
            return null;
        }
    }
    public Road removeRoad(Town sourceTown, Town destinationTown, int weight, String description) {
        return new Road(null, null, null);
    }
    public boolean removeTown(Town town) {
        Iterator<Town> townIterator = towns.iterator();
        Iterator<Road> roadIterator = roads.iterator();
        Town removedTown = new Town("");
        boolean EX = false;
        while (townIterator.hasNext()) {
            Town currentTown = townIterator.next();
            if (currentTown.equals(town)){
                townIterator.remove();
                removedTown = currentTown;
                EX = true;
                break;
            }
        }
        if (EX) {
            while (roadIterator.hasNext()) {
                Road currentRoad = roadIterator.next();
                if (currentRoad.getSource().equals(removedTown) || currentRoad.getDestination().equals(removedTown)) {
                    roadIterator.remove();
                }
            }
        }
        return EX;
    }
}
