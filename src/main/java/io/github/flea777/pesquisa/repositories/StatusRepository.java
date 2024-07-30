package io.github.flea777.pesquisa.repositories;

import io.github.flea777.pesquisa.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StatusRepository extends JpaRepository<Status, UUID> {

    Optional<Status> findByUsername(String username);
}
