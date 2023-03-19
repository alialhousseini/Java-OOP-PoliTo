package jobOffers;

import java.util.ArrayList;
import java.util.Comparator;

public class Candidate {
	private String name;
	private ArrayList<String> skills = new ArrayList<>();
	private ArrayList<Skill> ratings = new ArrayList<>();
	
	public void addRating(Skill a) {
		if(this.skills.contains(a.getName()))
			ratings.add(a);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Skill> getRatings() {
		return ratings;
	}
	public void setRatings(ArrayList<Skill> ratings) {
		this.ratings = ratings;
	}
	public void setSkills(ArrayList<String> skills) {
		this.skills = skills;
	}
	public ArrayList<String> getSkills() {
		return skills;
	}

	public Candidate(String name) {
		this.name = name;
	}
	
	public void addSkill(String s) {
		if(!skills.contains(s))
			skills.add(s);
	}
	
	
}
