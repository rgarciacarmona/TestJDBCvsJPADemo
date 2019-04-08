package pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5098321229166180236L;
	
	@Id
	@GeneratedValue(generator = "students")
	@TableGenerator(name = "students", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "students")
	private Integer id;
	private String name;
	private String email;
	@ManyToMany(mappedBy = "students",fetch = FetchType.LAZY)
	private List<Course> courses;
	
	public Student() {
		super();
		courses = new ArrayList<Course>();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public void addCourse(Course c) {
		if (!courses.contains(c))
			courses.add(c);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", courses=" + courses + "]";
	}

	
}
