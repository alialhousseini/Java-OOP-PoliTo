package university;

 class Student {
	
	private static final String SEPARATOR = " ";
	private int ID;
	private String first;
	private String last;
	
	private Course[] courses;
	private Exam[] exams;
	private int nextExam=0;
	
	public Student(int id, String first, String last) {
		this.ID = id;
		this.first = first;
		this.last = last;
		courses = new Course[University.MAX_COURSES_PER_STUDENT];
		exams = new Exam[University.MAX_COURSES_PER_STUDENT];
	}
	
	public String toString(){
		return ID + SEPARATOR + first + SEPARATOR + last;
		// TODO: probably to be optimized with StringBuffer
//		return (new StringBuffer()).append(ID).append(SEPARATOR).
//				append(first).append(SEPARATOR).
//				append(last).toString();
	}
	
	public void enroll(Course c){
		for(int i=0; i< courses.length; ++i){
			if( courses[i] == null){
				courses[i] = c;
				break;
			}
		}
	}

	public String courses() {
		StringBuffer result = new StringBuffer();
		for(Course c : courses){
			if(c!=null){
				result.append(c).append("\n");
			}
		}
		return result.toString();
	}
	
	void addExam(Exam e) {
		exams[nextExam++] = e;
	}
	
	double average() {
		if(nextExam==0) return Double.NaN;
		double average = 0.0;
		for(Exam e : exams) {
			if(e==null) break;
			average += e.getGrade();
		}
		return average/nextExam;
	}

	public int getId() {
		return ID;
	}
	
	public double getScore() {
		double avg = average();
		if(Double.isNaN(avg)) return Double.NEGATIVE_INFINITY;
		int taken = nextExam;
		int enrolled = 0;
		while(courses[enrolled]!=null) {enrolled++;}
		
		return avg + 10*taken/(double)enrolled;
	}

	public String getLast() {
		return last;
	}

	public String getFirst() {
		return first;
	}

}
