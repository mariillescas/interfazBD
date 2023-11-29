package ventana1;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ventana1.V3;
import javax.swing.ImageIcon;


public class F2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField password;
	private JTextField host;
	private JTextField puerto;
	private JTextField user;


	public void abrirF2() {
        V3 frame3 = new V3(null); // Pasa una referencia de V1 a F2
        frame3.setVisible(true);
    };
  
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F2 frame = new F2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public F2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(10, 0, 414, 52);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(255, 255, 255));
		comboBox.setBackground(new Color(128, 128, 192));
		comboBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    }    // Verificar qué elemento se seleccionó en el JComboBox
		    
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"MENÚ", "CERRAR CONEXION", "SALIR"}));



		comboBox.setBounds(10, 11, 117, 22);
		panel.add(comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("HACER CONEXIÓN");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(153, 13, 178, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(F2.class.getResource("/ventana1/inicio.png")));
		lblNewLabel_4.setBounds(341, 11, 48, 26);
		panel.add(lblNewLabel_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 128, 128));
		panel_1.setBounds(10, 51, 414, 199);
		contentPane.add(panel_1);
		
		JLabel lblPuerto = new JLabel("PUERTO");
		lblPuerto.setBounds(127, 42, 57, 14);
		
		JLabel lblNewLabel = new JLabel("HOST");
		lblNewLabel.setBounds(144, 11, 36, 14);
		
		JLabel lblNewLabel_1 = new JLabel("PASSWORD");
		lblNewLabel_1.setBounds(108, 103, 76, 14);
		
		password = new JTextField();
		password.setBounds(205, 100, 114, 20);
		password.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("USER");
		lblNewLabel_2.setBounds(144, 72, 40, 14);
		
		host = new JTextField();
		host.setBounds(205, 8, 114, 20);
		host.setColumns(10);
		
		puerto = new JTextField();
		puerto.setBounds(205, 39, 114, 20);
		puerto.setColumns(10);
		
		user = new JTextField();
		user.setBounds(205, 69, 114, 20);
		
		user.setColumns(10);
		
		JButton conectar = new JButton("CONECTAR");
		conectar.setBounds(86, 152, 98, 23);
		conectar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String jdbcURL = "jdbc:mysql://" + host.getText() + ":" + puerto.getText() + "/";
		        String usuario = user.getText();
		        String pass = password.getText();

		        try {
		            Connection conexion = DriverManager.getConnection(jdbcURL, usuario, pass);

		            if (conexion != null) {
		                // La conexión ha tenido éxito
		                System.out.println("Conexión exitosa a la base de datos MySQL");
		                // Aquí puedes realizar operaciones en la base de datos
		                // ...

		                V3 frame3 = new V3(conexion); // Pasa la conexión a V3
		                frame3.setVisible(true);

		                // Cierra la ventana actual (F2)
		                dispose();
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});

		
		JButton btnNewButton_1 = new JButton("TEST");
		btnNewButton_1.setBounds(262, 152, 76, 23);
		panel_1.setLayout(null);
		panel_1.add(lblNewLabel);
		panel_1.add(lblNewLabel_2);
		panel_1.add(lblPuerto);
		panel_1.add(lblNewLabel_1);
		panel_1.add(conectar);
		panel_1.add(user);
		panel_1.add(puerto);
		panel_1.add(host);
		panel_1.add(password);
		panel_1.add(btnNewButton_1);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(F2.class.getResource("/ventana1/Y1Mo.gif")));
		lblNewLabel_5.setBounds(0, 0, 76, 199);
		panel_1.add(lblNewLabel_5);
	}
}
