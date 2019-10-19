package cn.pompip.myblog.control;

import cn.pompip.myblog.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller

public class NotFoundController implements ErrorController   {
    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return "/error";
    }
    @RequestMapping(value = {"/error"})
    @ResponseBody
    public ResponseModel error(WebRequest request) {
        Throwable error = errorAttributes.getError(request);

        ResponseModel body = new ResponseModel();
        body.setCode(-1);
        if (error!=null){
            body.setMsg(error.toString());
            body.setData(error.getMessage());
        }else {
            body.setMsg("unknown error");
            body.setData("unknown error");
        }

        return body;
    }




}
