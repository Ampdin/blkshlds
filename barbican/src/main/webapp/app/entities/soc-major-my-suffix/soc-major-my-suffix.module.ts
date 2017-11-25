import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BarbicanSharedModule } from '../../shared';
import {
    SocMajorMySuffixService,
    SocMajorMySuffixPopupService,
    SocMajorMySuffixComponent,
    SocMajorMySuffixDetailComponent,
    SocMajorMySuffixDialogComponent,
    SocMajorMySuffixPopupComponent,
    SocMajorMySuffixDeletePopupComponent,
    SocMajorMySuffixDeleteDialogComponent,
    socMajorRoute,
    socMajorPopupRoute,
} from './';

const ENTITY_STATES = [
    ...socMajorRoute,
    ...socMajorPopupRoute,
];

@NgModule({
    imports: [
        BarbicanSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SocMajorMySuffixComponent,
        SocMajorMySuffixDetailComponent,
        SocMajorMySuffixDialogComponent,
        SocMajorMySuffixDeleteDialogComponent,
        SocMajorMySuffixPopupComponent,
        SocMajorMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        SocMajorMySuffixComponent,
        SocMajorMySuffixDialogComponent,
        SocMajorMySuffixPopupComponent,
        SocMajorMySuffixDeleteDialogComponent,
        SocMajorMySuffixDeletePopupComponent,
    ],
    providers: [
        SocMajorMySuffixService,
        SocMajorMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BarbicanSocMajorMySuffixModule {}
