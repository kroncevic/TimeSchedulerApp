package hr.tvz.repository;

import java.util.List;
import java.util.Optional;

import hr.tvz.domain.Schedule;

public interface ScheduleRepositoryCustom<T, ID> {
	
	Optional<List<Schedule>> findAllAvaliableSchedulesToCurrentTimeBySubjectName(String subjectName);
	
	int incrementNumberOfSubmittedStudents(Long scheduleId);
	
	int decrementNumberOfSubmittedStudents(Long scheduleId);

}
