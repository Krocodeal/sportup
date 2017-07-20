import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager , JhiDataUtils } from 'ng-jhipster';

import { Sport } from './sport.model';
import { SportService } from './sport.service';

@Component({
    selector: 'jhi-sport-detail',
    templateUrl: './sport-detail.component.html'
})
export class SportDetailComponent implements OnInit, OnDestroy {

    sport: Sport;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private sportService: SportService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSports();
    }

    load(id) {
        this.sportService.find(id).subscribe((sport) => {
            this.sport = sport;
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

    registerChangeInSports() {
        this.eventSubscriber = this.eventManager.subscribe(
            'sportListModification',
            (response) => this.load(this.sport.id)
        );
    }
}
