# Antique-Auction-Project
An antique item auctioning web app which is restfull and built with spring boot and h2 db and spring security.

This is a backend api for antique auction frontend project which is here [Antique-Auction-Frontend](https://github.com/berkaltug/Antique-Auction-Frontend)
### How to run
This project built with OpenJDK 8 so you need to have OpenJDK 8 or higher.(JDK 11 version could give you problems so try to stick with JDK 8)
.You need to have also maven 3 or higher version installed.(You could also use intellij embedded one).
Clone this repository , than open Intellij , click Open... , then select this project.Intellij should detect it as a maven project , wait for dependencies to load , when it finished you can run the project as a spring boot app.

(If you face any issue ,try maven clean install from command-line.)

###Email System
This spring boot app configured for use of gmail's smtp server.In order to app work ,you should provide your gmail account in .properties file 
and generate a one time application password and provide it too.(or you can go to google account settings and permit to less secure apps , if you do this you can use with your gmail password).

### Project Endpoints
  This project has 2 default account and roles are Admin and user, the credentials are admin/admin and user/user.
  All end-points are secured.
  
  Switched to using Swagger 2. You can now find all end points with
  their request and responses by hitting http://localhost:8080/swagger-ui.html after started the application. 
  

    
### Database
Used H2 embedded database.You can access the interface from ...:8080/h2-console endpoint.

**Important :** You must choose generic H2 embedded option, Driver class is org.h2.Driver , and JDBL url is **jdbc:h2:mem:testdb** username is sa, password is password

The project comes with 50 dummy antique item provided by data.sql file.