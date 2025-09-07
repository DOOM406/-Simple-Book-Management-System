package com.management.system.services;

import java.awt.print.Book;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.system.models.BookModel;
import com.management.system.repositories.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	public ArrayList<BookModel> getAllbooks(){
		return (ArrayList<BookModel>) bookRepository.findAll();
	}
	
	public Optional<BookModel> getBookById(Long id){
		return bookRepository.findById(id);
	}
	
	public ArrayList<BookModel> getBookByAuthor(String author){
		return bookRepository.findByAuthor(author);
	}
	
	public ArrayList<BookModel> getBookByAvailable(Boolean valor){
		// algo parecido a borrow y return? 
		return bookRepository.findByAvailable(valor); // estructurar bien esta opcion, QUE RETORNE SOLO LOS LIBROS DISPONIBLES
	}
	
	public ArrayList<BookModel> getBookByTitle(String tittle){
		return bookRepository.findByTitleContainingIgnoreCase(tittle);
	}
	
	public BookModel saveBook(BookModel book) {
		return bookRepository.save(book);
	}
	
	public BookModel updateBook(Long id, BookModel book) {
		//buscamos la entidad por la id
		BookModel book2 = bookRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Book not found, with id: "+id));
		//actualizamos datos (esto se podra hacer mas rapido?)
		book2.setAuthor(book.getAuthor());
		book2.setAvailable(book.isAvailable());
		book2.setId(book.getId());
		book2.setIsbn(book.getIsbn());
		book2.setPublishedYear(book.getPublishedYear());
		book2.setTitle(book.getTitle());
	
		return bookRepository.save(book2);
	}
	
	public boolean deleteBook(Long id) {
		try {
			bookRepository.deleteById(id);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public Optional<BookModel> borrowBook(Long id) {
		return bookRepository.findById(id).map(book ->{
			if(book.isAvailable()) { //si esta disponible
				book.setAvailable(false); //lo marcamos como no disponible
				return bookRepository.save(book); //actualizamos
			}
			return book; // ya esta prestado F
		});
	}
	
	public Optional<BookModel> returnBook(Long id) {
		return bookRepository.findById(id).map(book->{
			book.setAvailable(true);
			return bookRepository.save(book); //marcamos como disponible y listo
		});
	}
	
	//
}
