import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { JhiMapsComponent } from './maps.component';
import { GmapsComponent } from './gmaps/gmaps.component';
import { LeafletComponent } from './leaflet/leaflet.component';
import { JhiBubbleMapComponent } from './bubble/bubble-map.component';

const routes: Routes = [{
  path: '',
  component: JhiMapsComponent,
  children: [{
    path: 'gmaps',
    component: GmapsComponent,
  }, {
    path: 'leaflet',
    component: LeafletComponent,
  }, {
    path: 'bubble',
    component: JhiBubbleMapComponent,
  }],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MapsRoutingModule { }

export const routedComponents = [
  JhiMapsComponent,
  GmapsComponent,
  LeafletComponent,
  JhiBubbleMapComponent,
];
