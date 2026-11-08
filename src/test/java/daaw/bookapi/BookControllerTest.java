package daaw.bookapi;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import daaw.bookapi.application.dto.BookResponse;
import daaw.bookapi.application.dto.CreateBookRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class BookControllerTest {
    @LocalServerPort
    int port;
    @Autowired
    TestRestTemplate rest;

    private String url(String path) {
        return "http://localhost:" + port + path;
    }

    @Test
    void createBook_thenRetrieveItById() {

        // Create a new book
        CreateBookRequest request = new CreateBookRequest("Clean Code",
                "Robert C. Martin");
        ResponseEntity<BookResponse> createResponse = rest.postForEntity(url("/api/books"), request,
                BookResponse.class);
        // Verify the POST response
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        BookResponse createdBook = Objects.requireNonNull(createResponse.getBody());

        assertThat(createdBook.id()).isNotNull();
        // Retrieve the created book
        ResponseEntity<BookResponse> getResponse = rest.getForEntity(
                url("/api/books/" + createdBook.id()),
                BookResponse.class);

        // Verify the GET response
        assertThat(getResponse.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        BookResponse retrievedBook = Objects.requireNonNull(getResponse.getBody());
        assertThat(retrievedBook.id()).isEqualTo(createdBook.id());
        assertThat(retrievedBook.title()).isEqualTo("Clean Code");
        assertThat(retrievedBook.author()).isEqualTo("Robert C. Martin");
    }
}