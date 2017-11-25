import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BarbicanSharedModule } from '../../shared';
import {
    SocBroadMySuffixService,
    SocBroadMySuffixPopupService,
    SocBroadMySuffixComponent,
    SocBroadMySuffixDetailComponent,
    SocBroadMySuffixDialogComponent,
    SocBroadMySuffixPopupComponent,
    SocBroadMySuffixDeletePopupComponent,
    SocBroadMySuffixDeleteDialogComponent,
    socBroadRoute,
    socBroadPopupRoute,
} from './';

const ENTITY_STATES = [
    ...socBroadRoute,
    ...socBroadPopupRoute,
];

@NgModule({
    imports: [
        BarbicanSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SocBroadMySuffixComponent,
        SocBroadMySuffixDetailComponent,
        SocBroadMySuffixDialogComponent,
        SocBroadMySuffixDeleteDialogComponent,
        SocBroadMySuffixPopupComponent,
        SocBroadMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        SocBroadMySuffixComponent,
        SocBroadMySuffixDialogComponent,
        SocBroadMySuffixPopupComponent,
        SocBroadMySuffixDeleteDialogComponent,
        SocBroadMySuffixDeletePopupComponent,
    ],
    providers: [
        SocBroadMySuffixService,
        SocBroadMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BarbicanSocBroadMySuffixModule {}
