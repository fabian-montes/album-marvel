package com.marvel.albumdigital.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ThumbnailDato (
	@JsonAlias("path") String path,
	@JsonAlias("extension") String extension
) {}