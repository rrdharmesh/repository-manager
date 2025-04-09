package com.yubi.repository.manager.service;

import com.yubi.repository.manager.model.Repository;
import com.yubi.repository.manager.repository.RepositoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepositoryService {
    private final RepositoryRepository repositoryRepository;

    @Transactional(readOnly = true)
    @TrackLatency("get_all_repositories")
    public List<Repository> getAllRepositories() {
        return repositoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    @TrackLatency("get_repository_by_id")
    public Repository getRepositoryById(Long id) {
        return repositoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repository not found with id: " + id));
    }

    @Transactional
    @TrackLatency("create_repository")
    @NotifyOnException
    public Repository createRepository(Repository repository) {
        return repositoryRepository.save(repository);
    }

    @Transactional
    @TrackLatency("update_repository")
    @NotifyOnException
    public Repository updateRepository(Long id, Repository repository) {
        Repository existingRepo = getRepositoryById(id);
        existingRepo.setRepositoryUrl(repository.getRepositoryUrl());
        existingRepo.setAccessToken(repository.getAccessToken());
        return repositoryRepository.save(existingRepo);
    }

    @Transactional
    @TrackLatency("delete_repository")
    @NotifyOnException
    public void deleteRepository(Long id) {
        Repository repository = getRepositoryById(id);
        repositoryRepository.delete(repository);
    }
}