import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";

import { SERVER_API_URL } from "app/app.constants";
import { createRequestOption } from "app/shared";
import { IStudent } from "app/shared/model/student.model";

type EntityResponseType = HttpResponse<IStudent>;
type EntityArrayResponseType = HttpResponse<IStudent[]>;

@Injectable({ providedIn: "root" })
export class StudentService {
    public resourceUrl = SERVER_API_URL + "api/students";
    public resourceUrl2 = SERVER_API_URL + "api/students/by_schedule";

    constructor(private http: HttpClient) {}

    create(student: IStudent): Observable<EntityResponseType> {
        return this.http.post<IStudent>(this.resourceUrl, student, {
            observe: "response"
        });
    }

    update(student: IStudent): Observable<EntityResponseType> {
        return this.http.put<IStudent>(this.resourceUrl, student, {
            observe: "response"
        });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IStudent>(`${this.resourceUrl}/${id}`, {
            observe: "response"
        });
    }

    findAllStudentsByScheduleId(
        id: number
    ): Observable<EntityArrayResponseType> {
        return this.http.get<IStudent[]>(`${this.resourceUrl2}/${id}`, {
            observe: "response"
        });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IStudent[]>(this.resourceUrl, {
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
