package com.example.to_do.repository;

import com.example.to_do.model.Todo_Entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Task_Repository extends JpaRepository<Todo_Entity,Long> {
}
