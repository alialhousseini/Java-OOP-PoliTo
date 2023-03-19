package jobOffers;

public class Application implements Comparable<Application>{
	private Candidate candidate;
	private Position position;
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Application(Candidate candidate, Position position) {
		this.candidate = candidate;
		this.position = position;
	}
	
	public String toString() {
		return this.candidate.getName()+":"+this.position.getRole();
	}
	@Override
	public int compareTo(Application o) {
		if(this.candidate.getName().equals(o.getCandidate().getName()))
			return this.position.getRole().compareTo(o.getPosition().getRole());
		else
			return this.candidate.getName().compareTo(o.getCandidate().getName());
	}
	
	public boolean is_eligible()
	{
		int i=0;
		if(this.candidate.getRatings().size()<this.position.getSkills().size())
			return false;

		for(Skill sp:this.position.getSkills()) {
			for(Skill sc: this.candidate.getRatings()) {
				if(sp.getName().equals(sc.getName()))
					if(sc.getLevel()>=sp.getLevel())
						i++;
			}
		}
		if(i==position.getSkills().size())
			return true;
		return false;
	}
}
