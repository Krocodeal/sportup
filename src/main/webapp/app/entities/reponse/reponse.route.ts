import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ReponseComponent } from './reponse.component';
import { ReponseDetailComponent } from './reponse-detail.component';
import { ReponsePopupComponent } from './reponse-dialog.component';
import { ReponseDeletePopupComponent } from './reponse-delete-dialog.component';

export const reponseRoute: Routes = [
    {
        path: 'reponse',
        component: ReponseComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Reponses'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'reponse/:id',
        component: ReponseDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Reponses'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const reponsePopupRoute: Routes = [
    {
        path: 'reponse-new',
        component: ReponsePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Reponses'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'reponse/:id/edit',
        component: ReponsePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Reponses'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'reponse/:id/delete',
        component: ReponseDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Reponses'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
