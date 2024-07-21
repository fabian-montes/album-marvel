package com.marvel.albumdigital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marvel.albumdigital.principal.Principal;

@SpringBootApplication
public class AlbumdigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbumdigitalApplication.class, args);
		Principal obj = new Principal();
		obj.enviarSolicitud();
	}

}
