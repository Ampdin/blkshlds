import { Component } from '@angular/core';

// TODO: move layouts into the framework
@Component({
  selector: 'jhi-ngx-one-column-layout',
  styleUrls: ['./one-column.layout.scss'],
  template: `
    <nb-layout>
      <nb-layout-header fixed>
        <jhi-ngx-header></jhi-ngx-header>
      </nb-layout-header>

      <nb-sidebar class="menu-sidebar" tag="menu-sidebar" responsive>
        <nb-sidebar-header>
          <a href="#" class="btn btn-hero-success main-btn">
            <i class="ion ion-social-github"></i> <span>Support Us</span>
          </a>
        </nb-sidebar-header>
        <ng-content select="nb-menu"></ng-content>
      </nb-sidebar>

      <nb-layout-column>
        <ng-content select="router-outlet"></ng-content>
      </nb-layout-column>

      <nb-layout-footer fixed>
        <jhi-ngx-footer></jhi-ngx-footer>
      </nb-layout-footer>
    </nb-layout>
  `,
})
export class JhiOneColumnLayoutComponent {
}
