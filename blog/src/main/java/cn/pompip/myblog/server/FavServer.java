package cn.pompip.myblog.server;

import cn.pompip.myblog.dao.FavCategoryDao;
import cn.pompip.myblog.dao.FavDao;
import cn.pompip.myblog.entity.FavCategoryEntity;
import cn.pompip.myblog.entity.FavEntity;
import cn.pompip.myblog.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavServer {
    @Autowired
    FavDao favDao;
    @Autowired
    FavCategoryDao favCategoryDao;
    @Autowired
    LogUtil logUtil;

    public List<FavEntity> getAllFav(){
        return favDao.findAll(Sort.by("time"));
    }

    public FavEntity addEntity(FavEntity favEntity) {
        logUtil.e("addEntity"+favEntity);
        favEntity.setTime(System.currentTimeMillis());

        Optional<FavCategoryEntity> byId = favCategoryDao.findById(favEntity.getCategoryId());
        if (byId.isEmpty()){
            FavCategoryEntity categoryEntity = new FavCategoryEntity();
            categoryEntity.setName("默认");
            categoryEntity.setId(1);
            favEntity.setCategoryId(1);
            favEntity.setFavCategoryEntity(categoryEntity);
        }else {
            FavCategoryEntity categoryEntity = byId.get();
            favEntity.setFavCategoryEntity(categoryEntity);
        }

        FavEntity entity = favDao.saveAndFlush(favEntity);

        return entity;
    }

    public boolean deleteEntity(long id) {
        favDao.deleteById(id);
        return true;
    }

    public FavCategoryEntity createCategory(String name){
        FavCategoryEntity categoryEntity = new FavCategoryEntity();
        categoryEntity.setName(name);
        return favCategoryDao.saveAndFlush(categoryEntity);
    }

    public List<FavCategoryEntity> findAllCategory(){
        return favCategoryDao.findAll();
    }

    public FavEntity saveFavAndCategory(FavEntity favEntity,String name){
        FavCategoryEntity favCategoryEntity = new FavCategoryEntity();
        favCategoryEntity.setName(name);
        favCategoryDao.saveAndFlush(favCategoryEntity);
        favEntity.setCategoryId(favCategoryEntity.getId());
        return favDao.saveAndFlush(favEntity);
    }
}
