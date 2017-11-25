import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { SocMinorMySuffix } from './soc-minor-my-suffix.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class SocMinorMySuffixService {

    private resourceUrl = '/armory/api/soc-minors';
    private resourceSearchUrl = '/armory/api/_search/soc-minors';

    constructor(private http: Http) { }

    create(socMinor: SocMinorMySuffix): Observable<SocMinorMySuffix> {
        const copy = this.convert(socMinor);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(socMinor: SocMinorMySuffix): Observable<SocMinorMySuffix> {
        const copy = this.convert(socMinor);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<SocMinorMySuffix> {
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
     * Convert a returned JSON object to SocMinorMySuffix.
     */
    private convertItemFromServer(json: any): SocMinorMySuffix {
        const entity: SocMinorMySuffix = Object.assign(new SocMinorMySuffix(), json);
        return entity;
    }

    /**
     * Convert a SocMinorMySuffix to a JSON which can be sent to the server.
     */
    private convert(socMinor: SocMinorMySuffix): SocMinorMySuffix {
        const copy: SocMinorMySuffix = Object.assign({}, socMinor);
        return copy;
    }
}
