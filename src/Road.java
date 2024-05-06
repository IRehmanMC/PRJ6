public class Road implements Comparable<Road> {
	private Town endpoint1;
	private Town endpoint2;
	private int weight;
	private String name;
	
	public Road(Town source, Town destination, int distance, String name)
	{
		endpoint1 = source;
		endpoint2 = destination;
		weight = distance;
		this.name = name;
	}
	public Road(Town source, Town destination, String name) {
		this(source,destination,1,name);
	}
	public boolean contains(Town town)
	{
		if (endpoint1.equals(town) || endpoint2.equals(town)) {
			return true;
		}
		return false;
	}
	public boolean equals(Object obj)
	{
		Road road = (Road) obj;
		return road.contains(endpoint1) && road.contains(endpoint2);
	}
	public Town getDestination() {
		return endpoint2;
	}
	public int getDistance() {
		return weight;
	}
	public String getName() {
		return name;
	}
	public Town getSource() {
		return endpoint1;
	}
	public int compareTo(Road r) {
		return name.compareToIgnoreCase(r.getName());
	}
}
