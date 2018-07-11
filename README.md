# Thingworx-Server
Webserver app for receiving data from thingworx

to access the server on the same device, open localhost:8080
to access server on other devices on the same network, replace localhost with phones i.p address

(e.g. 130.64.160.230:8080/?value=theValue)

thingworx property changed -> event in js to send GET request to 130.64.160.230:8080/?value=newValue -> phone recieves value.
