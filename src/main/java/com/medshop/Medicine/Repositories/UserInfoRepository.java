package com.medshop.Medicine.Repositories;


import com.medshop.Medicine.Models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

    @Query(value = "select * from user_info where username = :username", nativeQuery = true)
    public UserInfo fetchByUsername(@Param("username") String username);
}
