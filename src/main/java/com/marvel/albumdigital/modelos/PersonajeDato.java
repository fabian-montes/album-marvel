package com.marvel.albumdigital.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PersonajeDato (
	@JsonAlias("id") Integer id,
	@JsonAlias("name") String nombre,
	@JsonAlias("description") String descripcion,
	@JsonAlias("thumbnail") ThumbnailDato miniatura
) {}