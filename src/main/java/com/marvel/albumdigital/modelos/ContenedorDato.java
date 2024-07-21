package com.marvel.albumdigital.modelos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ContenedorDato(
	@JsonAlias("total") Integer total,
	@JsonAlias("results") List<PersonajeDato> personajes
) {}