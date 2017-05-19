import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { Hero }                from './hero';
import { HeroService }         from './hero.service';

@Component({
  selector: 'my-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: [ './heroes.component.css' ]
})
export class HeroesComponent implements OnInit {
  heroes: Hero[];
  selectedHero: Hero;
  errorMessage: string = "error heroes.component";

  constructor(
    private heroService: HeroService,
    private router: Router) { }

  getHeroes(): void {
    this.heroService.getHeroes()
        .subscribe(
        heroes => this.heroes = heroes,
        error =>  this.errorMessage = <any>error);
  }

  add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.heroService.create(name)
      .subscribe(hero => {
        this.heroes.push(hero);
        this.selectedHero = null;
      });
  }

/**
  delete(hero: Hero): void {
    console.log("heroes.component.delete()");
    this.heroService
        .delete(hero.id)
        .subscribe(() => {
          this.heroes = this.heroes.filter(h => h !== hero);
          if (this.selectedHero === hero) { this.selectedHero = null; }
        });
  }
*/
  ngOnInit(): void {
    this.getHeroes();
  }

  onSelect(hero: Hero): void {
    this.selectedHero = hero;
  }

  gotoDetail(): void {
    this.router.navigate(['/app/detail', this.selectedHero.id]);
  }
}


/*
Copyright 2017 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/