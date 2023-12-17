package ru.flamexander.spring.data.jdbc.demo.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagedContentDto<T> {
    private List<T> content;
    private Integer currentPage;
    @Setter(AccessLevel.NONE)
    private Integer pageSize;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Integer totalPages;
    private Integer totalBooks;

    public void setPageSize(Integer value) {
        if (value < 1) {
            throw new ArrayIndexOutOfBoundsException(String.format("illegal page size = %d!", value));
        } else {
            pageSize = value;
        }
    }

    public Integer getTotalPages() {
        if (pageSize < 1) {
            return 1;
        } else if (pageSize == Integer.MAX_VALUE) {
            return 1;
        } else {
            return Math.ceilDiv(totalBooks, pageSize);
        }
    }
}
