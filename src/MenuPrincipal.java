import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuPrincipal {

    public JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuPrincipal window = new MenuPrincipal();
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
    public MenuPrincipal() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        // Crear el frame
        frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setTitle("Menu Principal");
        frame.getContentPane().setLayout(null);
        
        //Panel Principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        frame.setContentPane(mainPanel); 
        

        //Panel logo
        JPanel panelLogo = new JPanel();
        panelLogo.setBounds(0, 0, 211, 119);
        panelLogo.setBackground(Color.WHITE);
        mainPanel.add(panelLogo);

        //Logo
        JLabel lblLogo = new JLabel(new ImageIcon(Login.class.getResource("/Imagenes/logo-jiga-nuevo_resized.png")));
        panelLogo.add(lblLogo);

        //Menu
        JMenuBar menuBar = new JMenuBar();
        
        //Espacio en la barra de menú para que empiece donde acaba el logo
        menuBar.add(Box.createHorizontalStrut(215));

        //Elementos menu
        JMenu menuProveedores = new JMenu("Proveedores");
        configuracionMenu(menuProveedores);
        menuProveedores.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Imagenes/iconoProveedor.png")));
        
        //Submenu proveedores
        JMenuItem gestionProvedoresItem = new JMenuItem("Gestionar Proveedores");
        configuracionItems(gestionProvedoresItem);
        gestionProvedoresItem.addActionListener(e -> {
        	GestionProveedores gestionProveedores = new GestionProveedores();
        	gestionProveedores.frame.setVisible(true);
        });
        menuProveedores.add(gestionProvedoresItem);
        
        menuBar.add(menuProveedores);
        
        JMenu menuCompras = new JMenu("Compras");
        configuracionMenu(menuCompras);
        menuCompras.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Imagenes/iconoCompra.png")));
        
        //Submenus Compras
        JMenuItem registrarCompraItem = new JMenuItem("Registar Compras");
        configuracionItems(registrarCompraItem);
        registrarCompraItem.addActionListener(e -> {
        	
        });
        menuCompras.add(registrarCompraItem);
        
        JMenuItem verComprasItem = new JMenuItem("Ver Compras");
        configuracionItems(verComprasItem);
        verComprasItem.addActionListener(e -> {
        	
        });
        menuCompras.add(verComprasItem);
        
        menuBar.add(menuCompras);
        
        JMenu menuStock = new JMenu("Stock");
        configuracionMenu(menuStock);
        menuStock.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Imagenes/iconoCaja.png")));
        
        //Submenu Stock
        JMenuItem gestionStockItem = new JMenuItem("Gestionar Stock");
        configuracionItems(gestionStockItem);
        gestionStockItem.addActionListener(e -> {
        	GestionStock gestionStock = new GestionStock();
        	gestionStock.frame.setVisible(true);
        });
        menuStock.add(gestionStockItem);
        
        menuBar.add(menuStock);

        JMenu menuVentas = new JMenu("Ventas");
        configuracionMenu(menuVentas);
        menuVentas.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Imagenes/iconoVenta.png")));
        
        //Submenu Ventas
        JMenuItem registrarVentaItem = new JMenuItem("Registar Ventas");
        configuracionItems(registrarVentaItem);
        registrarVentaItem.addActionListener(e -> {
        	
        });
        menuVentas.add(registrarVentaItem);
        
        JMenuItem verVentasItem = new JMenuItem("Ver Ventas");
        configuracionItems(verVentasItem);
        verVentasItem.addActionListener(e -> {
        	
        });
        menuVentas.add(verVentasItem);
        
        menuBar.add(menuVentas);
        
        JMenu menuClientes = new JMenu("Clientes");
        configuracionMenu(menuClientes);
        menuClientes.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Imagenes/iconoCliente.png")));
        
        //Submenu Clientes
        JMenuItem gestionClienteItem = new JMenuItem("Gestionar Clientes");
        configuracionItems(gestionClienteItem);
        gestionClienteItem.addActionListener(e -> {
        	
        });
        menuClientes.add(gestionClienteItem);
        
        menuBar.add(menuClientes);
        
        JMenu menuSalir = new JMenu("Salir");
        configuracionMenu(menuSalir);
        menuSalir.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/Imagenes/iconoSalir.png")));
        
        //Submenus salir
        JMenuItem salirItem = new JMenuItem("Cerrar app");
        salirItem.setForeground(Color.RED);
        configuracionItems(salirItem);
        salirItem.addActionListener(e -> {
        	System.exit(0);
        });
        menuSalir.add(salirItem);
        
        JMenuItem cerrarSesion = new JMenuItem("Cerar Sesión");
        configuracionItems(cerrarSesion);
        cerrarSesion.addActionListener(e -> {
        	frame.dispose();
        	
        	Login login = new Login();
        	login.frame.setVisible(true);
        });
        menuSalir.add(cerrarSesion);
        
        
        menuBar.add(menuSalir);
        
        frame.setJMenuBar(menuBar);

    }
    
    private void configuracionMenu(JMenu menu) {
    	//menuStock.setOpaque(true);
        //menuStock.setBackground(Color.decode("#484cb4"));
        //menuStock.setForeground(Color.WHITE);
        menu.setFont(new Font("Arial", Font.BOLD, 20));
        menu.setBorder(new EmptyBorder(0, 5, 0, 10));
    }
    
    private void configuracionItems(JMenuItem item) {
    	item.setFont(new Font("Arial", Font.BOLD, 17));
    }
}