package edu.asu.diging.springaction.core.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import edu.asu.diging.simpleusers.core.model.IUser;
import edu.asu.diging.springaction.core.data.BookRepository;
import edu.asu.diging.springaction.core.model.Book;
import edu.asu.diging.springaction.core.model.impl.BookImpl;
import edu.asu.diging.springaction.core.service.BookManager;

@Service
public class BookManagerImpl implements BookManager {
    
    @Autowired
    private BookRepository bookRepo;

    @Override
	public List<Book> all() {
        return Streamable.of(bookRepo.findAll()).stream().collect(Collectors.toList());
    }

    @Override
	public Book store(String author, String title, boolean available) {
        Book book = new BookImpl();
        book.setAuthor(author);
        book.setTitle(title);
        book.setAvailable(true);
        return bookRepo.save((BookImpl)book);
    }
	@Override
    public Book get(Long id) {
        return bookRepo.findById(id).orElse(null);
    }
	@Override
    public void borrow(IUser user, Book book) {
        book.setBorrower(user);
        book.setAvailable(false);
        bookRepo.save((BookImpl)book);
    }
	@Override
	public List<Book> findByUser(IUser user) {
	    List<BookImpl> books = bookRepo.findByBorrower(user);
	    return books.stream().filter(b -> !b.isAvailable()).collect(Collectors.toList());
	}

	@Override
	public void Return(IUser user, Book book) {
		// TODO Auto-generated method stub
        book.setBorrower(null);
        book.setAvailable(true);
        bookRepo.save((BookImpl)book);
	}
}