import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { NgbModalRef } from "@ng-bootstrap/ng-bootstrap";

import { VERSION } from "app/app.constants";
import { Principal, LoginModalService, LoginService } from "app/core";
import { ProfileService } from "../profiles/profile.service";
import { ISubject } from "app/shared/model/subject.model";
import { SubjectService } from "app/entities/subject/subject.service";
import { IScheduler } from "app/shared/model/scheduler.model";
import { SchedulerService } from "app/entities/scheduler/scheduler.service";

@Component({
    selector: "jhi-navbar",
    templateUrl: "./navbar.component.html",
    styleUrls: ["navbar.css"]
})
export class NavbarComponent implements OnInit {
    inProduction: boolean;
    isNavbarCollapsed: boolean;
    languages: any[];
    swaggerEnabled: boolean;
    modalRef: NgbModalRef;
    version: string;
    subjects: ISubject[];
    scheduler: IScheduler[];

    constructor(
        private loginService: LoginService,
        private principal: Principal,
        private loginModalService: LoginModalService,
        private profileService: ProfileService,
        private router: Router,
        private subjectService: SubjectService,
        private schedulerService: SchedulerService
    ) {
        this.version = VERSION ? "v" + VERSION : "";
        this.isNavbarCollapsed = true;
    }

    ngOnInit() {
        this.profileService.getProfileInfo().then(profileInfo => {
            this.inProduction = profileInfo.inProduction;
            this.swaggerEnabled = profileInfo.swaggerEnabled;
        });
        this.subjectService.query().subscribe(data => {
            this.subjects = data.body;
        });
        this.schedulerService.query().subscribe(data => {
            this.scheduler = data.body;
        });
    }

    collapseNavbar() {
        this.isNavbarCollapsed = true;
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }

    logout() {
        this.collapseNavbar();
        this.loginService.logout();
        this.router.navigate([""]);
    }

    toggleNavbar() {
        this.isNavbarCollapsed = !this.isNavbarCollapsed;
    }

    getImageUrl() {
        return this.isAuthenticated() ? this.principal.getImageUrl() : null;
    }
}
