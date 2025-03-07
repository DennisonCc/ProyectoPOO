package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import modelos.Usuario;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {
    //primero creo  una coleccion tipo document de mongo db llamada coleccion
    private MongoCollection<Document> coleccion;

    // Constructor para inicializar la colección
    public UsuariosDAO(MongoCollection<Document> coleccion) {
        if (coleccion == null) {
            throw new IllegalArgumentException("La colección no puede ser nula.");
        }
        this.coleccion = coleccion;
    }

    /**
     * Inserta un usuario en la colección.
     *
     * @param usuario El usuario a insertar.
     */
    public void insertarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        }
        coleccion.insertOne(usuario.toDocument());
    }

    /**
     * Obtiene todos los usuarios almacenados en la colección.
     *
     * @return Una lista de objetos de tipo Usuario.
     */
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (MongoCursor<Document> cursor = coleccion.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Usuario usuario = new Usuario(
                        doc.getString("id"),
                        doc.getString("nombre"),
                        doc.getString("correo"),
                        doc.getString("contraseña"),
                        doc.getString("rol")
                );
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
}
