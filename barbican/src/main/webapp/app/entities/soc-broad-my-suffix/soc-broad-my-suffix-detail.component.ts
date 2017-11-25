import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { SocBroadMySuffix } from './soc-broad-my-suffix.model';
import { SocBroadMySuffixService } from './soc-broad-my-suffix.service';

@Component({
    selector: 'jhi-soc-broad-my-suffix-detail',
    templateUrl: './soc-broad-my-suffix-detail.component.html'
})
export class SocBroadMySuffixDetailComponent implements OnInit, OnDestroy {

    socBroad: SocBroadMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private socBroadService: SocBroadMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSocBroads();
    }

    load(id) {
        this.socBroadService.find(id).subscribe((socBroad) => {
            this.socBroad = socBroad;
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

    registerChangeInSocBroads() {
        this.eventSubscriber = this.eventManager.subscribe(
            'socBroadListModification',
            (response) => this.load(this.socBroad.id)
        );
    }
}
