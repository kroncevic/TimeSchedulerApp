package hr.tvz.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import hr.tvz.domain.Schedule;
import hr.tvz.service.dto.ScheduleDTO;

public class ScheduleMapper {
	
	   public ScheduleDTO scheduleToScheduleDTO(Schedule schedule) {
	        return new ScheduleDTO(schedule);
	    }

	    public List<ScheduleDTO> scheduleToScheduleDTOs(List<Schedule> schedules) {
	        return schedules.stream()
	            .filter(Objects::nonNull)
	            .map(this::scheduleToScheduleDTO)
	            .collect(Collectors.toList());
	    }

	    public Schedule scheduleDTOToSchedule(ScheduleDTO scheduleDTO) {
	        if (scheduleDTO == null) {
	            return null;
	        } else {
	        	Schedule schedule = new Schedule();
	        	schedule.setId(scheduleDTO.getId());
	        	schedule.setStartTime(scheduleDTO.getStartTime());
	        	schedule.setEndTime(scheduleDTO.getStartTime());
	        	schedule.setSubjectName(scheduleDTO.getSubjecName());
	        	schedule.setNumberOfStudents(scheduleDTO.getNumberOfStudents());
	        	schedule.setNumberOfSubmitted(scheduleDTO.getNumberOfSubmitted());
	          
	            return schedule;
	        }
	    }

	    public List<Schedule> scheduleDTOToSchedules(List<ScheduleDTO> scheduleDTOs) {
	        return scheduleDTOs.stream()
	            .filter(Objects::nonNull)
	            .map(this::scheduleDTOToSchedule)
	            .collect(Collectors.toList());
	    }

	    public Schedule scheduleFromId(Long id) {
	        if (id == null) {
	            return null;
	        }
	        Schedule schedule = new Schedule();
	        schedule.setId(id);
	        return schedule;
	    }

}
