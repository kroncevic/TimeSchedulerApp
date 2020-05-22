package hr.tvz.service;

import hr.tvz.domain.Subject;
import hr.tvz.repository.SubjectRepository;
import hr.tvz.service.dto.SubjectDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

/**
 * Service class for managing subjects.
 */
@Service
@Transactional
public class SubjectService {
	
    private final Logger log = LoggerFactory.getLogger(SubjectService.class);

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }
    
    public Subject createSubject (SubjectDTO subjectDTO) {
    	
    	Subject subject = new Subject();
        
    	subject.setSubjectName(subjectDTO.getSubjectName());
    	subject.setSubjectDescription(subjectDTO.getSubjectDescription());
    	
        subjectRepository.save(subject);

        log.debug("Created Information for Subject: {}", subject);
        return subject;
    }

    public Subject save(Subject subject) {
        log.debug("Request to save Subject : {}", subject);
        return subjectRepository.save(subject);
    }
    public void delete(Long id) {
        log.debug("Request to delete subject : {}", id);
        subjectRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public Page<Subject> findAll(Pageable pageable) {
        log.debug("Request to get all Subjects");
        return subjectRepository.findAll(pageable);
    }
    
    @Transactional(readOnly = true)
    public Optional<Subject> findOne(Long id) {
        log.debug("Request to get Subject : {}", id);
        return subjectRepository.findById(id);
    }
    
}
