package projectbookstore.demo.Controller;

import projectbookstore.demo.Models.Book;
import projectbookstore.demo.Service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.list();
        return ResponseEntity.ok(books);
    }

    @GetMapping("books/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }
    @PostMapping("/books")
    public ResponseEntity<Book> create(@Valid @RequestBody Book book) {
        Book createdBook = bookService.create(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }
    @PutMapping("books/{id}")
    public ResponseEntity<Book> update(
            @PathVariable @NotNull(message = "Id não pode ser nulo")
            @Min(value = 1, message = "Id deve ser maior que 0") Long id,
            @Valid @RequestBody Book book) {
        Book updatedBook = bookService.update(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("books/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable @NotNull(message = "Id não pode ser nulo")
            @Min(value = 1, message = "Id deve ser maior que 0") Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

