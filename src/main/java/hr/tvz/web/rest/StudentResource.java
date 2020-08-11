package hr.tvz.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import hr.tvz.domain.Schedule;
import hr.tvz.domain.Student;
import hr.tvz.domain.Subject;
import hr.tvz.repository.StudentRepository;
import hr.tvz.repository.SubjectRepository;
import hr.tvz.service.StudentService;
import hr.tvz.service.SubjectService;
import hr.tvz.service.dto.StudentDTO;
import hr.tvz.service.dto.SubjectDTO;
import hr.tvz.web.rest.errors.BadRequestAlertException;
import hr.tvz.web.rest.util.HeaderUtil;
import hr.tvz.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class StudentResource {
	
	private final Logger log = LoggerFactory.getLogger(StudentResource.class);

    private static final String ENTITY_NAME = "student";

    private final StudentService studentService;
    
    private final StudentRepository studentRepository;

    public StudentResource(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @PostMapping("/students")
    @Timed
    public ResponseEntity<Student> createStudent(@RequestBody StudentDTO studentDTO) throws URISyntaxException {
    	
        log.debug("REST request to save Student : {}", studentDTO);
        
        if (studentDTO.getId() != null) {
            throw new BadRequestAlertException("A new student cannot already have an ID", ENTITY_NAME, "idexists");
        }
        else {     	
        	Student result = studentService.createStudent(studentDTO);
        	return ResponseEntity.created(new URI("/api/students/" + result.getId()))
        			.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        			.body(result);
		}
    }

    @PutMapping("/students")
    @Timed
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) throws URISyntaxException {
        log.debug("REST request to update Student : {}", student);
        if (student.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Student result = studentService.save(student);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, student.getId().toString()))
            .body(result);
    }

    @GetMapping("/students")
    @Timed
    public ResponseEntity<List<Student>> getAllStudents(Pageable pageable) {
        log.debug("REST request to get a page of Students");
        Page<Student> page = studentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/students");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/students/{id}")
    @Timed
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        log.debug("REST request to get Student : {}", id);
        Optional<Student> student = studentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(student);
    }
    
    @GetMapping("/students/by_schedule/{id}")
    @Timed
    public ResponseEntity<List<Student>> getAllStudentsByScheduleId(@PathVariable Long id) {
        log.debug("REST request to get Students by schedule_id: {}", id);
        List<Student> students = studentService.findAllByScheduleId(id);
        return ResponseEntity.ok(students);
    }

    @DeleteMapping("/students/{id}")
    @Timed
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        log.debug("REST request to delete Student : {}", id);
        studentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
