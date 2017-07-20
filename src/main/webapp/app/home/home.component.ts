import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Account, LoginModalService, Principal } from '../shared';
import {QuestionService} from '../entities/question/question.service';
import {ReponseService} from '../entities/reponse/reponse.service';
import {Question} from "../entities/question/question.model";
import {Reponse} from "../entities/reponse/reponse.model";
import {ResponseWrapper} from "../shared/model/response-wrapper.model";

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: [
        'home.scss'
    ]

})
export class HomeComponent implements OnInit {
    account: Account;
    modalRef: NgbModalRef;
    question: Question;
    reponses: Reponse[];

    constructor(
        private principal: Principal,
        private loginModalService: LoginModalService,
        private questionService: QuestionService,
        private reponseService: ReponseService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();
        this.questionService.find(2651).subscribe(res => {
            this.question = res;
        });
        this.reponseService.findForQuestionId(this.question.id).subscribe((res: ResponseWrapper) => {
            this.reponses = res.json;
            console.log(this.reponses)
        });
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', (message) => {
            this.principal.identity().then((account) => {
                this.account = account;
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }
}
