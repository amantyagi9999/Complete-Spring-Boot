package com.example.services;

import com.example.entities.Author;
import com.example.entities.Book;
import com.example.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }


    public Author getAuthor(Long authorId) {
        return authorRepository.findById(authorId).orElse(null);
    }
}
