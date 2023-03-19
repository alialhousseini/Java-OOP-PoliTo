package travelPortal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Proposal {
	private String code;
	private TravelAgency travelAgency;
	private String destination;
	private String period;
	private int minNP;
	private int maxNP;
	private int price;
	private ArrayList<Activity> activities = new ArrayList<>();
	private ArrayList<String> participants = new ArrayList<>();
	private ArrayList<Evaluation> evaluations = new ArrayList<>();
	
	
	public void addEvaluation(Evaluation e)
	{
		this.evaluations.add(e);
	}
	
	
	public ArrayList<Evaluation> getEvaluations() {
		return evaluations;
	}



	public boolean is_overlap(Proposal p)
	{
		if(this.getMonth()==p.getMonth())
			if(this.getDepartureDay()<p.getReturnDay() && p.getDepartureDay()<this.getReturnDay())
				return true;
		return false;
	}
	public ArrayList<String> getParticipants() {
		return participants;
	}

	public void setParticipants(ArrayList<String> participants) {
		this.participants = participants;
	}

	public void addParticipant(String name)
	{
		participants.add(name);
	}
	
	public int getAllPrice()
	{
		return this.totalPrice()+this.price;
	}
	public int totalPrice() {
		int sum=0;
		for(Activity a: this.activities)
			sum+=a.getPrice();
		return sum;
	}
	public void addActivity(Activity a) {
		activities.add(a);
	}
	
	public ArrayList<Activity> getActivities() {
		return activities;
	}

	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}

	public int getMonth() {
		String[] abc=period.split(":");
		int x = Integer.parseInt(abc[0]);
		return x;
	}
	
	public int getDepartureDay() {
		String[] abc=period.split(":");
		String[] def=abc[1].split("-");
		int x = Integer.parseInt(def[0]);
		return x;
	}
	
	public int getReturnDay() {
		String[] abc=period.split(":");
		String[] def=abc[1].split("-");
		int x = Integer.parseInt(def[1]);
		return x;
	}
	
	public String getCode() {
		return code;
	}
	public TravelAgency getTravelAgency() {
		return travelAgency;
	}
	public String getDestination() {
		return destination;
	}
	public String getPeriod() {
		return period;
	}
	public int getMinNP() {
		return minNP;
	}
	public int getMaxNP() {
		return maxNP;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setTravelAgency(TravelAgency travelAgency) {
		this.travelAgency = travelAgency;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public void setMinNP(int minNP) {
		this.minNP = minNP;
	}
	public void setMaxNP(int maxNP) {
		this.maxNP = maxNP;
	}
	public Proposal(String code, TravelAgency travelAgency, String destination, String period, int minNP, int maxNP,int price) {
		super();
		this.code = code;
		this.travelAgency = travelAgency;
		this.destination = destination;
		this.period = period;
		this.minNP = minNP;
		this.maxNP = maxNP;
		this.price=price;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	

}
