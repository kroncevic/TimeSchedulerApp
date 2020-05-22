package hr.tvz.service;

import hr.tvz.domain.Schedule;
import hr.tvz.domain.Subject;
import hr.tvz.repository.ScheduleRepository;
import hr.tvz.repository.SubjectRepository;
import hr.tvz.service.dto.ScheduleDTO;
import hr.tvz.service.dto.SubjectDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Service class for managing subjects.
 */
@Service
@Transactional
public class ScheduleService {
	
    private final Logger log = LoggerFactory.getLogger(ScheduleService.class);

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }
    
    public Schedule createSchedule (ScheduleDTO scheduleDTO) {
    	
    	Schedule schedule = new Schedule();
        
    	schedule.setStartTime(scheduleDTO.getStartTime());
    	schedule.setEndTime(scheduleDTO.getEndTime());
    	schedule.setNumberOfStudents(scheduleDTO.getNumberOfStudents());
    	schedule.setSubjectName(scheduleDTO.getSubjecName());
    	schedule.setNumberOfSubmitted(schedule.getNumberOfSubmitted());
    	
    	scheduleRepository.save(schedule);

        log.debug("Created Information for Schedule: {}", schedule);
        return schedule;
    }

    public Schedule save(Schedule schedule) {
        log.debug("Request to save Schedule : {}", schedule);
        return scheduleRepository.save(schedule);
    }
    public void delete(Long id) {
        log.debug("Request to delete Schedule : {}", id);
        scheduleRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public Page<Schedule> findAll(Pageable pageable) {
        log.debug("Request to get all Schedules");
        return scheduleRepository.findAll(pageable);
    }
    
    @Transactional(readOnly = true)
    public Optional<List<Schedule>> findAllBySubjectName(String subjectName) {
        log.debug("Request to get Schedules by subject name");
        return scheduleRepository.findAllBySubjectName(subjectName);
    }
    
    @Transactional(readOnly = true)
    public Optional<List<Schedule>> findAllAvaliableSchedulesToCurrentTimeBySubjectName(String subjectName) {
        log.debug("Request to get Schedules to display on Reservations page");
        return scheduleRepository.findAllAvaliableSchedulesToCurrentTimeBySubjectName(subjectName);
    }
    
    @Transactional(readOnly = true)
    public int incrementNumberOfSubmittedStudents(Long scheduleId) {
        log.debug("Request to increment number of submitted students");
        return scheduleRepository.incrementNumberOfSubmittedStudents(scheduleId);
    }
    
    @Transactional(readOnly = true)
    public int decrementNumberOfSubmittedStudents(Long scheduleId) {
        log.debug("Request to increment number of submitted students");
        return scheduleRepository.decrementNumberOfSubmittedStudents(scheduleId);
    }
    
    @Transactional(readOnly = true)
    public Optional<Schedule> findOne(Long id) {
        log.debug("Request to get Schedule : {}", id);
        return scheduleRepository.findById(id);
    }
    
    public boolean checkOverlapping(List<Schedule> scheduleList, ScheduleDTO currentSchedule) {
    	
    	return scheduleList
    			.stream()
    			.filter(schedule -> isOverlapping(schedule.getStartTime(),schedule.getEndTime()
    					, currentSchedule.getStartTime(), currentSchedule.getEndTime())).count() == 0;
    			    	
    }
    
    public static boolean isOverlapping(LocalDateTime existingStartTime, LocalDateTime existingEndTime, LocalDateTime newStartTime, LocalDateTime newEndTime)
    {
    	return existingStartTime.compareTo(newEndTime) <= 0 && existingEndTime.compareTo(newStartTime) >= 0;
    }
    
}
