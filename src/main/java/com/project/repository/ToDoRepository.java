package com.project.repository;

import com.project.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {

    @Transactional
    void deleteById(Integer id);
}