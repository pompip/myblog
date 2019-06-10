package cn.pompip.myblog.server;

import cn.pompip.myblog.dao.ArticleDao;
import cn.pompip.myblog.entity.ArticleEntity;
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
    private ArticleDao articleMapper;
    private MarkdownService markdownService;

    @Autowired
    public ArticleServer(ArticleDao articleMapper, MarkdownService markdownService) {
        this.articleMapper = articleMapper;
        this.markdownService = markdownService;
    }

    public List<ArticleEntity> getIndexArticleList() {
        List<ArticleEntity> findAll = articleMapper.findAll();
        findAll.forEach(this::generateBrief);
        return findAll;
    }

    public WebPage<ArticleEntity> getArticleListWithPage(int pageNum) {

        Page<ArticleEntity> pages = articleMapper.findAll(  PageRequest.of(pageNum,10,Sort.Direction.DESC,"createTimestamp") );
        if (pages.isEmpty()){
            return null;
        }
//        pages.forEach(this::generateBrief);

        WebPage<ArticleEntity> webPage = new WebPage<>();
        webPage.setContent(pages.getContent());
        webPage.setCurrent(pageNum);
        webPage.setTotal(pages.getTotalPages());
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


    public ArticleEntity saveArticle(ArticleEntity articleEntity){
        System.out.println(articleEntity);
        Scanner scanner = new Scanner(articleEntity.getContent());
        String title = "无标题";

        String line = scanner.nextLine();
        if (line != null && line.startsWith("# ")) {
            title = line.substring(2);
        }
        articleEntity.setTitle(title);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (articleEntity.getCreateTimestamp()==null){
            articleEntity.setCreateTimestamp(timestamp);
        }
        articleEntity.setUpdateTimestamp(timestamp);

        articleEntity.setAuthorId(1);
        articleEntity.setCategoryId(1);
        return articleMapper.saveAndFlush(articleEntity);
    }

    public ArticleEntity updateArticle(String content, Long id) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//         articleMapper.updateContent(id,content,timestamp);
        return getArticle(id);

    }

    public void deleteArticle(long id) {
        articleMapper.deleteById(id);
    }

}