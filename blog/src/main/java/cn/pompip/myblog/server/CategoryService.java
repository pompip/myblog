package cn.pompip.myblog.server;

import cn.pompip.myblog.entity.CategoryEntity;
import cn.pompip.myblog.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryMapper categoryMapper;
    public List<CategoryEntity> findAllCategory(){
        return categoryMapper.findAllCategory();
    }
}
