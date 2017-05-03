<h1>PIK</h1> <img src="https://travis-ci.org/Thun0/pik.svg?branch=master"/>

<h2>how to import:</h2>
Import as Eclipse gradle project (existing gradle project), build with gradle build. 
To obtain dependencies right-click on project and select gradle -> refresh gradle project.

<h2>in index.html:</h2>
document.getElementById('base').href = '/' is for developing locally (ng run), 
document.location.origin + '/' + document... is for deploying on tomcat

<h2>for a clean build:</h2>
delete bin + build + src/main/resources/static directories. 
(this needs to get automated. some combination of gradle clean + ng clean perhaps.)

<h2>routing:</h2>
Mapping http request types and addresses at which given requests have been sent to 
functions / resources first takes place in Spring controller classes. Since Spring 
has no idea about Angular and its resources, this situation would lead to user not 
being able to see any Angular resources (maybe apart from those that would get served
as sort of a main page, at http://127.0.0.1:PORT/name-in-tomcat/ and those reached by 
navigting from that main page. But after copying that address and entering it in a new
tab, the server would return 404 instead of valid Angular resource. The reason is, 
Spring would decide it does not have that resource and so it would return 404 error page.
There are (more than) two options to amend that:
1. return custom 404 page which in fact is Angular's index.html, or
2. return index.html when user asks about "/" and "/app/**" addresses (our ServeIndex 
Spring @Controller class does that), and configure Angular routing to be serving stuff 
at '/app/**' adresses. 
(The 'app' could be anything - e.g. it could also be a '#'.)
Currently we have opted for the second option, so to sum up:
Angular views (well, different variants of index.html) will be served at addresses 
with an <b>/app</b> prefix, such as: http://127.0.0.1:9996/pik/<b>app</b>/whatever.  
Spring will serve jsons under addresses without that /app prefix.
And main routing configuration for Angular is available in front-src/app/app-routing.module.ts,
if anybody was interested.

<h2>database:</h2>
We have problems with our database, it insists on exposing its contents at strange adresses :< 