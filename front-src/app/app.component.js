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
                            //console.log(text);
                            //document.getElementById('text_area').innerText = text;
                            var lines = text.split("\n");
                            for (var i = 0; i < lines.length; ++i) {
                                var isWord = false;
                                var word = "", sign = "";
                                for (var j = 0; j < lines[i].length; ++j) {
                                    sign = lines[i].charAt(j);
                                    if (sign.toLowerCase() != sign.toUpperCase()) {
                                        isWord = true;
                                        word += sign;
                                    }
                                    else {
                                        if (isWord == true) {
                                            var html_to_insert = "<button class=" + '"' + "link" + '"' + " onClick=\"showWordDetails('" + word + "')\">" + word + "</button>";
                                            document.getElementById('text_area').innerHTML += html_to_insert;
                                            word = "";
                                        }
                                        isWord = false;
                                        document.getElementById('text_area').innerHTML += ("<div style=\"display: inline-block;\">" + sign + "</div");
                                    }
                                }
                                if (isWord == true) {
                                    var html_to_insert = "<button class=\"link\" onClick=\"showWordDetails('" + word + "')\">" + word + "</button>";
                                    document.getElementById('text_area').innerHTML += html_to_insert;
                                    word = "";
                                }
                                //document.getElementById('text_area2').innerText += sign;
                                document.getElementById('text_area').innerHTML += "<br />";
                            }
                            //this.cdRef.detectChanges(); // to ensure the DOM is updated
                            // add event handlers imperatively
                            //this.elRef.nativeElement.querySelectorAll(button).forEach(b => b.addEventListener('click', this.eventHandler.bind(this)));
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
        templateUrl: 'app/app.component.html',
        styleUrls: ['app/app.component.css']
    })
], AppComponent);
exports.AppComponent = AppComponent;
//# sourceMappingURL=app.component.js.map