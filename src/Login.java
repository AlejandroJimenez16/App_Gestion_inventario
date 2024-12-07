import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {

	public JFrame frame;
	
	// Obtengo la conexion
	Connection conexion = DatabaseConnection.getConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	// Crea la aplicacion
	 
	public Login() {
		initialize();
	}

	// Contenido de la aplicacion
	
	private void initialize() {
		
		//Ventana
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Login - App Papa");
        frame.getContentPane().setLayout(null);
        
        //Panel
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 217, 411);
        panel.setBackground(Color.decode("#484cb4"));
        panel.setLayout(null);
        frame.getContentPane().add(panel);
        
        //Logo
        JLabel lblLogo = new JLabel("");
        lblLogo.setBounds(10, 103, 264, 137);
        lblLogo.setIcon(new ImageIcon(Login.class.getResource("/Imagenes/logo-jiga-nuevo_resized.png")));
        panel.add(lblLogo);
        
        //Iniciar sesion
        JLabel lblLogin = new JLabel("INICIAR SESIÓN");
        lblLogin.setFont(new Font("Calibri", Font.BOLD, 25));
        lblLogin.setBounds(241, 22, 258, 44);
        frame.getContentPane().add(lblLogin);
        
        //Usuario
        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("Calibri", Font.BOLD, 18));
        lblUsuario.setBounds(241, 95, 134, 38);
        frame.getContentPane().add(lblUsuario);
        
        //textField usuario
        JTextField textFieldUsu = new JTextField();
        textFieldUsu.setBounds(241, 131, 272, 38);
        textFieldUsu.setColumns(10);
        frame.getContentPane().add(textFieldUsu);
        
        //Password
        JLabel lblpassword = new JLabel("Contraseña");
        lblpassword.setFont(new Font("Calibri", Font.BOLD, 18));
        lblpassword.setBounds(241, 194, 134, 38);
        frame.getContentPane().add(lblpassword);
        
        //passwordField password
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(241, 228, 272, 38);
        frame.getContentPane().add(passwordField);
        
        //Boton Entrar
        JButton btnAcceder = new JButton("Acceder");
        btnAcceder.setBounds(248, 307, 100, 29);
        btnAcceder.setBackground(Color.decode("#484cb4"));
        btnAcceder.setForeground(Color.WHITE);
        btnAcceder.setFocusPainted(false);
        btnAcceder.setBorderPainted(false);
        frame.getContentPane().add(btnAcceder);
        
        btnAcceder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Accion del boton
				String usuario = textFieldUsu.getText();
				char[] passwordArray = passwordField.getPassword();
				String password = new String(passwordArray);
				
				//Comprobar que los campos no estan vacios
				if(usuario.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Rellena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String hashPassword = hashPassword(password);
				
				String query = "SELECT email, password FROM usuario WHERE email = ? AND password = ?";
				
				try {
					//Crear la consulta preparada
					PreparedStatement ps = conexion.prepareStatement(query);
					ps.setString(1, usuario);
					ps.setString(2, hashPassword);
					
					//Ejecutar consulta
					ResultSet rs = ps.executeQuery();
					
					if(rs.next()) {
						frame.dispose();
						
						MenuPrincipal nuevaVentana = new MenuPrincipal();
						nuevaVentana.frame.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(frame, "El usuario no existe", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					ps.close();
					rs.close();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
        
        //Boton Registrar
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setBackground(new Color(72, 76, 180));
        btnRegistrar.setBounds(399, 307, 100, 29);
        frame.getContentPane().add(btnRegistrar);
        
        btnRegistrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Accion del boton
				String usuario = textFieldUsu.getText();
				char[] passwordArray = passwordField.getPassword();
				String password = new String(passwordArray);
				
				//Comprobar que los campos no estan vacios
				if(usuario.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Rellena todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String querySelect = "SELECT email, password FROM usuario WHERE email = ?";
				
				try {
					//Crear la consulta preparada
					PreparedStatement ps = conexion.prepareStatement(querySelect);
					ps.setString(1, usuario);
					
					//Ejecutar consulta
					ResultSet rs = ps.executeQuery();
					
					if(rs.next()) {
						JOptionPane.showMessageDialog(frame, "El usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
					}else {
						String queryCreate = "INSERT INTO usuario (email, password) VALUES (?, ?)";
						PreparedStatement ps1 = conexion.prepareStatement(queryCreate);
						ps1.setString(1, usuario);
						ps1.setString(2, hashPassword(password));
						
						//Ejecuto la inserccion
						ps1.executeUpdate();
						
						JOptionPane.showMessageDialog(frame, "Usuario registrado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
						
						//Vaciar los textField
						textFieldUsu.setText("");
						passwordField.setText("");
						
						ps1.close();
					}
					
					ps.close();
					rs.close();
					
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
					
			}
		});
        
	}
	
	public static String hashPassword(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
			//hasheo la password
			byte[] hash = digest.digest(password.getBytes());
			
			//Convertir de bytes a hexadecimal
			 StringBuilder hexString = new StringBuilder();
			 for(byte b : hash) {
				 String hex = Integer.toHexString(0xff & b);
				 if(hex.length() == 1) {
					 hexString.append('0');
				 }
				 hexString.append(hex);
			 }
			 
			 //Devuelve la password hasheada
			 return hexString.toString();
			 
		} catch (NoSuchAlgorithmException  e) {
			 throw new RuntimeException("Error al hashear la contraseña", e);
		}
	}
}
