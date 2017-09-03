import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { EmployeeMySuffix } from './employee-my-suffix.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class EmployeeMySuffixService {

    private resourceUrl = 'api/employees';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(employee: EmployeeMySuffix): Observable<EmployeeMySuffix> {
        const copy = this.convert(employee);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(employee: EmployeeMySuffix): Observable<EmployeeMySuffix> {
        const copy = this.convert(employee);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<EmployeeMySuffix> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        for (let i = 0; i < jsonResponse.length; i++) {
            this.convertItemFromServer(jsonResponse[i]);
        }
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convertItemFromServer(entity: any) {
        entity.hireDate = this.dateUtils
            .convertDateTimeFromServer(entity.hireDate);
    }

    private convert(employee: EmployeeMySuffix): EmployeeMySuffix {
        const copy: EmployeeMySuffix = Object.assign({}, employee);

        copy.hireDate = this.dateUtils.toDate(employee.hireDate);
        return copy;
    }
}
