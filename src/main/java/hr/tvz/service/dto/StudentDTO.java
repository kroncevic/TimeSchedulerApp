package hr.tvz.service.dto;

import hr.tvz.domain.Schedule;
import hr.tvz.domain.Student;

public class StudentDTO {
	
	private Long id;
	
    private String studentName;

    private String studentLastName;

    private String phoneNumber;
    
    private Schedule schedule;

    public StudentDTO() {
        // Empty constructor needed for Jackson.
    }

    public StudentDTO(Student student) {
    	
        this.id = student.getId();
        this.studentName = student.getStudentName();
        this.studentLastName = student.getStudentLastName();
        this.phoneNumber = student.getPhoneNumber();
        this.schedule = student.getSchedule();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	@Override
	public String toString() {
		return "StudentDTO [id=" + id + ", studentName=" + studentName + ", studentLastName=" + studentLastName
				+ ", phoneNumber=" + phoneNumber + ", schedule=" + schedule + "]";
	}

}
