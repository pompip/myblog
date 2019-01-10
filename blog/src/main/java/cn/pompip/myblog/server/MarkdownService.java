package cn.pompip.myblog.server;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.profiles.pegdown.Extensions;
import com.vladsch.flexmark.profiles.pegdown.PegdownOptionsAdapter;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.options.DataHolder;
import org.springframework.stereotype.Service;

@Service
public class MarkdownService {
    private final HtmlRenderer renderer;
    private final Parser parser;

    public MarkdownService() {
        DataHolder options = PegdownOptionsAdapter.flexmarkOptions(true, Extensions.ALL);
        parser = Parser.builder(options).build();
        renderer = HtmlRenderer.builder(options).build();
    }


    public String markdown2Html(String markdown) {
        Node document = parser.parse(markdown);
        String html = renderer.render(document);
        return html;
    }
}
