import { Component } from '@angular/core';

import { MENU_ITEMS } from './pages-menu';

@Component({
  selector: 'jhi-ngx-pages',
  template: `
    <jhi-ngx-sample-layout>
      <nb-menu [items]="menu"></nb-menu>
      <router-outlet></router-outlet>
    </jhi-ngx-sample-layout>
  `,
})
export class JhiPagesComponent {

  menu = MENU_ITEMS;
}
