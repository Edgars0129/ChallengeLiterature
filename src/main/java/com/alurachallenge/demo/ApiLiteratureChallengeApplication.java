package com.alurachallenge.demo;

import com.alurachallenge.demo.Principal.Principal;
import com.alurachallenge.demo.Repository.AutorRepository;
import com.alurachallenge.demo.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.alurachallenge.demo.Service.AutorService;
import com.alurachallenge.demo.Service.LibroService;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class ApiLiteratureChallengeApplication implements CommandLineRunner{

	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private LibroService libroService;

	@Autowired
	private AutorService autorService;

	public static void main(String[] args) {
		SpringApplication.run(ApiLiteratureChallengeApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroService, autorService);
		principal.mostrarMenu();
	}
}
