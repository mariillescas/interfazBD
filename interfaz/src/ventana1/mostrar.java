package ventana1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class mostrar extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JScrollPane scrollPane;

    // Variables de conexión a la base de datos
    private Connection connection;
    private Statement statement;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    mostrar frame = new mostrar();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public mostrar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        table = new JTable();
        scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        cargarDatos(); // Carga los datos al crear la ventana

        // Cierra la conexión al salir de la ventana
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void cargarDatos() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        try {
            // Establecer la conexión a la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mariamarquez", "root", "Maria123");
            statement = connection.createStatement();

            // Realizar una consulta a la base de datos
            ResultSet resultSet = statement.executeQuery("SELECT id, nombre FROM usuario");

            // Agregar los datos a la tabla
            while (resultSet.next()) {
                Object[] rowData = new Object[2];
                rowData[0] = resultSet.getInt("id");
                rowData[1] = resultSet.getString("nombre");
                model.addRow(rowData);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
