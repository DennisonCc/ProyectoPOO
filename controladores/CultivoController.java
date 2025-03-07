package controladores;

import dao.CultivoDAO;
import modelos.Cultivo;

import java.util.List;

public class CultivoController {
    private CultivoDAO cultivoDAO;

    public CultivoController(CultivoDAO cultivoDAO) {
        this.cultivoDAO = cultivoDAO;
    }

    public void agregarCultivo(Cultivo cultivo) {
        cultivoDAO.insertarCultivo(cultivo);
    }

    public List<Cultivo> obtenerCultivos() {
        return cultivoDAO.obtenerCultivos();
    }
}
