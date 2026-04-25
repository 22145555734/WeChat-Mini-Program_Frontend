package com.yinnnh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yinnnh.common.Result;
import com.yinnnh.mapper.CategoryMapper;
import com.yinnnh.pojo.entity.Category;
import com.yinnnh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;


    @GetMapping("/categories")
    public Result<List<Category>> getCategories() {
        return categoryService.getCategories();

    }
}
