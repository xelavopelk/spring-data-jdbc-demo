package ru.flamexander.spring.data.jdbc.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.flamexander.spring.data.jdbc.demo.dtos.ReviewDto;
import ru.flamexander.spring.data.jdbc.demo.entities.Review;
import ru.flamexander.spring.data.jdbc.demo.services.ReviewsService;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewsController {
    private final ReviewsService service;

    @Autowired
    public ReviewsController(ReviewsService service) {
        this.service = service;
    }

    @PostMapping
    public Review createNewReview(@RequestBody ReviewDto reviewDto) {
        return service.create(new Review(reviewDto));
    }
}
