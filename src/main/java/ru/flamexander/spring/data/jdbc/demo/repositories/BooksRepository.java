package ru.flamexander.spring.data.jdbc.demo.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.flamexander.spring.data.jdbc.demo.dtos.DetailedBookDto;
import ru.flamexander.spring.data.jdbc.demo.entities.Book;

import java.time.Instant;
import java.util.List;

@Repository
public interface BooksRepository extends ListCrudRepository<Book, Long> {
    @Query(
            """
                    select b.id, b.title, b.genre, a.full_name as author_name, bd.description, rs.avg_score
                    from BOOKS b
                    left join AUTHORS a on b.author_id = a.id
                    left join BOOKS_DETAILS bd on bd.book_id = b.id
                    left join (
                    	select book_id,avg(score) avg_score
                    	from review
                    	group by book_id
                    ) rs on rs.book_id=b.id"""
    )
    List<DetailedBookDto> findAllDetailedBooks();

    @Query(
            """
                    select b.id, b.title, b.genre, a.full_name as author_name, bd.description, bs.avg_score
                    from BOOKS b
                    left join AUTHORS a on b.author_id = a.id
                    left join BOOKS_DETAILS bd on bd.book_id = b.id
                    inner join
                    (
                    	select book_id,avg(score) avg_score
                    	from review
                    	where created_at>:start
                    	group by book_id
                    	order by avg_score desc
                    	limit :limit
                    ) bs on bs.book_id=b.id
                    order by bs.avg_score desc"""
    )
    List<DetailedBookDto> findTopRewiedBooks(Integer limit, Instant start);


    @Query(
            """
                    select count(*) as cow
                    from books"""
    )
    Integer getBookStat();

    @Query(
            """
                select b.id, b.title, b.genre, a.full_name as author_name, bd.description, rs.avg_score
                from BOOKS b
                left join AUTHORS a on b.author_id = a.id
                left join BOOKS_DETAILS bd on bd.book_id = b.id
                left join (
                    select book_id,avg(score) avg_score
                    from review
                    group by book_id
                ) rs on rs.book_id=b.id
                offset :offset
                limit :pagesize
                                        
                    """
    )
    List<DetailedBookDto> findPageDetailedBooks(Integer offset, Integer pagesize);


    @Query("update books set title = :title where id = :id")
    @Modifying
    void changeTitleById(Long id, String title);
}