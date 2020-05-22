package hr.tvz.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hr.tvz.domain.Student;
import hr.tvz.service.dto.StudentDTO;

@Service
public class StudentMapper {
	
    public StudentDTO studentToStudentDTO(Student student) {
        return new StudentDTO(student);
    }

    public List<StudentDTO> studentsToStudentDTOs(List<Student> students) {
        return students.stream()
            .filter(Objects::nonNull)
            .map(this::studentToStudentDTO)
            .collect(Collectors.toList());
    }

    public Student studentDTOToStudent(StudentDTO studentDTO) {
        if (studentDTO == null) {
            return null;
        } else {
        	Student student = new Student();
        	student.setId(studentDTO.getId());
        	student.setStudentName(studentDTO.getStudentName());
        	student.setStudentLastName(studentDTO.getStudentLastName());
        	student.setPhoneNumber(studentDTO.getPhoneNumber());
        	student.setSchedule(studentDTO.getSchedule());
          
            return student;
        }
    }

    public List<Student> studentDTOsToStudents(List<StudentDTO> studentDTOs) {
        return studentDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::studentDTOToStudent)
            .collect(Collectors.toList());
    }

    public Student studentFromId(Long id) {
        if (id == null) {
            return null;
        }
        Student student = new Student();
        student.setId(id);
        return student;
    }

}
