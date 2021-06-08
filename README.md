# Spring Boot project

## Subject 
Create 2 REST services
- Register a user 
- Displays the details of a registered user

### User properties 
```sh
{
    "lastname": String,
    "firstname":String,
    "email": String,
    "student": Boolean,
    "age": Integer, // >17 only adults can create an account 
    "country": String //  =="France" : only adult that live in France can create an account
}
```
### Routes

| URI | CRUD | RESULT   |
| ------ | ------ | ------ |
| [ HTTP GET ] http://localhost:8091/registrants | Read : Displays all users registered | 200 (OK) |
| [ HTTP GET ] http://localhost:8091/registrants/{id} | Read : Display the details of a registered user  | 200 (OK), 404 (Not Found) id not found. |
| [ HTTP POST ] http://localhost:8091/registrants | Create : Create a new user | 201 (Created), 400 (Bad Request) fields mandatory null or not valid |