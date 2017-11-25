import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { BarbicanRegionMySuffixModule } from './region-my-suffix/region-my-suffix.module';
import { BarbicanSocMajorMySuffixModule } from './soc-major-my-suffix/soc-major-my-suffix.module';
import { BarbicanCountryMySuffixModule } from './country-my-suffix/country-my-suffix.module';
import { BarbicanSocMinorMySuffixModule } from './soc-minor-my-suffix/soc-minor-my-suffix.module';
import { BarbicanSocBroadMySuffixModule } from './soc-broad-my-suffix/soc-broad-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        BarbicanRegionMySuffixModule,
        BarbicanSocMajorMySuffixModule,
        BarbicanCountryMySuffixModule,
        BarbicanSocMinorMySuffixModule,
        BarbicanSocBroadMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BarbicanEntityModule {}
