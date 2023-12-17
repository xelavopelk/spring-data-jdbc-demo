package ru.flamexander.spring.data.jdbc.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.flamexander.spring.data.jdbc.demo.dtos.DetailedBookDto;
import ru.flamexander.spring.data.jdbc.demo.dtos.PagedContentDto;
import ru.flamexander.spring.data.jdbc.demo.dtos.SimplestPageDto;
import ru.flamexander.spring.data.jdbc.demo.entities.Book;
import ru.flamexander.spring.data.jdbc.demo.repositories.BooksPageRepository;
import ru.flamexander.spring.data.jdbc.demo.repositories.BooksRepository;

import java.time.Instant;
import java.util.List;

@Service
public class BooksService {
    private final BooksRepository booksRepository;

    private final BooksPageRepository booksPageRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, BooksPageRepository booksPageRepository) {
        this.booksRepository = booksRepository;
        this.booksPageRepository = booksPageRepository;
    }

    public List<DetailedBookDto> findAllDetailedBooks() {
        return booksRepository.findAllDetailedBooks();
    }

    public List<DetailedBookDto> findTopByPeriod(Integer count, Instant start) {
        return booksRepository.findTopRewiedBooks(count, start);
    }

    public void updateTitleById(Long id, String newTitle) {
        booksRepository.changeTitleById(id, newTitle);
    }

    public PagedContentDto<DetailedBookDto> findPageDetailedBooks(Integer page, Integer pageSize) {
        var res = new PagedContentDto<DetailedBookDto>();
        var bsStat = booksRepository.getBookStat();
        res.setTotalBooks(bsStat.intValue());
        if (page == null || pageSize == null) {
            res.setPageSize(Integer.MAX_VALUE);
            res.setCurrentPage(0);
            res.setContent(booksRepository.findAllDetailedBooks());
        } else {
            res.setPageSize(pageSize);
            res.setCurrentPage(page);
            res.setContent(booksRepository.findPageDetailedBooks(page * pageSize, pageSize));
        }
        return res;
    }

    public SimplestPageDto<Book> findPageDetailedBooks_v2(Integer page, Integer pageSize) {
        Pageable paging = PageRequest.of(page, pageSize);
        var res = new SimplestPageDto<Book>();
        var p = booksPageRepository.findAll(paging);
        res.setContent(p.getContent());
        return res;
    }
}
