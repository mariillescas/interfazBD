package ventana1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class V1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public void abrirF2() {
        F2 frame2 = new F2(); // Pasa una referencia de V1 a F2
        frame2.setVisible(true);
    };
  
	    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					V1 frame = new V1();
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
	public V1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 128));
		panel.setBounds(153, 10, 281, 46);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("DATA´S DB");
		lblNewLabel.setForeground(new Color(240, 248, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 128, 192));
		panel_1.setBounds(10, 10, 145, 240);
		contentPane.add(panel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(32, 11, 79, 20);
		comboBox.setBackground(new Color(0, 0, 0));
		comboBox.setForeground(new Color(255, 255, 255));
		comboBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Verificar qué elemento se seleccionó en el JComboBox
		        String selectedItem = (String) comboBox.getSelectedItem();
		        if ("CONEXION".equals(selectedItem)) {
		            // Si se selecciona "CONEXION", abrir el JFrame 2 (F2)
		            F2 frame2 = new F2(); // Suponiendo que ya tienes una clase F2
		            frame2.setVisible(true);
		            V1 frame1 = new V1();
		            setVisible(false);
		            
		        } else if ("CERRAR CONEXION".equals(selectedItem)) {
		            // Realiza acciones para cerrar la conexión aquí
		        } else if ("SALIR".equals(selectedItem)) {
		        	System.exit(0);	
		        }
		    }
		});
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Bienvenido");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Showcard Gothic", Font.PLAIN, 21));
		lblNewLabel_2.setBounds(10, 127, 135, 27);
		panel_1.add(lblNewLabel_2);
		panel_1.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Menú", "CONEXION", "SALIR"}));
		
		JLabel lblNewLabel_3 = new JLabel("SGBD");
		lblNewLabel_3.setBackground(new Color(0, 0, 0));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 18));
		lblNewLabel_3.setBounds(47, 89, 70, 27);
		panel_1.add(lblNewLabel_3);

		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(153, 56, 281, 194);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(V1.class.getResource("/ventana1/sql.gif")));
		lblNewLabel_1.setBounds(0, 0, 281, 194);
		panel_2.add(lblNewLabel_1);
	}
}
