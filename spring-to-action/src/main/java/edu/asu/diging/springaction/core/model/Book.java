package edu.asu.diging.springaction.core.model;

import edu.asu.diging.simpleusers.core.model.IUser;

public interface Book {

	Long getId();

	void setId(Long id);

	String getTitle();

	void setTitle(String title);

	String getAuthor();

	void setAuthor(String author);

	boolean isAvailable();

	void setAvailable(boolean available);

	IUser getBorrowe();

	void setBorrower(IUser borrower);

}