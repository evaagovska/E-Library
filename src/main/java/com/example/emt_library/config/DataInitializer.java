package com.example.emt_library.config;

import com.example.emt_library.models.Author;
import com.example.emt_library.models.Country;
import com.example.emt_library.models.enums.Category;
import com.example.emt_library.services.AuthorService;
import com.example.emt_library.services.BookService;
import com.example.emt_library.services.CountryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataInitializer {

    private final BookService bookService;

    private final AuthorService authorService;

    private final CountryService countryService;

    public DataInitializer(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @PostConstruct
    public void initData(){
        this.countryService.create("Macedonia", "Europe");
        this.countryService.create("United Kingdom", "Europe");
        this.countryService.create("U.S.", "North America");
        this.countryService.create("Ireland", "Europe");
        List<Country> countryList = this.countryService.listAll();

        this.authorService.create("Tashko", "Georgievski", countryList.get(0).getId());
        this.authorService.create("J.K.", "Rowling", countryList.get(1).getId());
        this.authorService.create("Agatha", "Christie", countryList.get(1).getId());
        this.authorService.create("Stephen", "King", countryList.get(2).getId());
        this.authorService.create("Oscar", "Wilde", countryList.get(3).getId());
        this.authorService.create("William", "Shakespeare", countryList.get(1).getId());
        List<Author> authorList = this.authorService.listAll();


        this.bookService.save("Harry Potter", Category.NOVEL, authorList.get(1).getId(), 12);
        this.bookService.save("Black Seed", Category.NOVEL, authorList.get(0).getId(), 3);
        this.bookService.save("Romeo and Juliet", Category.DRAMA, authorList.get(5).getId(), 7);
        this.bookService.save("Curtain", Category.THRILER, authorList.get(2).getId(), 4);
        this.bookService.save("By the Pricking of My Thumbs", Category.FANTASY, authorList.get(2).getId(), 1);
        this.bookService.save("The Picture of Dorian Gray", Category.CLASSICS, authorList.get(4).getId(), 5);
        this.bookService.save("Misery", Category.THRILER, authorList.get(3).getId(), 3);
        this.bookService.save("The Red Horse", Category.HISTORY, authorList.get(0).getId(), 2);
        this.bookService.save("Hamlet", Category.DRAMA, authorList.get(5).getId(), 5);
    }

}
