import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { SocMajorMySuffix } from './soc-major-my-suffix.model';
import { SocMajorMySuffixService } from './soc-major-my-suffix.service';

@Component({
    selector: 'jhi-soc-major-my-suffix-detail',
    templateUrl: './soc-major-my-suffix-detail.component.html'
})
export class SocMajorMySuffixDetailComponent implements OnInit, OnDestroy {

    socMajor: SocMajorMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private socMajorService: SocMajorMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSocMajors();
    }

    load(id) {
        this.socMajorService.find(id).subscribe((socMajor) => {
            this.socMajor = socMajor;
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

    registerChangeInSocMajors() {
        this.eventSubscriber = this.eventManager.subscribe(
            'socMajorListModification',
            (response) => this.load(this.socMajor.id)
        );
    }
}
