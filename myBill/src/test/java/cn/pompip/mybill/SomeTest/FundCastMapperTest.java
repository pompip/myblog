package cn.pompip.mybill.SomeTest;

import cn.pompip.mybill.entity.FundCastEntity;
import cn.pompip.mybill.mapper.FundMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.function.Consumer;

public class FundCastMapperTest {
    FundMapper mapper;

    ObjectMapper objectMapper = new ObjectMapper();
    @Before
    public void createDatasource() throws IOException {
        SqlSession sqlSession = new DataSourceCreater().getSqlSession();
        mapper = sqlSession.getMapper(FundMapper.class);

    }
    @Test
    public void insert(){
        FundCastEntity fundCastEntity = new FundCastEntity();
        fundCastEntity.setDate(new Date());
        fundCastEntity.setMoney(1111);
        fundCastEntity.setReference("hello world");
        fundCastEntity.setWay("hihi");
        fundCastEntity.setProduct("haha");
        mapper.addFundCast(fundCastEntity);

    }
    @Test
    public void findall(){
        mapper.findAllFundCastByYearAndMonth(2019,1).forEach(new Consumer<FundCastEntity>() {
            @Override
            public void accept(FundCastEntity castEntity) {
                try {
                    System.out.println(objectMapper.writeValueAsString(castEntity));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Test
    public void findMonth(){
        mapper.findMonthCastByYear(2019).forEach(stringStringMap -> {
            try {
                System.out.println(objectMapper.writeValueAsString(stringStringMap));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }
}
