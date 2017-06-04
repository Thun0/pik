import { Component } from '@angular/core';

@Component({
  selector: 'my-app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
    listenerRegistered = false;
    
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
                            //console.log(text);
                            //document.getElementById('text_area').innerText = text;
                            
                            var lines = text.split("\n");
                            for(var i = 0; i < lines.length; ++i) {
                                var isWord = false;
                                var word = "", sign = "";
                                for(var j = 0; j < lines[i].length; ++j) {
                                    sign = lines[i].charAt(j);
                                    if(sign.toLowerCase() != sign.toUpperCase()) { // letter
                                        isWord = true;
                                        word += sign;
                                    }
                                    else {
                                        if(isWord == true) {
                                            var html_to_insert = "<button class="+'"'+"link"+'"'+" onClick=\"showWordDetails('"+word+"')\">"+word+"</button>";
                                            document.getElementById('text_area').innerHTML += html_to_insert;
                                            word = "";
                                            
                                        }
                                        isWord = false;
                                        document.getElementById('text_area').innerHTML += ("<div style=\"display: inline-block;\">"+sign+"</div");
                                    }
                                }
                                if(isWord == true) {
                                    var html_to_insert = "<button class=\"link\" onClick=\"showWordDetails('"+word+"')\">"+word+"</button>";
                                    document.getElementById('text_area').innerHTML += html_to_insert;
                                    word = "";
                                }
                                //document.getElementById('text_area2').innerText += sign;
                                document.getElementById('text_area').innerHTML += "<br />";
                            }
                            //this.cdRef.detectChanges(); // to ensure the DOM is updated
                            // add event handlers imperatively
                            //this.elRef.nativeElement.querySelectorAll(button).forEach(b => b.addEventListener('click', this.eventHandler.bind(this)));
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
