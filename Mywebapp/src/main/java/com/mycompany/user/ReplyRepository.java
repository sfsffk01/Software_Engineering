package com.mycompany.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    Optional<Reply> findById(Integer id);
}
