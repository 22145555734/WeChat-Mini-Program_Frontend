package com.yinnnh.service;


import com.yinnnh.common.Result;
import com.yinnnh.pojo.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService{
    Result<List<Category>> getCategories();
}