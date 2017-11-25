import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { SocBroadMySuffix } from './soc-broad-my-suffix.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class SocBroadMySuffixService {

    private resourceUrl = '/armory/api/soc-broads';
    private resourceSearchUrl = '/armory/api/_search/soc-broads';

    constructor(private http: Http) { }

    create(socBroad: SocBroadMySuffix): Observable<SocBroadMySuffix> {
        const copy = this.convert(socBroad);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(socBroad: SocBroadMySuffix): Observable<SocBroadMySuffix> {
        const copy = this.convert(socBroad);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<SocBroadMySuffix> {
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
     * Convert a returned JSON object to SocBroadMySuffix.
     */
    private convertItemFromServer(json: any): SocBroadMySuffix {
        const entity: SocBroadMySuffix = Object.assign(new SocBroadMySuffix(), json);
        return entity;
    }

    /**
     * Convert a SocBroadMySuffix to a JSON which can be sent to the server.
     */
    private convert(socBroad: SocBroadMySuffix): SocBroadMySuffix {
        const copy: SocBroadMySuffix = Object.assign({}, socBroad);
        return copy;
    }
}
