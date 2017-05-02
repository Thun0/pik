import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent }   from './dashboard.component';
import { HeroesComponent }      from './heroes.component';
import { HeroDetailComponent }  from './hero-detail.component';

const routes: Routes = [
  { path: 'app', redirectTo: '/app/dashboard', pathMatch: 'full' },
  { path: '', redirectTo: '/app/dashboard', pathMatch: 'full' },
  { path: 'app/dashboard',  component: DashboardComponent },
  { path: 'app/detail/:id', component: HeroDetailComponent },
  { path: 'app/heroes',     component: HeroesComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}


/*
Copyright 2017 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/