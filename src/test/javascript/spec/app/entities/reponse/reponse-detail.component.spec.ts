/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { SportupTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ReponseDetailComponent } from '../../../../../../main/webapp/app/entities/reponse/reponse-detail.component';
import { ReponseService } from '../../../../../../main/webapp/app/entities/reponse/reponse.service';
import { Reponse } from '../../../../../../main/webapp/app/entities/reponse/reponse.model';

describe('Component Tests', () => {

    describe('Reponse Management Detail Component', () => {
        let comp: ReponseDetailComponent;
        let fixture: ComponentFixture<ReponseDetailComponent>;
        let service: ReponseService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SportupTestModule],
                declarations: [ReponseDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ReponseService,
                    JhiEventManager
                ]
            }).overrideTemplate(ReponseDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ReponseDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReponseService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Reponse(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.reponse).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
