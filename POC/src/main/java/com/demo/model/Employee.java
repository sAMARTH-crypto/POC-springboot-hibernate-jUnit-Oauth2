package com.demo.model;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;







@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "fName")
	private String firstName;

	@Column(name = "lName")
	private String lastName;

	@Column(name = "department")
	private String depart;

	@Column(name = "designation")
	private String desig;

	@Column(name = "age")
	private String age;

	public Employee() {

	}

	public Employee(int id, String firstName, String lastName, String depart, String desig, String age) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.depart = depart;
		this.desig = desig;
		this.age = age;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public void setDesig(String desig) {
		this.desig = desig;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getDepart() {
		return depart;
	}

	public String getDesig() {
		return desig;
	}

	public String getAge() {
		return age;
	}

}
