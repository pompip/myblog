package cn.pompip.mybill.dao;

import cn.pompip.mybill.entity.IncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeDao extends JpaRepository<IncomeEntity,Long> {

    @Query(value = "select * from income where month(date) = ?2 and year (date) = ?1",nativeQuery = true)
    public List<IncomeEntity> findAllByMonth(int year,int month);

}
