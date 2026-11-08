package daaw.bookapi.application.usecases;

import org.springframework.stereotype.Service;
import daaw.bookapi.application.dto.CreateBookRequest;
import daaw.bookapi.application.dto.BookResponse;
import daaw.bookapi.domain.entities.Book;
import daaw.bookapi.domain.repositories.BookRepository;

@Service
public class CreateBookUseCase {
    private final BookRepository bookRepository;

    public CreateBookUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponse execute(CreateBookRequest request) {
        Book book = new Book(
                null,
                request.title(),
                request.author());
        Book savedBook = bookRepository.save(book);
        return new BookResponse(
                savedBook.getId(),
                savedBook.getTitle(),
                savedBook.getAuthor());
    }
}