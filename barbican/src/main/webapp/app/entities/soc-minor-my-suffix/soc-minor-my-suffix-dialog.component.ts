import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { SocMinorMySuffix } from './soc-minor-my-suffix.model';
import { SocMinorMySuffixPopupService } from './soc-minor-my-suffix-popup.service';
import { SocMinorMySuffixService } from './soc-minor-my-suffix.service';
import { SocMajor, SocMajorService } from '../soc-major';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-soc-minor-my-suffix-dialog',
    templateUrl: './soc-minor-my-suffix-dialog.component.html'
})
export class SocMinorMySuffixDialogComponent implements OnInit {

    socMinor: SocMinorMySuffix;
    isSaving: boolean;

    socmajors: SocMajor[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private socMinorService: SocMinorMySuffixService,
        private socMajorService: SocMajorService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.socMajorService.query()
            .subscribe((res: ResponseWrapper) => { this.socmajors = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
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
        this.dataUtils.clearInputImage(this.socMinor, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.socMinor.id !== undefined) {
            this.subscribeToSaveResponse(
                this.socMinorService.update(this.socMinor));
        } else {
            this.subscribeToSaveResponse(
                this.socMinorService.create(this.socMinor));
        }
    }

    private subscribeToSaveResponse(result: Observable<SocMinorMySuffix>) {
        result.subscribe((res: SocMinorMySuffix) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: SocMinorMySuffix) {
        this.eventManager.broadcast({ name: 'socMinorListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackSocMajorById(index: number, item: SocMajor) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-soc-minor-my-suffix-popup',
    template: ''
})
export class SocMinorMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private socMinorPopupService: SocMinorMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.socMinorPopupService
                    .open(SocMinorMySuffixDialogComponent as Component, params['id']);
            } else {
                this.socMinorPopupService
                    .open(SocMinorMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
