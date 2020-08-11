import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";

import { SERVER_API_URL } from "app/app.constants";
import { createRequestOption } from "app/shared";
import { IScheduler } from "app/shared/model/scheduler.model";

type EntityResponseType = HttpResponse<IScheduler>;
type EntityArrayResponseType = HttpResponse<IScheduler[]>;

@Injectable({ providedIn: "root" })
export class SchedulerService {
    public resourceUrl = SERVER_API_URL + "api/schedules";
    public resourceUrl2 = SERVER_API_URL + "api/schedules/get";
    public resourceUrl3 = SERVER_API_URL + "api/schedules/addStudent";
    public resourceUrl4 = SERVER_API_URL + "api/schedules/removeStudent";

    constructor(private http: HttpClient) {}

    create(schedule: IScheduler): Observable<EntityResponseType> {
        return this.http.post<IScheduler>(this.resourceUrl, schedule, {
            observe: "response"
        });
    }

    update(schedule: IScheduler): Observable<EntityResponseType> {
        return this.http.put<IScheduler>(this.resourceUrl, schedule, {
            observe: "response"
        });
    }

    incrementNumberOfSubmittedStudents(
        schedule: IScheduler
    ): Observable<EntityResponseType> {
        return this.http.put<IScheduler>(this.resourceUrl3, schedule, {
            observe: "response"
        });
    }

    decrementNumberOfSubmittedStudents(
        schedule: IScheduler
    ): Observable<EntityResponseType> {
        return this.http.put<IScheduler>(this.resourceUrl4, schedule, {
            observe: "response"
        });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IScheduler>(`${this.resourceUrl}/${id}`, {
            observe: "response"
        });
    }

    findAllAvaliableSchedulesToCurrentTimeBySubjectName(
        subjectName: string
    ): Observable<EntityArrayResponseType> {
        const url_param = new HttpParams().set("subjectName", subjectName);
        return this.http.get<IScheduler[]>(this.resourceUrl2, {
            params: url_param,
            observe: "response"
        });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IScheduler[]>(this.resourceUrl, {
            params: options,
            observe: "response"
        });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {
            observe: "response"
        });
    }
}
