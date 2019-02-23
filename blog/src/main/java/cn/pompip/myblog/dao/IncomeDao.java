package cn.pompip.myblog.dao;

import cn.pompip.myblog.entity.IncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeDao extends JpaRepository<IncomeEntity,Long> {
}
