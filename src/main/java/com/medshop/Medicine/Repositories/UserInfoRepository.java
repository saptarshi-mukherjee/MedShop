package com.medshop.Medicine.Repositories;


import com.medshop.Medicine.Models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
}
