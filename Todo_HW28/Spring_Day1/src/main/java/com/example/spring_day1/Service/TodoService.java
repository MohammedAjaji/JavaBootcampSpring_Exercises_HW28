package com.example.spring_day1.Service;

import com.example.spring_day1.ApiException.ApiException;
import com.example.spring_day1.Model.MyUser;
import com.example.spring_day1.Model.Todo;
import com.example.spring_day1.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    public List<Todo> getTodos(Integer userId) {
        return todoRepository.findTodosByUserId(userId);
    }

    public void addTodo(MyUser user, Todo todo) {
        todo.setUser(user);
        todoRepository.save(todo);
    }

    public void updateTodo(MyUser user, Todo todo, Integer todoId) {
        Todo oldTodo = todoRepository.findTodoById(todoId);

        if (oldTodo == null ){
            throw new ApiException("Todo Not found");
        }

        if (!(user.getId().equals(oldTodo.getUser().getId()))){
           throw new ApiException("Not Authorised");
        }
        oldTodo.setMessage(todo.getMessage());
        todoRepository.save(oldTodo);

    }

    public void deleteTodo(MyUser user, Integer todoId) {
        Todo todo = todoRepository.findTodoById(todoId);

        if (todo == null ){
            throw new ApiException("Todo Not found");
        }

        if (!(user.getId().equals(todo.getUser().getId()))){
            throw new ApiException("Not Authorised");
        }
            todoRepository.delete(todo);


    }
}
