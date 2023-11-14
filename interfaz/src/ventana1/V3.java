package ventana1;
import ventana1.V4;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;

public class V3 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private static Connection conexion;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					V3 frame = new V3(conexion);
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
	public V3(Connection conexion) { // Añade el parámetro de conexión
        this.conexion = conexion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 434, 51);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Crear base de datos");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(191, 11, 151, 29);
		panel.add(lblNewLabel);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Menú", "Cerrar Conexión", "Salir"}));
		comboBox_1.setBounds(10, 16, 95, 22);
		panel.add(comboBox_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBounds(108, 49, 326, 212);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre de la base de datos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(86, 11, 164, 14);
		panel_1.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(58, 36, 226, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"utf8mb4_general_ci", "utf8mb4_bin", "utf8mb4_croatian_ci", "utf8mb4_german?_ci"}));
		comboBox.setBounds(111, 109, 132, 22);
		panel_1.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Cotejamiento");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(127, 72, 91, 14);
		panel_1.add(lblNewLabel_2);
		
		JButton crear = new JButton("CREAR");
		
		crear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    String nombreBaseDatos = textField.getText();
                   
                    

                    if (nombreBaseDatos.isEmpty()) {
                       System.out.println("Error: Debes proporcionar un nombre de base de datos.");
                    } else {
                        try {
                            Statement statement = conexion.createStatement();
                            String createDatabaseQuery = "CREATE DATABASE " + nombreBaseDatos ;
                            statement.executeUpdate(createDatabaseQuery);
                           System.out.println("Base de datos creada con éxito: " + nombreBaseDatos);
                           V4 v4Frame = new V4(conexion, nombreBaseDatos);
                           v4Frame.setVisible(true);
                           dispose(); // Cierra la ventana V3
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            System.out.println("Error al crear la base de datos: " + ex.getMessage());
                        }
             }
			
                });
            }
			
		});
		crear.setBounds(129, 162, 89, 23);
		panel_1.add(crear);
		
		;
		
		 
	                }
		}
	
	

