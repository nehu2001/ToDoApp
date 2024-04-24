package com.project.service;

import com.project.model.ToDo;

import java.util.List;

public interface ToDoService {

    void deleteById(Integer id);

    List<ToDo> getAllToDoItems();

    ToDo findItemsById(Integer id);

    boolean updateStatus(Integer id);

    boolean updateOrSaveToDoItemInDB(ToDo toDo);
}