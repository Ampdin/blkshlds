import { Component, OnDestroy } from '@angular/core';
import { NbThemeService } from '@nebular/theme';

@Component({
  selector: 'jhi-ngx-traffic',
  styleUrls: ['./traffic.component.scss'],
  template: `
    <nb-card size="xsmall">
      <nb-card-header>
        <span>Traffic Consumption</span>
        <div class="dropdown ghost-dropdown" ngbDropdown>
          <button type="button" class="btn btn-sm" ngbDropdownToggle
                  [ngClass]="{ 'btn-success': currentTheme == 'default', 'btn-primary': currentTheme != 'default'}">
            {{ type }}
          </button>
          <ul ngbDropdown class="dropdown-menu">
            <li class="dropdown-item" *ngFor="let t of types" (click)="type = t">{{ t }}</li>
          </ul>
        </div>
      </nb-card-header>
      <nb-card-body class="p-0">
        <jhi-ngx-traffic-chart></jhi-ngx-traffic-chart>
      </nb-card-body>
    </nb-card>
  `,
})
export class JhiTrafficComponent implements OnDestroy {
  type = 'month';
  types = ['week', 'month', 'year'];
  currentTheme: string;
  themeSubscription: any;

  constructor(private themeService: NbThemeService) {
    this.themeSubscription = this.themeService.getJsTheme().subscribe((theme) => {
      this.currentTheme = theme.name;
    });
  }

  ngOnDestroy() {
    this.themeSubscription.unsubscribe();
  }
}
