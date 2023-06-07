package com.example.spring_day1.Controller;

import com.example.spring_day1.Model.MyUser;
import com.example.spring_day1.Model.Todo;
import com.example.spring_day1.Service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;


    @GetMapping("/get")
    public ResponseEntity getTodos(@AuthenticationPrincipal MyUser myUser){
        List<Todo> todos = todoService.getTodos(myUser.getId());
        return ResponseEntity.status(200).body(todos);
    }

    @PostMapping("/add")
    public ResponseEntity addTodo(@AuthenticationPrincipal MyUser myUser, @RequestBody Todo todo){
        todoService.addTodo(myUser, todo);
        return ResponseEntity.status(200).body("Todo Added");
    }

    @PutMapping("/update/{todoId}")
    public ResponseEntity updateTodo(@AuthenticationPrincipal MyUser myUser, @RequestBody Todo todo, @PathVariable Integer todoId){
        todoService.updateTodo(myUser,todo,todoId);
        return ResponseEntity.status(200).body("Todo Updated");
    }

    @DeleteMapping("/delete/{todoId}")
    public ResponseEntity updateTodo(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer todoId){
        todoService.deleteTodo(myUser,todoId);
        return ResponseEntity.status(200).body("Todo Deleted");
    }


}
