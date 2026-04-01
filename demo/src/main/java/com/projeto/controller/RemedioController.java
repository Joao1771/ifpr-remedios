package com.projeto.controller;

import com.projeto.model.Remedio;
import com.projeto.service.RemedioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/remedios")
public class RemedioController {

    private final RemedioService service;

    public RemedioController(RemedioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Remedio> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Remedio salvar(@RequestBody Remedio remedio) {
        return service.salvar(remedio);
    }
}