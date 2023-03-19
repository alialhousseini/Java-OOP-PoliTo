package travelPortal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ActivityType {
	private String name;
	private ArrayList<TravelAgency> travelagencies = new ArrayList<>();

	public void addTravelAgency(TravelAgency ta) {
		this.travelagencies.add(ta);
	}
	
	public List<String> getTravelagencies_string(){
		return travelagencies.stream().map(x->x.getName()).collect(Collectors.toList());
	}
	
	public ArrayList<TravelAgency> getTravelagencies() {
		return travelagencies;
	}

	public void setTravelagencies(ArrayList<TravelAgency> travelagencies) {
		this.travelagencies = travelagencies;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ActivityType(String name) {
		this.name = name;
	}
	
}
