package ru.flamexander.spring.data.jdbc.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.flamexander.spring.data.jdbc.demo.dtos.DetailedBookDto;
import ru.flamexander.spring.data.jdbc.demo.services.BooksService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BooksController {
    private final BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    public List<DetailedBookDto> findAllDetailedBooks() {
        return booksService.findAllDetailedBooks();
    }
}
