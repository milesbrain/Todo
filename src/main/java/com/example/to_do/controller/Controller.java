package com.example.to_do.controller;


import com.example.to_do.model.Todo_Entity;
import com.example.to_do.service.To_Do_Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class Controller {


    private final To_Do_Service to_do_service;


    @PostMapping
    public ResponseEntity<Todo_Entity> addtask(@RequestBody Todo_Entity todo){
        todo.setLocalDateTime(LocalDateTime.now());
        return ResponseEntity.ok(to_do_service.addTodo(todo));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Todo_Entity>gettask(@PathVariable Long id){
        return ResponseEntity.ok(to_do_service.getTo_do(id));

    }
    @GetMapping()
    public List<Todo_Entity> getalltask(){
        return to_do_service.getTodo();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Todo_Entity> createTodoWithId(
            @PathVariable Long id,
            @RequestBody Todo_Entity todo) {

        try {
            return new ResponseEntity<>(
                    to_do_service.addTodoById(todo, id),
                    HttpStatus.CREATED
            );
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Todo_Entity>deleteEntity(@PathVariable Long id) {

        var user= to_do_service.deletebyId(id);
        if(to_do_service.deletebyId(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
