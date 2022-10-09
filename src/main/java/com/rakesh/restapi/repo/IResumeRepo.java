package com.rakesh.restapi.repo;

import com.rakesh.restapi.dao.ResumeFileDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IResumeRepo extends JpaRepository<ResumeFileDao, Integer> {
}
