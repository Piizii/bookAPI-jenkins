package daaw.bookapi.application.usecases;

import org.springframework.stereotype.Service;
import daaw.bookapi.application.dto.BookResponse;
import daaw.bookapi.application.exceptions.BookNotFoundException;
import daaw.bookapi.domain.entities.Book;
import daaw.bookapi.domain.repositories.BookRepository;

@Service
public class GetBookByIdUseCase {
    private final BookRepository bookRepository;

    public GetBookByIdUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponse execute(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found."));
        return toResponse(book);
    }

    private BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor());
    }
}