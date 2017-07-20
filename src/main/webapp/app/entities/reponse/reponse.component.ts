import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiAlertService } from 'ng-jhipster';

import { Reponse } from './reponse.model';
import { ReponseService } from './reponse.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-reponse',
    templateUrl: './reponse.component.html'
})
export class ReponseComponent implements OnInit, OnDestroy {
reponses: Reponse[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private reponseService: ReponseService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.reponseService.query().subscribe(
            (res: ResponseWrapper) => {
                this.reponses = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInReponses();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Reponse) {
        return item.id;
    }
    registerChangeInReponses() {
        this.eventSubscriber = this.eventManager.subscribe('reponseListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
