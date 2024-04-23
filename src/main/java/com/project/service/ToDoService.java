package com.project.service;

import com.project.model.ToDo;

import java.util.List;

public interface ToDoService {

    boolean saveToDoItemInDB(ToDo toDo);


    void deleteById(Integer id);


    List<ToDo> getAllToDoItems();
}
