package com.yinnnh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yinnnh.common.Result;
import com.yinnnh.mapper.CategoryMapper;
import com.yinnnh.pojo.entity.Category;
import com.yinnnh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    // 注入 Mapper

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Result<List<Category>> getCategories() {
        // 查询条件：未删除、一级分类、按排序倒序
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getDeleted, 0)
                .eq(Category::getParentId, 0)
                .orderByDesc(Category::getSort);

        List<Category> list = categoryMapper.selectList(wrapper);
        return Result.success(list);
    }
}
