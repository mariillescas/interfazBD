package ventana1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Font;
import java.sql.Connection;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
public class V4 extends JFrame {

	private static final long serialVersionUID = 1L;
	 private Connection conexion;
	 
	 private String nombreBaseDatos;
	private JPanel contentPane;
	private JTextField nombre;
	private JTextField NOMBREATRIBUTO;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					V4 frame = new V4(null,null);
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
	public V4(Connection conexion, String nombreBaseDatos) {
	    this.conexion = conexion;
	    this.nombreBaseDatos = nombreBaseDatos; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 434, 45);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Crear Tabla");
		lblNewLabel_2.setBounds(245, 5, 89, 21);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		panel.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(255, 255, 255));
		comboBox.setBackground(new Color(128, 128, 192));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Menú", "Crear nuevo BD", "Cerrar conexión ", "Salir"}));
		comboBox.setBounds(10, 6, 106, 22);
		panel.add(comboBox);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(V4.class.getResource("/ventana1/inicio.png")));
		lblNewLabel_8.setBounds(355, 5, 46, 29);
		panel.add(lblNewLabel_8);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 128, 128));
		panel_1.setBounds(0, 45, 136, 205);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
	
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(113, 46, 321, 204);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre de la tabla");
		lblNewLabel.setBounds(129, 11, 127, 14);
		panel_2.add(lblNewLabel);
		
		nombre = new JTextField();
		nombre.setBounds(82, 36, 174, 20);
		panel_2.add(nombre);
		nombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Columnas");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel_1.setBounds(149, 77, 56, 14);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre");
		lblNewLabel_3.setBounds(45, 102, 46, 14);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Tipo Dato");
		lblNewLabel_4.setBounds(127, 102, 46, 14);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Longitud");
		lblNewLabel_5.setBounds(199, 102, 46, 14);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("PK");
		lblNewLabel_6.setBounds(255, 102, 27, 14);
		panel_2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("NN");
		lblNewLabel_7.setBounds(284, 102, 27, 14);
		panel_2.add(lblNewLabel_7);
		
		NOMBREATRIBUTO = new JTextField();
		NOMBREATRIBUTO.setBounds(26, 127, 86, 20);
		panel_2.add(NOMBREATRIBUTO);
		NOMBREATRIBUTO.setColumns(10);
		
		JComboBox DATO = new JComboBox();
		DATO.setModel(new DefaultComboBoxModel(new String[] {"INT", "DOUBLE", "VARCHAR", "FLOAT"}));
		DATO.setBounds(127, 126, 56, 22);
		panel_2.add(DATO);
		
		JSpinner LONGITUD = new JSpinner();
		LONGITUD.setBounds(199, 127, 36, 20);
		panel_2.add(LONGITUD);
		
		JCheckBox PK = new JCheckBox("");
		PK.setBounds(255, 124, 21, 23);
		panel_2.add(PK);
		
		JCheckBox NN = new JCheckBox("");
		NN.setBounds(284, 124, 21, 23);
		panel_2.add(NN);
		
		JButton crearTabla = new JButton("CREAR");
		crearTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreAtributo = NOMBREATRIBUTO.getText();
				String tipoDato = (String) DATO.getSelectedItem();
				int longitud = (int) LONGITUD.getValue();
				boolean esPK = PK.isSelected();
				boolean esNN = NN.isSelected();
				String nombreTabla = nombre.getText();

				try {
				    Statement statement = conexion.createStatement();
				    String createTableQuery = "CREATE TABLE " + nombreBaseDatos + "." + nombreTabla +
				        " (" + nombreAtributo + " " + tipoDato + "(" + longitud + ")";
				    if (esPK) {
				        createTableQuery += " PRIMARY KEY";
				    }
				    if (esNN) {
				        createTableQuery += " NOT NULL";
				    }
				    createTableQuery += ")";
				    
				    statement.executeUpdate(createTableQuery);
				    System.out.println("Tabla creada con éxito: " + nombreTabla);

				    V5 v5Frame = new V5(conexion, nombreBaseDatos, nombreTabla);
				    v5Frame.setVisible(true);
				    dispose();
				} catch (SQLException ex) {
				    ex.printStackTrace();
				    System.out.println("Error al crear la tabla: " + ex.getMessage());
				}
			            }
			        
			    });
			
		
		crearTabla.setBounds(134, 170, 89, 23);
		panel_2.add(crearTabla);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), null, null, Integer.valueOf(1)));
		spinner.setBounds(215, 74, 30, 20);
		panel_2.add(spinner);
	}
}
