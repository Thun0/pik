import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Hero }        from './hero';
import { HeroService } from './hero.service';

@Component({
  selector: 'my-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  errorMessage: string;
  heroes: Hero[] = [];
  mode = 'Observable';

  constructor(private heroService: HeroService) { }

  ngOnInit(): void {
    this.heroService.getHeroes()
      .subscribe(
       heroes => this.heroes = heroes.slice(0, 4),
       error =>  this.errorMessage = <any>error);
  }
  
  getHeroes() {
    this.heroService.getHeroes()
                     .subscribe(
                       heroes => this.heroes = heroes,
                       error =>  this.errorMessage = <any>error);
  }
  
  addHero(name: string) {
    if (!name) { return; }
    this.heroService.create(name)
                     .subscribe(
                       hero  => this.heroes.push(hero),
                       error =>  this.errorMessage = <any>error);
  }
  
}


/*
Copyright 2017 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/