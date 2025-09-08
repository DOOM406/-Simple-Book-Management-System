package com.management.system.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.management.system.models.BookModel;
import com.management.system.services.BookService;

import jakarta.annotation.security.PermitAll;

@RestController
@RequestMapping("/book")

public class BookController {

	@Autowired
	BookService bookService;
	
	//GET
	@GetMapping()
	public ArrayList<BookModel> getAllBook() {
		return bookService.getAllbooks();
	}
	@GetMapping(path="/{id}")
	public Optional<BookModel> getBookById(@PathVariable("id") Long id) {
		return bookService.getBookById(id);
	}
	
	@GetMapping(path="/author/{author}")
	public ArrayList<BookModel> getByAuthor(@PathVariable("author") String author){
		return bookService.getBookByAuthor(author);
	}
	
	@GetMapping(path="/available")
	public ArrayList<BookModel> getByAvailable(){
		return bookService.getBookByAvailable(true); // estructurar bien esta opcion, QUE RETORNE SOLO LOS LIBROS DISPONIBLES
	}
	
	@GetMapping(path="/search")
	public ArrayList<BookModel> getByTitle(@PathVariable("title") String title){
		return bookService.getBookByTitle(title);
	}

	//POST
	@PostMapping()
	public BookModel newBook(@RequestBody BookModel book) {
		return bookService.saveBook(book);
	}
	
	//PUT
	@PutMapping(path="/{id}")
	public ResponseEntity<BookModel> updateBook(@PathVariable("id") Long id, @RequestBody BookModel book){
		BookModel upBook = bookService.updateBook(id, book);
		return ResponseEntity.ok(upBook);
	}
	
	@PutMapping(path="/{id}/borrow")
	public ResponseEntity<BookModel> borrowBook(@PathVariable("id")Long id){
		 Optional<BookModel> updatedbook = bookService.borrowBook(id);
		 if(updatedbook.isPresent()) {
			 return ResponseEntity.ok(updatedbook.get());
		 }else {
			 return ResponseEntity.notFound().build();
		 }
	}
	@PutMapping(path="/{id}/return")
	public ResponseEntity<BookModel> returnBook(@PathVariable("id")Long id){
		Optional<BookModel> returnedBook = bookService.returnBook(id);
		if(returnedBook.isPresent()) {
			return ResponseEntity.ok(returnedBook.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//DELETE
	@DeleteMapping(path="/{id}")
	public String deleteBook(@PathVariable("id") Long id) {
		boolean ok = bookService.deleteBook(id);
		if(ok) {
			return "Book with id "+id+" deleted";
		}else {
			return "Book with id "+id+" not found";
		}
	}
}
