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
		comboBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    }    // Verificar qué elemento se seleccionó en el JComboBox
		    
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"MENÚ", "CERRAR CONEXION", "SALIR"}));



		comboBox.setBounds(10, 11, 120, 22);
		panel.add(comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("HACER CONEXIÓN");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(140, 13, 178, 14);
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 128, 128));
		panel_1.setBounds(10, 51, 414, 199);
		contentPane.add(panel_1);
		
		JLabel lblPuerto = new JLabel("PUERTO");
		
		JLabel lblNewLabel = new JLabel("HOST");
		
		JLabel lblNewLabel_1 = new JLabel("PASSWORD");
		
		password = new JTextField();
		password.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("USER");
		
		host = new JTextField();
		host.setColumns(10);
		
		puerto = new JTextField();
		puerto.setColumns(10);
		
		user = new JTextField();
		
		user.setColumns(10);
		
		JButton conectar = new JButton("CONECTAR");
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
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(153)
							.addComponent(lblNewLabel))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(124)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_2)
										.addComponent(lblPuerto)
										.addComponent(lblNewLabel_1)))
								.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
									.addContainerGap(109, Short.MAX_VALUE)
									.addComponent(conectar)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
										.addComponent(user)
										.addComponent(puerto)
										.addComponent(host)
										.addComponent(password, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)))
								.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
									.addComponent(btnNewButton_1)
									.addPreferredGap(ComponentPlacement.RELATED)))))
					.addContainerGap(101, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(host, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPuerto)
						.addComponent(puerto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(user, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(conectar))
					.addGap(24))
		);
		panel_1.setLayout(gl_panel_1);
	}
}
