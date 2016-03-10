package justus.tsp.ui;

public class Point {

	int x, y;
	int id;
	Point nextPoint;
	String name;

	public Point(int x, int y, int id, Point nextPoint, String name) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.nextPoint = nextPoint;
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNextPoint(Point nextPoint) {
		this.nextPoint = nextPoint;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Point getNextPoint() {
		return nextPoint;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
	    String nextName = "null";
	    if(nextPoint != null){
	        nextName = nextPoint.getName();
	    }
	    return name + "(" + x + "/" + y + ")[" + id + "] -> " + nextName;  
	}

}
