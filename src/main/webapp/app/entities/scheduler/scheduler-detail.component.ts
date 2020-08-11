import { Component, OnInit, OnDestroy, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IScheduler } from 'app/shared/model/scheduler.model';
import { IStudent } from 'app/shared/model/student.model';
import { StudentService } from './student.service';
import { SchedulerService } from './scheduler.service';

import { JhiEventManager } from 'ng-jhipster';
import { Subscription } from 'rxjs';

@Component({
    selector: 'jhi-scheduler-detail',
    templateUrl: './scheduler-detail.component.html'
})
export class SchedulerDetailComponent implements OnInit, OnDestroy  {
    schedule: IScheduler;
    students: IStudent[];
    studentModificationEvent: Subscription;

    constructor(
        private activatedRoute: ActivatedRoute,
        private studentService: StudentService,
        private schedulerService: SchedulerService,
        private eventManager: JhiEventManager
    ) {
        this.activatedRoute.data.subscribe(({ schedule }) => {
            this.schedule = schedule;
        });
}

    ngOnInit() {
        this.studentService
                .findAllStudentsByScheduleId(this.schedule.id)
                .subscribe(data => {
                    this.students = data.body;
        });
        this.studentModificationEvent = this.eventManager.subscribe('studentListModification', ()  => {
            this.schedulerService
                .decrementNumberOfSubmittedStudents(this.schedule)
                .subscribe(data => {
                    this.schedule = data.body;
            });
        });
     }

    previousState() {
        window.history.back();
    }

    trackId(index: number, item: IStudent) {
        return item.id;
    }

    ngOnDestroy() {
       this.eventManager.destroy(this.studentModificationEvent);
     }

}
