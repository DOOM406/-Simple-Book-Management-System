package com.management.system.services;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.system.BookException.BookAlreadyBorrowedException;
import com.management.system.BookException.BookNotFoundException;
import com.management.system.BookException.InvalidBookDataException;
import com.management.system.models.BookModel;
import com.management.system.repositories.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	public ArrayList<BookModel> getAllbooks(){
		return (ArrayList<BookModel>) bookRepository.findAll();
	}
	
	public BookModel getBookById(Long id){
		return bookRepository.findById(id).
				orElseThrow(() -> new BookNotFoundException("Book Not Found With id: "+id));
	}
	
	public ArrayList<BookModel> getBookByAuthor(String author){
		return bookRepository.findByAuthor(author);
	}
	
	public ArrayList<BookModel> getBookByAvailable(Boolean valor){
		return bookRepository.findByAvailable(valor); 
	}
	
	public ArrayList<BookModel> getBookByTitle(String tittle){
		return bookRepository.findByTitleContainingIgnoreCase(tittle);
	}
	
	public BookModel saveBook(BookModel book) {
		if(book.getTitle() == null || book.getTitle().trim().isEmpty()) {
			throw new InvalidBookDataException("Book title cannot be empty");
		}
		
		if(book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
			throw new InvalidBookDataException("Book author cannot be empty");
		}
		
		// se puse hacer con cada atributo 
		
		return bookRepository.save(book);
	}
	
	public BookModel updateBook(Long id, BookModel book) {
		//buscamos la entidad por la id
		BookModel book2 = bookRepository.findById(id)
				.orElseThrow(()-> new BookNotFoundException("Book not found, with id: "+id));
		//actualizamos datos (esto se podra hacer mas rapido?)
		book2.setAuthor(book.getAuthor());
		book2.setAvailable(book.isAvailable());
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
	
	public BookModel borrowBook(Long id) {
		BookModel book = bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not found, with id: "+id));
		if(!book.isAvailable()) {
			throw new BookAlreadyBorrowedException("Book with id: "+id+" is already borrow");
		}
		book.setAvailable(false);
		return bookRepository.save(book);
	}
	
	public Optional<BookModel> returnBook(Long id) {
		return bookRepository.findById(id).map(book->{
			book.setAvailable(true);
			return bookRepository.save(book); //marcamos como disponible y listo
		});
	}
	
	//
}
