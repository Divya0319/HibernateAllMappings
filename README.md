# REST API with all hibernate mappings using Spring REST  
To preview the working app, follow this [link](https://hibernate-mappings.herokuapp.com)

A Spring Boot REST API involving OneToOne, OneToMany, ManyToOne and ManyToMany mappings.
* #### OneToOne - from coders to coderDetail and vice versa.  
* #### OneToMany - from coders to booksReferred.
* #### OneToMany unidirectional - from booksReferred to bookReviews.
* #### ManyToOne  - from booksReferred to coders.
* #### ManyToMany  - from booksReferred to designers and vice versa.
The application exposes various endpoints to demonstrate the working of these mapping relationships. 

Base url: **https://hibernate-mappings.herokuapp.com/api**

### Following are the mappings available in this REST API.

1. **GET /coders** mapping to fetch all coders created.
2. **GET /coders/{coderId}** to fetch a coder by id.
3. **GET /coderDetail/{coderDetailId}/coders** to fetch coder pertaining to a coderDetail.
4. **GET /coders/{coderId}/coderDetail** to fetch coderDetail pertaining to a coder.
5. **GET /booksReferred** to fetch all books referred.
6. **GET /coders/{coderId}/booksReferred** to fetch all books referred by a particular coder.
7. **GET /booksReferred/coders?bookId=5** to fetch all coders who are referring to book with id=5.
8. **GET /booksReferred/{bookId}/bookReviews** to fetch all reviews for a book.
9. **GET /designers/{dId}/booksReferred** to fetch all books referred by a particular designer.
10. **GET /booksReferred/{dId}/designers** to fetch all designers who refer a particular book.
11. **POST /coders** to add a new coder.
  
  Sample Request body ->   
  {
    "firstName":"Rahul",
    "lastName":"Khanna",
    "age":46,
    "email":"rahul@gmail.com"
  } 

12. **POST /coders/{coderId}/coderDetail** to add a new coderDetail for a coder.
  
  Sample Request body ->   
  {
    "githubProfileUrl":"http:www.github.com/6484",
    "soRep":637"  
  }
  
  
13. **POST /coders/{coderId}/bookReferred** to add new books referred, to a particular coder.
  
  Sample Request body -> {
    "title":"Lets C"
}
  
  14. **POST /booksReferred/{bookId}/bookReviews** to add a new review to a book.
15. Sample Request body ->   
 {
    "comment":"Wonderful"
}
  
  16. **POST /designers** to add a new designer.
  
  Sample Request body ->   
{
    "firstName":"Rahul",
    "lastName":"Khanna",
    "age":46,
    "email":"rahul@gmail.com"
}
  
  17. **POST /designers/{designerId}/booksReferred** to add a new book referred, to a designer.
  
  Sample Request body ->   
  {
    "title":"UI practices"
  }

18. **PUT /coders** to update a coder.
  
  Sample Request body ->   
{
    "id":7,
    "firstName":"Rahul",
    "lastName":"Khanna",
    "age":46,
    "email":"rahul@gmail.com"
}

19. **DELETE /coders/{coderId}** to delete a coder by id.
20. **DELETE /booksReferred/{bookId}** to delete a book by id.
21. **DELETE /coderDetail/{coderDetailId}** to delete a coderDetail by id.
22. **DELETE /designers/{dId}** to delete a designer by id.
