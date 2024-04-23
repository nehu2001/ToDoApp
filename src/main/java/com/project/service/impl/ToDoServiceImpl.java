package com.project.service.impl;

import com.project.model.ToDo;
import com.project.repository.ToDoRepository;
import com.project.service.ToDoService;
import com.project.transformer.ToDoTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository toDoRepository;
    private final ToDoTransformer toDoTransformer;

    @Override
    public boolean saveToDoItemInDB(ToDo toDo) {
        log.debug("Inside saveToDoItemInDB: {}", toDo);

        try {
            ToDo todo = toDoTransformer.toEntity(toDo);
            toDoRepository.save(todo);
            return true;
        } catch (Exception exception) {
            log.error("Exception while saving. Error: {}", exception.getMessage(), exception);
        }

        log.debug("Leaving saveToDoItemInDB: {}", toDo);
        return false;
    }

    @Override
    public void deleteById(Integer id) {
        toDoRepository.deleteById(id);
    }

    @Override
    public List<ToDo> getAllToDoItems() {
        log.debug("Inside getAllToDoItems");
        List<ToDo> todoList = toDoRepository.findAll();
        log.debug("Leaving getAllToDoItems with {} items", todoList.size());
        return todoList;
    }
}