package daaw.bookapi.domain.repositories;

import java.util.List;
import java.util.Optional;
import daaw.bookapi.domain.entities.Book;

public interface BookRepository {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Book save(Book book);
}