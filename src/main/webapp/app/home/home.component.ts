import { Component, OnInit } from "@angular/core";
import { NgbModalRef } from "@ng-bootstrap/ng-bootstrap";
import { JhiEventManager, JhiAlertService } from "ng-jhipster";
import { NgbdCarouselConfig } from "./carousel.component";
import { Subscription } from "rxjs";
import {
    HttpErrorResponse,
    HttpHeaders,
    HttpResponse
} from "@angular/common/http";

import { LoginModalService, Principal, Account } from "app/core";
import { ISubject } from "app/shared/model/subject.model";
import { SubjectService } from "app/entities/subject/subject.service";

@Component({
    selector: "jhi-home",
    templateUrl: "./home.component.html",
    styleUrls: ["home.css"]
})
export class HomeComponent implements OnInit {
    account: Account;
    modalRef: NgbModalRef;
    subjects: ISubject[];
    eventSubscriber: Subscription;

    constructor(
        private principal: Principal,
        private loginModalService: LoginModalService,
        private subjectService: SubjectService,
        private eventManager: JhiEventManager,
        private jhiAlertService: JhiAlertService
    ) {}

    ngOnInit() {
        this.principal.identity().then(account => {
            this.account = account;
        });
        this.loadAll();
        this.registerAuthenticationSuccess();
        this.registerChangeInSubjects();
    }

    loadAll() {
        this.subjectService.query().subscribe(
            (res: HttpResponse<ISubject[]>) => (this.subjects = res.body),
            /**				this.logsService.findAll().subscribe(response => (this.loggers = response.body));*/
            (res: HttpResponse<any>) => this.onError(res.body)
        );
    }

    trackId(index: number, item: ISubject) {
        return item.id;
    }

    registerChangeInSubjects() {
        this.eventSubscriber = this.eventManager.subscribe(
            "subjectListModification",
            response => this.subjectService.query()
        );
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe("authenticationSuccess", message => {
            this.principal.identity().then(account => {
                this.account = account;
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
