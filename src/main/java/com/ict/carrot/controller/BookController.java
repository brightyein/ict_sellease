package com.ict.carrot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.carrot.model.Book;
import com.ict.carrot.service.BookService;

@RestController
@RequestMapping("/v1/books")
public class BookController {

  private final BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public ResponseEntity<List<Book>> getAllBooks() {
    List<Book> books = bookService.getAllBooks();
    return ResponseEntity.ok(books);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable Long id) {
    Optional<Book> book = bookService.getBookById(id);
    return book.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Book> addBook(@RequestBody Book book) {
    Book savedBook = bookService.saveBook(book);
    return ResponseEntity.ok(savedBook);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
    // Call the service method to update the book
    Optional<Book> existingBookOptional = bookService.getBookById(id);

    if (existingBookOptional.isPresent()) {
      Book existingBook = existingBookOptional.get();
      existingBook.setTitle(updatedBook.getTitle());
      existingBook.setAuthor(updatedBook.getAuthor());
      existingBook.setGenre(updatedBook.getGenre());

      // Save the updated book using the service
      Book savedBook = bookService.saveBook(existingBook);
      return ResponseEntity.ok(savedBook);
    } else {
      // Book with the given id not found
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    bookService.deleteBook(id);
    return ResponseEntity.noContent().build();
  }
}
