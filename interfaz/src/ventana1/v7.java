package ventana1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import java.sql.Statement;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class v7 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private Connection conexion;
	private JTextPane sql;
	private JCheckBox chckbxNewCheckBox;
    private JCheckBox chckbxNewCheckBox_1;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					v7 frame = new v7();
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
	public v7() {
		   String url = "jdbc:mysql://localhost:3306/mariamarquez";
	        String usuario = "root";
	        String contraseña = "Maria123";

	        try {
	            conexion = DriverManager.getConnection(url, usuario, contraseña);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(255, 255, 255), null));
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 434, 50);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextPane txtpnRealizarConsultas = new JTextPane();
		txtpnRealizarConsultas.setForeground(new Color(255, 255, 255));
		txtpnRealizarConsultas.setBackground(new Color(0, 0, 0));
		txtpnRealizarConsultas.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtpnRealizarConsultas.setText("Realizar Consultas");
		txtpnRealizarConsultas.setBounds(183, 9, 149, 20);
		panel.add(txtpnRealizarConsultas);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(255, 255, 255));
		comboBox.setBackground(new Color(128, 128, 192));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Menú", "Crear tabla", "Crear BD", "Cerrar Conexión", "Salir"}));
		comboBox.setBounds(21, 9, 120, 22);
		panel.add(comboBox);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(v7.class.getResource("/ventana1/inicio.png")));
		lblNewLabel.setBounds(351, 9, 46, 26);
		panel.add(lblNewLabel);
		
		JTextPane txtpnCreaUnaConsulta = new JTextPane();
		txtpnCreaUnaConsulta.setBackground(new Color(192, 192, 192));
		txtpnCreaUnaConsulta.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
		txtpnCreaUnaConsulta.setText("Crea una consulta para la tabla \"USUARIOS\"");
		txtpnCreaUnaConsulta.setBounds(86, 61, 277, 20);
		contentPane.add(txtpnCreaUnaConsulta);
		
	    chckbxNewCheckBox = new JCheckBox("id");
	    chckbxNewCheckBox.setBounds(151, 88, 46, 23);
	    contentPane.add(chckbxNewCheckBox);

	    chckbxNewCheckBox_1 = new JCheckBox("nombre");
	    chckbxNewCheckBox_1.setBounds(228, 88, 90, 23);
	    contentPane.add(chckbxNewCheckBox_1);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(128, 128, 255), 2));
		table.setBounds(86, 118, 288, 74);
		contentPane.add(table);
		
		sql = new JTextPane(); // en lugar de JTextPane sql = new JTextPane();

		sql.setForeground(new Color(0, 64, 128));
		sql.setBounds(10, 230, 414, 20);
		contentPane.add(sql);
		
		JButton btnActualizar = new JButton("Actualizar Tabla");
		btnActualizar.setForeground(new Color(255, 255, 255));
		btnActualizar.setBackground(new Color(0, 0, 64));
        btnActualizar.setBounds(151, 203, 150, 25);
        contentPane.add(btnActualizar);
        
        lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(v7.class.getResource("/ventana1/Y1Mo.gif")));
        lblNewLabel_1.setBounds(0, 49, 82, 201);
        contentPane.add(lblNewLabel_1);

        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                actualizarTabla();
            }
        });
    
	}

        // Obtener los nombres de las columnas seleccionadas
	// ... (código anterior)

	private void actualizarTabla() {
	    DefaultTableModel model = new DefaultTableModel();

	    try {
	        if (chckbxNewCheckBox.isSelected()) {
	            model.addColumn("id");
	        }

	        if (chckbxNewCheckBox_1.isSelected()) {
	            model.addColumn("nombre");
	        }

	        if (table != null) {
	            table.setModel(model); // Establecer el modelo en la tabla
	        }

	        // Construir la consulta SQL
	        StringBuilder consulta = new StringBuilder("SELECT ");
	        boolean isFirstColumn = true;

	        if (chckbxNewCheckBox.isSelected()) {
	            consulta.append("id");
	            isFirstColumn = false;
	        }

	        if (chckbxNewCheckBox_1.isSelected()) {
	            if (!isFirstColumn) {
	                consulta.append(", ");
	            }
	            consulta.append("nombre");
	        }

	        consulta.append(" FROM usuario");

	        // Mostrar la consulta SQL en el JTextPane
	        sql.setText("Consulta SQL: " + consulta.toString());

	        // Obtener datos de la base de datos y llenar la tabla
	        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mariamarquez", "root", "Maria123");
	             Statement statement = conexion.createStatement();
	             ResultSet resultSet = statement.executeQuery(consulta.toString())) {

	            while (resultSet.next()) {
	                Object[] rowData = new Object[model.getColumnCount()];
	                for (int i = 0; i < model.getColumnCount(); i++) {
	                    rowData[i] = resultSet.getObject(i + 1);
	                }
	                model.addRow(rowData);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}