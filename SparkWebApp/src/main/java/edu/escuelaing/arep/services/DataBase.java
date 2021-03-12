package edu.escuelaing.arep.services;
import java.util.ArrayList;
import java.util.HashMap;

import org.bson.Document;

import edu.escuelaing.arep.model.Mensaje;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DataBase {

    private MongoCollection<org.bson.Document> columnas;

    /**
     * Realiza la conexion con la base de datos
     */
    public DataBase() {
        // arep-mongo-db1 localhost

        MongoClientURI uri = new MongoClientURI(
                "mongodb://admin:1234@clusterareplab3-shard-00-00.dcoua.mongodb.net:27017,clusterareplab3-shard-00-01.dcoua.mongodb.net:27017,clusterareplab3-shard-00-02.dcoua.mongodb.net:27017/animals?ssl=true&replicaSet=atlas-5kfmsp-shard-0&authSource=admin&retryWrites=true&w=majority");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("animals");
        columnas = database.getCollection("pets");
    }

    /**
     * Añade un nuevo mensaje a la base de datos
     *
     * @param m Es el mensaje a añadir
     */
    public void addMensaje(Mensaje m) {
        HashMap<String, Object> map = new HashMap<>();
        String mensaje = m.getMensaje();
        String fecha = m.getFecha();
        map.put("Mensaje", mensaje);
        map.put("Fecha", fecha);
        Document registro = new Document(map);
        columnas.insertOne(registro);
    }

    /**
     * Consulta todos los mensaje de la base de datos
     * @return
     */
    public String getMensajes() {

        Mensaje mensaje;

        int cont = 0;

        ArrayList<Mensaje> mensajes = new ArrayList<>();

        for (Document d : columnas.find()) {

            cont++;
            if (columnas.countDocuments() - cont < 10) {
                mensaje = new Mensaje(d.get("Mensaje").toString(), d.get("Fecha").toString());
                mensajes.add(mensaje);
            }
        }

        ArrayList<Mensaje> invertido = new ArrayList<>();
        for (int i = mensajes.size() - 1; i >= 0; i--) {

            invertido.add(mensajes.get(i));
        }

        Gson gson = new Gson();

        String json = gson.toJson(invertido);
        return json;
    }

}