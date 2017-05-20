import { Injectable }              from '@angular/core';
import { Http, Response }          from '@angular/http';
import {RequestOptions, Request, RequestMethod} from '@angular/http';
import { Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';

import { Hero } from './hero';

@Injectable()
export class HeroService {
  
  private heroesUrl = '/pik/api/hero';  // URL to web API
  private heroNameUrl = '/pik/api/hero/byname';
  private heroIdUrl = '/pik/api/hero/byid';
  private headers = new Headers({ 'Content-Type': 'application/json' });
  constructor (private http: Http) {}
  
  getHeroes(): Observable<Hero[]> {
    return this.http
      .get(this.heroesUrl)
      .map(this.extractData)
      .catch(this.handleError);
  }
  
  create(name: string): Observable<Hero> {
    let options = new RequestOptions({ headers: this.headers });
    return this.http
      .post(this.heroesUrl, { name }, options)
      .map(this.extractData)
      .catch(this.handleError);
  }
  
   getHeroById(id: string): Observable<Hero> {
    const url = `${this.heroIdUrl}/${id}`;
    return this.http
      .get(url)
      .map(this.extractData)
      .catch(this.handleError);
  }
  
   getHeroByName(name: string): Observable<Hero> {
    const url = `${this.heroNameUrl}/${name}`;
    return this.http
      .get(url)
      .map(this.extractData)
      .catch(this.handleError);
  }
       
       
  delete(id: number): Observable<void> {
    const url = `${this.heroesUrl}/${id}`;
    return this.http
      .delete(url, {headers: this.headers})
      .map(() => null)
      .catch(this.handleError);
  }
      
 
  update(hero: Hero): Observable<Hero> {
    const url = `${this.heroIdUrl}/${hero.id}`;
    return this.http
      .put(url, JSON.stringify(hero), {headers: this.headers})
      .map(this.extractData)
      .catch(this.handleError);
  }
  
  private extractData(res: Response) {
    let body = res.json();
    return body;
  }
  
  private handleError (error: Response | any) {
    // In a real world app, you might use a remote logging infrastructure
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }
}
