package cn.pompip.myblog.control;

import cn.pompip.myblog.entity.FundCastEntity;
import cn.pompip.myblog.server.FundsServer;
import cn.pompip.myblog.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/funds")
public class FundsController {

    @Autowired
    FundsServer fundsServer;

    @GetMapping({"", "/add"})
    public String addFunds() {
        return "funds/add";
    }

    @GetMapping({"/{year}/{month}", "/current"})
    public String monthFunds(@PathVariable(required = false) Integer year, @PathVariable(required = false) Integer month, Model model) {
        if (year == null || month == null) {
            year = DateUtil.instance().getYear();
            month = DateUtil.instance().getMonth();
        }


        model.addAttribute("fundList", fundsServer.findFundsCast(year,month));
        model.addAttribute("total", fundsServer.getMonthTotal(year,month) + "元");
        model.addAttribute("monthCastList",fundsServer.findMonthCast(year));
        Map<String,Integer> currentTimeMap = new HashMap<>();
        currentTimeMap.put("year",year);
        currentTimeMap.put("month",month);
        model.addAttribute("current",currentTimeMap);

        return "funds/month";
    }

    @GetMapping("/{year}")
    public String yearFunds(@PathVariable int year, Model model) {
        List<Map<String, Object>> monthCastList = fundsServer.findMonthCast(year);
        model.addAttribute("monthCastList", monthCastList);
        return "funds/year";
    }

    @PostMapping("/save")
    @ResponseBody
    public FundCastEntity save(FundCastEntity entity) {
        fundsServer.saveFunds(entity);
        return entity;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器

    }

}
