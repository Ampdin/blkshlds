import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { SocMajorMySuffix } from './soc-major-my-suffix.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class SocMajorMySuffixService {

    private resourceUrl = '/armory/api/soc-majors';
    private resourceSearchUrl = '/armory/api/_search/soc-majors';

    constructor(private http: Http) { }

    create(socMajor: SocMajorMySuffix): Observable<SocMajorMySuffix> {
        const copy = this.convert(socMajor);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(socMajor: SocMajorMySuffix): Observable<SocMajorMySuffix> {
        const copy = this.convert(socMajor);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<SocMajorMySuffix> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
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

    search(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceSearchUrl, options)
            .map((res: any) => this.convertResponse(res));
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to SocMajorMySuffix.
     */
    private convertItemFromServer(json: any): SocMajorMySuffix {
        const entity: SocMajorMySuffix = Object.assign(new SocMajorMySuffix(), json);
        return entity;
    }

    /**
     * Convert a SocMajorMySuffix to a JSON which can be sent to the server.
     */
    private convert(socMajor: SocMajorMySuffix): SocMajorMySuffix {
        const copy: SocMajorMySuffix = Object.assign({}, socMajor);
        return copy;
    }
}
