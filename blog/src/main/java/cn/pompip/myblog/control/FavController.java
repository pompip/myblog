package cn.pompip.myblog.control;

import cn.pompip.myblog.entity.FavEntity;
import cn.pompip.myblog.server.FavServer;
import cn.pompip.myblog.utils.LogUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fav")
public class FavController {
    @Autowired
    LogUtil logUtil;
    @Autowired
    FavServer favServer;

    @GetMapping("/all")
    public List<FavEntity> getAllEntity(){
        return favServer.getAllFav();
    }

    @PostMapping("/add")
    public FavEntity addEntity(@RequestBody FavEntity favEntity) throws InvocationTargetException, IllegalAccessException {
        logUtil.e(favEntity);
//        FavEntity favEntity = new FavEntity();
//        BeanUtils.populate(favEntity,favEntityMap);

        return favServer.addEntity(favEntity);
    }

    @GetMapping("/delete")
    @ResponseBody
    public int deleteEntity(@RequestParam long id){
        favServer.deleteEntity(id );
        return 1;
    }
}
