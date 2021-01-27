package com.example.demoapi.controller;

import com.example.demoapi.entity.Todo;
import com.example.demoapi.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos(){
        List<Todo> todos = this.todoService.getAllTodos();
        return ResponseEntity.ok().body(todos);
    }

    @PostMapping("/newTodo")
    public ResponseEntity<?> newTodo(@RequestParam("title") String title){
        this.todoService.createNewTodo(title);
        return ResponseEntity.ok().body("create success");
    }

    @PutMapping("/todo")
    public ResponseEntity completedTodo(@RequestParam("id") Long id) {
        return ResponseEntity.ok().body(this.todoService.completedTodo(id));
    }

    @DeleteMapping("delete")
    public ResponseEntity deletedTodo(@RequestParam("id") Long id) {
        return ResponseEntity.ok().body(this.todoService.deletedTodo(id));
    }

}
