package cn.pompip.myblog.mapper;

import cn.pompip.myblog.entity.FundCastEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface FundMapper {
    @Select("select * from fund_cast where year(date)=#{year} and month(date)=#{month}")
    @Results({@Result(property = "createTimestamp",column = "create_timestamp",jdbcType = JdbcType.TIMESTAMP,javaType = Timestamp.class),
            @Result(property = "date",column = "date",jdbcType = JdbcType.DATE,javaType = Date.class)})
    List<FundCastEntity> findAllFundCastByYearAndMonth(@Param(value = "year") int year,@Param(value = "month")int month);

    @Insert("insert into fund_cast (date,money,product,way,reference) values (#{date},#{money},#{product},#{way},#{reference})")
    void addFundCast(FundCastEntity castEntity);

    @Select("select month(date) month,year(date) year ,sum(money) total from fund_cast  group by date_format(date,'%Y%c')")
    List<Map<String,Object>> findMonthCastByYear(int year);


}
