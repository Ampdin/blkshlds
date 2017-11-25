import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { SocBroadMySuffix } from './soc-broad-my-suffix.model';
import { SocBroadMySuffixPopupService } from './soc-broad-my-suffix-popup.service';
import { SocBroadMySuffixService } from './soc-broad-my-suffix.service';
import { SocMinor, SocMinorService } from '../soc-minor';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-soc-broad-my-suffix-dialog',
    templateUrl: './soc-broad-my-suffix-dialog.component.html'
})
export class SocBroadMySuffixDialogComponent implements OnInit {

    socBroad: SocBroadMySuffix;
    isSaving: boolean;

    socminors: SocMinor[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private socBroadService: SocBroadMySuffixService,
        private socMinorService: SocMinorService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.socMinorService.query()
            .subscribe((res: ResponseWrapper) => { this.socminors = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.socBroad, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.socBroad.id !== undefined) {
            this.subscribeToSaveResponse(
                this.socBroadService.update(this.socBroad));
        } else {
            this.subscribeToSaveResponse(
                this.socBroadService.create(this.socBroad));
        }
    }

    private subscribeToSaveResponse(result: Observable<SocBroadMySuffix>) {
        result.subscribe((res: SocBroadMySuffix) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: SocBroadMySuffix) {
        this.eventManager.broadcast({ name: 'socBroadListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackSocMinorById(index: number, item: SocMinor) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-soc-broad-my-suffix-popup',
    template: ''
})
export class SocBroadMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private socBroadPopupService: SocBroadMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.socBroadPopupService
                    .open(SocBroadMySuffixDialogComponent as Component, params['id']);
            } else {
                this.socBroadPopupService
                    .open(SocBroadMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
