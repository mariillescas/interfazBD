package ventana1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class interfaz1 {

    public static void main(String[] args) {
 
    
    	
            // Crear el marco principal
            JFrame frame = new JFrame("Interfaz");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Obtener el tamaño de la pantalla
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeight = screenSize.height;

            // Establecer el tamaño del marco y centrarlo en la pantalla
            int frameWidth = 1000;
            int frameHeight = 700;
            frame.setSize(frameWidth, frameHeight);
            frame.setLocation((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2);

            // Crear el panel azul en el lado izquierdo
            JPanel panelIzquierdo = new JPanel();
            panelIzquierdo.setBackground(new Color(0,0,0)); // Azul claro
            panelIzquierdo.setPreferredSize(new Dimension(frameWidth / 2, frameHeight));

            // Crear el panel de contraste en el lado derecho
            JPanel panelDerecho = new JPanel();
            panelDerecho.setBackground(new Color(64, 64, 64)); // Gris oscuro
            panelDerecho.setPreferredSize(new Dimension(frameWidth / 2, frameHeight));
            panelDerecho.setLayout(new GridBagLayout());

            // Cargar la imagen desde el paquete "Imagenes"
            ImageIcon imageIcon = new ImageIcon(interfaz1.class.getResource("im1.png"));
            JLabel imageLabel = new JLabel(imageIcon);
            panelDerecho.add(imageLabel);

            // Crear la barra de menú
            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("Menú");

            JMenuItem hacerConexionItem = new JMenuItem("Realizar conexión");
            hacerConexionItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panelDerecho.removeAll();

                    JPanel conexionPanel = new JPanel(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes

                    conexionPanel.add(new JLabel("Usuario:"), gbc);
                    gbc.gridx = 1;
                    JTextField usuarioField = new JTextField(20);
                    conexionPanel.add(usuarioField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    conexionPanel.add(new JLabel("Contraseña:"), gbc);
                    gbc.gridx = 1;
                    JPasswordField contrasenaField = new JPasswordField(20);
                    conexionPanel.add(contrasenaField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 2;
                    conexionPanel.add(new JLabel("Host:"), gbc);
                    gbc.gridx = 1;
                    JTextField hostField = new JTextField(20);
                    conexionPanel.add(hostField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 3;
                    conexionPanel.add(new JLabel("Puerto:"), gbc);
                    gbc.gridx = 1;
                    JTextField puertoField = new JTextField(20);
                    conexionPanel.add(puertoField, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 4;
                    JButton conectarButton = new JButton("Conectar");
                    conexionPanel.add(conectarButton, gbc);

                    gbc.gridx = 1;
                    JButton testButton = new JButton("Test");
                    conexionPanel.add(testButton, gbc);

                    // Acción al presionar el botón "Conectar"
                    conectarButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Lógica para hacer la conexión con los datos ingresados
                            String usuario = usuarioField.getText();
                            String contrasena = new String(contrasenaField.getPassword());
                            String host = hostField.getText();
                            String puerto = puertoField.getText();
                            System.out.println("Conectando con Usuario: " + usuario +
                                    ", Contraseña: " + contrasena +
                                    ", Host: " + host +
                                    ", Puerto: " + puerto);
                        }
                    });

                    // Acción al presionar el botón "Test"
                    testButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Lógica para hacer la prueba de conexión
                            System.out.println("Realizando prueba de conexión...");
                        }
                    });

                    panelDerecho.add(conexionPanel);
                    frame.revalidate();
                    frame.repaint();
                }
            });
           
            
            JMenuItem cerrarConexionItem = new JMenuItem("Cerrar conexión");
            cerrarConexionItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Remover todos los componentes del panel derecho
                    panelDerecho.removeAll();
                    
                    // Cargar la imagen desde el paquete "Imagenes"
                    ImageIcon newImageIcon = new ImageIcon(interfaz1.class.getResource("im1.png"));
                    JLabel newImageLabel = new JLabel(newImageIcon);
                    panelDerecho.add(newImageLabel);
                    
                    // Repintar y validar el panel para que los cambios sean visibles
                    panelDerecho.revalidate();
                    panelDerecho.repaint();
                }
            });
            JMenuItem sinFuncionItem = new JMenuItem("");

            menu.add(hacerConexionItem);
            menu.add(cerrarConexionItem);
            menu.add(sinFuncionItem);
            menuBar.add(menu);

            // Agregar los componentes al marco
            frame.setJMenuBar(menuBar);
            frame.setLayout(new GridLayout(1, 2)); // Dividir en dos partes
            frame.add(panelIzquierdo);
            frame.add(panelDerecho);
            frame.setVisible(true);
        }
    }