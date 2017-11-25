import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { SocMinorMySuffix } from './soc-minor-my-suffix.model';
import { SocMinorMySuffixService } from './soc-minor-my-suffix.service';

@Component({
    selector: 'jhi-soc-minor-my-suffix-detail',
    templateUrl: './soc-minor-my-suffix-detail.component.html'
})
export class SocMinorMySuffixDetailComponent implements OnInit, OnDestroy {

    socMinor: SocMinorMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private socMinorService: SocMinorMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSocMinors();
    }

    load(id) {
        this.socMinorService.find(id).subscribe((socMinor) => {
            this.socMinor = socMinor;
        });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSocMinors() {
        this.eventSubscriber = this.eventManager.subscribe(
            'socMinorListModification',
            (response) => this.load(this.socMinor.id)
        );
    }
}
