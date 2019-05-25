package cn.pompip.myblog.control;

import cn.pompip.myblog.entity.ArticleEntity;
import cn.pompip.myblog.server.ArticleServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;


@RestController
public class DownloadController {
    @Autowired
    ArticleServer articleServer;

    @GetMapping("/download/{id}")
    public void downloadArticle(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
        ArticleEntity article = articleServer.getArticle(id);
        String content = article.getContent();
        response.setHeader("Content-Disposition", String.format("attachment;filename*=utf-8''%s",URLEncoder.encode(article.getTitle(), "utf-8")));

        response.setContentLength(content.getBytes().length);
//        response.setHeader("content-type", "application/octet-stream");
//        response.setContentType("application/octet-stream");
//        response.setCharacterEncoding("UTF-8");
        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
        bos.write(content.getBytes());
        bos.flush();
    }
}
