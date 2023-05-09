package com.example.todosdajava.controllers;

import com.example.todosdajava.entities.TodoEntity;
import com.example.todosdajava.requests.TodoCreateRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// http://localhost:8080/api/v1/todos [GET]

// 200

@RestController
@RequestMapping("api/v1")
public class TodoController {

    private List<TodoEntity> todos = new ArrayList<>(Arrays.asList(
            new TodoEntity(1, "Vyvenci psa"),
            new TodoEntity(2, "Jdi do obchodu"),
            new TodoEntity(3, "Bez na koupaliste")
    ));

    @GetMapping("/todos")
    public ResponseEntity<Object> todos() {
        return ResponseEntity.ok().body(this.todos);
    }

    @PostMapping("/todos")
    public ResponseEntity<Object> create(@RequestBody @Valid TodoCreateRequest todoCreateRequest) {
        try {
            TodoEntity newTodo = new TodoEntity(this.todos.size() + 1, todoCreateRequest.getLabel());
            this.todos.add(newTodo);
            return ResponseEntity.ok().body(newTodo);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}

// GET, POST, DELETE, PUT, PATCH

// GET - ziskavani dat do tabulky, detaily todo, faktura atd.
// POST - prihlasovani, registrace, vytvareni novych dat
// DELETE - mazani
// PUT a PATCH - uprava dat

// GET nema body
// POST body ma

// kosik.cz?firstName=tomas&lastName=Svojanovsky
// query params

// kosik.cz/api/v1/maso/1
// kosik.cz/api/v1/maso/2
// path params

// my.page/api/v1/users/1/todos

// [GET] my.page/api/v1/users - ziskavani vsechn uzivatelu
// [POST] my.page/api/v1/users - zakladani uzivatele
// [DELETE] my.page/api/v1/users/1 - smazani uzivatele s id 1
// [PATCH] my.page/api/v1/users - uprava uzivatele - to zalezi na body

// [POST] my.page/api/v1/users

// kosik.cz/api/v1/login  - PRihlaseni
// kosik.cz/api/v1/ovoce  - Sekce ovoce
// kosik.cz/api/v2/ovoce  - Sekce ovoce
// kosik.cz/api/v1/maso   - Sekce maso

// api/v1/ovoce endpoint, resource
// unikatni