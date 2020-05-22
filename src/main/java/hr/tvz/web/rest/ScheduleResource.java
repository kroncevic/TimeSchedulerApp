package hr.tvz.web.rest;

import com.codahale.metrics.annotation.Timed;

import hr.tvz.domain.Schedule;
import hr.tvz.domain.Subject;
import hr.tvz.repository.ScheduleRepository;
import hr.tvz.repository.SubjectRepository;
import hr.tvz.service.ScheduleService;
import hr.tvz.service.SubjectService;
import hr.tvz.service.dto.ScheduleDTO;
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
public class ScheduleResource {
	
    private final Logger log = LoggerFactory.getLogger(ScheduleResource.class);

    private static final String ENTITY_NAME = "schedule";

    private final ScheduleService scheduleService;
    
    private final ScheduleRepository scheduleRepository;

    public ScheduleResource(ScheduleService scheduleService, ScheduleRepository scheduleRepository) {
        this.scheduleService = scheduleService;
        this.scheduleRepository = scheduleRepository;
    }

    @PostMapping("/schedules")
    @Timed
    public ResponseEntity<Schedule> createSchedule(@RequestBody ScheduleDTO scheduleDTO) throws URISyntaxException {
    	
        log.debug("REST request to save Schedule : {}", scheduleDTO);
        
        if (scheduleDTO.getId() != null) {
            throw new BadRequestAlertException("A new schedule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        else {     	
        	Schedule result = scheduleService.createSchedule(scheduleDTO);
        	return ResponseEntity.created(new URI("/api/schedules/" + result.getId()))
        			.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        			.body(result);
		}
    }

    @PutMapping("/schedules")
    @Timed
    public ResponseEntity<Schedule> updateSchedule(@RequestBody Schedule schedule) throws URISyntaxException {
        log.debug("REST request to update Schedule : {}", schedule);
        if (schedule.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Schedule result = scheduleService.save(schedule);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, schedule.getId().toString()))
            .body(result);
    }

    @GetMapping("/schedules")
    @Timed
    public ResponseEntity<List<Schedule>> getAllSchedules(Pageable pageable) {
        log.debug("REST request to get a page of Schedules");
        Page<Schedule> page = scheduleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/schedules");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    @GetMapping(value = "/schedules/{id}")
    @Timed
    public ResponseEntity<Schedule> getSchedule(@PathVariable Long id) {
        log.debug("REST request to get Schedule : {}", id);
        Optional<Schedule> schedule = scheduleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(schedule);
    }

    @DeleteMapping("/schedules/{id}")
    @Timed
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        log.debug("REST request to delete Schedule : {}", id);
        scheduleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping(path = "/schedules/get", params = "subjectName")
    @Timed
    public ResponseEntity<List<Schedule>> findAllAvaliableSchedulesToCurrentTimeBySubjectName(@RequestParam(value="subjectName") String subjectName) {
        log.debug("REST request to get Schedules for Reservetions page");
        Optional<List<Schedule>> schedules = scheduleService.findAllAvaliableSchedulesToCurrentTimeBySubjectName(subjectName);
        return ResponseUtil.wrapOrNotFound(schedules);
    }
    
    @PutMapping("/schedules/addStudent")
    @Timed
    public ResponseEntity<Schedule> incrementNumberOfSubmittedStudents (@RequestBody Schedule schedule) throws URISyntaxException {
    	
    	log.debug("REST request to increment number of submitted students");
    	
        if (schedule.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }   	

        scheduleService.incrementNumberOfSubmittedStudents(schedule.getId());
        
        return ResponseEntity.ok()
        		.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, schedule.getId().toString()))
        		.body(schedule);
    }

}
