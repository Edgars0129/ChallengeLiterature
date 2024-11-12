package com.alurachallenge.demo.Service;

import com.alurachallenge.demo.Model.Autor;
import com.alurachallenge.demo.Repository.AutorRepository;
import com.alurachallenge.demo.Repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutorService {
    private final AutorRepository autorRepository;
    private final LibroRepository libroRepository;

    public AutorService(AutorRepository autorRepository, LibroRepository libroRepository) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
    }

    @Transactional(readOnly = true)
    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Autor> buscarPorAnioVida(Integer anio) {
        return autorRepository.findAutoresVivosPorAnio(anio);
    }
}