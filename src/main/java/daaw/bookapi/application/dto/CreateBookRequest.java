package daaw.bookapi.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateBookRequest(
        @NotBlank 
        @Size(max = 100) 
        String title,
        
        @NotBlank 
        @Size(max = 100) 
        String author
) { }