package hr.tvz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.tvz.domain.Schedule;
import hr.tvz.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
            
    Optional<List<Student>> findAllByScheduleId(Long scheduleId);
        
}
