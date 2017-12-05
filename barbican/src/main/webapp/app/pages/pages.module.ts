import { NgModule } from '@angular/core';

import { JhiPagesComponent } from './pages.component';
import { DashboardModule } from './dashboard/dashboard.module';
import { JhiPagesRoutingModule } from './pages-routing.module';
import { ThemeModule } from '../@theme/theme.module';

const PAGES_COMPONENTS = [
  JhiPagesComponent,
];

@NgModule({
  imports: [
    JhiPagesRoutingModule,
    ThemeModule,
    DashboardModule,
  ],
  declarations: [
    ...PAGES_COMPONENTS,
  ],
})
export class PagesModule {
}
