package cn.pompip.myblog.server;

import cn.pompip.myblog.dao.CategoryDao;
import cn.pompip.myblog.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryDao categoryMapper;
    public List<CategoryEntity> findAllCategory(){
        return categoryMapper.findAll();
    }
}
