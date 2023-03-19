package university;

 class Course {
	
	private static final String SEPARATOR = ",";
	private int code;
	private String title;
	private String teacher;
	private Student[] students;
	private Exam[] exams;
	private int nextExam=0;

	public Course(int code, String title, String teacher) {
		this.code = code;
		this.title = title;
		this.teacher = teacher;
		students = new Student[University.MAX_STUDENTS_PER_COURSE];
		exams = new Exam[University.MAX_COURSES_PER_STUDENT];
	}
	
	public String toString(){
		return code + SEPARATOR + title + SEPARATOR + teacher;
	}

	public void enroll(Student s) {
		for (int i = 0; i < students.length; i++) {
			if(students[i] == null){
				students[i]=s;
				break;
			}
		}
	}
	
	public String attendees(){
		StringBuffer result = new StringBuffer();
		
		for(Student s : students){
			if(s!=null){
				result.append(s.toString()).append("\n");
			}
		}
		return result.toString();
	}

	public void addExam(Exam exam) {
		exams[nextExam++] = exam;
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
		return this.code;
	}

	public String getTitle() {
		return this.title;
	}

}
