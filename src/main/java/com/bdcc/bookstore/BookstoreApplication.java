package com.bdcc.bookstore;

import com.bdcc.bookstore.entities.Author;
import com.bdcc.bookstore.entities.Book;
import com.bdcc.bookstore.entities.Genre;
import com.bdcc.bookstore.repositories.AuthorRepository;
import com.bdcc.bookstore.repositories.BookRepository;
import com.bdcc.bookstore.repositories.GenreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository){
        return args -> {
          Stream.of("Fantasy", "Romance", "Mystery").forEach(genreName->{
              Genre genre = new Genre();
              genre.setName(genreName);
              genreRepository.save(genre);
          });

          Stream.of("George Martin", "Paulo Coelho", "Conan Doyle").forEach(authorName->{
              Author author = new Author();
              author.setFirstName(authorName.split(" ")[0]);
              author.setLastName(authorName.split(" ")[1]);
              author.setBirthDate(new Date());
              authorRepository.save(author);
          });

          String[] bookNames = {"Game of Thrones", "The aleph", "Sherlock Holmes"};
          List<Author> authorList = authorRepository.findAll();
          for(int i=0; i<authorList.size(); i++) {
              Book book = new Book();
              book.setBookName(bookNames[i]);
              book.setAuthor(authorList.get(i));
              book.setGenre(genreRepository.findById(authorList.get(i).getId()).get());
              bookRepository.save(book);
          }
        };
    }
}
