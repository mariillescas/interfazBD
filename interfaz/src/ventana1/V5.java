package ventana1;

import java.awt.EventQueue;
import ventana1.V4;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTree;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.ImageIcon;

public class V5 extends JFrame {

	private static final long serialVersionUID = 1L;
	public  Connection conexion;
	 private String nombreBaseDatos;
	 private String nombreTabla;
	 private JTable table;
	 private JPanel panelDatos;

	private JPanel contentPane;
	private JTextField NOMBRE;
	
   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					V5 frame = new V5(null, null, null);
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
	public V5(Connection conexion, String nombreBaseDatos, String nombreTabla) {
	    this.conexion = conexion;
	    this.nombreBaseDatos = nombreBaseDatos;
	    this.nombreTabla = nombreTabla;
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 0, 434, 42);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Insertar registro");
		lblNewLabel_1.setBounds(219, 5, 139, 21);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		panel_1.add(lblNewLabel_1);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setForeground(new Color(255, 255, 255));
		comboBox_3.setBackground(new Color(128, 128, 192));
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Menú", "Crear nuevo BD", "Cerrar conexión ", "Salir"}));
		comboBox_3.setBounds(10, 6, 117, 22);
		panel_1.add(comboBox_3);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(V5.class.getResource("/ventana1/inicio.png")));
		lblNewLabel_6.setBounds(378, 5, 46, 32);
		panel_1.add(lblNewLabel_6);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 192));
		panel.setBounds(142, 41, 292, 209);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setBounds(23, 34, 46, 14);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("TABLA NAME");
		lblNewLabel.setBounds(97, 11, 102, 18);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 14));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Tipo dato");
		lblNewLabel_3.setBounds(91, 34, 61, 14);
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Long");
		lblNewLabel_4.setBounds(162, 32, 37, 18);
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("PK");
		lblNewLabel_5.setBounds(224, 34, 29, 14);
		lblNewLabel_5.setForeground(new Color(192, 192, 192));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_7 = new JLabel("NN");
		lblNewLabel_7.setBounds(263, 34, 29, 14);
		lblNewLabel_7.setForeground(new Color(192, 192, 192));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblNewLabel_7);
		
		NOMBRE = new JTextField();
		NOMBRE.setBounds(10, 63, 68, 20);
		panel.add(NOMBRE);
		NOMBRE.setColumns(10);
		
		JComboBox TIPODATO = new JComboBox();
		TIPODATO.setBounds(97, 59, 55, 22);
		TIPODATO.setModel(new DefaultComboBoxModel(new String[] {"INT", "VARCHAR", "DOUBLE", "FLOAT", "DATE"}));
		panel.add(TIPODATO);
		
		JSpinner LONGITUD = new JSpinner();
		LONGITUD.setBounds(162, 61, 37, 20);
		panel.add(LONGITUD);
		
		JCheckBox PK = new JCheckBox("");
		PK.setBounds(224, 55, 21, 23);
		panel.add(PK);
		
		
		
		JCheckBox NN = new JCheckBox("");
		NN.setBounds(263, 55, 21, 23);
		panel.add(NN);
		
		JButton CREAR = new JButton("Insertar");
		CREAR.setBounds(23, 175, 89, 23);
		CREAR.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Asegúrate de que la conexión no sea nula
		        if (conexion != null) {
		            String nombreAtributo = NOMBRE.getText();
		            String tipoDato = (String) TIPODATO.getSelectedItem();
		            int longitud = (int) LONGITUD.getValue();
		            boolean esPK = PK.isSelected();
		            
		            boolean esNN = NN.isSelected();
		            try {
						Statement statement = conexion.createStatement();
					
						String useDatabaseQuery = "USE " + nombreBaseDatos;
						    
						    // Ejecutar la sentencia SQL USE
						  statement.executeUpdate(useDatabaseQuery);
						    String sql = "ALTER TABLE " + nombreTabla + " ADD COLUMN " + nombreAtributo + " " + tipoDato + "(" + longitud + ")";
						    if (esPK) {
						        sql += ", ADD PRIMARY KEY (" + nombreAtributo + ")";
						    }

						    if (esNN) {
						        sql += " NOT NULL";
						    }
						    statement.executeUpdate(sql);
						 System.out.println("Atributo creado con éxito.");
						 v6 V6Frame = new v6(nombreTabla, nombreTabla);
						    V6Frame.setVisible(true);
						    dispose();                    
		                    dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						 System.err.println("Error al crear el atributo.");
					}
		            // Define la sentencia SQL para agregar el atributo a la tabla
		           
		    
		        } else {
		            System.err.println("La conexión a la base de datos es nula. Asegúrate de pasar la conexión correctamente desde V4.");
		        }
		    }
		});
		panel.add(CREAR);
		
		    

		JButton btnNewButton_1 = new JButton("Ver tabla");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 v6 V6Frame = new v6(nombreTabla, nombreTabla);
				    V6Frame.setVisible(true);
				    dispose();
			}
		});
		btnNewButton_1.setBounds(173, 175, 89, 23);
		panel.add(btnNewButton_1);
		

		
			

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 0));
		panel_2.setBounds(0, 41, 142, 209);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(V5.class.getResource("/ventana1/2QZ9.gif")));
		lblNewLabel_8.setBounds(0, 0, 142, 209);
		panel_2.add(lblNewLabel_8);
		
		
	
		}
}
