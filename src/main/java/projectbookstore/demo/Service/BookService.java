package projectbookstore.demo.Service;

import projectbookstore.demo.Models.Book;
import projectbookstore.demo.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class BookService {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException{
        public ResourceNotFoundException(String message){
            super(message);
        }
    }

    @Autowired
    BookRepository bookRepository;

    public List<Book> list() {
        return  bookRepository.findAll();
    }

    public Book create(@RequestBody Book book){
        bookRepository.save(book);
        return book;

    }

    public Book findById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"+id));
    }

    public Book update(@PathVariable Long id, @RequestBody Book book) {
        return bookRepository.findById(id)
                .map(record -> {
                    record.setTitle(book.getTitle());
                    record.setAuthor(book.getAuthor());
                    record.setText(book.getText());
                    return bookRepository.save(record);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Book not found: " + id));
    }


    public void delete(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"+id));
        bookRepository.delete(book);
    }

}
