package com.example.to_do.service;


import com.example.to_do.model.Todo_Entity;
import com.example.to_do.repository.Task_Repository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@AllArgsConstructor
@Service
public class To_Do_Service {

    @Autowired
    private final Task_Repository task_repository;


    public List<Todo_Entity>getTodo(){
        return task_repository.findAll();
    }

    public Todo_Entity getTo_do(Long id){
        var findtodo = task_repository.findById(id).orElse(null);
        if(findtodo == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");


        }
        return findtodo;
    }

    public Todo_Entity addTodo(Todo_Entity todo){
        return task_repository.save(todo);
    }

    public Todo_Entity addTodoById(Todo_Entity todo, Long id) {
        if (task_repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Todo with ID " + id + " already exists"
            );
        }

        todo.setId(id);
        return task_repository.save(todo);
    }

    public boolean deletebyId(Long id){

        task_repository.deleteById(id);
        return true;
    }
}
