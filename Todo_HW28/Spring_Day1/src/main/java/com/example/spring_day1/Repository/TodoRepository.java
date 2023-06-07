package com.example.spring_day1.Repository;

import com.example.spring_day1.Model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    Todo findTodoById(Integer todoId);
    List<Todo> findTodosByUserId(Integer userId);
}
