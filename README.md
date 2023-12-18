<div align = "center">
  <h1> Social Media Project</h1>
 </div>



## Framework and Language
* Java 
* SpringBoot

## Dependencies
  * SpringWeb
  * Lombok
  * JPA
  * PostregSql database
  * Spring Security
    
## Database Used 
  * PostregSQL Database
    
## Data flow
  **The project has main packages which are following** 
  
* Controller - Controller package has all the api and Crud operations.
* Services - Service class has all the business logic and it will return the result of that method which is called by controller class . 
* Repository - Respository has all the datasource in it i used SQL Database
* Model - Model has User , Post and Comment , Like and Follow.
* Configuration - Spring Security Configuration.
> The flow of data is from controller has all the api and logics of that api has in service class and
service class is using the repository class which has all the data it keeps data source.The api called by user call the controller method
return the logic which is written in service class so service class object is called and service class use data source of repo class.

## Project Summary 
  This is aInstagram Backend Project we can do many more operations in this project like
  * Create User
  * Role Based Authentication
  * User can create Post
  * User can like a post
  * User can comment on Post
  * User can follow a user
