import { Route } from "@angular/router";
import { Injectable } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import {
    Resolve,
    ActivatedRouteSnapshot,
    RouterStateSnapshot,
    Routes
} from "@angular/router";
import { JhiPaginationUtil, JhiResolvePagingParams } from "ng-jhipster";
import { UserRouteAccessService } from "app/core";
import { Observable, of } from "rxjs";
import { filter, map } from "rxjs/operators";
import { Subject } from "app/shared/model/subject.model";
import { SubjectService } from "app/entities/subject/subject.service";
import { SubjectComponent } from "app/entities/subject/subject.component";
import { SubjectDetailComponent } from "app/entities/subject/subject-detail.component";
import { ISubject } from "app/shared/model/subject.model";
import { SubjectResolve } from "app/entities/subject/subject.route";
import { HomeComponent } from "./";

export const HOME_ROUTE: Route = {
    path: "",
    component: HomeComponent,
    data: {
        authorities: [],
        pageTitle: "Instrukcije Budućnost"
    }
};

export const subjectRoute: Routes = [
    {
        path: "subject",
        component: SubjectComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            //   authorities: ['ROLE_ANONYMOUS'],
            defaultSort: "id,asc",
            pageTitle: "Kolegij"
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: "subject/:id/view",
        component: SubjectDetailComponent,
        resolve: {
            subject: SubjectResolve
        },
        data: {
            //   authorities: ['ROLE_ANONYMOUS'],
            pageTitle: "Kolegij"
        },
        canActivate: [UserRouteAccessService]
    }
];
