package travelPortal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class TravelAgency {
	private String name;
	private ArrayList<String> activitytypes = new ArrayList<>();
	
	public String getName() {
		return name;
	}

	public ArrayList<String> getActivitytypes() {
		return activitytypes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setActivitytypes(ArrayList<String> activitytypes) {
		this.activitytypes = activitytypes;
	}

	public void addActivityType(ActivityType a)
	{
		if(!activitytypes.contains(a.getName()))
			this.activitytypes.add(a.getName());
	}
	
	public void addActivityType(String a)
	{
		if(!activitytypes.contains(a))
			this.activitytypes.add(a);
	}

	public TravelAgency(String name) {
		this.name = name;
	}
	
	public int getNumAcitivityTypes()
	{
		return activitytypes.size();
	}
	
}
