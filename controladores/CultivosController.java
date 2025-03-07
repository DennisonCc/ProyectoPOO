package controladores;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import modelos.Cultivo;
import modelos.Usuario;
import org.bson.Document;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CultivosController {
    private MongoCollection<Document> coleccion;
    private MongoCollection<Document> coleccionCultivos;

    public CultivosController() {
        // Conexión a MongoDB
        try {
            MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017"); // Cambia la URI si es necesario
            MongoDatabase database = mongoClient.getDatabase("gestion_agricola"); // Cambia el nombre de tu base de datos
            coleccionCultivos = database.getCollection("cultivos"); // Cambia el nombre de tu colección
        } catch (Exception e) {
            System.out.println("Error al conectar con MongoDB: " + e.getMessage());
        }
    }
    
    public void editarCultivo(Cultivo cultivo, String nombreOriginal) throws Exception {
        if (cultivo == null) {
            throw new Exception("El cultivo no tiene todos los datos requeridos.");
        }

        // Crear un filtro para buscar el cultivo original
        Document filtro = new Document("nombre", nombreOriginal);

        // Crear el documento con los nuevos datos
        Document nuevosDatos = new Document("$set", new Document()
            .append("nombre", cultivo.getNombre())
            .append("sector", cultivo.getSector())
            .append("area", cultivo.getArea())
            .append("fechaSiembra", cultivo.getFechaSiembra())
            .append("fechaCosecha", cultivo.getFechaCosecha()));

        // Actualizar el documento en la colección
        UpdateResult resultado = coleccionCultivos.updateOne(filtro, nuevosDatos);

        if (resultado.getMatchedCount() > 0) {
            System.out.println("Cultivo actualizado correctamente.");
        } else {
            throw new Exception("No se encontró el cultivo en la base de datos.");
        }
    }

    public void agregarCultivo(Cultivo cultivo) throws Exception {
        if (cultivo == null ) {
            throw new Exception("El usuario no tiene todos los datos requeridos.");
        }
        
        Document filtro = new Document("nombre", cultivo.getNombre())
                             .append("sector", cultivo.getSector())
                             .append("fechaSiembra", cultivo.getFechaSiembra())
                             .append("fechaCosecha", cultivo.getFechaCosecha())
                             .append("id", cultivo.getId());

        long count = coleccionCultivos.countDocuments(filtro);
        if (count > 0) {
            throw new Exception("Ya existe un cultivo con el mismo nombre, sector y fechas.");
        }

        // Crear el documento para MongoDB
        Document documentoUsuario = new Document()
                .append("nombre", cultivo.getNombre())
                .append("sector", cultivo.getSector())
                .append("area", cultivo.getArea())
                .append("fechaSiembra", cultivo.getFechaSiembra())
                .append("fechaCosecha", cultivo.getFechaCosecha())
                .append("id", cultivo.getId());

        // Insertar el documento en la colección
        coleccionCultivos.insertOne(documentoUsuario);
        System.out.println("Cultivo registrado con éxito en MongoDB.");
    }
    public List<Cultivo> obtenerCultivos() {
        List<Cultivo> listaCultivos = new ArrayList<>();
        
        for (Document doc : coleccionCultivos.find()) {
            Cultivo cultivo = new Cultivo(
                doc.getString("nombre"),
                doc.getString("sector"),
                doc.getDouble("area").intValue(),
                doc.getString("fechaSiembra"),
                doc.getString("fechaCosecha"),
                doc.getString("id")
            );
            listaCultivos.add(cultivo);
        }
        return listaCultivos;
    }
    
    public void eliminarCultivo(JTable tabla) throws Exception {
    int filaSeleccionada = tabla.getSelectedRow();

    if (filaSeleccionada == -1) {
        throw new Exception("Debe seleccionar un cultivo para eliminar.");
    }

    // Obtener los valores de la fila seleccionada
    String nombre = tabla.getValueAt(filaSeleccionada, 0).toString();
    String sector = tabla.getValueAt(filaSeleccionada, 1).toString();
    String fechaSiembra = tabla.getValueAt(filaSeleccionada, 3).toString();
    String fechaCosecha = tabla.getValueAt(filaSeleccionada, 4).toString();

    // Crear el filtro para eliminar en MongoDB
    Document filtro = new Document("nombre", nombre)
                            .append("sector", sector);

    // Intentar eliminar el documento
    DeleteResult resultado = coleccionCultivos.deleteOne(filtro);

    if (resultado.getDeletedCount() > 0) {
        System.out.println("Cultivo eliminado correctamente.");
        // Opcional: actualizar la tabla
        ((DefaultTableModel) tabla.getModel()).removeRow(filaSeleccionada);
    } else {
        throw new Exception("No se encontró el cultivo en la base de datos.");
    }
}

    
    

    
   
}
