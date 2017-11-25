import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SocBroadMySuffix } from './soc-broad-my-suffix.model';
import { SocBroadMySuffixPopupService } from './soc-broad-my-suffix-popup.service';
import { SocBroadMySuffixService } from './soc-broad-my-suffix.service';

@Component({
    selector: 'jhi-soc-broad-my-suffix-delete-dialog',
    templateUrl: './soc-broad-my-suffix-delete-dialog.component.html'
})
export class SocBroadMySuffixDeleteDialogComponent {

    socBroad: SocBroadMySuffix;

    constructor(
        private socBroadService: SocBroadMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.socBroadService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'socBroadListModification',
                content: 'Deleted an socBroad'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-soc-broad-my-suffix-delete-popup',
    template: ''
})
export class SocBroadMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private socBroadPopupService: SocBroadMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.socBroadPopupService
                .open(SocBroadMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
