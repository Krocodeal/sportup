import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Reponse } from './reponse.model';
import { ReponsePopupService } from './reponse-popup.service';
import { ReponseService } from './reponse.service';
import { Question, QuestionService } from '../question';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-reponse-dialog',
    templateUrl: './reponse-dialog.component.html'
})
export class ReponseDialogComponent implements OnInit {

    reponse: Reponse;
    isSaving: boolean;

    questions: Question[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private reponseService: ReponseService,
        private questionService: QuestionService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.questionService.query()
            .subscribe((res: ResponseWrapper) => { this.questions = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.reponse.id !== undefined) {
            this.subscribeToSaveResponse(
                this.reponseService.update(this.reponse));
        } else {
            this.subscribeToSaveResponse(
                this.reponseService.create(this.reponse));
        }
    }

    private subscribeToSaveResponse(result: Observable<Reponse>) {
        result.subscribe((res: Reponse) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Reponse) {
        this.eventManager.broadcast({ name: 'reponseListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    trackQuestionById(index: number, item: Question) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-reponse-popup',
    template: ''
})
export class ReponsePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private reponsePopupService: ReponsePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.reponsePopupService
                    .open(ReponseDialogComponent as Component, params['id']);
            } else {
                this.reponsePopupService
                    .open(ReponseDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
