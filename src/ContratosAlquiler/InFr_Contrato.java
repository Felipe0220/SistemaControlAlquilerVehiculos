/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package ContratosAlquiler;

import Clientes.Cliente;
import Clientes.ListaCliente;
import Vehiculos.Estado;
import Vehiculos.HashMapVehiculo;
import Vehiculos.Vehiculo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

/**
 *
 * @author kevin
 */
public class InFr_Contrato extends javax.swing.JInternalFrame {

    /**
     * Creates new form InFr_Contrato
     */
    private ListaCliente listaClientes;
    private ListaContratos listaContratos = new ListaContratos();
     private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
private HashMapVehiculo mapaVehiculos;
   private Cliente cliente;
    public InFr_Contrato(ListaCliente listaClientes, HashMapVehiculo mapaVehiculos) {
    this.listaClientes = listaClientes;
    this.mapaVehiculos = mapaVehiculos;
    this.cliente=cliente;
    
        initComponents();
        configurarValidaciones();
        configurarEventos();
        cargarDatosIniciales();
        actualizarTablaContratos();
          this.addInternalFrameListener(new InternalFrameAdapter() {
        @Override
        public void internalFrameActivated(InternalFrameEvent e) {
            refrescarVehiculos(); // Refrescar cada vez que se activa
        }
    });
    }
       @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            refrescarVehiculos(); // Refrescar cuando se hace visible
        }
    }   
        private void configurarValidaciones() {
        // Validación para cédula (solo números)
        if (txtCedula != null) {
            txtCedula.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!Character.isDigit(c) || txtCedula.getText().length() >= 8) {
                        e.consume();
                    }
                }
            });
        }
    }
        private void configurarEventos() {
         // Evento para calcular monto automáticamente
        if (txtTarifaDiaria != null && txtFechaInicio != null && txtFechaFin != null) {
            ActionListener calcularMontoListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calcularMonto();
                }
            };
            
            txtTarifaDiaria.addActionListener(calcularMontoListener);
            txtFechaInicio.addActionListener(calcularMontoListener);
            txtFechaFin.addActionListener(calcularMontoListener);
        }
        
        // Evento para el botón crear contrato
        if (btnCrearContrato != null) {
            btnCrearContrato.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    crearContrato();
                }
            });
        }
        
        // Evento para el botón finalizar contrato
        if (btnFinalizar != null) {
            btnFinalizar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    finalizarContrato();
                }
            });
        }
        
        // Evento para el botón cancelar contrato
        if (btnCancelar != null) {
            btnCancelar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cancelarContrato();
                }
            });
        }
        
        // Evento para el botón buscar contrato
        if (btnBuscarContrato != null) {
            btnBuscarContrato.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buscarContrato();
                }
            });
        }
        
        
        
    }
private void cargarDatosIniciales() {
    // Cargar estados en el combobox de estado del contrato
    txtEstado2.removeAllItems();
    for (Estado estado : Estado.values()) {
        txtEstado2.addItem(estado.toString());
    }
    
    // Cargar vehículos DISPONIBLES en el combobox de vehículos
    jComboBox1.removeAllItems(); // Asumiendo que se llama cmbVehiculos
    for (Vehiculo vehiculo : mapaVehiculos.getMap().values()) {
        if (vehiculo.getEstado()== Estado.Disponible) {
             jComboBox1.addItem(vehiculo.getPlaca() + " - " + vehiculo.getModelo());
        }
    }
    
}
private void calcularMonto() {
        try {
            if (txtFechaInicio.getText().isEmpty() || txtFechaFin.getText().isEmpty() || 
                txtTarifaDiaria.getText().isEmpty()) {
                return;
            }
            
            LocalDate fechaInicio = LocalDate.parse(txtFechaInicio.getText(), dateFormatter);
            LocalDate fechaFin = LocalDate.parse(txtFechaFin.getText(), dateFormatter);
            double tarifa = Double.parseDouble(txtTarifaDiaria.getText());
            
            long dias = java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, fechaFin);
            if (dias < 0) {
                JOptionPane.showMessageDialog(this, "La fecha fin debe ser posterior a la fecha inicio", 
                                             "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double monto = dias * tarifa;
            txtMontoTotal.setText(String.format("%.2f", monto));
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Use dd/MM/yyyy", 
                                         "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Tarifa diaria debe ser un número válido", 
                                         "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
private boolean validarDatos() {
        // Validar cédula
        if (txtCedula.getText().isEmpty() || txtCedula.getText().length() != 8) {
            JOptionPane.showMessageDialog(this, "La cédula debe tener 8 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validar correo electrónico
        String email = txtCorreo.getText();
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        if (!Pattern.matches(emailRegex, email)) {
            JOptionPane.showMessageDialog(this, "El correo electrónico no es válido", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validar licencia no vacía
        if (txtLicencia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El número de licencia no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validar fechas
        try {
            LocalDate fechaInicio = LocalDate.parse(txtFechaInicio.getText(), dateFormatter);
            LocalDate fechaFin = LocalDate.parse(txtFechaFin.getText(), dateFormatter);
            
            if (fechaFin.isBefore(fechaInicio)) {
                JOptionPane.showMessageDialog(this, "La fecha fin debe ser posterior a la fecha inicio", 
                                             "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Use dd/MM/yyyy", 
                                         "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
private void crearContrato() {
        if (!validarDatos()) {
            return;
        }
        
        try {
            // Obtener los datos del formulario
            int idContrato = generarNuevoId();
            
            // Aquí debes obtener el cliente y vehículo seleccionado de tus comboboxes
            // Esto es un ejemplo - debes adaptarlo a tu implementación real
            Cliente cliente = obtenerClienteSeleccionado();
            Vehiculo vehiculo = obtenerVehiculoSeleccionado();
            
            LocalDate fechaInicio = LocalDate.parse(txtFechaInicio.getText(), dateFormatter);
            LocalDate fechaFin = LocalDate.parse(txtFechaFin.getText(), dateFormatter);
            double tarifaDiaria = Double.parseDouble(txtTarifaDiaria.getText());
            
            // Crear el contrato
            Contrato nuevoContrato = new Contrato(
                idContrato, cliente, vehiculo, fechaInicio, fechaFin, tarifaDiaria, Estado.En_Alquiler
            );
            
            // Agregar a la lista
            if (listaContratos.Agregar(nuevoContrato)) {
                JOptionPane.showMessageDialog(this, "Contrato creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();
                actualizarTablaContratos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear el contrato", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
   private int generarNuevoId() {
        // Generar un ID único para el contrato
        int maxId = 0;
        for (Contrato contrato : listaContratos.getContratos()) {
            if (contrato.getIdContrato() > maxId) {
                maxId = contrato.getIdContrato();
            }
        }
        return maxId + 1;
    }
     private Cliente obtenerClienteSeleccionado() {
    try {
        // Convertir fecha de nacimiento (de String a LocalDate)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNacimiento = LocalDate.parse(txtFechaNacimiento.getText(), formatter);
        
        // Usar el constructor CORRECTO de tu clase Cliente
        Cliente cliente = new Cliente(
            txtLicencia.getText(),    // license (primer parámetro)
            txtCedula.getText(),      // id (segundo parámetro)  
            txtNombre.getText(),      // name (tercer parámetro)
            fechaNacimiento,          // birthDate (cuarto parámetro)
            txtTelefono.getText()     // phone (quinto parámetro) - ASUMIENDO que tienes txtTelefono
        );
        
        return cliente;
        
    } catch (DateTimeParseException e) {
        JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Use dd/MM/yyyy", 
                                    "Error", JOptionPane.ERROR_MESSAGE);
        return null;
    }


    }
      private Vehiculo obtenerVehiculoSeleccionado() {
       if (jComboBox1.getSelectedIndex() == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione un vehículo", "Error", JOptionPane.ERROR_MESSAGE);
        return null;
    }
    
    String itemSeleccionado = jComboBox1.getSelectedItem().toString();
    // Extraer la placa del vehículo (asumiendo formato "Placa - Modelo")
    String placa = itemSeleccionado.split(" - ")[0];
    
    return mapaVehiculos.buscarPorPlaca(placa);
}
  private void finalizarContrato() {
        int selectedRow = tblContratos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un contrato de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int idContrato = (Integer) tblContratos.getValueAt(selectedRow, 0);
        Contrato contrato = listaContratos.Buscar(idContrato);
        
        if (contrato != null) {
            contrato.finalizarContrato();
            listaContratos.Actualizar(contrato);
            JOptionPane.showMessageDialog(this, "Contrato finalizado exitosamente");
            actualizarTablaContratos();
        }
    }
  private void cancelarContrato() {
        int selectedRow = tblContratos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un contrato de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int idContrato = (Integer) tblContratos.getValueAt(selectedRow, 0);
        Contrato contrato = listaContratos.Buscar(idContrato);
        
        if (contrato != null) {
            if (listaContratos.Eliminar(contrato)) {
                JOptionPane.showMessageDialog(this, "Contrato cancelado exitosamente");
                actualizarTablaContratos();
            }
        }
    }
  private void buscarContrato() {
        String criterio = jTextField2.getText();
        if (criterio.isEmpty()) {
            actualizarTablaContratos(); // Mostrar todos si no hay criterio
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) tblContratos.getModel();
        model.setRowCount(0); // Limpiar tabla
        
        // Buscar por ID de contrato
        try {
            int id = Integer.parseInt(criterio);
            Contrato contrato = listaContratos.Buscar(id);
            if (contrato != null) {
                agregarFilaTabla(contrato, model);
            }
        } catch (NumberFormatException e) {
            // Buscar por otros criterios (placa, nombre cliente, etc.)
            for (Contrato contrato : listaContratos.getContratos()) {
                if (contrato.getVehiculo().getPlaca().contains(criterio) ||
                    contrato.getCliente().getName().contains(criterio)) {
                    agregarFilaTabla(contrato, model);
                }
            }
        }
    }
       private void actualizarTablaContratos() {
        DefaultTableModel model = (DefaultTableModel) tblContratos.getModel();
        model.setRowCount(0); // Limpiar tabla
        
        for (Contrato contrato : listaContratos.getContratos()) {
            agregarFilaTabla(contrato, model);
        }
    }
         private void agregarFilaTabla(Contrato contrato, DefaultTableModel model) {
        model.addRow(new Object[]{
            contrato.getIdContrato(),
            contrato.getCliente().getName(),
            contrato.getVehiculo().getPlaca(),
            contrato.getFechaInicio().format(dateFormatter),
            contrato.getFechaFin().format(dateFormatter),
            contrato.getMonto(),
            contrato.getEstadoContrato().toString()
        });
    }
    private void limpiarFormulario() {
        txtNumeroContrato.setText("");
        txtCedula.setText("");
        txtNombre.setText("");
        txtCorreo.setText("");
        txtLicencia.setText("");
        txtFechaInicio.setText("");
        txtFechaFin.setText("");
        txtTarifaDiaria.setText("");
        txtMontoTotal.setText("");
        txtEstado2.setSelectedIndex(0);
    }
    public void refrescarVehiculos() {
    jComboBox1.removeAllItems();
    
    // Cargar vehículos DISPONIBLES
    for (Vehiculo vehiculo : mapaVehiculos.getMap().values()) {
        if (vehiculo.getEstado() == Estado.Disponible) {
            jComboBox1.addItem(vehiculo.getPlaca() + " - " + vehiculo.getModelo());
        }
    }
    
    // Actualizar la tarifa si hay vehículos
    if (jComboBox1.getItemCount() > 0) {
        jComboBox1.setSelectedIndex(0);
    } else {
        txtTarifaDiaria.setText("");
        txtEstado1.setText("No hay vehículos disponibles");
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtFechaNacimiento = new javax.swing.JFormattedTextField();
        txtLicencia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtFechaInicio = new javax.swing.JFormattedTextField();
        txtFechaFin = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        btnCrearContrato = new javax.swing.JButton();
        btnLimpiarContrato = new javax.swing.JButton();
        txtTarifaDiaria = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtMontoTotal = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtEstado2 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        txtNumeroContrato = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtEstado1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblContratos = new javax.swing.JTable();
        btnRefrescar = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        btnBuscarContrato = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jLabel2.setText("Cliente");

        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });

        jLabel3.setText("Cedula");

        jLabel4.setText("Nombre completo");

        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });

        jLabel7.setText("Correo electronico");

        jLabel5.setText("Fecha de nacimiento");

        try {
            txtFechaNacimiento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel6.setText("Licencia");

        jLabel8.setText("Vehículo");

        jLabel9.setText("Seleccionar modelo y placa");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel10.setText("Estado");

        jLabel11.setText("Fechas");

        jLabel12.setText("Inicio:");

        try {
            txtFechaInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtFechaFin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel13.setText("Fin:");

        btnCrearContrato.setText("Crear");

        btnLimpiarContrato.setText("Limpiar");
        btnLimpiarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarContratoActionPerformed(evt);
            }
        });

        jLabel14.setText("Tarifa diaria");

        jLabel15.setText("Monto total");

        txtEstado2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel16.setText("Estado");

        txtEstado1.setText("jLabel17");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(btnCrearContrato)
                        .addGap(77, 77, 77)
                        .addComponent(btnLimpiarContrato))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel2))
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel11))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel4))
                                                .addGap(29, 29, 29)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel7)
                                                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(79, 79, 79)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel6)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(txtLicencia, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(99, 99, 99)
                                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addGap(26, 26, 26)
                                                .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(36, 36, 36)
                                                .addComponent(jLabel13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(66, 66, 66)
                                                .addComponent(jLabel10)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtEstado1))))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(txtTarifaDiaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jLabel16)
                                .addGap(36, 36, 36)
                                .addComponent(txtEstado2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(txtNumeroContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtLicencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jLabel9)
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtEstado1))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTarifaDiaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(txtMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEstado2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCrearContrato)
                            .addComponent(btnLimpiarContrato))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txtNumeroContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );

        jTabbedPane1.addTab("Crear Contrato", jPanel2);

        tblContratos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Num Contrato", "Cliente", "Vehiculos", "Fechas", "Monto", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblContratos);

        btnRefrescar.setText("Refrescar");
        btnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefrescarActionPerformed(evt);
            }
        });

        btnFinalizar.setText("Finalizar");

        btnCancelar.setText("Cancelar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRefrescar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Gestion de Contratos", jPanel1);

        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnBuscarContrato.setText("Buscar");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Numero de contrato ", "Cliente ", "Vehiculo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscarContrato, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2)
                    .addComponent(btnBuscarContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Buscar", jPanel3);

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel1.setText("Gestión de Contratos de Alquiler ");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 766, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaActionPerformed

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void btnLimpiarContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarContratoActionPerformed
   txtCedula.setText("");
    txtNombre.setText("");
    txtFechaInicio.setText("");
    txtCorreo.setText("");
    txtLicencia.setText("");
    jComboBox1.setSelectedIndex(-1);
    txtFechaInicio.setText("");
    txtFechaFin.setText("");
    txtTarifaDiaria.setText("");
    txtMontoTotal.setText("");
    txtEstado2.setSelectedItem("Activo");        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpiarContratoActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarContrato;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrearContrato;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnLimpiarContrato;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable tblContratos;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JLabel txtEstado1;
    private javax.swing.JComboBox<String> txtEstado2;
    private javax.swing.JFormattedTextField txtFechaFin;
    private javax.swing.JFormattedTextField txtFechaInicio;
    private javax.swing.JFormattedTextField txtFechaNacimiento;
    private javax.swing.JTextField txtLicencia;
    private javax.swing.JTextField txtMontoTotal;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumeroContrato;
    private javax.swing.JTextField txtTarifaDiaria;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
