package pl.coderslab.service;

import java.util.List;

import pl.coderslab.domain.Book;

public interface BookService {

	public List<Book> getList();
	public void setList(List<Book> list);
	public Book getBookById(long id);
	public void addBook(Book book);
	public void editBook(int id, Book book);
	public void deleteBook(Book book);
}
