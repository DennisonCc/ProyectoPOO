package controladores;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import modelos.Usuario;
import org.bson.Document;

public class UsuariosController {
    
    private MongoCollection<Document> coleccionUsuarios;

    public UsuariosController() {
        // Conexión a MongoDB
        try {
            MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017"); // Cambia la URI si es necesario
            MongoDatabase database = mongoClient.getDatabase("gestion_agricola"); // Cambia el nombre de tu base de datos
            coleccionUsuarios = database.getCollection("usuarios"); // Cambia el nombre de tu colección
        } catch (Exception e) {
            System.out.println("Error al conectar con MongoDB: " + e.getMessage());
        }
    }

    public void agregarUsuario(Usuario usuario) throws Exception {
        if (usuario == null || usuario.getNombre().isEmpty() || usuario.getCorreo().isEmpty()) {
            throw new Exception("El usuario no tiene todos los datos requeridos.");
        }

        // Crear el documento para MongoDB
        Document documentoUsuario = new Document()
                .append("nombre", usuario.getNombre())
                .append("correo", usuario.getCorreo())
                .append("contraseña", usuario.getContraseña())
                .append("rol", usuario.getRol());

        // Insertar el documento en la colección
        coleccionUsuarios.insertOne(documentoUsuario);
        System.out.println("Usuario registrado con éxito en MongoDB.");
    }
}
