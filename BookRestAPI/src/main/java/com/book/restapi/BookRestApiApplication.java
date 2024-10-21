package com.book.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BookRestApiApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BookRestApiApplication.class, args);

		/*
		 * BookRepo br = context.getBean(BookRepo.class);
		 * 
		 * Book b1 = new Book(25,"Spring","Author 1");
		 * 
		 * Book b2 = new Book(65,"Sring","Author 2");
		 * 
		 * Book b3 = new Book(95,"Sprng","Author 3");
		 * 
		 * Book b4 = new Book(150,"Spr","Author 4"); List<Book> list =
		 * List.of(b1,b2,b3,b4);
		 * 
		 * Iterable<Book> books = br.saveAll(list);
		 */
	}

}
