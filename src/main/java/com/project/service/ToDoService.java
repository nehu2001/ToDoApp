package com.project.service;

import com.project.model.ToDo;

import java.util.List;

public interface ToDoService {

    void deleteById(Integer id);

    List<ToDo> getAllToDoItems();

    boolean updateOrSaveToDoItemInDB(ToDo toDo);

    boolean updateStatus(Integer id);

    ToDo findItemsById(Integer id);
}
