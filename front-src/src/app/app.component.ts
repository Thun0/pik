import { Component } from '@angular/core';

import { Hero } from './hero';

const HEROES: Hero[] = [
  { id: 11, name: 'Mr. Nice' }
];

@Component({
  selector: 'my-app',
  template: `
    <h1 class="center">
        Moduł wyświetlania dla Korpusomatu
    </h1>
    <div class="center">
        <textarea id="text_area" rows="12" cols="100" style="resize: none;" readonly>
            
        </textarea>
        <br />
        <button (click)="loadFile('systemfile')">Wczytaj plik</button>
        <input id="file-input" type="file" name="name" style="display: none;" />
    </div>


    <h2>My Heroes</h2>
    <ul class="heroes">
      <li *ngFor="let hero of heroes"
        [class.selected]="hero === selectedHero"
        (click)="onSelect(hero)">
        <span class="badge">{{hero.id}}</span> {{hero.name}}
      </li>
    </ul>
    <hero-detail [hero]="selectedHero"></hero-detail>
  `,
  styles: [`
    .center {
        margin-left: auto;
        margin-right: auto;
        text-align: center;
    }
    .selected {
      background-color: #CFD8DC !important;
      color: white;
    }
    .heroes {
      margin: 0 0 2em 0;
      list-style-type: none;
      padding: 0;
      width: 15em;
    }
    .heroes li {
      cursor: pointer;
      position: relative;
      left: 0;
      background-color: #EEE;
      margin: .5em;
      padding: .3em 0;
      height: 1.6em;
      border-radius: 4px;
    }
    .heroes li.selected:hover {
      background-color: #BBD8DC !important;
      color: white;
    }
    .heroes li:hover {
      color: #607D8B;
      background-color: #DDD;
      left: .1em;
    }
    .heroes .text {
      position: relative;
      top: -3px;
    }
    .heroes .badge {
      display: inline-block;
      font-size: small;
      color: white;
      padding: 0.8em 0.7em 0 0.7em;
      background-color: #607D8B;
      line-height: 1em;
      position: relative;
      left: -1px;
      top: -4px;
      height: 1.8em;
      margin-right: .8em;
      border-radius: 4px 0 0 4px;
    }
  `]
})

export class AppComponent {
  listenerRegistered = false;
  title = 'Tour of Heroes';
  heroes = HEROES;
  selectedHero: Hero;
    

  onSelect(hero: Hero): void {
    this.selectedHero = hero;
  }
    
    loadFile(source:any) {
        if(source == "systemfile") {
            var fileInput = <HTMLInputElement>document.getElementById('file-input');
            
            if(this.listenerRegistered == false) {
                fileInput.addEventListener('change', function(e) {
                    var file = fileInput.files[0];
                    var textType = /text.*/;
    
                    if (file.type.match(textType)) {
                        var reader = new FileReader();
    
                        reader.onload = function(e) {
                            document.getElementById('text_area').innerText = reader.result;
                        }
                        reader.readAsText(file, 'UTF-8');    
                    } else {
                        alert("Nieobsługiwany format pliku!");
                    }
                });
                this.listenerRegistered = true;
            }
            fileInput.click();
        }
        else {
            console.log("[ERR] Load mode undefined!");
        }    
    }
}
