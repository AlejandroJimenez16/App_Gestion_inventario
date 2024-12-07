import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GestionProveedores {

	public JFrame frame;
	
	// Obtengo la conexion
	Connection conexion = DatabaseConnection.getConnection();
	
	//Datos originales
    private String originalNombre;
    private int originalTelefono;
    private int originalPuesto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionProveedores window = new GestionProveedores();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionProveedores() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		 DefaultTableModel model = new DefaultTableModel();
		
		//frame
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 550);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Gestión de Proveedores");
		frame.getContentPane().setLayout(null);
		
		//Panel Principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 884, 511);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        frame.getContentPane().add(mainPanel);
        
        //Logo
        JLabel lblLogo = new JLabel(new ImageIcon(GestionProveedores.class.getResource("/Imagenes/logo_papa_peq.png")));
        lblLogo.setBounds(10, 11, 143, 89);
        mainPanel.add(lblLogo);
      
        //Titulo
        JLabel lblTitulo = new JLabel("<html><u>Proveedores</u></html>");
        lblTitulo.setBounds(648, 11, 253, 67);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        mainPanel.add(lblTitulo);
        
        //Datos Proveedores
      	JLabel lblDatos = new JLabel("Datos Proveedores");
        lblDatos.setBounds(10, 120, 218, 24);
        lblDatos.setFont(new Font("Arial", Font.PLAIN, 24));
        mainPanel.add(lblDatos);
    
        //Panel Bordes
        JPanel panelBordes = new JPanel();
        panelBordes.setBounds(10, 155, 517, 110);
        panelBordes.setBackground(Color.decode("#e8e4e4"));
        mainPanel.add(panelBordes);
        panelBordes.setLayout(null);
        
        //Dato nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 22, 114, 14);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 18));
        panelBordes.add(lblNombre);
        
        //textField nombre
        JTextField textFieldNombre = new JTextField();
        textFieldNombre.setColumns(10);
        textFieldNombre.setBounds(100, 21, 90, 20);
        panelBordes.add(textFieldNombre);
        
        //Dato telefono
        JLabel lblTelef = new JLabel("Teléfono:");
        lblTelef.setFont(new Font("Arial", Font.BOLD, 18));
        lblTelef.setBounds(10, 68, 114, 14);
        panelBordes.add(lblTelef);
        
        //textField telefono
        JTextField textFieldTelefono = new JTextField();
        textFieldTelefono.setColumns(10);
        textFieldTelefono.setBounds(100, 67, 90, 20);
        panelBordes.add(textFieldTelefono);
        
        //Dato Puesto
        JLabel lblPuesto = new JLabel("Puesto:");
        lblPuesto.setFont(new Font("Arial", Font.BOLD, 18));
        lblPuesto.setBounds(292, 24, 114, 14);
        panelBordes.add(lblPuesto);
        
        //textField puesto
        JTextField textFieldPuesto = new JTextField();
        textFieldPuesto.setBounds(363, 21, 90, 20);
        panelBordes.add(textFieldPuesto);
        textFieldPuesto.setColumns(10);
        
        //Acciones
        JLabel lblAcciones = new JLabel("Acciones");
        lblAcciones.setFont(new Font("Arial", Font.PLAIN, 24));
        lblAcciones.setBounds(642, 120, 107, 24);
        mainPanel.add(lblAcciones);
        
        //Panel Acciones
        JPanel panelBordesAcciones = new JPanel();
        panelBordesAcciones.setLayout(null);
        panelBordesAcciones.setBackground(new Color(232, 228, 228));
        panelBordesAcciones.setBounds(604, 155, 186, 166);
        mainPanel.add(panelBordesAcciones);
        
        //Boton agregar
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(33, 11, 128, 33);
        btnAgregar.setIcon(new ImageIcon(GestionProveedores.class.getResource("/Imagenes/iconoAgregar.png")));
        btnAgregar.setBackground(Color.WHITE);
        panelBordesAcciones.add(btnAgregar);
        
        btnAgregar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Obtengo los datos
				String nombre = textFieldNombre.getText().trim();
				String telefonoTexto = textFieldTelefono.getText().trim();
				String puestoTexto = textFieldPuesto.getText().trim();
				
				//Comprobar que los campos no estan vacios
				if(nombre.isEmpty() || telefonoTexto.isEmpty() || puestoTexto.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Rellena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					try {
						if(telefonoTexto.length() != 9 || (!telefonoTexto.startsWith("6") && !telefonoTexto.startsWith("7"))){
							JOptionPane.showMessageDialog(frame, "El teléfono debe tener 9 dígitos y comenzar con 6 o 7.", "Error", JOptionPane.ERROR_MESSAGE);
						}else {
							int telefono = Integer.parseInt(telefonoTexto);
							int puesto = Integer.parseInt(puestoTexto);
							
							//Consultas
							String query = "SELECT nombreProveedor, telefono, puesto FROM proveedor WHERE nombreProveedor = ? AND telefono = ? AND puesto = ?";
							
							try {
								PreparedStatement ps = conexion.prepareStatement(query);
								ps.setString(1, nombre);
								ps.setInt(2, telefono);
								ps.setInt(3, puesto);
								
								//Ejecutar consulta
								ResultSet rs = ps.executeQuery();
								
								if(rs.next()) {
									JOptionPane.showMessageDialog(frame, "El Proveedor ya existe", "Error", JOptionPane.ERROR_MESSAGE);
								}else {
									String queryCreate = "INSERT INTO proveedor (nombreProveedor, telefono, puesto) VALUES (?, ?, ?)";
									PreparedStatement ps1 = conexion.prepareStatement(queryCreate);
									ps1.setString(1, nombre);
									ps1.setInt(2, telefono);
									ps1.setInt(3, puesto);
									
									ps1.executeUpdate();
									
									JOptionPane.showMessageDialog(frame, "El proveedor " + nombre + " se ha registrado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
									
									cargarDatos(model);
									
									ps1.close();
									
									//Vaciar los textField
									textFieldNombre.setText("");
									textFieldTelefono.setText("");
									textFieldPuesto.setText("");
								}
								
								rs.close();
								ps.close();
								
							} catch (SQLException e2) {
								JOptionPane.showMessageDialog(frame, "Ya existe un proveedor con el mismo nombre, telefono o puesto", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					} catch (NumberFormatException  e1) {
						JOptionPane.showMessageDialog(frame, "El teléfono y el puesto deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
        
        //Boton modificar
        JButton btnModificar = new JButton("Modificar");
        btnModificar.setBounds(33, 55, 128, 44);
        btnModificar.setIcon(new ImageIcon(GestionProveedores.class.getResource("/Imagenes/iconoModificar.png")));
        btnModificar.setBackground(Color.WHITE);
        panelBordesAcciones.add(btnModificar);
        
        /*
         * //Accion del btn_modificar mas abajo
        */
        
        //Boton eliminar
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(33, 110, 128, 41);
        btnEliminar.setIcon(new ImageIcon(GestionProveedores.class.getResource("/Imagenes/iconoEliminar.png")));
        btnEliminar.setBackground(Color.WHITE);
        panelBordesAcciones.add(btnEliminar);
        
        btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = textFieldNombre.getText().trim();
				String telefonoTexto = textFieldTelefono.getText().trim();
				String puestoTexto = textFieldPuesto.getText().trim();
				
				if(nombre.isEmpty() || telefonoTexto.isEmpty() || puestoTexto.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Rellena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int confirm = JOptionPane.showConfirmDialog(
			            frame,
			            "¿Estás seguro de que deseas eliminar al proveedor: " + nombre + "?",
			            "Confirmar Eliminación",
			            JOptionPane.YES_NO_OPTION
				);
				
				if(confirm == JOptionPane.YES_OPTION) {
					try {
						int telefono = Integer.parseInt(telefonoTexto);
						int puesto = Integer.parseInt(puestoTexto);
						
						String query = "DELETE FROM proveedor WHERE nombreProveedor = ? AND telefono= ? AND puesto = ?";
						
						try(PreparedStatement ps = conexion.prepareStatement(query)) {
							ps.setString(1, nombre);
							ps.setInt(2, telefono);
							ps.setInt(3, puesto);
							
							int rowsAffected = ps.executeUpdate();
							
							if(rowsAffected > 0) {
								JOptionPane.showMessageDialog(frame, "El proveedor " + nombre + " se ha eliminado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
								cargarDatos(model);
								
								//Vaciar los textField
								textFieldNombre.setText("");
								textFieldTelefono.setText("");
								textFieldPuesto.setText("");
								
								ps.close();
							}else {
								JOptionPane.showMessageDialog(frame, "No se encontró el proveedor para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
							}
						
						} 
						
					} catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(frame, "El teléfono y el puesto deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
		                
		            } catch (SQLException e2) {
						e2.printStackTrace();
					}
				}	
			}
		});
        
        //lista de prov
        JLabel lblListProv = new JLabel("Lista de Proveedores");
        lblListProv.setFont(new Font("Arial", Font.PLAIN, 24));
        lblListProv.setBounds(10, 278, 253, 24);
        mainPanel.add(lblListProv);
        
        //Tabla de informacion
        JTable table = new JTable(model);
        
        //Desactivar boton agregar si hay fila seleccionada
        table.getSelectionModel().addListSelectionListener(e -> {
        	// Si hay una fila seleccionada, desactiva el botón Agregar
            if (table.getSelectedRow() != -1) {
                btnAgregar.setEnabled(false); // Desactiva el botón Agregar
            } else {
                btnAgregar.setEnabled(true); // Activa el botón Agregar si no hay selección
            }
        });
        
        //Agrego las columnas de la tabla
        model.addColumn("Nombre Proveedor");
        model.addColumn("Teléfono");
        model.addColumn("Puesto");
        
        cargarDatos(model);
        
        // Añadir la tabla dentro de un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 310, 504, 150);
        mainPanel.add(scrollPane);

        mainPanel.setVisible(true);
        
        //Reyenar textField al clickar una fila de la tabla
        table.addMouseListener(new MouseAdapter() {
            private int lastSelectedRow = -1;

            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = table.getSelectedRow();

                if (filaSeleccionada >= 0) { // Verifica que la fila sea válida
                    if (filaSeleccionada == lastSelectedRow) {
                        table.clearSelection();
                        lastSelectedRow = -1;
                        
                        // Limpia los JTextFields
                        textFieldNombre.setText("");
                        textFieldTelefono.setText("");
                        textFieldPuesto.setText("");
                        
                        originalNombre = null;
                        originalTelefono = 0;
                        originalPuesto = 0;
                    } else {
                        lastSelectedRow = filaSeleccionada;
                        
                        String nombre = (String) model.getValueAt(filaSeleccionada, 0);
                        int telefono = (Integer) model.getValueAt(filaSeleccionada, 1);
                        int puesto = (Integer) model.getValueAt(filaSeleccionada, 2);

                        // Llenar los textFields
                        textFieldNombre.setText(nombre);
                        textFieldTelefono.setText(String.valueOf(telefono));
                        textFieldPuesto.setText(String.valueOf(puesto));
                        
                        originalNombre = nombre;
                        originalTelefono = telefono;
                        originalPuesto = puesto;
                    }
                }
            }
        });
        
        //Accion boton modificar
        btnModificar.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		//Obtener los nuevos valores
        		String nuevoNombre = textFieldNombre.getText();
        		int nuevoTelefono = Integer.parseInt(textFieldTelefono.getText());
        		int nuevoPuesto = Integer.parseInt(textFieldPuesto.getText());
        		
        		boolean nombreCambiado = !nuevoNombre.equals(originalNombre);
        		boolean telefonoCambiado = nuevoTelefono != originalTelefono;
        		boolean puestoCambiado = nuevoPuesto != originalPuesto;
        		
        		if(nombreCambiado || telefonoCambiado || puestoCambiado) {
        			try {
        				String query = "UPDATE proveedor SET nombreProveedor = ?, telefono = ?, puesto = ? WHERE nombreProveedor = ? AND telefono = ? AND puesto = ?";
        				PreparedStatement ps = conexion.prepareStatement(query);
        				
        				ps.setString(1, nuevoNombre);
        				ps.setInt(2, nuevoTelefono);
        				ps.setInt(3, nuevoPuesto);
        				
        				ps.setString(4, originalNombre);
        				ps.setInt(5, originalTelefono);
        				ps.setInt(6, originalPuesto);
        				
        				int rowsUpdated = ps.executeUpdate();
        				if (rowsUpdated > 0) {
        				    JOptionPane.showMessageDialog(null, "Proveedor actualizado con éxito.");
        				    cargarDatos(model);
        				} else {
        				    JOptionPane.showMessageDialog(null, "No se encontraron registros para actualizar.");
        				}
					} catch (SQLException e2) {
						JOptionPane.showMessageDialog(frame, "No se puede modificar, ya existe un proveedor con el mismo nombre, telefono o puesto", "Error", JOptionPane.ERROR_MESSAGE);
					}
        		}
        	}
        });
        
	}
	
	public static void cargarDatos(DefaultTableModel model) {
	    // Limpiar el modelo antes de cargar nuevos datos
	    model.setRowCount(0);

	    Connection conexion = DatabaseConnection.getConnection();
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        String query = "SELECT nombreProveedor, telefono, puesto FROM proveedor";
	        ps = conexion.prepareStatement(query);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            String nombre = rs.getString("nombreProveedor");
	            int telefono = rs.getInt("telefono");
	            int puesto = rs.getInt("puesto");

	            model.addRow(new Object[]{nombre, telefono, puesto});
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	        } catch (SQLException e2) {
	            e2.printStackTrace();
	        }
	    }
	}

	
}
