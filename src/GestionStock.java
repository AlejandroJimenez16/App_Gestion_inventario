import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GestionStock {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionStock window = new GestionStock();
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
	public GestionStock() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//frame
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 550);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Gestion del Stock");
		frame.getContentPane().setLayout(null);
		
		//Panel Principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 884, 511);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        frame.getContentPane().add(mainPanel);
		
		//Logo
		JLabel lblLogo = new JLabel(new ImageIcon(GestionStock.class.getResource("/Imagenes/logo_papa_peq.png")));
		lblLogo.setBounds(10, 11, 143, 89);
		mainPanel.add(lblLogo);
		
		//Titulo
		JLabel lblTitulo = new JLabel("Stock");
		lblTitulo.setBounds(766, 11, 118, 67);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
		mainPanel.add(lblTitulo);
		
		//Datos Productos
		JLabel lblDatos = new JLabel("Datos Productos");
        lblDatos.setBounds(10, 120, 218, 24);
        lblDatos.setFont(new Font("Arial", Font.PLAIN, 24));
        mainPanel.add(lblDatos);
		
		//Panel Bordes
		JPanel panelBordes = new JPanel();
        panelBordes.setBounds(10, 155, 517, 110);
        panelBordes.setBackground(Color.decode("#e8e4e4"));
        mainPanel.add(panelBordes);
        panelBordes.setLayout(null);
        
        //Dato proveedor
        JLabel lblProv = new JLabel("Proveedor:");
        lblProv.setBounds(10, 22, 114, 14);
        lblProv.setFont(new Font("Arial", Font.BOLD, 18));
        panelBordes.add(lblProv);
        
        //Desplegable proovedores
        JComboBox<String> despProv = new JComboBox<String>();
        despProv.setBounds(134, 20, 90, 22);
        panelBordes.add(despProv);
        
        //Dato Producto
        JLabel lblProducto = new JLabel("Producto:");
        lblProducto.setFont(new Font("Arial", Font.BOLD, 18));
        lblProducto.setBounds(10, 68, 114, 14);
        panelBordes.add(lblProducto);
        
        //Desplegable Producto
        JComboBox<String> despProd = new JComboBox<String>();
        despProd.setBounds(134, 66, 90, 22);
        panelBordes.add(despProd);
        
        //Dato Peso
        JLabel lblPeso = new JLabel("Peso:");
        lblPeso.setFont(new Font("Arial", Font.BOLD, 18));
        lblPeso.setBounds(292, 24, 114, 14);
        panelBordes.add(lblPeso);
        
        //textField peso
        JTextField textFieldPeso = new JTextField();
        textFieldPeso.setBounds(363, 21, 90, 20);
        panelBordes.add(textFieldPeso);
        textFieldPeso.setColumns(10);
        
        //Kg
        JLabel lblKg = new JLabel("Kg");
        lblKg.setBounds(458, 22, 46, 20);
        lblKg.setFont(new Font("Arial", Font.PLAIN, 18));
        panelBordes.add(lblKg);
        
        //Stock
        JLabel lblStock = new JLabel("Stock");
        lblStock.setFont(new Font("Arial", Font.PLAIN, 24));
        lblStock.setBounds(10, 276, 218, 24);
        mainPanel.add(lblStock);
        
        
        
        
        
        
        
        
        
        
        
		
		
		
	}
}
