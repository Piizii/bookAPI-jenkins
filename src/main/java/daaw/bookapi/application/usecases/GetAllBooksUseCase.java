package daaw.bookapi.application.usecases;

import java.util.List;
import org.springframework.stereotype.Service;
import daaw.bookapi.application.dto.BookResponse;
import daaw.bookapi.domain.entities.Book;
import daaw.bookapi.domain.repositories.BookRepository;

@Service
public class GetAllBooksUseCase {
    private final BookRepository bookRepository;

    // Spring injects the repository (constructor injection)
    public GetAllBooksUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookResponse> execute() {
        return bookRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor());
    }
}