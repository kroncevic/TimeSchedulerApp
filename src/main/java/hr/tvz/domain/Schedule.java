package hr.tvz.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "schedule")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Schedule implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "startTime")
    private LocalDateTime startTime;
    
    @NotNull
    @Column(name = "endTime")
    private LocalDateTime endTime;
    
    @NotNull
    @Column(name = "numberOfStudents")
    private int numberOfStudents;
    
    @Column(name = "numberOfSubmitted", nullable = false, columnDefinition = "int default 0")
    private int numberOfSubmitted;
    
    @NotNull
    @Column(name = "subjectName")
    private String subjectName;
    
    @JsonIgnore
    @OneToMany(mappedBy="schedule", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Student> students;

    public String getsubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setNumberOfSubmitted(int numberOfSubmitted) {
		this.numberOfSubmitted = numberOfSubmitted;
	}
	
	public int getNumberOfSubmitted() {
		return numberOfSubmitted;
	}

	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + numberOfStudents;
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((subjectName == null) ? 0 : subjectName.hashCode());
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
		Schedule other = (Schedule) obj;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (numberOfStudents != other.numberOfStudents)
			return false;
		if (numberOfSubmitted != other.numberOfSubmitted)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (subjectName == null) {
			if (other.subjectName != null)
				return false;
		} else if (!subjectName.equals(other.subjectName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", numberOfStudents="
				+ numberOfStudents + ", numberOfSubmitted=" + numberOfSubmitted + ", subjectName=" + subjectName
				+ ", students=" + students + "]";
	}

}
