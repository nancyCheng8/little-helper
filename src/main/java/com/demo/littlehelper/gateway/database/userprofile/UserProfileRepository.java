 package com.demo.littlehelper.gateway.database.userprofile;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
 public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long>, JpaSpecificationExecutor<UserProfileEntity>{
    
    Optional<UserProfileEntity> findByUserAccountAndPwd(String userAccount, String pwd);
}
