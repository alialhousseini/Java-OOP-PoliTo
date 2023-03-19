package jobOffers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Position {
	private String role;
	private ArrayList<Skill> skills = new ArrayList<>();
	private ArrayList<Candidate> candidates = new ArrayList<>();
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public ArrayList<Skill> getSkills() {
		return skills;
	}

	public List<Candidate> getCandidates() {
		return candidates.stream().sorted(Comparator.comparing(Candidate::getName)).collect(Collectors.toList());
	}
	
	public List<String> getCandidates_str(){
		return this.getCandidates().stream().map(s->s.getName()).collect(Collectors.toList());
	}
	public void setCandidates(ArrayList<Candidate> candidates) {
		this.candidates = candidates;
	}
	public Position(String role) {
		this.role = role;
	}
	
	public List<String> getSkills_str(){
		return this.skills.stream().map(v->v.getName()).collect(Collectors.toList());
	}
	public void addSkill(Skill s) {
		if(!skills.stream().map(v->v.getName()).collect(Collectors.toList()).contains(s.getName()))
			skills.add(s);
	}
	
	public void addCandidate(Candidate c) {
		
		candidates.add(c);
	}
	
	
	

}
