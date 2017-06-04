import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
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
                            
                            var promise = new Promise((resolve, reject) => {
                                let xhr:XMLHttpRequest = new XMLHttpRequest();
                                xhr.onreadystatechange = () => {
                                    if (xhr.readyState === 4) {
                                        if (xhr.status === 200) {
                                            resolve(JSON.parse(xhr.response));
                                        } else {
                                            reject(xhr.response);
                                        }
                                    }
<<<<<<< HEAD
                                }
                            };
                            xhr.open('POST', '/mywebapp/upload', true);
                            let formData = new FormData();
                            formData.append("file", file, file.name);
                            xhr.send(formData);
                        });
                        promise.then((resolve) => {
                            console.log(resolve);
                        
                        /**/
                        }, (reject) => {
                            console.error(reject);
                        });/**/    
=======
                                };
                                xhr.open('POST', '/mywebapp/echo/liner2', true);
                                //let formData = new FormData();
                                //formData.append("file", text, text.name);
                                xhr.send(text);
                            });
                            promise.then((resolve) => {
                                console.log(resolve);
>>>>>>> 61c8b6ce704931de63ad7e87f6d3d6b47bce6154
                            
                            /**/
                            }, (reject) => {
                                console.error(reject);
                            });/**/    
                                
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
                                
                            
                                /*    
                            }, (reject) => {
                                console.error(reject);
                            });*/       
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
