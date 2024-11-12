package com.alurachallenge.demo.Service;

import com.alurachallenge.demo.Model.Autor;
import com.alurachallenge.demo.Model.Libro;
import com.alurachallenge.demo.RecordDTO.DatosLibro;
import com.alurachallenge.demo.RecordDTO.DatosRespuestaAPI;
import com.alurachallenge.demo.Repository.AutorRepository;
import com.alurachallenge.demo.Repository.LibroRepository;
import com.alurachallenge.demo.Util.URLBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LibroService {
    private final Logger log = LoggerFactory.getLogger(LibroService.class);

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;
    private final ConsumoAPI consumoAPI;
    private final ConvierteDatos convierteDatos;
    private final URLBuilder urlBuilder;

    public LibroService(LibroRepository libroRepository,
                        AutorRepository autorRepository,
                        ConsumoAPI consumoAPI,
                        ConvierteDatos convierteDatos,
                        URLBuilder urlBuilder) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.consumoAPI = consumoAPI;
        this.convierteDatos = convierteDatos;
        this.urlBuilder = urlBuilder;
    }

    @Transactional(readOnly = true)
    public List<Libro> listarTodos() {
        try {
            return libroRepository.findAll();
        } catch (Exception e) {
            log.error("Error al listar todos los libros", e);
            return new ArrayList<>();
        }
    }

    @Transactional
    public List<Libro> buscarPorTitulo(String titulo) {
        try {
            List<Libro> librosLocales = libroRepository.findByTituloContainingIgnoreCase(titulo);

            if (!librosLocales.isEmpty()) {
                return librosLocales;
            }

            // Si no se encuentra en la BD, buscar en la API
            String url = urlBuilder.buildSearchUrl(titulo);
            String json = consumoAPI.obtenerDatos(url);
            DatosRespuestaAPI respuesta = convierteDatos.obtenerDatos(json, DatosRespuestaAPI.class);

            if (respuesta != null && respuesta.resultados() != null) {
                return guardarLibrosDeLaAPI(respuesta.resultados());
            }

            return new ArrayList<>();
        } catch (Exception e) {
            log.error("Error al buscar libros por título: " + titulo, e);
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Libro> buscarPorIdioma(String idioma) {
        try {
            return libroRepository.findByIdioma(idioma);
        } catch (Exception e) {
            log.error("Error al buscar libros por idioma: " + idioma, e);
            return new ArrayList<>();
        }
    }

    @Transactional(readOnly = true)
    public List<Libro> buscarPorAutor(String nombreAutor) {
        try {
            return libroRepository.findByAutorNombreContainingIgnoreCase(nombreAutor);
        } catch (Exception e) {
            log.error("Error al buscar libros por autor: " + nombreAutor, e);
            return new ArrayList<>();
        }
    }

    @Transactional
    public List<Libro> guardarLibrosDeLaAPI(List<DatosLibro> datosLibros) {
        List<Libro> librosGuardados = new ArrayList<>();

        for (DatosLibro datos : datosLibros) {
            try {
                if (!libroRepository.existsById(datos.id())) {
                    Libro libro = new Libro(datos);

                    // Guardar autores si existen
                    if (datos.autores() != null) {
                        for (var datosAutor : datos.autores()) {
                            var autor = new Autor(datosAutor);
                            autor = autorRepository.save(autor);
                            libro.getAutores().add(autor);
                        }
                    }

                    libro = libroRepository.save(libro);
                    librosGuardados.add(libro);
                }
            } catch (Exception e) {
                log.error("Error al guardar libro: " + datos.titulo(), e);
            }
        }

        return librosGuardados;
    }
}