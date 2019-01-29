package cn.pompip.myblog.model;



import java.util.List;

public class WebPage <T>{
    private long total;
    private long current;
    private List<T> content;

    public void setTotal(long total) {
        this.total = total;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getTotal() {
        return total;
    }

    public long getCurrent() {
        return current;
    }

    public List<T> getContent() {
        return content;
    }


}
