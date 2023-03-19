package rentacar;

public class User {
	private int code=-1;
	private String name;
	private String city;
	private double points=0;
	private double average=0;
	
	public void setAverage(double avg) {
		this.average=avg;
	}
	
	public double getAverage() {
		return average;
	}

	public double getPoints() {
		return points;
	}

	public void addPoints(double newpoint) {
		this.points += newpoint;
	}

	public User(String name,String city)
	{
		this.name=name;
		this.city=city;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String toString()
	{
		return String.format("%d:%s",this.getCode(),this.getName());
	}
	
	

}
