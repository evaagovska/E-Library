package com.example.emt_library.repositories;

import com.example.emt_library.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Long> {

   Optional<Book> findByName(String name);

   void deleteByName(String name);

   Page<Book> findAll(Pageable pageable);

}
