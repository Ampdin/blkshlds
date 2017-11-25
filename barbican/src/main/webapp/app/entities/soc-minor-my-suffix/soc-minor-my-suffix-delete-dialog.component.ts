import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SocMinorMySuffix } from './soc-minor-my-suffix.model';
import { SocMinorMySuffixPopupService } from './soc-minor-my-suffix-popup.service';
import { SocMinorMySuffixService } from './soc-minor-my-suffix.service';

@Component({
    selector: 'jhi-soc-minor-my-suffix-delete-dialog',
    templateUrl: './soc-minor-my-suffix-delete-dialog.component.html'
})
export class SocMinorMySuffixDeleteDialogComponent {

    socMinor: SocMinorMySuffix;

    constructor(
        private socMinorService: SocMinorMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.socMinorService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'socMinorListModification',
                content: 'Deleted an socMinor'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-soc-minor-my-suffix-delete-popup',
    template: ''
})
export class SocMinorMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private socMinorPopupService: SocMinorMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.socMinorPopupService
                .open(SocMinorMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
