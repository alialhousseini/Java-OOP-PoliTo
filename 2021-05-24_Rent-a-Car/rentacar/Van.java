package rentacar;

public class Van extends Vehicle {
	
	private int limit;
	public Van(String manufacturer, String model, int year, String gear, String color, char category, int seats,int limit)
	{
		super(manufacturer,model,year,gear,color,category,seats);
		this.limit=limit;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	
}
