import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { HttpResponse, HttpErrorResponse } from "@angular/common/http";
import { Observable } from "rxjs";
import { IScheduler } from "app/shared/model/scheduler.model";
import { IStudent } from "app/shared/model/student.model";
import { SchedulerService } from "./scheduler.service";
import { StudentService } from "./student.service";
import { JhiAlertService } from "ng-jhipster";

@Component({
    selector: "jhi-scheduler-reservation",
    encapsulation: ViewEncapsulation.None,
    styleUrls: ["scheduler.css"],
    templateUrl: "./scheduler-reservation.component.html"
})
export class SchedulerReservationComponent implements OnInit {
    scheduler: IScheduler;
    student: IStudent;
    isSaving: boolean;
    schedulerId: number;

    constructor(
        private schedulerService: SchedulerService,
        private studentService: StudentService,
        private activatedRoute: ActivatedRoute,
        private jhiAlertService: JhiAlertService
    ) {
        this.activatedRoute.queryParams.subscribe(params => {
            this.schedulerId = params["scheduler"];
        });
        this.schedulerService.find(this.schedulerId).subscribe(data => {
            this.scheduler = data.body;
        });
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ schedule }) => {
            this.student = schedule;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.student.id !== undefined) {
            this.subscribeToSaveResponse(
                this.studentService.update(this.student)
            );
        } else {
            this.student.schedule = this.scheduler;
            this.subscribeToSaveResponse(
                this.studentService.create(this.student)
            );
            this.subscribeToSaveResponse(
                this.schedulerService.incrementNumberOfSubmittedStudents(
                    this.scheduler
                )
            );
        }
    }

    private subscribeToSaveResponse(
        result: Observable<HttpResponse<IStudent>>
    ) {
        result.subscribe(
            (res: HttpResponse<IStudent>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
