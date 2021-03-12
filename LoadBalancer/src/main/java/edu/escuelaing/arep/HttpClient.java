package edu.escuelaing.arep;

import okhttp3.*;

import java.io.IOException;

public class HttpClient {

    private OkHttpClient httpClient;
    private String baseUrl = "http://172.17.0.1";
    private String[] ports = { ":10001", ":10002", ":10003" };
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private int serverNumber = 0;

    /**
     * Constructor de la clase
     */
    public HttpClient() {
        httpClient = new OkHttpClient();
    }

    /**
     * Hace una petición get al path dado de la url dada
     * @param path Es el path a hacer el get
     * @return Un string con la información devuelta por la petición realizada
     * @throws IOException En caso de no poder realizar la excepción
     */
    public String getMensajes(String path) throws IOException {
        Request request = request = new Request.Builder().url(baseUrl + ports[serverNumber] + path).get().build();
        System.out.println("Request enviado a nodo " + baseUrl + ports[serverNumber] + path);
        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * Hace una petición post al path dado de la url dada con los datos dados
     * @param message Son los datos a enviar para realizar la petición post
     * @param path Es el path a hacer el post
     * @return Un string con la información devuelta por la petición realizada
     * @throws IOException En caso de no poder realizar la excepción
     */
    public String postMensajes(String message, String path) throws IOException {
        RequestBody body = RequestBody.create(message, JSON);
        Request request = new Request.Builder().url(baseUrl + ports[serverNumber] + path).post(body).build();
        System.out.println("Sending POST to node: " + baseUrl + ports[serverNumber] + path);
        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * Determina que puerto se utilizará, que será el nodo que responderá la petición
     */
    public void roundRobin() {
        this.serverNumber = (this.serverNumber + 1) % ports.length;
    }
}