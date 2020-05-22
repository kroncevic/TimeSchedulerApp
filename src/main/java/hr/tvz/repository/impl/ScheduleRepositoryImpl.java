package hr.tvz.repository.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hr.tvz.domain.Schedule;
import hr.tvz.repository.ScheduleRepositoryCustom;

@Repository
@Transactional(readOnly = true)
public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom<Schedule, Long>{
	

    @PersistenceContext
    EntityManager entityManager;
    
	@Override
	public Optional<List<Schedule>> findAllAvaliableSchedulesToCurrentTimeBySubjectName(String subjectName) {
						
	    String queryStr = "SELECT * FROM SCHEDULE where SUBJECT_NAME = ?1 AND START_TIME > CURRENT_TIMESTAMP() AND "
	    		+ "NUMBER_OF_SUBMITTED < NUMBER_OF_STUDENTS ORDER BY START_TIME";

	    Query query = entityManager.createNativeQuery(queryStr, Schedule.class);
	    query.setParameter(1, subjectName);
	    
	    List<Schedule> filteredSchedules = query.getResultList();

	    return Optional.of(filteredSchedules);
	}

	@Override
	public int incrementNumberOfSubmittedStudents(Long scheduleId) {

		String queryStr = "UPDATE SCHEDULE SET NUMBER_OF_SUBMITTED = NUMBER_OF_SUBMITTED + 1 WHERE ID = ?1";

	    Query query = entityManager.createNativeQuery(queryStr, Schedule.class);
	    query.setParameter(1, scheduleId);
	    
	    int result = query.executeUpdate();
	    
	    return result;
	}

	@Override
	public int decrementNumberOfSubmittedStudents(Long scheduleId) {

		String queryStr = "UPDATE SCHEDULE SET NUMBER_OF_SUBMITTED = NUMBER_OF_SUBMITTED - 1 WHERE ID = ?1";

	    Query query = entityManager.createNativeQuery(queryStr, Schedule.class);
	    query.setParameter(1, scheduleId);
	    
	    int result = query.executeUpdate();
	    
	    return result;
	}

}
