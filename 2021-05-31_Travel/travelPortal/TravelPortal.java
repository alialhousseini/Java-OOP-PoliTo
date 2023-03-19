package travelPortal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;
public class TravelPortal {

	private Map<String,ActivityType> activityTypes = new TreeMap<>();
	private Map<String,TravelAgency> travelAgencies = new TreeMap<>();
	private Map<String,Proposal> proposals = new TreeMap<>();
	private ArrayList<Activity> activities = new ArrayList<>();
	public List<String> getActivityTypes()
	{
		return this.activityTypes.values().stream().map(a->a.getName()).collect(Collectors.toList());
	}
	//R1
	public List<String> addActivityTypes(String... names) {
		for(String s: names) {
			activityTypes.put(s,new ActivityType(s));
		}
		return this.getActivityTypes();
	}

	public int AddTravelAgency(String name, String... activityTypes) throws TPException {
		if(travelAgencies.containsKey(name))
			throw new TPException();
		TravelAgency ta = new TravelAgency(name);
		for(String s: activityTypes) {
			if(!this.activityTypes.containsKey(s))
				throw new TPException();
			this.activityTypes.get(s).addTravelAgency(ta);
			ta.addActivityType(s);
			
		}
		travelAgencies.put(name, ta);
		return ta.getNumAcitivityTypes();
	}

	public SortedMap<String, List<String>> getAgenciesForActivityTypes() {
		SortedMap<String, List<String>> afa  = new TreeMap<>();
		for(ActivityType ta: this.activityTypes.values().stream().sorted(Comparator.comparing(ActivityType::getName)).collect(Collectors.toList())) {
			afa.put(ta.getName(), ta.getTravelagencies().stream().map(x->x.getName()).sorted(Comparator.comparing(String::toString)).collect(Collectors.toList()));
		}
		return afa;
	}



//R2
	public int addProposal(String code, String agency, String destination, String period, int minNP, int maxNP,
			int price) throws TPException {
		if(proposals.containsKey(code))
			throw new TPException();
		if(!travelAgencies.containsKey(agency))
			throw new TPException();
		Proposal p = new Proposal(code,travelAgencies.get(agency),destination,period,minNP,maxNP,price);
		proposals.put(code, p);
		return p.getReturnDay()-p.getDepartureDay();
	}

	public int addActivity(String code, String activityType, String description, int price) throws TPException {
		if(!proposals.get(code).getTravelAgency().getActivitytypes().contains(activityType))
			throw new TPException();
		Activity a = new Activity(proposals.get(code),activityType,description,price);
		activities.add(a);
		proposals.get(code).addActivity(a);
		return a.getProposal().totalPrice();
	}

	public int getProposalPrice(String code) throws TPException {
		if(!proposals.containsKey(code))
			throw new TPException();
		return proposals.get(code).getAllPrice();
	}

//R3
	public List<String> addParticipants(String code, String... names) throws TPException {
		List<String> accepted = new ArrayList<>();
		if(!proposals.containsKey(code))
			throw new TPException();
		Proposal prop = proposals.get(code);
		for(String name:names) {
			List<Proposal> exist_name = proposals.values().stream().filter(p->p.getParticipants().contains(name)).filter(p->!p.getCode().equals(code)).collect(Collectors.toList());
			if(exist_name.size()==0)
				accepted.add(name);
			else {
				for(Proposal p: exist_name)
				{
					if(!p.is_overlap(prop))
						accepted.add(name);
				}
			}		
		}
		if(accepted.size()<prop.getMinNP() | accepted.size()>prop.getMaxNP())
			throw new TPException();
		for(String a:accepted)
			prop.addParticipant(a);
		return accepted;
	}

	public int getIncome(String code) {
		return proposals.get(code).getParticipants().size()*proposals.get(code).getAllPrice();
	}

//R4
	public String addRatings(String code, int... evaluations) throws TPException {
		int i=0;
		for(int e:evaluations)
			i++;
		if(i!=proposals.get(code).getParticipants().size())
			throw new TPException();
		StringBuilder sb = new StringBuilder();
		Proposal p = proposals.get(code);
		for(int j=0;j<p.getParticipants().size();j++) {
			Evaluation e = new Evaluation(p.getParticipants().get(j),evaluations[j]);
			p.addEvaluation(e);
		}
		int j=0;
		for(int k=0;k<p.getEvaluations().size()-1;k++)
		{
			sb.append(p.getEvaluations().get(k).toString()).append(", ");
			j++;
		}
		sb.append(p.getEvaluations().get(j).toString());
		return sb.toString();
	}

	public SortedMap<String, Integer> getTotalRatingsForParticipants() {
		Map<String,List<Integer>> temp= proposals.values().stream().flatMap(p->p.getEvaluations().stream())
				.collect(groupingBy(Evaluation::getAuthor,Collectors.mapping(Evaluation::getEvaluation, toList())));
		SortedMap<String,Integer> x = new TreeMap<String,Integer>();
		for(Entry<String, List<Integer>> entry : temp.entrySet())
		{
			x.put(entry.getKey(), entry.getValue().stream().mapToInt(e->e).sum());
		}
		return x;
		/*
		 * employees.stream()
                    .collect(Collectors.groupingBy(Employee::getDepartment,
                                                   Collectors.summingInt(Employee::getSalary)));
		 * */
	}

//R5
	public SortedMap<String, Integer> incomeForActivityTypes() {
		Map<String,List<Integer>> temp=activities.stream().collect(groupingBy(Activity::getActivityType,Collectors.mapping(Activity::getPrice, toList())));
		SortedMap<String,Integer> x = new TreeMap<String,Integer>();
		for(Entry<String, List<Integer>> entry : temp.entrySet())
		{
			x.put(entry.getKey(), entry.getValue().stream().mapToInt(e->e).sum());
		}
		return x;
	}

	public SortedMap<Integer, List<String>> participantsWithSameNofProposals() {
		Map<String, Long> m1 = proposals.values().stream()
				.flatMap(e->e.getEvaluations().stream())
				.collect(groupingBy(Evaluation::getAuthor,TreeMap::new,counting()));
		SortedMap<Integer, List<String>> m2 = new TreeMap<>();
		m2 =  m1.entrySet().stream()
				.collect(groupingBy((Map.Entry<String,Long> f)->f.getValue().intValue(), TreeMap::new,mapping((Map.Entry<String, Long> f) -> f.getKey(),toList())));
		return m2;
	}
}
