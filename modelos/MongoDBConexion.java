package modelos;

import vistas.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBConexion {
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    // Inicializa la conexión con MongoDB
    public static void conectar() {
        if (mongoClient == null) {
            mongoClient = new MongoClient("localhost",27017); // Cambia el puerto si es necesario
            database = mongoClient.getDatabase("gestion_agricola");
            System.out.println("Conexión a MongoDB establecida.");

            // Asegúrate de que la base y una colección inicial existan
            verificarOCrearColeccion("coleccion_inicial");
        }
    }
    

    // Verifica si la colección existe, si no, la crea
    private static void verificarOCrearColeccion(String nombreColeccion) {
        if (database.getCollection(nombreColeccion) == null) {
            System.out.println("La colección '" + nombreColeccion + "' no existe. Creándola...");
            MongoCollection<Document> coleccion = database.getCollection(nombreColeccion);
            coleccion.insertOne(new Document("mensaje", "Colección creada automáticamente"));
            System.out.println("Colección '" + nombreColeccion + "' creada.");
        }
        else{
            System.out.println("Ingreso a Colección '" + nombreColeccion + "' exitoso");
        }
    }

    // Obtiene una colección específica
    public static MongoCollection<Document> getCollection(String nombreColeccion) {
        if (database == null) {
            conectar();
        }
        return database.getCollection(nombreColeccion);
    }

    // Cierra la conexión con MongoDB
    public static void cerrar() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexión a MongoDB cerrada.");
        }
    }
    
    public static void insertarUsuarioPredeterminado() {
        conectar(); // Asegura que la conexión está establecida
        MongoCollection<Document> usuarios = database.getCollection("usuarios");
        // Verifica si el usuario ya existe
        Document usuarioExistente = usuarios.find(new Document("usuario", "admin")).first();
        if (usuarioExistente == null) {
            Document usuario = new Document("nombre", "admin")
                    .append("correo", "admin@es.com")
                    .append("contraseña", "1234") // Cambia la contraseña si es necesario
                    .append("rol", "administrador");
            usuarios.insertOne(usuario);
            System.out.println("Usuario predeterminado insertado.");
        } else {
            System.out.println("El usuario predeterminado ya existe.");
        }
    }
}
