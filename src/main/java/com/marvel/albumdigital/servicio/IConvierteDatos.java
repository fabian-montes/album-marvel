package com.marvel.albumdigital.servicio;

public interface IConvierteDatos {
	<T> T obtenerDatos(String json, Class<T> clase);
}