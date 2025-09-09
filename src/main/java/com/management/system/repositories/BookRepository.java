package com.management.system.repositories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.management.system.models.BookModel;
@Repository
public interface BookRepository extends CrudRepository<BookModel, Long>{

	public abstract ArrayList<BookModel> findByAuthor(String author);
	public abstract ArrayList<BookModel> findByAvailable(Boolean available);
	public abstract ArrayList<BookModel> findByPublishedYearBetween(Integer startYear, Integer endYear);
	public abstract ArrayList<BookModel> findByTitleContainingIgnoreCase(String tittle); 
}
