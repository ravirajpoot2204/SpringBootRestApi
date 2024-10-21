package com.book.restapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.restapi.model.Book;
import com.book.restapi.repository.BookRepo;

@RestController
public class BookController {
	@Autowired
	private BookRepo br;

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks() {

		/*
		 * Spring boot automaticaly convert data to json uses jackson dependency
		 * 
		 */
		List<Book> books = br.findAll();
		if (books.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {

			return ResponseEntity.of(Optional.of(books));
		}
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
		Book byID = br.findById(id);
		if (byID == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(byID));
	}

	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book b) {

		Book book = null;
		try {
			book = br.save(b);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<Book> deleteBookById(@PathVariable("id") int id) {
		Book b = br.findById(id);
		if (b == null) {
			System.out.println("Could not find data to delete");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {

			br.delete(b);
			System.out.println(id + " : Book Deleted");
			return ResponseEntity.status(HttpStatus.OK).build();

		}

	}

	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@RequestBody Book b, @PathVariable("id") int id) {
		if (br.findById(id) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			Book ubook = br.save(b);
			System.out.println("_________book Updated_______________");
			System.out.println(ubook);
			return ResponseEntity.of(Optional.of(ubook));
		}
	}
}
