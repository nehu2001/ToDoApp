package com.project.repository;

import com.project.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Integer> {

    @Transactional
    void deleteById(Integer id);

    List<ToDo> findToDoById(Integer id);
}