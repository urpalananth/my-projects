**Setup**

git clone https://github.com/urpalananth/my-projects.git

cd <download location>my-projects/photo-album-manager

**Build and run in Eclipse**
- Import -> Existing Maven Projects 
  - give the full for path to pom.xml ex: <download location>/my-projects/photo-album-manager/pom.xml
  
- Open class -> inst.an.photoalbummanager.PhotoAlbumManagerApplication
  - Right Click -> Run As Java Application
  
The application should be up and running on http://localhost:8080/

**Build and run command line** 

cd <download location>my-projects/photo-album-manager

./mvnw spring-boot:run

The application should be up and running on http://localhost:8080/

**User Interface**

credentials to login - any of the below

|user id|user name	|password|
|-------|-----------|--------|
|1	| aa	| aa	|
|2	| ab	| ab	|
|3	| ac	| ac	|
|4	| ad	| ad	|
|5	| ae	| ae	|
|6	| af	| af	|
|7	| ag	| ag	|
|8	| ah	| ah	|
|9	| ai	| ai	|
|10	| aj	| aj	|
|11	| admin	| admin	|

**Rest Endpoints**

|Endpoint|Comments|
|----|----|
|http://localhost:8080/albums	|(with methods GET, POST, PUT, DELETE)|
|http://localhost:8080/photos	|(with methods GET, POST, PUT, DELETE)|
|http://localhost:8080/init		|(to initialize data)|
|http://localhost:8080/all		|(for admin to get all the albums)|
|http://localhost:8080/all		|(for admin to get all the albums)|

**Technologies Used**
- Spring boot, Spring devtools.
- Authentication is Done by Spring Security.
- Controllers – Using Spring Data Rest.
- JPA – Hibernate JPA implementation wrapped with Spring.
- Database – H2 in memory database.
- Testing – Integration Tests with live rest service.
