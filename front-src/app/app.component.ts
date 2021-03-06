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
    
                        reader.onload = function(event) {
                            var text = reader.result;

                            var promise = new Promise((resolve, reject) => {
                                let xhr: XMLHttpRequest = new XMLHttpRequest();
                                xhr.onreadystatechange = () => {
                                    if (xhr.readyState === 4) {
                                        if (xhr.status === 200) {
                                            resolve(xhr.response); //XMLHttpRequest.response == the response's body
                                        } else {
                                            reject(xhr.response);
                                        }
                                    }
                                };
                                xhr.open('POST', '/' + document.location.pathname.split('/')[1] + '/echo/txtfile', true);
                                //let formData = new FormData();
                                //formData.append("file", file, file.name);
                                xhr.send(text);
                            });
                            promise.then((resolve) => {
                              //document.getElementById('text_area').innerHTML = '';
                              //document.getElementById('text_area').innerHTML += resolve + "<br />";
                              //console.log(resolve);
                                
                                //text = resolve;
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
                            /**/
                            }, (reject) => {
                                console.error(reject);
                            });/**/   
                          
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
                                };
                                xhr.open('POST', '/' + document.location.pathname.split('/')[1] + '/echo/liner2' + '?filepath=/opt/liner2.3/test/' + file.name, true);
                                //let formData = new FormData();
                                //formData.append("file", file, file.name);
                                xhr.send(text);
                            });
                            promise.then((resolve) => {
                                console.log(resolve);
                                var buttons = document.getElementsByClassName("link");
                                for(var index in resolve[0]) {
                                    
                                    buttons[index].style.backgroundColor = "yellow";        
                                }    
                            /**/
                            }, (reject) => {
                                console.error(reject);
                            });/**/    
                                
                                /*    
                            }, (reject) => {
                                console.error(reject);
                            });*/       
                        }
                        reader.readAsText(file, 'UTF-8');
                        
                    } else {
                        alert("Nieobslugiwany format pliku!");
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