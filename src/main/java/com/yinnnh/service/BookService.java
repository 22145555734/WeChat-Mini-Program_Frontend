package com.yinnnh.service;

import com.yinnnh.common.PageResult;
import com.yinnnh.pojo.dto.BookDTO;
import com.yinnnh.pojo.entity.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    // 统一返回 PageResult<Book>
    PageResult<Book> getHotBooks(Integer page, Integer limit);
    PageResult<Book> getNewBooks(Integer page, Integer limit);

    Book getBookDetailById(Long id);


    PageResult<Book> getBooksByCategory(BookDTO bookDTO);

    PageResult<Book> searchBooks(BookDTO bookDTO);

    Integer getBookStockById(Long id);
}