package hr.tvz.service.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import hr.tvz.domain.Schedule;
import hr.tvz.domain.Subject;

public class ScheduleDTO {

	
    private Long id;
    
    private LocalDateTime startTime;
    
    private LocalDateTime endTime;

    private String subjectName;
    
    private int numberOfStudents;
    
    private int numberOfSubmitted;

    public ScheduleDTO() {
        // Empty constructor needed for Jackson.
    }

    public ScheduleDTO (Schedule schedule) {
    	
        this.id = schedule.getId();
        this.startTime = schedule.getStartTime();
        this.endTime = schedule.getEndTime();
        this.subjectName = schedule.getsubjectName();
        this.numberOfStudents = schedule.getNumberOfStudents();
        this.numberOfSubmitted = schedule.getNumberOfSubmitted();
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

	public String getSubjecName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}
	
	public int getNumberOfSubmitted() {
		return numberOfSubmitted;
	}

	public void setNumberOfSubmitted(int numberOfSubmitted) {
		this.numberOfSubmitted = numberOfSubmitted;
	}

	@Override
	public String toString() {
		return "ScheduleDTO [startTime=" + startTime + ", endTime=" + endTime + ", subjectName="
				+ subjectName + ", numberOfStudents=" + numberOfStudents + ", numberOfSubmitted=" + numberOfSubmitted + "]";
	}
}
