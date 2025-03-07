package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import modelos.Cultivo;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class CultivoDAO {
    private MongoCollection<Document> coleccion;

    public CultivoDAO(MongoCollection<Document> coleccion) {
        this.coleccion = coleccion;
    }

    // Insertar un cultivo
    public void insertarCultivo(Cultivo cultivo) {
        coleccion.insertOne(cultivo.toDocument());
    }

    // Obtener todos los cultivos
    public List<Cultivo> obtenerCultivos() {
        List<Cultivo> cultivos = new ArrayList<>();
        MongoCursor<Document> cursor = coleccion.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Cultivo cultivo = new Cultivo(
                        doc.getString("nombre"),
                        doc.getString("sector"),
                        doc.getDouble("area").floatValue(),
                        doc.getString("fechaSiembra"),
                        doc.getString("fechaCosecha"),
                        doc.getString("id")
                );
                cultivos.add(cultivo);
            }
        } finally {
            cursor.close();
        }

        return cultivos;
    }
}
