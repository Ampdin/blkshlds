import { NgModule } from '@angular/core';
import { AngularEchartsModule } from 'ngx-echarts';

import { ThemeModule } from '../../@theme/theme.module';
import { JhiDashboardComponent } from './dashboard.component';
import { JhiStatusCardComponent } from './status-card/status-card.component';
import { JhiContactsComponent } from './contacts/contacts.component';
import { JhiRoomsComponent } from './rooms/rooms.component';
import { JhiRoomSelectorComponent } from './rooms/room-selector/room-selector.component';
import { JhiTemperatureComponent } from './temperature/temperature.component';
import { JhiTemperatureDraggerComponent } from './temperature/temperature-dragger/temperature-dragger.component';
import { JhiTeamComponent } from './team/team.component';
import { JhiKittenComponent } from './kitten/kitten.component';
import { JhiSecurityCamerasComponent } from './security-cameras/security-cameras.component';
import { JhiElectricityComponent } from './electricity/electricity.component';
import { JhiElectricityChartComponent } from './electricity/electricity-chart/electricity-chart.component';
import { JhiWeatherComponent } from './weather/weather.component';
import { JhiSolarComponent } from './solar/solar.component';
import { JhiPlayerComponent } from './rooms/player/player.component';
import { JhiTrafficComponent } from './traffic/traffic.component';
import { JhiTrafficChartComponent } from './traffic/traffic-chart.component';

@NgModule({
  imports: [
    ThemeModule,
    AngularEchartsModule,
  ],
  declarations: [
    JhiDashboardComponent,
    JhiStatusCardComponent,
    JhiTemperatureDraggerComponent,
    JhiContactsComponent,
    JhiRoomSelectorComponent,
    JhiTemperatureComponent,
    JhiRoomsComponent,
    JhiTeamComponent,
    JhiKittenComponent,
    JhiSecurityCamerasComponent,
    JhiElectricityComponent,
    JhiElectricityChartComponent,
    JhiWeatherComponent,
    JhiPlayerComponent,
    JhiSolarComponent,
    JhiTrafficComponent,
    JhiTrafficChartComponent,
  ],
})
export class DashboardModule { }
