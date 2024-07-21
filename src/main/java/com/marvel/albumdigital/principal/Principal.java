package com.marvel.albumdigital.principal;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

import com.marvel.albumdigital.modelos.EnvoltorioDato;
import com.marvel.albumdigital.servicio.ConvierteDatos;

public class Principal {
	private String URL_BASE = "https://gateway.marvel.com:443/v1/public";
	private String APIKEY_PUBLICA = System.getenv("MARVEL_PUBLIC_APIKEY");
	private String APIKEY_PRIVADA = System.getenv("MARVEL_PRIVATE_APIKEY");

	public void enviarSolicitud() {
		// obtener timestamp del sistema en segundos
		long unixTimestamp = Instant.now().getEpochSecond();
		String timestamp = Long.toString(unixTimestamp);

		// generar MD5 hash
		String hash = "";
		String texto = timestamp + APIKEY_PRIVADA + APIKEY_PUBLICA;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(texto.getBytes());
			byte[] digest = md.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			hash = bigInt.toString(16);
			while(hash.length() < 32 ) {
				hash = "0"+hash;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// contruir link de Marvel API (firmar solicitud con llaves)
		int numero_elemento = 0;
		String url = URL_BASE + "/characters"
				+ "?ts=" + timestamp + "&apikey=" + APIKEY_PUBLICA + "&hash=" + hash 
				+ "&limit=1&offset=" + numero_elemento;
		// System.out.println(url);

		// enviar solicitud a la API de Marvel
		String json = solicitudJson(url);
		// System.out.println(json);

		// deserealizacion del json
		ConvierteDatos conversor = new ConvierteDatos();
		EnvoltorioDato dato = conversor.obtenerDatos(json, EnvoltorioDato.class);
		System.out.println(dato);
	}

	public String solicitudJson(String url) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.build();

		HttpResponse<String> response = null;
		try {
			response = client
					.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		String json = response.body();
		return json;
	}
}