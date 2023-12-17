package ru.flamexander.spring.data.jdbc.demo.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;
import ru.flamexander.spring.data.jdbc.demo.dtos.ReviewDto;

import java.time.Instant;

@Getter
@Setter
@Table("review")
public class Review {
    @Id
    private Long id;
    private String userName;
    private String text;
    private Long bookId;
    private Instant createdAt;
    private Short score;

    @PersistenceCreator
    public Review(Long id, String userName, String text, Long bookId, Instant createdAt, Short score) {
        this.id = id;
        this.userName = userName;
        this.text = text;
        this.bookId = bookId;
        this.createdAt = createdAt;
        this.score = score;
    }

    public Review(ReviewDto r) {
        if (r.getId() == 0) {
            this.id = null;
        } else {
            this.id = r.getId();
        }
        this.userName = r.getUser_name();
        this.text = r.getText();
        this.bookId = r.getBook_id();
        this.createdAt = r.getCreated_at();
        this.score = r.getScore();
    }
}
