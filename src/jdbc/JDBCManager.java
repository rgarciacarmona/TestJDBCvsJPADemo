package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import interfaces.DBManager;
import pojos.*;

public class JDBCManager implements DBManager {

	private Connection con;
	
	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:./db/students.db");
			con.createStatement().execute("PRAGMA foreign_keys=ON");
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertCourse(Course c) {
		// TODO
	}
	
	public void insertStudent(Student s, Course c) {
		try {
			// Insert student
			PreparedStatement p = con.prepareStatement("INSERT INTO students (name, email)"
					+ " VALUES (?,?)");
			p.setString(1, s.getName());
			p.setString(2, s.getEmail());
			p.executeUpdate();
			// Get the ID of student
			String query = "SELECT last_insert_rowid() AS lastId";
			PreparedStatement p1 = con.prepareStatement(query);
			ResultSet rs = p1.executeQuery();
			Integer lastId = rs.getInt("lastId");
			// Insert into enrollment the ID of the student
			//      and the ID of the course
			PreparedStatement p2 = con.prepareStatement("INSERT INTO enrollments"
					+ " (id_stu, id_cou)"
					+ " VALUES (?,?)");
			p2.setInt(1, lastId);
			p2.setInt(2, c.getId());
			p2.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
