"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var HEROES = [
    { id: 11, name: 'Mr. Nice' }
];
var AppComponent = (function () {
    function AppComponent() {
        this.listenerRegistered = false;
        this.title = 'Tour of Heroes';
        this.heroes = HEROES;
    }
    AppComponent.prototype.onSelect = function (hero) {
        this.selectedHero = hero;
    };
    AppComponent.prototype.loadFile = function (source) {
        if (source == "systemfile") {
            var fileInput = document.getElementById('file-input');
            if (this.listenerRegistered == false) {
                fileInput.addEventListener('change', function (e) {
                    var file = fileInput.files[0];
                    var textType = /text.*/;
                    if (file.type.match(textType)) {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            var text = reader.result;
                            text = text.split("<br>").join("KULA");
                            console.log(text);
                            document.getElementById('text_area').innerText = text;
                        };
                        reader.readAsText(file, 'UTF-8');
                    }
                    else {
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
    };
    return AppComponent;
}());
AppComponent = __decorate([
    core_1.Component({
        selector: 'my-app',
        template: "\n    <ul class=\"top_bar\">\n      <li><a class=\"active\" href=\"http://korpusomat.nlp.ipipan.waw.pl/\">Korpusomat</a></li>\n      <li><a href=\"#null\">O projekcie</a></li>\n      <li><a href=\"#null\">Kontakt</a></li>\n    </ul>   \n\n    <h1 class=\"center\" style=\"padding-top: 30px;\">\n        Modu\u0142 wy\u015Bwietlania dla Korpusomatu\n    </h1>\n    <div class=\"center\">\n        <div id=\"text_area\" class=\"textarea\" contenteditable=false></div>\n        <br />\n        <button (click)=\"loadFile('systemfile')\">Wczytaj plik</button>\n        <input id=\"file-input\" type=\"file\" name=\"name\" style=\"display: none;\" />\n    </div>\n\n    <!--\n    <h2>My Heroes</h2>\n    <ul class=\"heroes\">\n      <li *ngFor=\"let hero of heroes\"\n        [class.selected]=\"hero === selectedHero\"\n        (click)=\"onSelect(hero)\">\n        <span class=\"badge\">{{hero.id}}</span> {{hero.name}}\n      </li>\n    </ul>\n    <hero-detail [hero]=\"selectedHero\"></hero-detail>\n    -->\n  ",
        styleUrls: ['app/app.component.css']
    })
], AppComponent);
exports.AppComponent = AppComponent;
//# sourceMappingURL=app.component.js.map