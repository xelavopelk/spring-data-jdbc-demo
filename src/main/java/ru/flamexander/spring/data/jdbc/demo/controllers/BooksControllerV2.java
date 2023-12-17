package ru.flamexander.spring.data.jdbc.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.flamexander.spring.data.jdbc.demo.dtos.SimplestPageDto;
import ru.flamexander.spring.data.jdbc.demo.entities.Book;
import ru.flamexander.spring.data.jdbc.demo.services.BooksService;

@RestController
@RequestMapping("/api/v2/books")
public class BooksControllerV2 {
    private final BooksService booksService;

    @Autowired
    public BooksControllerV2(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    public SimplestPageDto<Book> findAllDetailedBooks_v2(@RequestParam(required = false) Integer page, @RequestParam(name = "page-size", required = false) Integer pageSize) {
        return booksService.findPageDetailedBooks_v2(page, pageSize);
    }
}
