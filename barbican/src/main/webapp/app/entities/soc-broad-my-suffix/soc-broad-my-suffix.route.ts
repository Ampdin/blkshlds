import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { SocBroadMySuffixComponent } from './soc-broad-my-suffix.component';
import { SocBroadMySuffixDetailComponent } from './soc-broad-my-suffix-detail.component';
import { SocBroadMySuffixPopupComponent } from './soc-broad-my-suffix-dialog.component';
import { SocBroadMySuffixDeletePopupComponent } from './soc-broad-my-suffix-delete-dialog.component';

export const socBroadRoute: Routes = [
    {
        path: 'soc-broad-my-suffix',
        component: SocBroadMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'barbicanApp.socBroad.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'soc-broad-my-suffix/:id',
        component: SocBroadMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'barbicanApp.socBroad.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const socBroadPopupRoute: Routes = [
    {
        path: 'soc-broad-my-suffix-new',
        component: SocBroadMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'barbicanApp.socBroad.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'soc-broad-my-suffix/:id/edit',
        component: SocBroadMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'barbicanApp.socBroad.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'soc-broad-my-suffix/:id/delete',
        component: SocBroadMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'barbicanApp.socBroad.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
