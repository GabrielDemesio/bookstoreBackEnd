package projectbookstore.demo.Repository;

import projectbookstore.demo.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
