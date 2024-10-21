package com.book.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.restapi.model.Book;

public interface BookRepo extends JpaRepository<Book, Integer> {
public Book findById(int id);
}
