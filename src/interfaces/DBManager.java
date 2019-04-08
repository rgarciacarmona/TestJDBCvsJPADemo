package interfaces;

import pojos.*;

public interface DBManager {

	public void connect();
	public void insertCourse(Course c);
	public void insertStudent(Student s, Course c);
}
