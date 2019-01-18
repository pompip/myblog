package cn.pompip.myblog.server;

import cn.pompip.lib.dao.ArticleDao;
import cn.pompip.lib.entity.ArticleEntity;
import cn.pompip.myblog.exe.ArticleWrapper;
import cn.pompip.myblog.model.WebPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.sql.Timestamp;
import java.util.*;


@Service
public class ArticleServer {
    private ArticleDao articleDao;
    private MarkdownService markdownService;

    @Autowired
    public ArticleServer(ArticleDao articleDao,MarkdownService markdownService) {
        this.articleDao = articleDao;
        this.markdownService = markdownService;
    }

    public List<ArticleEntity> getIndexArticleList() {
        List<ArticleEntity> findAll = articleDao.findAll(Sort.by(Sort.Direction.DESC, "createTimestamp"));
        findAll.forEach(this::generateBrief);
        return findAll;
    }

    public WebPage<ArticleEntity> getArticleListWithPage(int pageNum) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTimestamp");
        Page<ArticleEntity> pages = articleDao.findAll(PageRequest.of(pageNum,10,sort));
        if (pages.isEmpty()){
            return null;
        }
        pages.forEach(this::generateBrief);
        WebPage<ArticleEntity> webPage = new WebPage<>(pages);
        return webPage;
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
        ArticleEntity articleEntity =  articleDao.findById(id).orElse(null);
        articleEntity.setContent(markdownService.markdown2Html(articleEntity.getContent()));
        return articleEntity;

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