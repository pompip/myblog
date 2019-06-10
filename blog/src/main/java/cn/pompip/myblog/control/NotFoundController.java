package cn.pompip.myblog.control;

import cn.pompip.myblog.model.ResponseModel;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller

public class NotFoundController implements ErrorController   {

    @Override
    public String getErrorPath() {
        return "/error";
    }
    @RequestMapping(value = {"/error"})
    @ResponseBody
    public ResponseModel error(HttpServletRequest request) {
        ResponseModel body = new ResponseModel();
        body.setCode(-1);
        body.setMsg(request.getPathInfo());
        body.setData(request.toString());
        return body;
    }




}
