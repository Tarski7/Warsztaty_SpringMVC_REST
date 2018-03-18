package pl.coderslab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.domain.Book;
import pl.coderslab.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	@RequestMapping("/hello")
	public String hello() {
		return "{hello: World}";
	}
	
	@RequestMapping("/helloBook")
	public Book helloBook(){
		return new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel", "Helion", "programming");
	}
	
	@Autowired
	BookService bookService;
	
	@RequestMapping("/getBooks")
	public List<Book> getBooks() {
		return bookService.getList();
	}
	
	@RequestMapping("/getBook/{id}")
	public Book getBookById(@PathVariable long id) {	
		return bookService.getBookById(id);
	}
	
	@RequestMapping("/addBook/{id}/{isbn}/{title}/{author}/{publisher}/{type}")
	public String addBook(@PathVariable long id, @PathVariable String isbn,
						@PathVariable String title, @PathVariable String author,
						@PathVariable String publisher, @PathVariable String type) {
		Book book = new Book(id, isbn, title, author, publisher, type);
		bookService.addBook(book);
		return "Book has been added";
	}
	
	@RequestMapping("/editBook/{id}/{isbn}/{title}/{author}/{publisher}/{type}")
	public String editBook(@PathVariable int id, @PathVariable String isbn,
						   @PathVariable String title, @PathVariable String author,
						   @PathVariable String publisher, @PathVariable String type) {
		Book book = bookService.getBookById(id);
		int index = bookService.getList().indexOf(book);
		Book newBook = new Book(id, isbn, title, author, publisher, type);
		bookService.editBook(index, newBook);
		return "Book has been edited";
	}
	
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable long id) {
		Book book = bookService.getBookById(id);
		bookService.deleteBook(book);
		return "Book has been deleted";
	}
}
