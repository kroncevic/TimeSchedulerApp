package hr.tvz.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.tvz.domain.Student;
import hr.tvz.repository.StudentRepository;
import hr.tvz.service.dto.StudentDTO;

@Service
@Transactional
public class StudentService {
	
    private final Logger log = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    public Student createStudent (StudentDTO studentDTO) {
    	
    	Student student = new Student();
        
    	student.setStudentName(studentDTO.getStudentName());
    	student.setStudentLastName(studentDTO.getStudentLastName());
    	student.setPhoneNumber(studentDTO.getPhoneNumber());
    	student.setSchedule(studentDTO.getSchedule());	
    	
    	studentRepository.save(student);

        log.debug("Created Information for Student: {}", student);
        return student;
    }

    public Student save(Student student) {
        log.debug("Request to save Student : {}", student);
        return studentRepository.save(student);
    }
    public void delete(Long id) {
        log.debug("Request to delete student : {}", id);
        studentRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public Page<Student> findAll(Pageable pageable) {
        log.debug("Request to get all Students");
        return studentRepository.findAll(pageable);
    }
    
    @Transactional(readOnly = true)
    public List<Student> findAllByScheduleId(Long id) {
        log.debug("Request to get all Students for schedule");
        return studentRepository.findAllByScheduleId(id);
    }
    
    
    @Transactional(readOnly = true)
    public Optional<Student> findOne(Long id) {
        log.debug("Request to get Student : {}", id);
        return studentRepository.findById(id);
    }

}
