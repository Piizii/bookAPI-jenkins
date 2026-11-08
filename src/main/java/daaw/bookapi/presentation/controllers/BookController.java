package daaw.bookapi.presentation.controllers;

import jakarta.validation.Valid; 
import java.net.URI;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import daaw.bookapi.application.dto.BookResponse;
import daaw.bookapi.application.dto.CreateBookRequest;
import daaw.bookapi.application.usecases.GetAllBooksUseCase;
import daaw.bookapi.application.usecases.GetBookByIdUseCase;
import daaw.bookapi.application.usecases.CreateBookUseCase;


@RestController
@RequestMapping("/api/books")
public class BookController {
    private final GetAllBooksUseCase getAllBooksUseCase;
    private final GetBookByIdUseCase getBookByIdUseCase;
    private final CreateBookUseCase createBookUseCase;

    // Spring injects use cases automatically (constructor injection)
    public BookController(
            GetAllBooksUseCase getAllBooksUseCase,
            GetBookByIdUseCase getBookByIdUseCase,
            CreateBookUseCase createBookUseCase) {
        this.getAllBooksUseCase = getAllBooksUseCase;
        this.getBookByIdUseCase = getBookByIdUseCase;
        this.createBookUseCase = createBookUseCase;
    }

    @GetMapping
    public List<BookResponse> findAll() {
        return getAllBooksUseCase.execute();
    }

    @GetMapping("/{id}")
    public BookResponse findById(@PathVariable Long id) {
        return getBookByIdUseCase.execute(id);
    }

    @PostMapping
    public ResponseEntity<BookResponse> create(
            @Valid @RequestBody CreateBookRequest request,
            UriComponentsBuilder uriBuilder) {
                
        BookResponse savedBook = createBookUseCase.execute(request);
        URI location = uriBuilder
                .path("/api/books/{id}")
                .buildAndExpand(savedBook.id())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(savedBook);
    }
}