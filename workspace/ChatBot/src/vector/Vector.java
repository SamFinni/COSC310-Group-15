package vector;

public class Vector{

	private double x, y, z;
	private final double PROX_DIST = 3;

	public Vector(double a, double b, double c) {
		this.x = a;
		this.y = b;
		this.z = c;
	}

	public double[] vectorAsArray(){
		double[] arr = { x, y, z };
		return arr;
	}

	public String toString(){
		return ("[" +x +" , " + y + " , " + z + "]");
	}
	
	public void setVectorAtIndex(int index, double value) {
		switch (index) {
		case 0:
			setX(value);
			break;
		case 1:
			setY(value);
			break;
		case 2:
			setZ(value);
			break;
		}
	}
	
	public Vector minus(Vector other){
		return new Vector(this.x - other.getX(), this.y - other.getY(), this.z - other.getZ());
	}
	
	//Temp Augmenting Formula: Move to the halfway point
	public void augmentBy(Vector other){
		this.x = (this.x + other.getX()) / 2;
		this.y = (this.y + other.getY()) / 2;
		this.z = (this.z + other.getZ()) / 2;	
	}
	
	public boolean checkProximity(Vector other){
		double dist = this.getDistance(other);
		if(dist <= PROX_DIST)
			return true;
		else
			return false;
	}
	
	public double getDistance(Vector other){
		return  Math.sqrt(
					Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2) + Math.pow(this.z - other.getZ(), 2)
					);
	}
	
	public void setX(double value) {
		this.x = value;
	}

	public void setY(double value) {
		this.y = value;
	}

	public void setZ(double value) {
		this.z = value;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public double getZ(){
		return this.z;
	}
}
