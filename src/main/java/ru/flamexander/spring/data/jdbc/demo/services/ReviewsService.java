package ru.flamexander.spring.data.jdbc.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.flamexander.spring.data.jdbc.demo.entities.Review;
import ru.flamexander.spring.data.jdbc.demo.repositories.ReviewRepository;

import java.security.InvalidParameterException;

@Service
public class ReviewsService {
    private final ReviewRepository repository;

    @Autowired
    public ReviewsService(ReviewRepository repository) {
        this.repository = repository;
    }

    public Review create(Review review) {
        if (review.getId() != null && review.getId()!=0) {
            throw new InvalidParameterException(String.format("may be review have been already created. id=%d",review.getId()));
        }
        return repository.save(review);
    }
}
