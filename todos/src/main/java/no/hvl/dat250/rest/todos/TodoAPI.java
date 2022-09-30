package no.hvl.dat250.rest.todos;

import com.google.gson.Gson;

import static spark.Spark.*;

/**
 * Rest-Endpoint.
 */
public class TodoAPI {

    static Todo todo = null;
    static TodoDAO dao = new TodoDAO();

    public static Long convertStringToLong(String toBeConverted) {
        return Long.parseLong(toBeConverted);
    }



    public static boolean isNumeric(String string) {
        int intValue;

        System.out.println(String.format("Parsing string: \"%s\"", string));

        if(string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }


    public static void main(String[] args) {
        if (args.length > 0) {
            port(Integer.parseInt(args[0]));
        } else {
            port(8080);
        }

        after((req, res) -> res.type("application/json"));

        post("/todos", (req, res) -> {
            res.type("application/json");
            Todo todo = new Gson().fromJson(req.body(), Todo.class);
            dao.addTodo(todo);

            return new Gson().toJson(todo);
        });

        get("/todos", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(new Gson()
                    .toJsonTree(dao.getTodos()));
        });

        get("/todos/description", (req, res) -> todo.getDescription());

        get("/todos/summary", (req, res) -> todo.getSummary());

        get("/todos/:id", (req, res) -> {
            res.type("application/json");
            if (!(isNumeric(req.params(":id")))){
                return String.format("The id \"%s\" is not a number!", req.params(":id"));
            }
            else if (dao.todoExist(req.params(":id"))){
            return new Gson().toJson( new Gson()
                            .toJsonTree(dao.getTodo(req.params(":id"))));}
            else {
                return String.format("Todo with the id \"%s\" not found!", req.params(":id"));
            }
        });

        put("/todos/:id", (req, res) -> {
            res.type("application/json");
            if (!(isNumeric(req.params(":id")))){
                return String.format("The id \"%s\" is not a number!", req.params(":id"));
            }
            Todo toEdit = new Gson().fromJson(req.body(), Todo.class);
            Todo editedTodo = dao.editTodo(toEdit);
            if (editedTodo != null) {
                return new Gson().toJson(new Gson().toJsonTree(editedTodo));
            }
            else {
                return String.format("The todo with id \"%s\" cannot be found", req.params(":id"));
            }
        });

        delete("/todos/:id", (req, res) -> {
            res.type("application/json");
            if (!(isNumeric(req.params(":id")))){
                return String.format("The id \"%s\" is not a number!", req.params(":id"));
            }
            else if (!(dao.todoExist(req.params(":id")))){
                return String.format("Todo with the id \"%s\" not found!", req.params(":id"));
            }
            else {
                dao.deleteTodo(convertStringToLong(req.params(":id")));
                return String.format("Todo with the id \"%s\" deleted!", req.params(":id"));}
        });




    }
}
