package com.projeto.service;

import com.projeto.model.Remedio;
import com.projeto.repository.RemedioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemedioService {

    private final RemedioRepository repository;

    public RemedioService(RemedioRepository repository) {
        this.repository = repository;
    }

    public List<Remedio> listarTodos() {
        return repository.findAll();
    }

    public Remedio salvar(Remedio remedio) {
        return repository.save(remedio);
    }

}