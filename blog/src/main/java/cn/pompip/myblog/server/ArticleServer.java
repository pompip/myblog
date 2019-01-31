package cn.pompip.myblog.server;

import cn.pompip.myblog.entity.ArticleEntity;
import cn.pompip.myblog.exe.ArticleWrapper;
import cn.pompip.myblog.mapper.ArticleMapper;
import cn.pompip.myblog.model.WebPage;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.sql.Timestamp;
import java.util.*;


@Service
public class ArticleServer {
    private ArticleMapper articleMapper;
    private MarkdownService markdownService;

    @Autowired
    public ArticleServer(ArticleMapper articleMapper, MarkdownService markdownService) {
        this.articleMapper = articleMapper;
        this.markdownService = markdownService;
    }

    public List<ArticleEntity> getIndexArticleList() {
        List<ArticleEntity> findAll = articleMapper.findAll();
        findAll.forEach(this::generateBrief);
        return findAll;
    }

    public WebPage<ArticleEntity> getArticleListWithPage(int pageNum) {

        List<ArticleEntity> pages = articleMapper.findAllLimit(pageNum*10);
        if (pages.isEmpty()){
            return null;
        }
        pages.forEach(this::generateBrief);
        long total = articleMapper.count();
        WebPage<ArticleEntity> webPage = new WebPage<>();
        webPage.setContent(pages);
        webPage.setCurrent(pageNum);
        webPage.setTotal(total/10+1);
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
        return articleMapper.findAll();
    }

    public ArticleEntity getArticleHTML(long id) {
        ArticleEntity articleEntity =  articleMapper.findById(id).orElse(null);
        articleEntity.setContent(markdownService.markdown2Html(articleEntity.getContent()));
        return articleEntity;

    }
    public ArticleEntity getArticle(long id) {
        return articleMapper.findById(id).orElse(null);
    }


    public long saveArticle(String content){
        ArticleWrapper articleWrapper = new ArticleWrapper(content);
        ArticleEntity articleEntity = articleWrapper.createArticleEntity();
        articleEntity.setAuthorId(1);
        articleEntity.setCategoryId(1);
        articleMapper.insert(articleEntity);
        return articleEntity.getId();
    }

    public ArticleEntity updateArticle(String content, Long id) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
         articleMapper.updateContent(id,content,timestamp);
        return getArticle(id);

    }

    public void deleteArticle(long id) {
        articleMapper.deleteById(id);
    }

}