package ventana1;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Vector;


public class Conexion {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mariamarquez";
        String usuario = "root";
        String contrasena = "Maria123";

        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);
            Statement declaracion = conexion.createStatement();
            ResultSet resultado = declaracion.executeQuery("SELECT * FROM usuario");

            // Obtén los metadatos
            ResultSetMetaData metaData = resultado.getMetaData();
            int numeroDeColumnas = metaData.getColumnCount();

            // Nombres de las columnas
            Vector<String> nombresColumnas = new Vector<>();
            for (int columna = 1; columna <= numeroDeColumnas; columna++) {
                nombresColumnas.add(metaData.getColumnName(columna));
            }

            // Datos de la tabla
            Vector<Vector<Object>> datosTabla = new Vector<>();
            while (resultado.next()) {
                Vector<Object> vector = new Vector<>();
                for (int columnIndex = 1; columnIndex <= numeroDeColumnas; columnIndex++) {
                    vector.add(resultado.getObject(columnIndex));
                }
                datosTabla.add(vector);
            }

            // Crea la tabla y muestra los datos
            JTable table = new JTable(datosTabla, nombresColumnas);

            // Crea tres JTextField para la entrada de datos
            JTextField textField1 = new JTextField();
            JTextField textField2 = new JTextField();
            // Crea un JButton para la acción de agregar
            JButton agregarButton = new JButton("Agregar");
            
            table.getTableHeader().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int columnIndex = table.columnAtPoint(e.getPoint());
                    // Selecciona todas las celdas de la columna
                    table.setColumnSelectionInterval(columnIndex, columnIndex);
                    // Copia los valores de la columna
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < table.getRowCount(); i++) {
                        sb.append(table.getValueAt(i, columnIndex));
                        if (i < table.getRowCount() - 1) {
                            sb.append("\n");
                        }
                    }
                    StringSelection stringSelection = new StringSelection(sb.toString());
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);
                }
            });
            
            
            
            


            agregarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String textoIngresado1 = textField1.getText();
                    String textoIngresado2 = textField2.getText();
                    try {
                        // Crear una nueva declaración
                        Statement insertarDeclaracion = conexion.createStatement();
                        // Comprobar si se proporcionó un ID
                        if (textoIngresado1.isEmpty()) {
                            // Si no se proporcionó un ID, deja que MySQL genere uno
                            insertarDeclaracion.executeUpdate("INSERT INTO usuario (nombre) VALUES ('" + textoIngresado2 + "')");
                        } else {
                            // Si se proporcionó un ID, úsalo
                            insertarDeclaracion.executeUpdate("INSERT INTO usuario (id, nombre) VALUES ('" + textoIngresado1 + "', '" + textoIngresado2 + "')");
                        }

                        // Actualizar la tabla
                        ResultSet resultadoActualizado = declaracion.executeQuery("SELECT * FROM usuario");
                        datosTabla.clear();
                        while (resultadoActualizado.next()) {
                            Vector<Object> vector = new Vector<>();
                            for (int columnIndex = 1; columnIndex <= numeroDeColumnas; columnIndex++) {
                                vector.add(resultadoActualizado.getObject(columnIndex));
                            }
                            datosTabla.add(vector);
                        }
                        table.updateUI();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            
         // Crea un JTextField para la entrada del ID a eliminar
            JTextField textFieldEliminar = new JTextField();
            // Crea un JButton para la acción de eliminar
            JButton eliminarButton = new JButton("Eliminar");
            eliminarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String idEliminar = textFieldEliminar.getText();
                    try {
                        // Crear una nueva declaración
                        Statement eliminarDeclaracion = conexion.createStatement();
                        // Ejecutar la consulta de eliminación
                        eliminarDeclaracion.executeUpdate("DELETE FROM usuario WHERE id = '" + idEliminar + "'");

                        // Actualizar la tabla
                        ResultSet resultadoActualizado = declaracion.executeQuery("SELECT * FROM usuario");
                        datosTabla.clear();
                        while (resultadoActualizado.next()) {
                            Vector<Object> vector = new Vector<>();
                            for (int columnIndex = 1; columnIndex <= numeroDeColumnas; columnIndex++) {
                                vector.add(resultadoActualizado.getObject(columnIndex));
                            }
                            datosTabla.add(vector);
                        }
                        table.updateUI();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            
            
         // Crea tres JTextField para la entrada de datos a actualizar
            JTextField textFieldActualizarId = new JTextField();
            JTextField textFieldActualizarNombre = new JTextField();
            // Crea un JButton para la acción de actualizar
            JButton actualizarButton = new JButton("Actualizar");
            actualizarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String idActualizar = textFieldActualizarId.getText();
                    String nombreActualizar = textFieldActualizarNombre.getText();
                    try {
                        // Crear una nueva declaración
                        Statement actualizarDeclaracion = conexion.createStatement();
                        // Ejecutar la consulta de actualización
                        actualizarDeclaracion.executeUpdate("UPDATE usuario SET nombre = '" + nombreActualizar + "'  WHERE id = '" + idActualizar + "'");

                        // Actualizar la tabla
                        ResultSet resultadoActualizado = declaracion.executeQuery("SELECT * FROM usuario");
                        datosTabla.clear();
                        while (resultadoActualizado.next()) {
                            Vector<Object> vector = new Vector<>();
                            for (int columnIndex = 1; columnIndex <= numeroDeColumnas; columnIndex++) {
                                vector.add(resultadoActualizado.getObject(columnIndex));
                            }
                            datosTabla.add(vector);
                        }
                        table.updateUI();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });


         


            // Añade los JTextField y JButton a la GUI
            


            // Añade los JTextField y JButton a la GUI
            
            
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(new JScrollPane(table), BorderLayout.CENTER);
            JPanel inputPanel = new JPanel(new GridLayout(4, 1));
            inputPanel.add(textField1);
            inputPanel.add(textField2);
            inputPanel.add(agregarButton);
            frame.add(inputPanel, BorderLayout.SOUTH);
            frame.pack();
            frame.setVisible(true);
            
            
      
            inputPanel.add(textField1);
            inputPanel.add(textField2);
            inputPanel.add(agregarButton);
            inputPanel.add(textFieldEliminar);
            inputPanel.add(eliminarButton);
            frame.add(inputPanel, BorderLayout.SOUTH);
            
         // Añade los JTextField y JButton a la GUI
          
            inputPanel.add(textField1);
            inputPanel.add(textField2);
            inputPanel.add(agregarButton);
            inputPanel.add(textFieldEliminar);
            inputPanel.add(eliminarButton);
            inputPanel.add(textFieldActualizarId);
            inputPanel.add(textFieldActualizarNombre);
            inputPanel.add(actualizarButton);
            frame.add(inputPanel, BorderLayout.SOUTH);
            
         // Crea un GridBagLayout y GridBagConstraints
            GridBagLayout layout = new GridBagLayout();
            GridBagConstraints gbc = new GridBagConstraints();
            inputPanel.setLayout(layout);

            // Define algunas propiedades comunes de GridBagConstraints
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;

            // Agrega los JTextField y JButton al panel de entrada
            gbc.gridy = 0;
            inputPanel.add(new JLabel("ID:"), gbc);
            gbc.gridy++;
            inputPanel.add(textField1, gbc);

            gbc.gridy++;
            inputPanel.add(new JLabel("Nombre:"), gbc);
            gbc.gridy++;
            inputPanel.add(textField2, gbc);

            
            gbc.gridy++;
            inputPanel.add(agregarButton, gbc);

            gbc.gridy++;
            inputPanel.add(new JLabel("ID a eliminar:"), gbc);
            gbc.gridy++;
            inputPanel.add(textFieldEliminar, gbc);

            gbc.gridy++;
            inputPanel.add(eliminarButton, gbc);

            gbc.gridy++;
            inputPanel.add(new JLabel("ID a actualizar:"), gbc);
            gbc.gridy++;
            inputPanel.add(textFieldActualizarId, gbc);

            gbc.gridy++;
            inputPanel.add(new JLabel("Nuevo nombre:"), gbc);
            gbc.gridy++;
            inputPanel.add(textFieldActualizarNombre, gbc);

                       gbc.gridy++;
            inputPanel.add(actualizarButton, gbc);


            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
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
}