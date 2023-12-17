package ru.flamexander.spring.data.jdbc.demo.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import ru.flamexander.spring.data.jdbc.demo.dtos.DetailedBookDto;
import ru.flamexander.spring.data.jdbc.demo.entities.Book;

import java.util.List;

public interface BooksPageRepository extends ListPagingAndSortingRepository<Book, Long> {
    @Query(
            """
                    select b.id, b.title, b.genre, a.full_name as author_name, bd.description, rs.avg_score\s
                    from BOOKS b\s
                    left join AUTHORS a on b.author_id = a.id\s
                    left join BOOKS_DETAILS bd on bd.book_id = b.id
                    left join (
                    	select book_id,avg(score) avg_score
                    	from review
                    	group by book_id
                    ) rs on rs.book_id=b.id"""
    )
    List<DetailedBookDto> findAllDetailedBooks(Pageable pageable);
}
