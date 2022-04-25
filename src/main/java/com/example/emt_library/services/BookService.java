package com.example.emt_library.services;

import com.example.emt_library.models.dto.BookDto;
import com.example.emt_library.models.enums.Category;
import com.example.emt_library.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface BookService {
    public List<Book> findAll();

    public Optional<Book> findById(Long id);

    public Optional<Book> findByName(String name);

    public Page<Book> findAllWithPagination (Pageable pageable);

    public Optional<Book> save (String name, Category category, Long authorId, int availableCopies);

    Optional<Book> save(BookDto bookDto);

    public Optional<Book> edit (String name, Category category, Long authorId, int availableCopies);

    Optional<Book> edit(Long id, BookDto bookDto);

    public void deleteById (Long bookId);

    public Optional<Book> markAsTaken (Long bookId);
}
