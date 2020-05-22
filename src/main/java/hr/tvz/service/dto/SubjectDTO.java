package hr.tvz.service.dto;

import hr.tvz.domain.Subject;

public class SubjectDTO {

	
    private Long id;

    private String subjectName;

    private String subjectDescription;

    public SubjectDTO() {
        // Empty constructor needed for Jackson.
    }

    public SubjectDTO(Subject subject) {
    	
        this.id = subject.getId();
        this.subjectName = subject.getSubjectName();
        this.subjectDescription = subject.getSubjectDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        this.subjectDescription = subjectDescription;
    }

    @Override
    public String toString() {
        return "SubjectDTO{" +
            ", subjectName='" + subjectName + '\'' +
            ", subjectDescription=" + subjectDescription +
            "}";
    }
}
