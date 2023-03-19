package travelPortal;

class Evaluation {
	private String Author;
	private int evaluation;
	public String getAuthor() {
		return Author;
	}
	public int getEvaluation() {
		return evaluation;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}
	public Evaluation(String author, int evaluation) {
		super();
		Author = author;
		this.evaluation = evaluation;
	}
	@Override
	public String toString() {
		return Author + ":" + evaluation;
	}
	
}
