**Code Challenge (REST): Photo Album Manager**

Create a simple Photo Album manager. The manager should implement a REST
API. Authentication is not necessary, but be prepared to discuss how you would add it.

The REST API should support the following functionality:

 - Standard CRUD operations for albums,and photos.
 - Add a photo to an existing album
 - Remove a photo from an existing album
 - Return a list of all albums, with the photos associated with those albums.
 - Return a list of photos given an album id.
  	The relationship between these should be:
		- Each photo is associated with an album (photo.albumId associated with album.id).
	The following constraint should be implemented:
		- Albums cannot be deleted if any associated photos exist.
	Albums and photos should have the following attributes

|Albums|
|----|
|id |
|title|

|Photos|
|----|
|albumId |
|title | 
|url|

   In addition to REST endpoints implementing the CRUD operations, there should be a REST endpoint “init” that should initialize all of the users, albums, and photos from the REST resources located here:

Albums: https://jsonplaceholder.typicode.com/albums 
Photos: https://jsonplaceholder.typicode.com/photos

Please develop this application using modern techniques and frameworks, and in the fashion you would develop real services for work. We will be focusing on the quality and innovation of your solution, both design and implementation, and use of best practices among other things.
Only include the source code, not the binaries for your solution. Provide a way for us to build the resulting application (maven, gradle, etc.).
We prefer that you place your source code in a personal GitHub area and provide us access and a link to your project. Alternatively, you may send us a ZIP file with the solution in it.
Again, the project should look just like something you would produce for work.
