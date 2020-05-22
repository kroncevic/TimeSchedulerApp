package hr.tvz.service.mapper;

import hr.tvz.domain.Subject;
import hr.tvz.service.dto.SubjectDTO;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubjectMapper {

	
    public SubjectDTO subjectToSubjectDTO(Subject subject) {
        return new SubjectDTO(subject);
    }

    public List<SubjectDTO> subjectsToSubjectDTOs(List<Subject> subjects) {
        return subjects.stream()
            .filter(Objects::nonNull)
            .map(this::subjectToSubjectDTO)
            .collect(Collectors.toList());
    }

    public Subject subjectDTOToSubject(SubjectDTO subjectDTO) {
        if (subjectDTO == null) {
            return null;
        } else {
            Subject subject = new Subject();
            subject.setId(subjectDTO.getId());
            subject.setSubjectName(subjectDTO.getSubjectName());
            subject.setSubjectDescription(subjectDTO.getSubjectDescription());
          
            return subject;
        }
    }

    public List<Subject> subjectDTOsToSubjects(List<SubjectDTO> subjectDTOs) {
        return subjectDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::subjectDTOToSubject)
            .collect(Collectors.toList());
    }

    public Subject subjectFromId(Long id) {
        if (id == null) {
            return null;
        }
        Subject subject = new Subject();
        subject.setId(id);
        return subject;
    }

}
