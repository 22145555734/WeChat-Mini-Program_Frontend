package com.yinnnh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yinnnh.pojo.dto.BookDTO;
import com.yinnnh.pojo.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
    // 热门图书
    @Select("select * from book where deleted = 0 and sales > 300")
    List<Book> selectHotBookList();


    // 最新图书
    @Select("select * from book where deleted = 0")
    List<Book> selectNewBookList();


    //selectbyid在mp

    //
    // 按分类查询图书（带动态排序）
    List<Book> selectBooksByCategory(@Param("dto") BookDTO bookDTO);


    // 关键词搜索图书
    List<Book> selectSearchBooks(@Param("dto") BookDTO bookDTO);
}