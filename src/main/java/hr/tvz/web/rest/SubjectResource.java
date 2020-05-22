package hr.tvz.web.rest;

import com.codahale.metrics.annotation.Timed;
import hr.tvz.domain.Subject;
import hr.tvz.repository.SubjectRepository;
import hr.tvz.service.SubjectService;
import hr.tvz.service.dto.SubjectDTO;
import hr.tvz.web.rest.errors.BadRequestAlertException;
import hr.tvz.web.rest.util.HeaderUtil;
import hr.tvz.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class SubjectResource {
	
    private final Logger log = LoggerFactory.getLogger(SubjectResource.class);

    private static final String ENTITY_NAME = "subject";

    private final SubjectService subjectService;
    
    private final SubjectRepository subjectRepository;

    public SubjectResource(SubjectService subjectService, SubjectRepository subjectRepository) {
        this.subjectService = subjectService;
        this.subjectRepository = subjectRepository;
    }

    @PostMapping("/subjects")
    @Timed
    public ResponseEntity<Subject> createSubject(@RequestBody SubjectDTO subjectDTO) throws URISyntaxException {
    	
        log.debug("REST request to save Subject : {}", subjectDTO);
        
        if (subjectDTO.getId() != null) {
            throw new BadRequestAlertException("A new subject cannot already have an ID", ENTITY_NAME, "idexists");
        }
        else if (subjectRepository.findOneBySubjectName(subjectDTO.getSubjectName().toLowerCase()).isPresent()) {
            throw new BadRequestAlertException("A new subject cannot already have an name", ENTITY_NAME, "idexists");
        }
        else {     	
        	Subject result = subjectService.createSubject(subjectDTO);
        	return ResponseEntity.created(new URI("/api/subjects/" + result.getId()))
        			.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        			.body(result);
		}
    }

    @PutMapping("/subjects")
    @Timed
    public ResponseEntity<Subject> updateSubject(@RequestBody Subject subject) throws URISyntaxException {
        log.debug("REST request to update Subject : {}", subject);
        if (subject.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Subject result = subjectService.save(subject);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, subject.getId().toString()))
            .body(result);
    }

    @GetMapping("/subjects")
    @Timed
    public ResponseEntity<List<Subject>> getAllSubjects(Pageable pageable) {
        log.debug("REST request to get a page of Subjects");
        Page<Subject> page = subjectService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/subjects");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/subjects/{id}")
    @Timed
    public ResponseEntity<Subject> getSubject(@PathVariable Long id) {
        log.debug("REST request to get Subject : {}", id);
        Optional<Subject> subject = subjectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(subject);
    }

    @DeleteMapping("/subjects/{id}")
    @Timed
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        log.debug("REST request to delete Subject : {}", id);
        subjectService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }


}
