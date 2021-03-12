package edu.escuelaing.arep;

import static spark.Spark.*;
public class App
{
    private static HttpClient httpClient = new HttpClient();

    /**
     * Ejecuta la aplicación para poder utilizar los servicios
     * @param args Son los parametros al momento de ejecutar.
     */
    public static void main(String... args) {
        port(getPort());
        staticFiles.location("/public");
        get("hello", (req, res) -> "Hello Docker!");
        get("/mensajes", (req, res) -> {
            String response = httpClient.getMensajes("/mensajes");
            httpClient.roundRobin();
            return response;
        });
        post("/addMensaje", (req, res) -> {
            String response = httpClient.postMensajes(req.body(),"/addMensaje");
            httpClient.roundRobin();
            return response;
        });

    }

    /**
     * Funcion que retorna el número del puerto por el cual se correrá el servicio.
     * @return El número de puerto del servicio.
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}
