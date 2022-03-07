package edu.asu.diging.springaction.core.service;

import java.util.List;

import edu.asu.diging.simpleusers.core.model.IUser;
import edu.asu.diging.springaction.core.model.Book;

public interface BookManager {

	List<Book> all();

	Book store(String author, String title,boolean available);

	Book get(Long id);

	void borrow(IUser user, Book book);
	
	void Return(IUser user, Book book);

	List<Book> findByUser(IUser user);

}