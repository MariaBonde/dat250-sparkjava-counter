package no.hvl.dat250.rest.todos;

import java.util.Collection;

public interface TodoService {
    public void addTodo ( Todo todo);
    public Collection<Todo> getTodos();
    public Todo getTodo(Long id);
    public Todo getTodo(String id);
    public Todo editTodo(Todo todo);
    public void deleteTodo(Long id);
    public boolean todoExist(Long id);
    public boolean todoExist(String id);
}
