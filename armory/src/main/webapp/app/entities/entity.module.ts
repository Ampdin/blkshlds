import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ArmoryRegionMySuffixModule } from './region/region-my-suffix.module';
import { ArmoryCountryMySuffixModule } from './country/country-my-suffix.module';
import { ArmoryLocationMySuffixModule } from './location/location-my-suffix.module';
import { ArmoryDepartmentMySuffixModule } from './department/department-my-suffix.module';
import { ArmoryTaskMySuffixModule } from './task/task-my-suffix.module';
import { ArmoryEmployeeMySuffixModule } from './employee/employee-my-suffix.module';
import { ArmoryJobMySuffixModule } from './job/job-my-suffix.module';
import { ArmoryJobHistoryMySuffixModule } from './job-history/job-history-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        ArmoryRegionMySuffixModule,
        ArmoryCountryMySuffixModule,
        ArmoryLocationMySuffixModule,
        ArmoryDepartmentMySuffixModule,
        ArmoryTaskMySuffixModule,
        ArmoryEmployeeMySuffixModule,
        ArmoryJobMySuffixModule,
        ArmoryJobHistoryMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArmoryEntityModule {}
