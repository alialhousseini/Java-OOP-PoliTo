package university;

public class Exam {
	private Student student;
	private Course course;
	private int grade;
	
	Exam(Student student, Course course, int grade) {
		super();
		this.student = student;
		this.course = course;
		this.grade = grade;
		student.addExam(this);
		course.addExam(this);
	}

	public Student getStudent() {
		return student;
	}

	public Course getCourse() {
		return course;
	}

	public int getGrade() {
		return grade;
	}

}
