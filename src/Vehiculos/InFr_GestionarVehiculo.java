/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vehiculos;



import Clientes.InFr_GestionarCliente;
import ContratosAlquiler.InFr_Contrato;
import Utils.UtilDate;
import Utils.UtilGui;

import Menu.FrmMenu;
import Utils.UtilDate;
import Utils.UtilGui;
import Vehiculos.Estado;
import Vehiculos.InFr_BuscarVehiculo;
import Vehiculos.Tipo;
import Vehiculos.Vehiculo;
import java.awt.Container;



import java.time.LocalDate;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author UTN
 */
public class InFr_GestionarVehiculo extends javax.swing.JInternalFrame {
    HashMapVehiculo list;
    private Vehiculo vehiculo;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(InFr_GestionarVehiculo.class.getName());

    /**
     * Creates new form InFr_AgregarVehiculo
     */
    public InFr_GestionarVehiculo() {
        initComponents();
        list=new HashMapVehiculo();
        showTipo();
        showEstado();
        
    }
    
     private void showTipo(){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for(Tipo tipo:Tipo.values()){
            model.addElement(tipo);
            
        }
        jTxtTipo.setModel(model);
    }
     
      private void showEstado(){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for(Estado estado:Estado.values()){
            model.addElement(estado);
            
        }
        jTxtEstado.setModel(model);
    }
        private void clear(){
         jTxt_Placa.setText("");
            jText_Marca.setText("");
            jText_Modelo.setText("");
            jText_Año.setText("");
            jTxtTipo.setSelectedIndex(-1);
            jTxtEstado.setSelectedIndex(-1);
            //TxtBirthday.setText("");
            
    }
        
        private boolean validateRequiere(){
        return UtilGui.validateRequiere(jTxt_Placa,jText_Marca,jText_Modelo,
               jText_Año,jTxtTipo,jTxtEstado);
    }
        
         private void save(){
        if(!validateRequiere()){
           UtilGui.showErrorMessage( this, "faltan datos","error");
           return;  
        }
        String placa=jTxt_Placa.getText();
        String marca=jText_Marca.getText();
        String modelo=jText_Modelo.getText();
        int año = Integer.parseInt(jText_Año.getText().trim());
        Tipo tipovehiculo=(Tipo)jTxtTipo.getSelectedItem();
        String estado=jTxtEstado.getSelectedItem().toString();
        
        vehiculo= new Vehiculo(placa,marca,modelo,año,tipovehiculo);
        
        if (!list.Agregar(vehiculo)){
            JOptionPane.showMessageDialog(this,"no se agrego");
            return;
        }
        UtilGui.showMessage(this,"registro agregado "+vehiculo.getModelo(),"agregado");
        refrescarFormularioContratos();
        showTipo();
        showEstado();
    }
  private void refrescarFormularioContratos() {
    // Obtener el desktop pane del padre (FrmMenu)
    Container parent = this.getParent();
    while (parent != null && !(parent instanceof JDesktopPane)) {
        parent = parent.getParent();
    }
    
    if (parent instanceof JDesktopPane) {
        JDesktopPane desktopPane = (JDesktopPane) parent;
        
        // Buscar FrmContratos entre los internal frames abiertos
        for (JInternalFrame frame : desktopPane.getAllFrames()) {
            if (frame instanceof InFr_Contrato && frame.isVisible()) {
                InFr_Contrato frmContratos = (InFr_Contrato) frame;
                frmContratos.refrescarVehiculos();
                break;
            }
        }
    }
}
         private void Update(){
        if(!validateRequiere()){
           UtilGui.showErrorMessage( this, "faltan datos","error");
           return;  
        }
         String tipo=jTxtTipo.getSelectedItem().toString();
         String estado=jTxtTipo.getSelectedItem().toString();
         String modelo=jText_Modelo.getText();
      
         //vehiculo.setZone(Zone.valueOf(zone));
         vehiculo.setEstadovehiculo(Estado.valueOf(estado));
          vehiculo.setModelo(modelo);
           vehiculo.setTipovehiculo(Tipo.valueOf(estado));
    }
         
         private void delete(){
        if(vehiculo==null){
            UtilGui.showErrorMessage(this,"no hay animal", "error");
            return;
        }
        if(!list.Eliminar(vehiculo)){
             JOptionPane.showMessageDialog(this,"no se elimino");
             return;
        }
        clear();
        }
         
         private void showData(){
        jTxt_Placa.setText(vehiculo.getPlaca());
        jText_Marca.setText(vehiculo.getMarca());
        //TxtBirthday.setText(UtilDate.toString(animal.getBirthDate()));
        jTxtTipo.setSelectedItem(vehiculo.getTipovehiculo());
        jTxtEstado.setSelectedItem(vehiculo.getEstado());
    }
         

         
private void search() {
  InFr_BuscarVehiculo buscarFrame = new InFr_BuscarVehiculo();
        buscarFrame.setList(list);
        
        // Obtener el escritorio del menú principal
        javax.swing.JDesktopPane desktop = (javax.swing.JDesktopPane) this.getParent();
        desktop.add(buscarFrame);
        buscarFrame.setVisible(true);

        // Centrar el internal frame
        buscarFrame.setLocation((desktop.getWidth() - buscarFrame.getWidth()) / 2,
                               (desktop.getHeight() - buscarFrame.getHeight()) / 2);

        vehiculo = buscarFrame.getVehiculo();
        if (vehiculo != null) {
            showData();
        }
}
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLB_Placa = new javax.swing.JLabel();
        jTxt_Placa = new javax.swing.JFormattedTextField();
        jLB_Placa1 = new javax.swing.JLabel();
        jText_Marca = new javax.swing.JTextField();
        jLB_Placa2 = new javax.swing.JLabel();
        jText_Modelo = new javax.swing.JTextField();
        jText_Año = new javax.swing.JTextField();
        jLB_Placa4 = new javax.swing.JLabel();
        jTxtTipo = new javax.swing.JComboBox<>();
        jLB_Placa3 = new javax.swing.JLabel();
        jLB_Placa5 = new javax.swing.JLabel();
        jTxtEstado = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jBt_Agregar = new javax.swing.JButton();
        jBt_Elliminar = new javax.swing.JButton();
        jBt_Buscar = new javax.swing.JButton();
        jBt_Actualizar = new javax.swing.JButton();
        jBt_Limpiar = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 102, 102));
        setClosable(true);
        setForeground(new java.awt.Color(0, 102, 102));
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GESTIONAR VEHICULO");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Sitka Text", 0, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("FullDrive");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLB_Placa.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLB_Placa.setText("Placa");
        jLB_Placa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTxt_Placa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTxt_Placa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxt_PlacaActionPerformed(evt);
            }
        });

        jLB_Placa1.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLB_Placa1.setText("Marca");
        jLB_Placa1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jText_Marca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLB_Placa2.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLB_Placa2.setText("Modelo");
        jLB_Placa2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jText_Modelo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jText_Año.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jText_Año.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_AñoActionPerformed(evt);
            }
        });

        jLB_Placa4.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLB_Placa4.setText("Tipo\n");
        jLB_Placa4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTxtTipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTxtTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtTipoActionPerformed(evt);
            }
        });

        jLB_Placa3.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLB_Placa3.setText("Año");
        jLB_Placa3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLB_Placa5.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jLB_Placa5.setText("Estado\n");
        jLB_Placa5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTxtEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jTxtEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jTxtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLB_Placa4, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLB_Placa5, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 132, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTxt_Placa, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(jLB_Placa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jText_Marca, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jText_Modelo))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLB_Placa1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLB_Placa2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLB_Placa3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jText_Año, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLB_Placa)
                    .addComponent(jLB_Placa1)
                    .addComponent(jLB_Placa2)
                    .addComponent(jLB_Placa3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTxt_Placa, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jText_Marca, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jText_Modelo)
                    .addComponent(jText_Año))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLB_Placa4)
                    .addComponent(jLB_Placa5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTxtTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jTxtEstado))
                .addGap(29, 29, 29))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jBt_Agregar.setText("Agregar");
        jBt_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBt_AgregarActionPerformed(evt);
            }
        });

        jBt_Elliminar.setText("Eliminar");
        jBt_Elliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBt_ElliminarActionPerformed(evt);
            }
        });

        jBt_Buscar.setText("Buscar");
        jBt_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBt_BuscarActionPerformed(evt);
            }
        });

        jBt_Actualizar.setText("Actualizar");

        jBt_Limpiar.setText("Limpiar");
        jBt_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBt_LimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jBt_Agregar)
                .addGap(18, 18, 18)
                .addComponent(jBt_Elliminar)
                .addGap(18, 18, 18)
                .addComponent(jBt_Buscar)
                .addGap(18, 18, 18)
                .addComponent(jBt_Actualizar)
                .addGap(18, 18, 18)
                .addComponent(jBt_Limpiar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBt_Agregar)
                    .addComponent(jBt_Elliminar)
                    .addComponent(jBt_Buscar)
                    .addComponent(jBt_Actualizar)
                    .addComponent(jBt_Limpiar))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTxt_PlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxt_PlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxt_PlacaActionPerformed

    private void jBt_ElliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBt_ElliminarActionPerformed
     delete();   // TODO add your handling code here:
    }//GEN-LAST:event_jBt_ElliminarActionPerformed

    private void jTxtTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtTipoActionPerformed
      showTipo();
    }//GEN-LAST:event_jTxtTipoActionPerformed

    private void jBt_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBt_LimpiarActionPerformed
        clear();
    }//GEN-LAST:event_jBt_LimpiarActionPerformed

    private void jText_AñoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_AñoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_AñoActionPerformed

    private void jBt_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBt_AgregarActionPerformed
      save();  // TODO add your handling code here:
    }//GEN-LAST:event_jBt_AgregarActionPerformed

    private void jBt_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBt_BuscarActionPerformed
   search();     // TODO add your handling code here:
    }//GEN-LAST:event_jBt_BuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBt_Actualizar;
    private javax.swing.JButton jBt_Agregar;
    private javax.swing.JButton jBt_Buscar;
    private javax.swing.JButton jBt_Elliminar;
    private javax.swing.JButton jBt_Limpiar;
    private javax.swing.JLabel jLB_Placa;
    private javax.swing.JLabel jLB_Placa1;
    private javax.swing.JLabel jLB_Placa2;
    private javax.swing.JLabel jLB_Placa3;
    private javax.swing.JLabel jLB_Placa4;
    private javax.swing.JLabel jLB_Placa5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jText_Año;
    private javax.swing.JTextField jText_Marca;
    private javax.swing.JTextField jText_Modelo;
    private javax.swing.JComboBox<String> jTxtEstado;
    private javax.swing.JComboBox<String> jTxtTipo;
    private javax.swing.JFormattedTextField jTxt_Placa;
    // End of variables declaration//GEN-END:variables
}
