package com.example._250902_sprsecu_practice_methodsecurity.repository;

import com.example._250902_sprsecu_practice_methodsecurity.model.Post;
import com.example._250902_sprsecu_practice_methodsecurity.model.Status;
import com.example._250902_sprsecu_practice_methodsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(User author);
    List<Post> findByIsPublicTrue();
    List<Post> findByStatus(Status status);
}
