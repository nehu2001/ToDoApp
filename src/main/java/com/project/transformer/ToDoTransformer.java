package com.project.transformer;


import com.project.model.ToDo;
import org.springframework.stereotype.Component;

@Component
public class ToDoTransformer {

    public ToDo toEntity(ToDo toDo) {

        ToDo toDoData = new ToDo();
        toDoData.setTitle(toDo.getTitle());
        toDoData.setDate(toDo.getDate());
        toDoData.setStatus(toDo.getStatus());

        return toDoData;
    }
}