 package com.demo.littlehelper.gateway.database.userexchangerates;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
 public interface UserExchangeRatesRepository extends JpaRepository<UserExchangeRatesEntity, Long>, JpaSpecificationExecutor<UserExchangeRatesEntity>{
    
    List<UserExchangeRatesEntity> queryByUserIdOrderByCurrencyCodeAscQueryDateStringAsc(Long userId);
}
