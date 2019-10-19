package cn.pompip.myblog.control;

import cn.pompip.myblog.entity.CommentEntity;
import cn.pompip.myblog.server.CommentServer;
import cn.pompip.myblog.utils.NeedToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentServer commentServer;


    @GetMapping("/list/{id}")
    public List<CommentEntity> commentList(@PathVariable long id){
        return  commentServer.getArticleComment(id);
    }
    @NeedToken
    @PostMapping("")
    public CommentEntity postComment(CommentEntity commentEntity){
        return commentServer.postComment(commentEntity);
    }
}
