package ru.flamexander.spring.data.jdbc.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private String user_name;
    private String text;
    private Long book_id;
    private Instant created_at;
    private Short score;
}
