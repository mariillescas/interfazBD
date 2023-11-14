package ventana1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.SystemColor;
import javax.swing.JToggleButton;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class V6 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public  Connection connection;
	 private String nombreBaseDatos;
	 private String nombreTabla;
	 private static int numColumnas;
	public void abrirV5() {
       
    };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					V6 frame = new V6(null, null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param  
	 * @param nombreTabla 
	 * @param nombreBaseDatos 
	 * @param connection 
	 */
	
	public V6(Connection connection, String nombreBaseDatos, String nombreTabla) {
		 this.connection = connection;
		    this.nombreBaseDatos = nombreBaseDatos;
		    this.nombreTabla = nombreTabla;
	
		    		    
		    
		    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 434, 47);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TABLES");
		lblNewLabel.setBounds(185, 5, 64, 21);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		panel.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String selectedItem = (String) comboBox.getSelectedItem();
				 if ("Crear Tabla".equals(selectedItem)) {
			            // Si se selecciona "CONEXION", abrir el JFrame 2 (F2)
			           
			}}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Menú", "Crear Tabla", "Crear nueva BD", "Cerrar Conexión", "Salir"}));
		comboBox.setBounds(10, 6, 105, 22);
		panel.add(comboBox);
		
		JPanel mostrar = new JPanel();
		mostrar.setBounds(0, 48, 434, 202);
		contentPane.add(mostrar);
		
		  try {
	            DatabaseMetaData metaData = connection.getMetaData();
	            ResultSet tables = metaData.getTables(nombreBaseDatos, null, null, new String[]{"TABLE"});
	            DefaultListModel<String> tableListModel = new DefaultListModel<>();

	            while (tables.next()) {
	                String tableName = tables.getString("TABLE_NAME");
	                tableListModel.addElement(tableName);
	            }
	            mostrar.setLayout(null);
	            JScrollPane scrollPane = new JScrollPane();
	            scrollPane.setBounds(10, 10, 292, 150);
	            mostrar.add(scrollPane);

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    



		
		JSeparator separator = new JSeparator();
		separator.setBounds(108, 56, 1, 2);
		mostrar.add(separator);
	}
}
