package ru.flamexander.spring.data.jdbc.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.flamexander.spring.data.jdbc.demo.dtos.DetailedBookDto;
import ru.flamexander.spring.data.jdbc.demo.dtos.SimplestPageDto;
import ru.flamexander.spring.data.jdbc.demo.services.BooksService;

@RestController
@RequestMapping("/api/v1/books")
public class BooksController {
    private final BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    public SimplestPageDto<DetailedBookDto> findAllDetailedBooks() {
        return new SimplestPageDto<>(booksService.findAllDetailedBooks());
    }

    @PatchMapping("/{id}/title")
    public void updateTitleById(@PathVariable Long id, @RequestParam String value) {
        booksService.updateTitleById(id, value);
    }
}
