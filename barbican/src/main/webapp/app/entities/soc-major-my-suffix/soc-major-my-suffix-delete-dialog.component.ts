import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SocMajorMySuffix } from './soc-major-my-suffix.model';
import { SocMajorMySuffixPopupService } from './soc-major-my-suffix-popup.service';
import { SocMajorMySuffixService } from './soc-major-my-suffix.service';

@Component({
    selector: 'jhi-soc-major-my-suffix-delete-dialog',
    templateUrl: './soc-major-my-suffix-delete-dialog.component.html'
})
export class SocMajorMySuffixDeleteDialogComponent {

    socMajor: SocMajorMySuffix;

    constructor(
        private socMajorService: SocMajorMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.socMajorService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'socMajorListModification',
                content: 'Deleted an socMajor'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-soc-major-my-suffix-delete-popup',
    template: ''
})
export class SocMajorMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private socMajorPopupService: SocMajorMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.socMajorPopupService
                .open(SocMajorMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
