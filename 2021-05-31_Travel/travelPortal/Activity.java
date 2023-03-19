package travelPortal;

class Activity {
	private Proposal proposal;
	private String activityType;
	private String description;
	private int price;
	public Proposal getProposal() {
		return proposal;
	}
	public String getActivityType() {
		return activityType;
	}
	public String getDescription() {
		return description;
	}
	public int getPrice() {
		return price;
	}
	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Activity(Proposal proposal, String activityType, String description, int price) {
		super();
		this.proposal = proposal;
		this.activityType = activityType;
		this.description = description;
		this.price = price;
	}
	
}
