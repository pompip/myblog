package cn.pompip.myblog.model;


import org.springframework.data.domain.Page;
import org.thymeleaf.expression.Strings;

import java.util.ArrayList;
import java.util.List;

public class WebPage <T>{
    private int total;
    private int current;
    private List<T> content;

    public   WebPage(Page<T> page){
        total = page.getTotalPages();
        current = page.getPageable().getPageNumber();
        content = page.getContent();
    }

    public int getTotal() {
        return total;
    }

    public int getCurrent() {
        return current;
    }

    public List<T> getContent() {
        return content;
    }


}
