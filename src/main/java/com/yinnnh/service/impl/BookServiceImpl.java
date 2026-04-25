package com.yinnnh.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinnnh.common.PageResult;
import com.yinnnh.mapper.BookMapper;
import com.yinnnh.pojo.dto.BookDTO;
import com.yinnnh.pojo.entity.Book;
import com.yinnnh.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public PageResult<Book> getHotBooks(Integer page, Integer limit) {
        // 1. 开启分页 (PageHelper 会自动拦截紧随其后的第一个查询)
        PageHelper.startPage(page, limit);
        // 2. 执行查询 (此时返回的 list 实际上已经被 PageHelper 包装成了 Page 对象)
        // 注意：这里不需要传 offset 和 limit 了，SQL 中也不需要这两个参数
        List<Book> books = bookMapper.selectHotBookList();
        // 3. 强转为 Page 对象以获取总记录数
        // 如果不强转，就需要像第一段代码那样单独查 count，那就没意义了
        Page<Book> p = (Page<Book>) books;
        // 4. 封装结果
        PageResult<Book> pageResult = new PageResult<>();
        pageResult.setRecords(p.getResult()); // 获取当前页数据
        pageResult.setTotal(p.getTotal());    // 获取总记录数 (PageHelper 自动执行了 count 查询)
        pageResult.setSize(limit);         // 设置每页大小
        return pageResult;
    }

    @Override
    public PageResult<Book> getNewBooks(Integer page, Integer limit) {
        // 1. 开启分页 (PageHelper 会自动拦截紧随其后的第一个查询)
        PageHelper.startPage(page, limit);

        List<Book> books = bookMapper.selectNewBookList();

        Page<Book> p = (Page<Book>) books;

        PageResult<Book> pageResult = new PageResult<>();
        pageResult.setRecords(p.getResult()); // 获取当前页数据
        pageResult.setTotal(p.getTotal());    // 获取总记录数 (PageHelper 自动执行了 count 查询)
        pageResult.setSize(limit);         // 设置每页大小
        return pageResult;
    }

    @Override
    public Book getBookDetailById(Long id) {
        // 1. 查询图书主体信息
        Book book = bookMapper.selectById(id);
        if (book == null || book.getDeleted() == 1) {
            throw new RuntimeException("图书不存在或已删除");
        }
        return book;
    }


    // 根据分类ID获取图书列表
    @Override
    public PageResult<Book> getBooksByCategory(BookDTO bookDTO) {
        // 1. 处理分页参数默认值
        int page = bookDTO.getPage() == null ? 1 : bookDTO.getPage();
        int limit = bookDTO.getLimit() == null ? 10 : bookDTO.getLimit();

        // 2. 开启分页（PageHelper 自动拦截后续查询，拼接分页）
        PageHelper.startPage(page, limit);

        // 3. 执行查询（查询逻辑完全写在 Mapper 里，符合你的要求）
        List<Book> bookList = bookMapper.selectBooksByCategory(bookDTO);

        // 4. 封装分页结果
        Page<Book> pageData = (Page<Book>) bookList;
        PageResult<Book> pageResult = new PageResult<>();
        pageResult.setRecords(pageData.getResult());
        pageResult.setTotal(pageData.getTotal());
        pageResult.setSize(limit);
        return pageResult;
    }

    @Override
    public PageResult<Book> searchBooks(BookDTO bookDTO) {
        // 1. 处理分页默认值
        int page = bookDTO.getPage() == null ? 1 : bookDTO.getPage();
        int limit = bookDTO.getLimit() == null ? 10 : bookDTO.getLimit();

        // 2. 开启分页
        PageHelper.startPage(page, limit);

        // 3. 执行查询
        List<Book> bookList = bookMapper.selectSearchBooks(bookDTO);

        // 4. 封装分页结果
        Page<Book> pageData = (Page<Book>) bookList;
        PageResult<Book> pageResult = new PageResult<>();
        pageResult.setRecords(pageData.getResult());
        pageResult.setTotal(pageData.getTotal());
        pageResult.setSize(limit);
        return pageResult;
    }

    @Override
    public Integer getBookStockById(Long id) {
        // 1. 查询图书（使用 MP 自带的 selectById，自动过滤逻辑删除）
        Book book = bookMapper.selectById(id);
        // 2. 校验图书是否存在/已删除
        if (book == null) {
            throw new RuntimeException("图书不存在");
        }
        // 3. 返回库存数量
        return book.getStock();
    }


}