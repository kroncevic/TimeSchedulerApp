import { Injectable } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import {
    Resolve,
    ActivatedRouteSnapshot,
    RouterStateSnapshot,
    Routes
} from "@angular/router";
import { JhiResolvePagingParams } from "ng-jhipster";
import { UserRouteAccessService } from "app/core";
import { Observable, of } from "rxjs";
import { filter, map } from "rxjs/operators";
import { Scheduler } from "app/shared/model/scheduler.model";
import { SchedulerService } from "./scheduler.service";
import { SchedulerComponent } from "./scheduler.component";
import { SchedulerUpdateComponent } from "./scheduler-update.component";
import { SchedulerDeletePopupComponent } from "./scheduler-delete-dialog.component";
import { SchedulerDetailComponent } from "./scheduler-detail.component";
import { SchedulerStudentViewComponent } from "./scheduler-student-view.component";
import { SchedulerReservationComponent } from "./scheduler-reservation.component";
import { IScheduler } from "app/shared/model/scheduler.model";

@Injectable({ providedIn: "root" })
export class SchedulerResolve implements Resolve<IScheduler> {
    constructor(private service: SchedulerService) {}

    resolve(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): Observable<Scheduler> {
        const id = route.params["id"] ? route.params["id"] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    filter((response: HttpResponse<Scheduler>) => response.ok),
                    map((scheduler: HttpResponse<Scheduler>) => scheduler.body)
                );
        }
        return of(new Scheduler());
    }
}

export const schedulerRoute: Routes = [
    {
        path: "schedule",
        pathMatch: "full",
        component: SchedulerComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            defaultSort: "id,asc",
            pageTitle: "Termini"
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: "schedule/new",
        component: SchedulerUpdateComponent,
        resolve: {
            schedule: SchedulerResolve
        },
        data: {
            authorities: ["ROLE_ADMIN"],
            pageTitle: "Termini"
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: "schedule/:id/edit",
        component: SchedulerUpdateComponent,
        resolve: {
            schedule: SchedulerResolve
        },
        data: {
            authorities: ["ROLE_ADMIN"],
            pageTitle: "Termini"
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: "schedule/:id/view",
        component: SchedulerDetailComponent,
        resolve: {
            schedule: SchedulerResolve
        },
        data: {
            pageTitle: "Termin"
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: "schedule/get",
        component: SchedulerStudentViewComponent,
        resolve: {
            schedule: SchedulerResolve
        },
        data: {
            pageTitle: "Termin"
        },
        canActivate: [UserRouteAccessService]
    }
];

export const schedulerPopupRoute: Routes = [
    {
        path: "schedule/:id/delete",
        component: SchedulerDeletePopupComponent,
        resolve: {
            schedule: SchedulerResolve
        },
        data: {
            authorities: ["ROLE_ADMIN"],
            pageTitle: "Schedules"
        },
        canActivate: [UserRouteAccessService],
        outlet: "popup"
    }
];
