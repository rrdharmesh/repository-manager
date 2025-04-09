package com.yubi.repository.manager.repository;

import com.yubi.repository.manager.model.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRepository extends JpaRepository<Repository, Long> {
}