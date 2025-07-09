package org.example.todoapispring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    private static List<Todo> todoList;


    private TodoService todoService;
    private TodoService todoService2;


    // constructor based dependency injection, field inject @autowired is not recomended by spring
    public TodoController(@Qualifier("fakeTodoService") TodoService todoService, @Qualifier("anotherTodoService") TodoService todoService2) {
        this.todoService = todoService;
        this.todoService2 = todoService2;
        todoList = new ArrayList<>();

        todoList.add(new Todo(1,false,"Todo 1",1));
        todoList.add(new Todo(2,true,"Todo 2",2));

    }

    // @GetMapping("/todos")

    @GetMapping
    // adding query para
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false,defaultValue = "true") boolean isCompleted) {
        System.out.println("Incoming Query Param: " + isCompleted + this.todoService.doSomething());
        return ResponseEntity.status(HttpStatus.OK).body(todoList);
    }

   // @PostMapping("/todos")
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo){
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }


   // @GetMapping("/todos/{todoId}")
    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodo(@PathVariable Long todoId){
        for(Todo todo : todoList){
            if(todo.getId() == todoId){
                return ResponseEntity.status(HttpStatus.OK).body(todo);
            }
        }
        return ResponseEntity.notFound().build(); 
    }
}
