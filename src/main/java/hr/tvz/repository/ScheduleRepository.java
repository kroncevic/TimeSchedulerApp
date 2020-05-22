package hr.tvz.repository;

import hr.tvz.domain.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Subject entity.
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>, ScheduleRepositoryCustom<Schedule, Long> {

    Optional<Schedule> findOneBySubjectName(String subjectName);
    
    Optional<List<Schedule>> findAllBySubjectName(String subjectName);
        
    Optional<List<Schedule>> findAllByStartTime(ZonedDateTime startTime);
    
    Optional<List<Schedule>> findAllByEndTime(ZonedDateTime endTime);
    
}
