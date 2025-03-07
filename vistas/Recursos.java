/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.Cultivo;
import modelos.MongoDBConexion;
import org.bson.Document;

/**
 *
 * @author HP-102102
 */
public class Recursos extends javax.swing.JFrame {
    DefaultTableModel mt = new DefaultTableModel();
    /**
     * Creates new form Recursos
     */
    public Recursos() {
        initComponents();
        String[] columnas = {"Cantidad", "Sector","fecha"};
        DefaultTableModel mt = new DefaultTableModel();
        mt.setColumnIdentifiers(columnas);
        aguaTabla.setModel(mt);

        mt.setRowCount(0); // Limpiar tabla antes de agregar datos

        
        MongoCollection<Document> usuarioCollection = MongoDBConexion.getCollection("usuarioLogeado");
        Document usuarioLogeado = usuarioCollection.find().first();
        String correo = usuarioLogeado.getString("correo");
        
        MongoCollection<Document> recursosAgua = MongoDBConexion.getCollection("consumoAgua");
        FindIterable<Document> recursos = recursosAgua.find();
        
        
        int consumoNumerico = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        for (Document recurso : recursos) {
            String idUsuario = recurso.getString("id"); // Asegúrate de que este campo existe en MongoDB

            if (idUsuario != null && idUsuario.equals(correo)) {
                    Date fechaBD = recurso.getDate("fecha");
                    String fechaBDStr = sdf.format(fechaBD); 
                
                    consumoNumerico += Integer.parseInt(recurso.getString("consumo"));
                        mt.addRow(new Object[]{
                                recurso.getString("consumo"), // Cantidad de agua consumida
                                recurso.getString("sector"),
                                fechaBDStr
                                //recurso.getString("fecha")// Sector donde se registró el consumo
                        });
                    
            }
        }
        consumoTotalAgua.setText(String.valueOf(consumoNumerico));
        
        
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        aguaTabla = new javax.swing.JTable();
        añadirAguaButton = new javax.swing.JButton();
        eliminarAguaButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        consumoTotalAgua = new javax.swing.JLabel();
        fechaConsumo = new com.toedter.calendar.JDateChooser();
        returnButton = new javax.swing.JButton();
        Total = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Riego");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Consumo de agua:");

        aguaTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Cantidad", "Sector"
            }
        ));
        jScrollPane1.setViewportView(aguaTabla);

        añadirAguaButton.setText("Añadir");
        añadirAguaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                añadirAguaButtonActionPerformed(evt);
            }
        });

        eliminarAguaButton.setText("Eliminar");
        eliminarAguaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarAguaButtonActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Consumo total de agua:");

        consumoTotalAgua.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        consumoTotalAgua.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        returnButton.setText("Regresar");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        Total.setText("Actualizar");
        Total.setToolTipText("");
        Total.setActionCommand("Total");
        Total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(returnButton)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(añadirAguaButton)
                                            .addGap(26, 26, 26)
                                            .addComponent(eliminarAguaButton))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(24, 24, 24))))
                                .addGap(17, 17, 17))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Total)
                                    .addComponent(consumoTotalAgua, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(fechaConsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(returnButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fechaConsumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(añadirAguaButton)
                    .addComponent(eliminarAguaButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(consumoTotalAgua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Total))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void eliminarAguaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarAguaButtonActionPerformed
        try {
            // TODO add your handling code here:

            eliminarElemento(aguaTabla);
        } catch (Exception ex) {
            Logger.getLogger(Recursos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_eliminarAguaButtonActionPerformed

    private void añadirAguaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añadirAguaButtonActionPerformed
        // TODO add your handling code here:
        Date fechaSeleccionada = fechaConsumo.getDate();
        if(fechaSeleccionada != null){
        AñadirConsumoAgua ventanaConsumo = new AñadirConsumoAgua(fechaSeleccionada);
        ventanaConsumo.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una fecha antes de continuar.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_añadirAguaButtonActionPerformed

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        MenuPrincipal ventanaPrincipal = new MenuPrincipal();
        ventanaPrincipal.setVisible(true);
        dispose(); // Cerrar la ventana de login
    }//GEN-LAST:event_returnButtonActionPerformed

    private void TotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalActionPerformed
        // TODO add your handling code here:
        String[] columnas = {"Cantidad", "Sector","fecha"};
        DefaultTableModel mt = new DefaultTableModel();
        mt.setColumnIdentifiers(columnas);
        aguaTabla.setModel(mt);

        mt.setRowCount(0); // Limpiar tabla antes de agregar datos

        
        MongoCollection<Document> usuarioCollection = MongoDBConexion.getCollection("usuarioLogeado");
        Document usuarioLogeado = usuarioCollection.find().first();
        String correo = usuarioLogeado.getString("correo");
        
        MongoCollection<Document> recursosAgua = MongoDBConexion.getCollection("consumoAgua");
        FindIterable<Document> recursos = recursosAgua.find();
        
        int consumoNumerico = 0;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                
                
        for (Document recurso : recursos) {
            String idUsuario = recurso.getString("id"); // Asegúrate de que este campo existe en MongoDB

            if (idUsuario != null && idUsuario.equals(correo)) {
                    Date fechaBD = recurso.getDate("fecha"); // Obtener fecha de MongoDB
                    String fechaBDStr = sdf.format(fechaBD); 
                    
                        consumoNumerico += Integer.parseInt(recurso.getString("consumo"));
                        mt.addRow(new Object[]{
                                recurso.getString("consumo"),
                                recurso.getString("sector"),
                                fechaBDStr
                                //recurso.getString("fecha")// Sector donde se registró el consumo
                        });
                    
                }
        }
        
        
        
        consumoTotalAgua.setText(String.valueOf(consumoNumerico));
    }//GEN-LAST:event_TotalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Recursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Recursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Recursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Recursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Recursos().setVisible(true);
            }
        });
    }
    
    public void eliminarElemento(JTable tabla) throws Exception {
        
        MongoCollection<Document> usuarioCollection = MongoDBConexion.getCollection("usuarioLogeado");
        Document usuarioLogeado = usuarioCollection.find().first();
        String correo = usuarioLogeado.getString("correo");

        MongoCollection<Document> recursosAgua = MongoDBConexion.getCollection("consumoAgua");

        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada == -1) {
            throw new Exception("Debe seleccionar un consumo de agua para eliminar.");
        }

        // Obtener los valores de la fila seleccionada
        String consumo = tabla.getValueAt(filaSeleccionada, 0).toString(); // Cantidad de agua
        String sector = tabla.getValueAt(filaSeleccionada, 1).toString();  // Sector
        String fecha = tabla.getValueAt(filaSeleccionada, 2).toString();   // Fecha

        // Crear el filtro para eliminar en MongoDB
        Document filtro = new Document("id", correo) // Asegurar que pertenece al usuario
                                 .append("consumo", consumo)
                                 .append("sector", sector);

        // Intentar eliminar el documento
        DeleteResult resultado = recursosAgua.deleteOne(filtro);

        if (resultado.getDeletedCount() > 0) {
            System.out.println("Registro de consumo eliminado correctamente.");
            // Eliminar la fila de la tabla
            ((DefaultTableModel) tabla.getModel()).removeRow(filaSeleccionada);
        } else {
            throw new Exception("No se encontró el registro en la base de datos.");
        }
        
        
        
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Total;
    private javax.swing.JTable aguaTabla;
    private javax.swing.JButton añadirAguaButton;
    private javax.swing.JLabel consumoTotalAgua;
    private javax.swing.JButton eliminarAguaButton;
    private com.toedter.calendar.JDateChooser fechaConsumo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton returnButton;
    // End of variables declaration//GEN-END:variables
}
