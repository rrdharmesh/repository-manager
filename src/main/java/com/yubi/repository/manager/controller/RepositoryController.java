package com.yubi.repository.manager.controller;

import com.yubi.repository.manager.model.Repository;
import com.yubi.repository.manager.service.RepositoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repositories")
@RequiredArgsConstructor
public class RepositoryController {
    private final RepositoryService repositoryService;

    @GetMapping
    @TrackLatency("get_all_repositories_api")
    public ResponseEntity<List<Repository>> getAllRepositories() {
        return ResponseEntity.ok(repositoryService.getAllRepositories());
    }

    @GetMapping("/{id}")
    @TrackLatency("get_repository_by_id_api")
    public ResponseEntity<Repository> getRepositoryById(@PathVariable Long id) {
        return ResponseEntity.ok(repositoryService.getRepositoryById(id));
    }

    @PostMapping
    @TrackLatency("create_repository_api")
    @NotifyOnException
    public ResponseEntity<Repository> createRepository(@Valid @RequestBody Repository repository) {
        return new ResponseEntity<>(repositoryService.createRepository(repository), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @TrackLatency("update_repository_api")
    @NotifyOnException
    public ResponseEntity<Repository> updateRepository(@PathVariable Long id, @Valid @RequestBody Repository repository) {
        return ResponseEntity.ok(repositoryService.updateRepository(id, repository));
    }

    @DeleteMapping("/{id}")
    @TrackLatency("delete_repository_api")
    @NotifyOnException
    public ResponseEntity<Void> deleteRepository(@PathVariable Long id) {
        repositoryService.deleteRepository(id);
        return ResponseEntity.noContent().build();
    }
}