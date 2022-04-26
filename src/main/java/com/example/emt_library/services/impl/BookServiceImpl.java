package com.example.emt_library.services.impl;

import com.example.emt_library.models.dto.BookDto;
import com.example.emt_library.models.enums.Category;
import com.example.emt_library.models.Author;
import com.example.emt_library.models.Book;
import com.example.emt_library.models.exceptions.AuthorNotFoundException;
import com.example.emt_library.models.exceptions.BookNotFoundException;
import com.example.emt_library.repositories.AuthorRepository;
import com.example.emt_library.repositories.BooksRepository;
import com.example.emt_library.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BooksRepository booksRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BooksRepository booksRepository, AuthorRepository authorRepository) {
        this.booksRepository = booksRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Book> findAll() {
        return this.booksRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.booksRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.booksRepository.findByName(name);
    }

    @Override
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return this.booksRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Optional<Book> save(String name, Category category, Long authorId, int availableCopies) {
       Author author = this.authorRepository.findById(authorId)
               .orElseThrow(() -> new AuthorNotFoundException(authorId));

        this.booksRepository.deleteByName(name);
        Book book = new Book(name, category, author, availableCopies);
        this.booksRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        Book book = new Book(bookDto.getName(), bookDto.getCategory(),author, bookDto.getAvailableCopies());
        this.booksRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, String name, Category category, Long authorId, int availableCopies) {
        Book book = this.booksRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setName(name);
        book.setCategory(category);
        book.setAvailableCopies(availableCopies);

       Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        book.setAuthor(author);

        this.booksRepository.save(book);
        return Optional.of(book);

    }

    @Override
    @Transactional
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.booksRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAvailableCopies(bookDto.getAvailableCopies());

        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        book.setAuthor(author);

        this.booksRepository.save(book);
        return Optional.of(book);

    }

    @Override
    public void deleteById(Long bookId) {
        this.booksRepository.deleteById(bookId);
    }

    @Override
    public Optional<Book> markAsTaken(Long bookId) {
        Book book = this.booksRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
        }

        return Optional.of(book);
    }




}
