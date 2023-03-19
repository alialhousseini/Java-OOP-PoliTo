package jobOffers;

import java.util.ArrayList;

public class Consultant {
	private String name;
	private ArrayList<String> skills = new ArrayList<>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getSkills() {
		return skills;
	}
	public void setSkills(ArrayList<String> skills) {
		this.skills = skills;
	}
	public Consultant(String name) {
		this.name = name;
	}
	
	public void addSkill(String s) {
		skills.add(s);
	}
	
	
}
