package no.hvl.dat250.rest.todos;

import java.util.ArrayList;
import java.util.Collection;

public class TodoDAO implements TodoService {
    Collection<Todo> todos = new ArrayList<>();

    @Override
    public void addTodo(Todo todo) {
        todos.add(todo);
    }

    @Override
    public Collection<Todo> getTodos() {
        return todos;
    }

    @Override
    public Todo getTodo(Long id) {
        Todo todo = null;
            for (Todo elem : todos) {
                if (elem.getId().equals(id))
                    todo = elem;
            }
        return todo;
    }

    @Override
    public Todo getTodo(String id) {
        Long newId = Long.parseLong(id);
        return getTodo(newId);
    }

    @Override
    public Todo editTodo(Todo todo) {
        Todo updatedTodo = getTodo(todo.getId());
        todos.remove(todo);
        if (updatedTodo != null) {
            updatedTodo.setDescription(todo.getDescription());
            updatedTodo.setSummary(todo.getSummary());
            todos.add(updatedTodo);}
        return updatedTodo;
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = getTodo(id);
        todos.remove(todo);
    }

    @Override
    public boolean todoExist(Long id) {
        Todo todo = getTodo(id);
        return todo != null;
    }
    @Override
    public boolean todoExist(String id) {
        Todo todo = getTodo(id);
        return todo != null;
    }
}