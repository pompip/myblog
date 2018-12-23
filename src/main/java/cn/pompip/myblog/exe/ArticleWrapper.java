package cn.pompip.myblog.exe;

import cn.pompip.lib.entity.ArticleEntity;

import java.sql.Timestamp;
import java.util.Scanner;

public class ArticleWrapper {

    private String title;
    private String content;
    private Timestamp createTimestamp;

    public ArticleWrapper(String con) {
        Scanner scanner = new Scanner(con);
        String line = scanner.nextLine();
        if (line != null && line.startsWith("# ")) {
            this.title = line.substring(2);
        } else {
            title = "无标题";
        }

        content = con;
        createTimestamp = new Timestamp(System.currentTimeMillis());
    }

    public ArticleEntity createArticleEntity() {
        ArticleEntity a = new ArticleEntity();
        a.setContent(content);
        a.setTitle(title);
        a.setCreateTimestamp(createTimestamp);
        return a;

    }
}
