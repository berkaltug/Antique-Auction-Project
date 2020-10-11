# Antique-Auction-Project
An antique item auctioning web app which is restfull and built with spring boot and h2 db and spring security.

This is a backend api for antique auction frontend project which is here [Antique-Auction-Frontend](https://github.com/berkaltug/Antique-Action-Frontend)
### How to run
This project built with OpenJDK 8 so you need to have OpenJDK 8 or higher.(JDK 11 version could give you problems so try to stick with JDK 8)
.You need to have also maven 3 or higher version installed.(You could also use intellij embedded one).
Clone this repository , than open Intellij , click Open... , then select this project.Intellij should detect it as a maven project , wait for dependencies to load , when it finished you can run the project as a spring boot app.

(If you face any issue ,try maven clean install from command-line.)


### Project Endpoints
This project has 2 user roles Admin and user, the credentials are admin/admin and user/user.
All end-points are secured.

Gets all antique (page param cant be 0)

    localhost:8080/antique/list?page=1&sort=DESC 
Gets one antique details

    localhost:8080/antique/get/{id}
 Add an antique (request body is multipart form)
 
    localhost:8080/antique/add
 Updates and antique (request body is multipart form)
    
    localhost:8080/antique/update/
search for given string through names

    localhost:8080/antique/search?str={str}&page={pageno}&sort={direction}
deletes an antique with given id

    localhost:8080/antique/delete/{id}
And users make bids to antiques with this url

    localhost:8080/antique/bid
    
### Database
Used H2 embedded database.You can access the interface from ...:8080/h2-console endpoint.

**Important :** You must choose generic H2 embedded option, Driver class is org.h2.Driver , and JDBL url is **jdbc:h2:mem:testdb**

The project comes with 50 dummy antique item provided by data.sql file.