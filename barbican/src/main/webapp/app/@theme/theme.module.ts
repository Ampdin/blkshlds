import { ModuleWithProviders, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import {
    NbActionsModule,
    NbCardModule,
    NbLayoutModule,
    NbMenuModule,
    NbRouteTabsetModule,
    NbSearchModule,
    NbSidebarModule,
    NbTabsetModule,
    NbThemeModule,
    NbUserModule,
    NbCheckboxModule,
} from '@nebular/theme';

import {
    JhiFooterComponent,
    JhiHeaderComponent,
    JhiSearchInputComponent,
    JhiThemeSettingsComponent,
    JhiThemeSwitcherComponent,
    JhiTinyMCEComponent
} from './components';
import { CapitalizePipe, PluralPipe, RoundPipe, TimingPipe } from './pipes';
import {
    JhiOneColumnLayoutComponent,
    JhiSampleLayoutComponent,
    JhiThreeColumnsLayoutComponent,
    JhiTwoColumnsLayoutComponent,
} from './layouts';
import { DEFAULT_THEME } from './../../content/scss/theme/theme.default';
import { COSMIC_THEME } from './../../content/scss/theme/theme.cosmic';

const BASE_MODULES = [CommonModule, FormsModule, ReactiveFormsModule];

const NB_MODULES = [
    NbCardModule,
    NbLayoutModule,
    NbTabsetModule,
    NbRouteTabsetModule,
    NbMenuModule,
    NbUserModule,
    NbActionsModule,
    NbSearchModule,
    NbSidebarModule,
    NbCheckboxModule,
    NgbModule,
];

const COMPONENTS = [
    JhiThemeSwitcherComponent,
    JhiHeaderComponent,
    JhiFooterComponent,
    JhiSearchInputComponent,
    JhiThemeSettingsComponent,
    JhiTinyMCEComponent,
    JhiOneColumnLayoutComponent,
    JhiSampleLayoutComponent,
    JhiThreeColumnsLayoutComponent,
    JhiTwoColumnsLayoutComponent,
];

const PIPES = [
    CapitalizePipe,
    PluralPipe,
    RoundPipe,
    TimingPipe,
];

const NB_THEME_PROVIDERS = [
    ...NbThemeModule.forRoot(
        {
            name: 'cosmic',
        },
        [ DEFAULT_THEME, COSMIC_THEME ],
    ).providers,
    ...NbSidebarModule.forRoot().providers,
    ...NbMenuModule.forRoot().providers,
];

@NgModule({
    imports: [...BASE_MODULES, ...NB_MODULES],
    exports: [...BASE_MODULES, ...NB_MODULES, ...COMPONENTS, ...PIPES],
    declarations: [...COMPONENTS, ...PIPES],
})
export class ThemeModule {
    static forRoot(): ModuleWithProviders {
        return <ModuleWithProviders>{
            ngModule: ThemeModule,
            providers: [...NB_THEME_PROVIDERS],
        };
    }
}
