import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";

import { IScheduler } from "app/shared/model/scheduler.model";
import { IStudent } from "app/shared/model/student.model";
import { StudentService } from "./student.service";

@Component({
    selector: "jhi-scheduler-detail",
    templateUrl: "./scheduler-detail.component.html"
})
export class SchedulerDetailComponent implements OnInit {
    schedule: IScheduler;
    students: IStudent[];

    constructor(
        private activatedRoute: ActivatedRoute,
        private studentService: StudentService
    ) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ schedule }) => {
            this.schedule = schedule;
        });
        this.loadAllStudents();
        console.log(this.students);
    }

    loadAllStudents() {
        this.studentService
            .findAllStudentsByScheduleId(this.schedule.id)
            .subscribe(data => {
                this.students = data.body;
            });
    }

    previousState() {
        window.history.back();
    }

    trackId(index: number, item: IStudent) {
        return item.id;
    }
}
