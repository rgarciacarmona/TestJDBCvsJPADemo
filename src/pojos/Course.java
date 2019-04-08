package pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(generator = "courses")
	@TableGenerator(name = "courses", table = "sqlite_sequence",
		pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "courses")
	private Integer id;
	private String name;
	private Integer year;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="enrollment",
	joinColumns={@JoinColumn(name="id_cou", referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn(name="id_stu", referencedColumnName="id")})
	private List<Student> students;
	
	public Course() {
		super();
		students = new ArrayList<Student>();
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
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public void addStudent(Student s) {
		if (!students.contains(s))
			students.add(s);
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
		Course other = (Course) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", year=" + year + "]";
	}
	
	
}
