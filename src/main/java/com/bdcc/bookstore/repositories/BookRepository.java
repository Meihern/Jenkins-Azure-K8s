package com.bdcc.bookstore.repositories;

import com.bdcc.bookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BookRepository extends JpaRepository<Book, Long>{

}
