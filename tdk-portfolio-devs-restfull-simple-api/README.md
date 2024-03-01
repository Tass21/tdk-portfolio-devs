Problem Statement:

Create a simple RESTful API for managing a collection of books. Each book should have attributes such as title, author, ISBN, and publication year. You need to implement the backend using Java and Spring Boot.

Requirements:

1. Implement a Book class with the following attributes:

title (String)
author (String)
ISBN (String)
publicationYear (int)
2. Implement a RESTfull API with the following endpoints:

GET /books: Retrieve a list of all books.
GET /books/{id}: Retrieve details of a specific book by its ID.
POST /books: Add a new book to the collection.
PUT /books/{id}: Update details of a specific book.
DELETE /books/{id}: Delete a book from the collection.

3. Document API  with OpenAPI & Swagger

4. Use PostgresSql data store to store the books.

5. Implement appropriate error handling for cases such as invalid requests or resource not found.

6. Implement validation for the book attributes (e.g., ensure ISBN is in the correct format, publication year is within a valid range).
7. Add pagination support to the GET /books endpoint to retrieve books in batches.
8Implement sorting and filtering capabilities for the GET /books endpoint (e.g., sort by title, filter by author).
9. Write unit tests to ensure the correctness of your implementation.