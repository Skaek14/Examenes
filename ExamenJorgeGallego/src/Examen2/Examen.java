  package Examen2;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Examen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton Abrirfichero;
	private JButton Gentxt;
	private JButton Genpdf;
	private JButton abrirfichs;
	static String path;
	public static JFileChooser archivo;
	public static BufferedReader br;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Examen frame = new Examen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void abrirfichero() throws IOException {

		archivo = new JFileChooser();
		archivo.setCurrentDirectory(new File("System.getProperty(\"user.home\") + \"/Documents\"")); // Elegimos directorio a abrir
		archivo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		// Tipo archivos
		FileNameExtensionFilter filtro3 = new FileNameExtensionFilter("Documentos txt", "txt");
		archivo.setFileFilter(filtro3);
		archivo.setFileFilter(null); // Ninguno de los anteriores es el predeterminado
		int r = archivo.showOpenDialog(null); // Opciones del cuadro de diálogo
		if (r == JFileChooser.APPROVE_OPTION) { // Si se Abre
			String path = archivo.getSelectedFile().getPath();
				br = new BufferedReader(new FileReader(path));
			String cadena;
			while ((cadena = br.readLine()) != null) {
				
			System.out.println(cadena);
			}
			br.close();		

		} else if (r == JFileChooser.CANCEL_OPTION) { // Si se cancela
			JOptionPane.showMessageDialog(null, "Ha cancelado su selección");
		} else {
			JOptionPane.showMessageDialog(null, "Error en la selección");
		}
		}


	public Examen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Referencias de productos");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(153, 10, 269, 30);
		contentPane.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(33, 69, 510, 2);
		separator.setOpaque(true);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(33, 121, 510, 2);
		separator_1.setOpaque(true);
		contentPane.add(separator_1);

		Abrirfichero = new JButton("1º.-Abrir fichero");
		Abrirfichero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					abrirfichero();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Abrirfichero.setFont(new Font("Tahoma", Font.BOLD, 16));
		Abrirfichero.setBounds(175, 81, 203, 30);
		contentPane.add(Abrirfichero);
 
		Gentxt = new JButton("Generar TXT");
		Gentxt.setFont(new Font("Tahoma", Font.BOLD, 16));
		Gentxt.setBounds(33, 148, 203, 30);
		contentPane.add(Gentxt);

		abrirfichs = new JButton("Abrir todos los ficheros\r\n");
		abrirfichs.setFont(new Font("Tahoma", Font.BOLD, 16));
		abrirfichs.setBounds(154, 219, 235, 30);
		contentPane.add(abrirfichs);

		Genpdf = new JButton("Generar PDF");
		Genpdf.setFont(new Font("Tahoma", Font.BOLD, 16));
		Genpdf.setBounds(340, 148, 203, 30);
		contentPane.add(Genpdf);
	}
}
