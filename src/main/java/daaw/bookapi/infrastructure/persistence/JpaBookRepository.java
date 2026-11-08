package daaw.bookapi.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBookRepository extends JpaRepository<JpaBookEntity, Long> 
{}