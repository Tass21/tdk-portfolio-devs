package com.tdksoft.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBookService {

    Book saveBook(Book book);

    public List<Book> findAllBooks();

    public Optional<Book> findBookById(UUID bookId) throws Exception;

    public void deleteBook(UUID bookId);

}
