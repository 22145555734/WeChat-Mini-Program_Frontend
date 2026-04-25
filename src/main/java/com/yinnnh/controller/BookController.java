package com.yinnnh.controller;

import com.yinnnh.common.PageResult;
import com.yinnnh.common.Result;
import com.yinnnh.pojo.dto.BookDTO;
import com.yinnnh.pojo.entity.Book;
import com.yinnnh.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/hot")
    public Result<PageResult<Book>> getHotBooks(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {
        PageResult<Book> result = bookService.getHotBooks(page, limit);
        return Result.success(result);
    }

    @GetMapping("/new")
    public Result<PageResult<Book>> getNewBooks(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer limit) {
        PageResult<Book> result = bookService.getNewBooks(page, limit);
        return Result.success(result);
    }

    // 你之前的/hot、/new方法不变，加上详情接口
    @GetMapping("/{id}")
    public Result<Book> getBookDetail(@PathVariable("id") Long id) {
        Book book = bookService.getBookDetailById(id);
        return Result.success(book);
    }

    // 根据分类ID获取图书列表
    @GetMapping
    public Result<PageResult<Book>> getBooksByCategory(BookDTO bookDTO) {
        PageResult<Book> result = bookService.getBooksByCategory(bookDTO);
        return Result.success(result);
    }

    @GetMapping("/search")
    public Result<PageResult<Book>> searchBooks(BookDTO bookDTO) {
        PageResult<Book> result = bookService.searchBooks(bookDTO);
        return Result.success(result);
    }

    @GetMapping("/{id}/stock")
    public Result<Integer> getBookStock(@PathVariable Long id) {
        Integer stock = bookService.getBookStockById(id);
        return Result.success(stock);
    }

}