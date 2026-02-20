package com.aluracursos.screenmatch_spring_vsc;

import com.aluracursos.screenmatch_spring_vsc.principal.PrincipalFinal;
import com.aluracursos.screenmatch_spring_vsc.repository.SerieFinalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchSpringVscApplicationFinal implements CommandLineRunner {
    
    @Autowired
    private SerieFinalRepository repository;
    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchSpringVscApplicationFinal.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        PrincipalFinal principal = new PrincipalFinal(repository);
        principal.muestraElMenu();
    }
}
