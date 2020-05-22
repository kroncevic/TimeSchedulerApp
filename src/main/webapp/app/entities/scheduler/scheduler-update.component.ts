import {
    Component,
    OnInit,
    ViewChild,
    TemplateRef,
    ViewEncapsulation
} from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { HttpResponse, HttpErrorResponse } from "@angular/common/http";
import { Observable } from "rxjs";
import { IScheduler } from "app/shared/model/scheduler.model";
import { ISubject } from "app/shared/model/subject.model";
import { SchedulerService } from "./scheduler.service";
import { SubjectService } from "app/entities/subject/subject.service";

import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { JhiEventManager, JhiParseLinks, JhiAlertService } from "ng-jhipster";

@Component({
    selector: "jhi-schedule-update",
    encapsulation: ViewEncapsulation.None,
    styleUrls: ["scheduler.css"],
    templateUrl: "./scheduler-update.component.html"
})
export class SchedulerUpdateComponent implements OnInit {
    schedule: IScheduler;
    subjects: ISubject[];
    isSaving: boolean;

    constructor(
        private schedulerService: SchedulerService,
        private subjectService: SubjectService,
        private activatedRoute: ActivatedRoute,
        private jhiAlertService: JhiAlertService
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ schedule }) => {
            this.schedule = schedule;
        });
        this.subjectService.query().subscribe(data => {
            this.subjects = data.body;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.schedule.id !== undefined) {
            this.subscribeToSaveResponse(
                this.schedulerService.update(this.schedule)
            );
        } else {
            this.subscribeToSaveResponse(
                this.schedulerService.create(this.schedule)
            );
        }
    }

    private subscribeToSaveResponse(
        result: Observable<HttpResponse<IScheduler>>
    ) {
        result.subscribe(
            (res: HttpResponse<IScheduler>) => this.onSaveSuccess(),
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
