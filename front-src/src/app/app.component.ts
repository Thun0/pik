import { Component } from '@angular/core';

import { Hero } from './hero';

const HEROES: Hero[] = [
  { id: 11, name: 'Mr. Nice' }
];

@Component({
  selector: 'my-app',
  template: `
    <ul class="top_bar">
      <li><a class="active" href="http://korpusomat.nlp.ipipan.waw.pl/">Korpusomat</a></li>
      <li><a href="#null">O projekcie</a></li>
      <li><a href="#null">Kontakt</a></li>
    </ul>   

    <h1 class="center" style="padding-top: 30px;">
        Moduł wyświetlania dla Korpusomatu
    </h1>
    <div class="center">
        <div id="text_area" class="textarea" contenteditable=false></div>
        <br />
        <button (click)="loadFile('systemfile')">Wczytaj plik</button>
        <input id="file-input" type="file" name="name" style="display: none;" />
    </div>

    <!--
    <h2>My Heroes</h2>
    <ul class="heroes">
      <li *ngFor="let hero of heroes"
        [class.selected]="hero === selectedHero"
        (click)="onSelect(hero)">
        <span class="badge">{{hero.id}}</span> {{hero.name}}
      </li>
    </ul>
    <hero-detail [hero]="selectedHero"></hero-detail>
    -->
  `,
  styleUrls: ['app/app.component.css']
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
                            var text = reader.result;
                            text = text.split("<br>").join("KULA");
                            console.log(text);
                            document.getElementById('text_area').innerText = text;
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
