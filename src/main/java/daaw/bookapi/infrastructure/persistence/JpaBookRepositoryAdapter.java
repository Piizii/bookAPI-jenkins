package daaw.bookapi.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import daaw.bookapi.domain.entities.Book;
import daaw.bookapi.domain.repositories.BookRepository;

@Repository
public class JpaBookRepositoryAdapter implements BookRepository {
    private final JpaBookRepository jpaBookRepository;

    public JpaBookRepositoryAdapter(JpaBookRepository jpaBookRepository) {
        this.jpaBookRepository = jpaBookRepository;
    }

    @Override
    public List<Book> findAll() {
        return jpaBookRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return jpaBookRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Book save(Book book) {
        JpaBookEntity entity = toJpaEntity(book);
        JpaBookEntity savedEntity = jpaBookRepository.save(entity);
        return toDomain(savedEntity);
    }

    private Book toDomain(JpaBookEntity entity) {
        return new Book(
                entity.getId(),
                entity.getTitle(),
                entity.getAuthor());
    }

    private JpaBookEntity toJpaEntity(Book book) {
        return new JpaBookEntity(
                book.getId(),
                book.getTitle(),
                book.getAuthor());
    }
}