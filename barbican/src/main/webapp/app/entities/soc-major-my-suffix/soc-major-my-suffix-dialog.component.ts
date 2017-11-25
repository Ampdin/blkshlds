import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { SocMajorMySuffix } from './soc-major-my-suffix.model';
import { SocMajorMySuffixPopupService } from './soc-major-my-suffix-popup.service';
import { SocMajorMySuffixService } from './soc-major-my-suffix.service';

@Component({
    selector: 'jhi-soc-major-my-suffix-dialog',
    templateUrl: './soc-major-my-suffix-dialog.component.html'
})
export class SocMajorMySuffixDialogComponent implements OnInit {

    socMajor: SocMajorMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private socMajorService: SocMajorMySuffixService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
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
        this.dataUtils.clearInputImage(this.socMajor, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.socMajor.id !== undefined) {
            this.subscribeToSaveResponse(
                this.socMajorService.update(this.socMajor));
        } else {
            this.subscribeToSaveResponse(
                this.socMajorService.create(this.socMajor));
        }
    }

    private subscribeToSaveResponse(result: Observable<SocMajorMySuffix>) {
        result.subscribe((res: SocMajorMySuffix) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: SocMajorMySuffix) {
        this.eventManager.broadcast({ name: 'socMajorListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-soc-major-my-suffix-popup',
    template: ''
})
export class SocMajorMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private socMajorPopupService: SocMajorMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.socMajorPopupService
                    .open(SocMajorMySuffixDialogComponent as Component, params['id']);
            } else {
                this.socMajorPopupService
                    .open(SocMajorMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
