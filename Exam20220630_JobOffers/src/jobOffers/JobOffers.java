package jobOffers; 
import java.util.*;
import java.util.stream.Collectors;


public class JobOffers  {
	
	private int skillN=0;
	private ArrayList<String> skills_name = new ArrayList<>();
	private Map<String,Position> positions = new TreeMap<>();
	private Map<String,Candidate> candidates = new TreeMap<>();
	private Map<String,Consultant> consultants = new TreeMap<>();
	private ArrayList<Application> applications = new ArrayList<>();
	
//R1
	public int addSkills (String... skills) {
		for(String s: skills) {
			if(!this.skills_name.contains(s)) {
				skillN++;
				skills_name.add(s);
			}
		}
		return skillN;
	}
	
	public int addPosition (String position, String... skillLevels) throws JOException {
		if(positions.containsKey(position))
			throw new JOException("Error");
		Position p = new Position(position);
		int average=0;
		int num=0;
		for(String sl:skillLevels) {
			String s[] = sl.split(":");
			String a = s[0];
			int b = Integer.parseInt(s[1]);
			if(!skills_name.contains(a))
				throw new JOException("Error");
			if(b<4 | b>8)
				throw new JOException("Error");
			average+=b;
			num++;
			Skill skill = new Skill(a,b);
			p.addSkill(skill);
		}
		positions.put(position, p);
		return average/num;
	}
	
//R2	
	public int addCandidate (String name, String... skills) throws JOException {
		int count=0;
		
		if(candidates.containsKey(name))
			throw new JOException("error");
		
		Candidate c = new Candidate(name);
		for(String s:skills) {
			if(!skills_name.contains(s))
				throw new JOException("Error");
			c.addSkill(s);
			count++;
		}
		this.candidates.put(name, c);
		return count;
	}
	
	public List<String> addApplications (String candidate, String... positions) throws JOException {
		List<Application> to_print = new ArrayList<>();
		
		if(!candidates.containsKey(candidate))
			throw new JOException("sdsd");
		for(String s:positions)
			if(!this.positions.containsKey(s))
				throw new JOException("sdasd");
		Candidate c = candidates.get(candidate);
		for(String s:positions) {
			Position p = this.positions.get(s);
			for(String sk: p.getSkills_str())
				if(!c.getSkills().contains(sk))
					throw new JOException("dsffsfd");
			Application a = new Application(c,p);
			p.addCandidate(c);
			this.applications.add(a);
			to_print.add(a);
		}
		to_print.sort(null);
		return to_print.stream().map(v->v.toString()).collect(Collectors.toList());
	} 
	
	public TreeMap<String, List<String>> getCandidatesForPositions() {
		TreeMap<String, List<String>> m2 = new TreeMap<>();
		Map<String, List<String>> m1 = positions.values().stream().filter(p->p.getCandidates().size()!=0).sorted(Comparator.comparing(Position::getRole)).collect(Collectors.toMap(Position::getRole, Position::getCandidates_str));
		for(Map.Entry<String, List<String>> x : m1.entrySet())
			m2.put(x.getKey(), x.getValue());
		return m2;
	}
	
	
//R3
	public int addConsultant (String name, String... skills) throws JOException {
		int SN=0;
		if(consultants.containsKey(name))
			throw new JOException("abcd");
		for(String s:skills)
			if(!this.skills_name.contains(s))
				throw new JOException("sda");
		Consultant c= new Consultant(name);
		for(String s:skills)
			if(!c.getSkills().contains(s)) {
				SN++;
				c.addSkill(s);
			}
		consultants.put(name, c);
		return SN;
	}
	
	public Integer addRatings (String consultant, String candidate, String... skillRatings)  throws JOException {

		int average=0;
		int num=0;
		if(!candidates.containsKey(candidate))
			throw new JOException("error");
		if(!consultants.containsKey(consultant))
			throw new JOException("error");
		Candidate ca = candidates.get(candidate);
		Consultant co = consultants.get(consultant);
		
		for(String sk:ca.getSkills())
			if(!co.getSkills().contains(sk))
				throw new JOException("error");
		
		for(String s: skillRatings) {
			String sp[] = s.split(":");
			String a = sp[0];
			int b = Integer.parseInt(sp[1]);
			if(!ca.getSkills().contains(a) | !co.getSkills().contains(a))
				throw new JOException("dsdasd");
			if(b<4 | b>10)
				throw new JOException("dassdda");
			Skill rate = new Skill(a,b);
			ca.addRating(rate);
			average+=b;
			num++;
			
		}
		return average/num;
	}
	
//R4
	public List<String> discardApplications() {
		List<String> to_print = new ArrayList<>();
		this.applications.sort(null);
		to_print = this.applications.stream().filter(a->a.is_eligible()==false).map(a->a.toString()).collect(Collectors.toList());
		return to_print;
	}
	
	 
	public List<String> getEligibleCandidates(String position) {
		List<String> to_print = new ArrayList<>();
		List<String> to_discard;
		if(!positions.containsKey(position))
			return to_print;
		if(applications.stream().map(a->a.getPosition().getRole().equals(position)).count()==0)
			return to_print;
		to_discard = this.discardApplications();
		List<String> finalTo_discard = to_discard;
		to_print = applications.stream().filter(a->a.getPosition().getRole().equals(position))
				.filter(a->finalTo_discard.contains(a.toString())==false)
				.map(a->a.getCandidate().getName())
				.sorted()
				.collect(Collectors.toList());
		return to_print;
	}
	

	
}

		
