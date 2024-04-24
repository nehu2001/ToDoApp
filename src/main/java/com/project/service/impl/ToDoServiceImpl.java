package com.project.service.impl;

import com.project.model.ToDo;
import com.project.repository.ToDoRepository;
import com.project.service.ToDoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository toDoRepository;

    @Override
    public boolean updateStatus(Integer id) {
        List<ToDo> toDo = toDoRepository.findToDoById(id);
        ToDo todoData = toDo.get(0);
        todoData.setStatus("Completed");
        return updateOrSaveToDoItemInDB(todoData);
    }

    @Override
    public ToDo findItemsById(Integer id) {
        List<ToDo> toDo = toDoRepository.findToDoById(id);
        return toDo.get(0);
    }

    @Override
    public boolean updateOrSaveToDoItemInDB(ToDo toDo) {

        try {
            toDoRepository.save(toDo);
            return true;
        } catch (Exception exception) {
            log.error("Exception while saving. Error: {}", exception.getMessage(), exception);
        }
        return false;
    }

    @Override
    public void deleteById(Integer id) {
        toDoRepository.deleteById(id);
    }

    @Override
    public List<ToDo> getAllToDoItems() {
        return toDoRepository.findAll();
    }
}