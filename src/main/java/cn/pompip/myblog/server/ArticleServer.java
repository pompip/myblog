package cn.pompip.myblog.server;

import cn.pompip.lib.dao.ArticleDao;
import cn.pompip.lib.entity.ArticleEntity;
import cn.pompip.myblog.exe.ArticleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.sql.Timestamp;
import java.util.*;


@Service
public class ArticleServer {
    @Autowired
    ArticleDao articleDao;

    public List<ArticleEntity> getIndexArticleList() {
        List<ArticleEntity> findAll = articleDao.findAll(Sort.by(Sort.Direction.DESC, "createTimestamp"));
        findAll.forEach(this::generateBrief);
        return findAll;
    }

    private void generateBrief(ArticleEntity articleEntity) {
        StringBuilder builder = new StringBuilder();
        Scanner scanner = new Scanner(articleEntity.getContent());
        int lineNum = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = StringUtils.deleteAny(line, "#*<>`/");
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
        return articleDao.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public ArticleEntity getOne(long id) {

        return articleDao.findById(id).orElse(null);

    }

    public ArticleEntity saveArticle(String content){
        ArticleWrapper articleWrapper = new ArticleWrapper(content);
        ArticleEntity articleEntity = articleWrapper.createArticleEntity();
        ArticleEntity save = articleDao.save(articleEntity);
        return save;
    }

    public ArticleEntity updateArticle(String content, Long id) {
        ArticleEntity entity = getOne(id);
        entity.setContent(content);
        entity.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
        articleDao.save(entity);
        return entity;
    }

    public void deleteArticle(Long id) {
        articleDao.deleteById(id);
    }

}