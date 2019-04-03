package cn.pompip.mybill.server;

import cn.pompip.mybill.dao.IncomeDao;
import cn.pompip.mybill.entity.FundCastEntity;
import cn.pompip.mybill.entity.IncomeEntity;
import cn.pompip.mybill.mapper.FundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FundsServer {
    @Autowired
    FundMapper fundMapper;

    @Autowired
    IncomeDao incomeDao;


    public List<FundCastEntity> findFundsCast(int year,int month){
        List<FundCastEntity> fundCastEntityArrayList = fundMapper.findAllFundCastByYearAndMonth(year, month);
        return fundCastEntityArrayList;
    }

    public List<Map<String,Object>> findMonthCast(int year){
        List<Map<String, Object>> monthCastList = fundMapper.findMonthCastByYear(year);
        return monthCastList;
    }

    public double getMonthTotal(Integer year,Integer month){
        String finalYear = year.toString();
        String finalMonth = month.toString();
        return findMonthCast(year)
                .stream()
                .filter(stringObjectMap -> stringObjectMap.get("month").toString().equals(finalMonth))
                .filter(map->map.get("year").toString().equals(finalYear))
                .mapToDouble(value -> (double) value.get("total"))
                .sum();
    }

    public void saveFunds(FundCastEntity fundCastEntity){
        fundMapper.addFundCast(fundCastEntity);
    }

    public List<IncomeEntity> fundIncomeByMonth(int year,int month){
        return incomeDao.findAllByMonth(year,month);
    }
}
