package cn.pompip.myblog.server;

import cn.pompip.myblog.dao.ArticleDao;
import cn.pompip.myblog.entity.ArticleEntity;
import cn.pompip.myblog.exe.ArticleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.sql.Timestamp;
import java.util.*;


@Service
public class ArticleServer {
    @Autowired
    ArticleDao dao;

    public List<ArticleEntity> getIndexArticleList() {
        List<ArticleEntity> findAll = dao.findAll(Sort.by(Sort.Direction.DESC, "createTimestamp"));
        findAll.forEach(this::generateBrief);
        return findAll;
    }

    private void generateBrief(ArticleEntity articleEntity) {
        StringBuilder builder = new StringBuilder();
        Scanner scanner = new Scanner(articleEntity.getContent());
        int lineNum = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            StringUtils.deleteAny(line, "#");
            StringUtils.deleteAny(line, "*");
            StringUtils.deleteAny(line, "<");
            StringUtils.deleteAny(line, ">");
            StringUtils.deleteAny(line, "`");
            StringUtils.deleteAny(line, "/");

            if (StringUtils.isEmpty(line)) {
                continue;
            }
            builder.append(line).append("<br>");
            if (lineNum == 3) {
                break;
            }
            lineNum++;
        }
        articleEntity.setContent(builder.toString());
    }

    public List<ArticleEntity> getAllArticle() {
        return dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public ArticleEntity getOne(long id) {

        return dao.findById(id).orElse(null);

    }

    public ArticleEntity saveArticle(String content){
        ArticleWrapper articleWrapper = new ArticleWrapper(content);
        ArticleEntity articleEntity = articleWrapper.createArticleEntity();
        ArticleEntity save = dao.save(articleEntity);
        return save;
    }

    public ArticleEntity updateArticle(String content, Long id) {
        ArticleEntity entity = getOne(id);
        entity.setContent(content);
        entity.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
        dao.save(entity);
        return entity;
    }

    public void deleteArticle(Long id) {
        dao.deleteById(id);
    }

}