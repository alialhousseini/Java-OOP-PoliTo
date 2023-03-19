package rentacar;

public class Reservation {
	
	private int uid;
	private char category;
	private int seats;
	private int duration;
	private Vehicle vehicle;
	public Reservation(int uid, char category, int seats, int duration,Vehicle v)
	{
		this.uid=uid;
		this.category=category;
		this.seats=seats;
		this.duration=duration;
		this.vehicle=v;
	}
	public Vehicle getVehicle() {
		return this.vehicle;
	}
	public int getUid() {
		return uid;
	}
	public char getCategory() {
		return category;
	}
	public int getSeats() {
		return seats;
	}
	public int getDuration() {
		return duration;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public void setCategory(char category) {
		this.category = category;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	
}
