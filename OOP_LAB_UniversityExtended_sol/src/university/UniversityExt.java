package university;

import java.util.Comparator;
import java.util.logging.Logger;

/**
 * This class is an extended version of the {@Link University} class.
 * 
 *
 */
public class UniversityExt extends University {
	
	private final static Logger logger = Logger.getLogger("University"); // DO NOT CHANGE THIS LINE!!
																		 // tests assume the logger name is "University"

	public UniversityExt(String name) {
		super(name);
		// Example of logging
		logger.info("Creating extended university object");
	}

	@Override
	public int enroll(String first, String last){
		int id = super.enroll(first, last);
		logger.info("New student enrolled: " + id + ", " + first + " " + last );
		return id;
	}
	
	@Override
	public int activate(String title, String teacher){
		int code = super.activate(title, teacher);
		logger.info("New course activated: " + code + ", " + title + " " + teacher );
		return code;
	}

	
	@Override
	public void register(int studentID, int courseCode){
		super.register(studentID, courseCode);
		logger.info("Student " + studentID + " signed up for course " + courseCode);
	}
	
	/**
	 * records the grade (integer 0-30) for an exam can 
	 * 
	 * @param studentId the ID of the student
	 * @param courseID	course code 
	 * @param grade		grade ( 0-30)
	 */
	public void exam(int studentId, int courseId, int grade) {
		Student s = findStudent(studentId);
		Course c = findCourse(courseId);
		if(c.attendees().contains(Integer.toString(studentId))) {
			new Exam(s,c,grade);
			logger.info("Student " + studentId + " took an exam in course " + courseId + " with grade" + grade);
		}else {
			System.err.printf("Student %d not enrolled in course %d!",studentId,courseId);
		}
	}

	/**
	 * Computes the average grade for a student and formats it as a string
	 * using the following format 
	 * 
	 * {@code "Student STUDENT_ID : AVG_GRADE"}. 
	 * 
	 * If the student has no exam recorded the method
	 * returns {@code "Student STUDENT_ID hasn't taken any exams"}.
	 * 
	 * @param studentId the ID of the student
	 * @return the average grade formatted as a string.
	 */
	public String studentAvg(int studentId) {
		Student s = findStudent(studentId);
		double avg = s.average();
		if(Double.isNaN(avg)) return String.format("Student %d hasn't taken any exams", s.getId());
		return String.format("Student %d : %.1f", s.getId(), avg);
	}
	
	/**
	 * Computes the average grades of all students that took the exam for a given course.
	 * 
	 * The format is the following: 
	 * {@code "The average for the course COURSE_TITLE is: COURSE_AVG"}.
	 * 
	 * If no student took the exam for that course it returns {@code "No student has taken the exam in COURSE_TITLE"}.
	 * 
	 * @param courseId	course code 
	 * @return the course average formatted as a string
	 */
	public String courseAvg(int courseId) {
		Course c = findCourse(courseId);
		double avg = c.average();
		if(Double.isNaN(avg)) return String.format("No student has taken the exam in %s", c.getTitle());
		return String.format("The average for the course %s is: %.1f", c.getTitle(), avg);
	}
	
	/**
	 * Retrieve information for the best students to award a price.
	 * 
	 * The students' score is evaluated as the average grade of the exams they've taken. 
	 * To take into account the number of exams taken and not only the grades, 
	 * a special bonus is assigned on top of the average grade: 
	 * the number of taken exams divided by the number of courses the student is enrolled to, multiplied by 10.
	 * The bonus is added to the exam average to compute the student score.
	 * 
	 * The method returns a string with the information about the three students with the highest score. 
	 * The students appear one per row (rows are terminated by a new-line character {@code '\n'}) 
	 * and each one of them is formatted as: {@code "STUDENT_FIRSTNAME STUDENT_LASTNAME : SCORE"}.
	 * 
	 * @return info of the best three students.
	 */
	public String topThreeStudents() {
		Student[] top = top(3,Comparator.comparingDouble(Student::getScore));
		String res="";
		for(Student s : top) {
			if(Double.NEGATIVE_INFINITY == s.getScore()) continue;
			res += s.getLast() + " " + s.getFirst() + " : " + s.getScore() + "\n";
		}
		return res;
	}
}
