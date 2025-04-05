package com.example.services;

import com.example.entities.Author;
import com.example.entities.Book;
import com.example.repositories.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }


    public ResponseEntity<Book> createBook(Book book) {
        Book createdBook = bookRepository.save(book);
        return new ResponseEntity<>(createdBook, HttpStatus.OK);
    }

    public ResponseEntity<Book> updateBook(Long bookId, Long authorId) {
        Book getBook = bookRepository.findById(bookId).orElse(null);
        Author author = new Author();
        author.setId(authorId);
        getBook.setAuthor(author);
        return new ResponseEntity<>(bookRepository.save(getBook), HttpStatus.OK);

    }



}
