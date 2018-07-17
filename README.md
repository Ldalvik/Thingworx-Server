# Thingworx-Server
Webserver app for receiving data from thingworx

to access the server on the same device, open localhost:8080
to access server on other devices on the same network, replace localhost with phones i.p address

(e.g. 130.64.160.230:8080/add/?text=text&x=10&y=10&color=255,0,255&id=1&size=15&url=(optional)

/add/ will add a view
/update/ will change an existing view
/delete/ will delete an existing view

thingworx property changed -> event in js to send GET request to 130.64.160.230:8080/method/?param=value -> phone receives request.


will now serve a control interface html page if http:ip.address:8080/control/ is opened
