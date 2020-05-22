import { Injectable } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import {
    Resolve,
    ActivatedRouteSnapshot,
    RouterStateSnapshot,
    Routes
} from "@angular/router";
import { UserRouteAccessService } from "app/core";
import { Observable, of } from "rxjs";
import { filter, map } from "rxjs/operators";
import { StudentService } from "./student.service";
import { SchedulerReservationComponent } from "./scheduler-reservation.component";
import { IStudent, Student } from "app/shared/model/student.model";

@Injectable({ providedIn: "root" })
export class StudentResolve implements Resolve<IStudent> {
    constructor(private service: StudentService) {}

    resolve(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): Observable<Student> {
        const id = route.params["id"] ? route.params["id"] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    filter((response: HttpResponse<Student>) => response.ok),
                    map((student: HttpResponse<Student>) => student.body)
                );
        }
        return of(new Student());
    }
}

export const studentRoute: Routes = [
    {
        path: "student",
        component: SchedulerReservationComponent,
        resolve: {
            schedule: StudentResolve
        },
        data: {
            // authorities: ['ROLE_ANONYMOUS'],
            pageTitle: "Termin"
        },
        canActivate: [UserRouteAccessService]
    }
];
