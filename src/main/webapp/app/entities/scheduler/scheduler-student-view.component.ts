import { Component, OnInit, OnDestroy } from "@angular/core";
import {
    HttpErrorResponse,
    HttpHeaders,
    HttpResponse
} from "@angular/common/http";
import { ActivatedRoute, Router } from "@angular/router";
import { Subscription } from "rxjs";
import { JhiEventManager, JhiParseLinks, JhiAlertService } from "ng-jhipster";

import { IScheduler } from "app/shared/model/scheduler.model";
import { IStudent } from "app/shared/model/student.model";
import { Principal } from "app/core";

import { ITEMS_PER_PAGE } from "app/shared";
import { SchedulerService } from "./scheduler.service";
import { StudentService } from "./student.service";

import { registerLocaleData } from "@angular/common";
import localeHr from "@angular/common/locales/hr";
registerLocaleData(localeHr, "hr");

@Component({
    selector: "jhi-scheduler-student-view",
    templateUrl: "./scheduler-student-view.component.html"
})
export class SchedulerStudentViewComponent implements OnInit, OnDestroy {
    currentAccount: any;
    schedulers: IScheduler[];
    students: IStudent[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    subjectName: string;

    constructor(
        private schedulerService: SchedulerService,
        private studentService: StudentService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager
    ) {
        this.activatedRoute.queryParams.subscribe(params => {
            this.subjectName = params["subjectName"];
            this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        });
    }

    loadAll() {
        this.schedulerService
            .findAllAvaliableSchedulesToCurrentTimeBySubjectName(
                this.subjectName
            )
            .subscribe(data => {
                this.schedulers = data.body;
            });
        this.studentService.query().subscribe(
            (res: HttpResponse<IStudent[]>) => ({ students }) => {
                this.students = students;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInStudents();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IStudent) {
        return item.id;
    }

    registerChangeInStudents() {
        this.eventSubscriber = this.eventManager.subscribe(
            "studentListModification",
            response => this.loadAll()
        );
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
