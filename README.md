The project was carried out with spring, hibernate, jpa, java and postgresSQL.
It has a controller connected to some services as an interface, each of these services,
has its own implementation where the business logic of the method is, this layer is the only one that can connect to the repository which has access to the database.

# cookingRecipe
cookingRecipe

1.- Data Base On a Postgress DataBase run the nexts Scripts:

--CREATE DATABASE cooking

--CREATE SCHEMA recipes --AUTHORIZATION postgres;

2.- The aplication 

2.1.- Please generate the war with maven and publish it on a server.

2.2.- The services: 
-- header 
key:User value:{the consumer} 
key:Content-Type value:application/json 
// to add a new user POST http://localhost:8080/b-cooking/api/user 
// the body examples: { "name":"Raul", "lastName":"Gongora", "email":"rgongora@gmail.com", "password":"123456" }
//the login method POST http://localhost:8080/b-cooking/api/user/login 
// the body examples: { "user":"rgongora@gmail.com", "password":"msalinas" } 
-- it will return you a token where on the others methots you need to use { "success": true, "result": "80a3b85a-d416-4066-9337-b7b126dc71ad", <-- token "message": "Success" }
//add a header with the token for consume the others services: -- header key:Token value:{token}


