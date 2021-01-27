package com.example.demoapi.service;

import com.example.demoapi.entity.Todo;
import com.example.demoapi.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos(){
        return this.todoRepository.findAll();
    }

    public void createNewTodo(String title) {
        Todo todo = new Todo();
        todo.setTitle(title);
        this.todoRepository.save(todo);
    }

    public ResponseEntity<?> completedTodo(Long id){
       Optional<Todo> todo = this.todoRepository.findById(id);

       if(todo.isPresent()){
           todo.get().setCompleted(!todo.get().isCompleted());
           this.todoRepository.save(todo.get());
           return ResponseEntity.ok().body("completedTodo success");
       } else {
           return ResponseEntity.badRequest().body("completedTodo fail");
       }
    }

    public ResponseEntity<?> deletedTodo(Long id){
        try {
            Optional<Todo> todo = this.todoRepository.findById(id);
            if(todo.isPresent()){
                this.todoRepository.deleteById(id);
                return ResponseEntity.ok().body("deletedTodo success");
            } else {
                return ResponseEntity.badRequest().body("deletedTodo fail");
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown Error");
        }
    }
}
