package com.bdcc.bookstore.repositories;

import com.bdcc.bookstore.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
