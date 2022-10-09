package com.rakesh.restapi.repo;

import com.rakesh.restapi.dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<UserDao, Integer> {
}
