import java.util.ArrayList;

public class Town implements Comparable <Town>{
	private String name;
	private ArrayList<Town> adjTown;
	
	public Town(String name) {
		this.name = name;
		adjTown = new ArrayList<Town>();
	}
	public void addAdjTown(Town adjTownIn) {
		adjTown.add(adjTownIn);
	}
	public int compareTo(Town otherTown) {
		if(otherTown.getName().equalsIgnoreCase(name)) {
			return 0;
		}
		else {
			return name.compareToIgnoreCase(otherTown.getName());
		}
	}
	public boolean equals(Object obj) {
		Town anotherTown = (Town)obj;
		
		if (obj == null) {
			return false;
		}
		if (name.equals(anotherTown.name)) {
			return true;
		}
		else {
			return false;
		}
	}
	public ArrayList<Town> getAdjacentTowns(){
		return adjTown;
	}
	public String getName() {
		return name;
	}
	public int hashCode() {
		return name.hashCode();
	}
}
