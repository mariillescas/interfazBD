package ventana1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel; 
public class consult {
    private static JTable table;
    private static Vector<Vector<Object>> datosTabla;
    private static Vector<String> nombresColumnas;
    private static JTextArea sqlTextArea;
    private static JCheckBox[] checkboxes;

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mariamarquez";
        String usuario = "root";
        String contrasena = "Maria123";

        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);
            Statement declaracion = conexion.createStatement();
            ResultSet resultado = declaracion.executeQuery("SELECT * FROM usuario");

            ResultSetMetaData metaData = resultado.getMetaData();
            int numeroDeColumnas = metaData.getColumnCount();
            nombresColumnas = new Vector<>();

            for (int columna = 1; columna <= numeroDeColumnas; columna++) {
                nombresColumnas.add(metaData.getColumnName(columna));
            }

            datosTabla = new Vector<>();
            while (resultado.next()) {
                Vector<Object> vector = new Vector<>();
                for (int columnIndex = 1; columnIndex <= numeroDeColumnas; columnIndex++) {
                    vector.add(resultado.getObject(columnIndex));
                }
                datosTabla.add(vector);
            }

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            table = new JTable(datosTabla, nombresColumnas);
            frame.add(new JScrollPane(table), BorderLayout.CENTER);

            JPanel checkboxPanel = new JPanel();
            checkboxPanel.setLayout(new FlowLayout());
            JCheckBox[] checkboxes = new JCheckBox[numeroDeColumnas];

            sqlTextArea = new JTextArea(5, 20);
            sqlTextArea.setEditable(false);

            checkboxes = new JCheckBox[numeroDeColumnas];

            for (int i = 0; i < numeroDeColumnas; i++) {
                checkboxes[i] = new JCheckBox(nombresColumnas.get(i));
                final int columnIndex = i;
                checkboxes[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        actualizarTabla();
                    }
                });
                checkboxPanel.add(checkboxes[i]);
            }

            frame.add(checkboxPanel, BorderLayout.NORTH);
            frame.add(sqlTextArea, BorderLayout.SOUTH);

            frame.pack();
            frame.setVisible(true);

            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent e) {
                    try {
                        resultado.close();
                        declaracion.close();
                        conexion.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void actualizarTabla() {
        if (checkboxes == null || table == null) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableColumnModel columnModel = table.getColumnModel();

        // Limpiar columnas existentes
        while (columnModel.getColumnCount() > 0) {
            columnModel.removeColumn(columnModel.getColumn(0));
        }

        // Agregar columnas seleccionadas
        Vector<String> columnasSeleccionadas = new Vector<>();
        for (int j = 0; j < nombresColumnas.size(); j++) {
            if (checkboxes[j].isSelected()) {
                columnasSeleccionadas.add(nombresColumnas.get(j));
                TableColumn newColumn = new TableColumn(j);
                newColumn.setHeaderValue(nombresColumnas.get(j));
                columnModel.addColumn(newColumn);
            }
        }

        // Actualizar datos y notificar al modelo
        Vector<Vector<Object>> nuevosDatos = new Vector<>();
        for (Vector<Object> fila : datosTabla) {
            Vector<Object> nuevaFila = new Vector<>();
            for (int j = 0; j < nombresColumnas.size(); j++) {
                if (checkboxes[j].isSelected()) {
                    nuevaFila.add(fila.get(j));
                }
            }
            nuevosDatos.add(nuevaFila);
        }

        model.setDataVector(nuevosDatos, columnasSeleccionadas);

        // Actualizar el área de texto con la consulta SQL
        sqlTextArea.setText("SELECT " + obtenerColumnasSeleccionadas() + " FROM usuario");
    }

    private static Vector<String> obtenerColumnasSeleccionadas() {
        Vector<String> columnasSeleccionadas = new Vector<>();
        for (int j = 0; j < nombresColumnas.size(); j++) {
            if (checkboxes[j].isSelected()) {
                columnasSeleccionadas.add(nombresColumnas.get(j));
            }
        }
        return columnasSeleccionadas;
    }
}