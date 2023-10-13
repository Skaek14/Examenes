package Examen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

public class Jorge_Gallego extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton Abrirfichero;
	private JButton Gentxt;
	private JButton Genpdf;
	private JButton abrirfichs;
	static String path;
	JFileChooser archivo;
	RandomAccessFile fichero;
	RandomAccessFile ficheroP;
	RandomAccessFile ficheroA;
	RandomAccessFile ficheroC;
	static boolean mensaje;
//	char provincias;
	Collection<String> Pro = new ArrayList<>();
	int contador = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jorge_Gallego frame = new Jorge_Gallego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	
	
	public Jorge_Gallego() {
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

				archivo = new JFileChooser();
				archivo.setCurrentDirectory(new File(System.getProperty("user.home") + "/Documents")); // Elegimos
																										// directorio a
																										// abrir
				archivo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				// Tipo archivos
				FileNameExtensionFilter filtro3 = new FileNameExtensionFilter("Documentos txt", "txt");
				archivo.setFileFilter(filtro3);
				archivo.setFileFilter(null); // Ninguno de los anteriores es el predeterminado
				int r = archivo.showOpenDialog(null); // Opciones del cuadro de diálogo
				if (r == JFileChooser.APPROVE_OPTION) { // Si se Abre
					path = archivo.getSelectedFile().getPath();
					JOptionPane.showMessageDialog(null, "Extraccion de datos completada");
				} else if (r == JFileChooser.CANCEL_OPTION) { // Si se cancela
					JOptionPane.showMessageDialog(null, "Ha cancelado su selección");
				} else {
				JOptionPane.showMessageDialog(null,"Error en la selección");
				}
				try {
					fichero = new RandomAccessFile(path, "r");
					if (fichero.length() == 9) {
						mensaje = false;
					} else {
						mensaje = true;
					}
					new MyException(mensaje);
					
					
					PDDocument docu = new PDDocument(); // Crea pdf
					PDPage pagina1 = new PDPage(); // Crea página en blanco
					docu.addPage(pagina1); // Agrega la página
					PDPage page = docu.getPage(0); // Obtiene página 1

					
			
					PDPageContentStream contenido = new PDPageContentStream(docu, page);
					contenido.beginText(); // Inicio BLOQUE 1 de texto
					contenido.setFont(PDType1Font.TIMES_ROMAN, 8);
					contenido.setLeading(10.5f); // Interlineado
					contenido.newLineAtOffset(25, 775);

					contenido.setCharacterSpacing(0.5f);// Espacio entre caracteres
					contenido.showText("Productos");
					contenido.newLine();


					 String cadena = fichero.readLine(); // leemos la primera línea
					while (cadena != null) {
						System.out.println(cadena);
						cadena =fichero.readLine();
						if(cadena.contains("B")) {
							contenido.showText("Badajoz");
							contenido.newLine();}
							if(cadena.contains("C")) {
								contenido.showText("Caceres");
								contenido.newLine();}
								if(cadena.contains("A")) {
									contenido.showText("Aragon");
									contenido.newLine();}
									if(cadena.contains("Z")) {
										contenido.showText("Zaragoza");
										contenido.newLine();}
										if(cadena.contains("T")) {
											contenido.showText("Teruel");
											contenido.newLine();}
											if(cadena.contains("H")) {
												contenido.showText("Huesca");
												contenido.newLine();}
											if(cadena.contains("E")) {
												contenido.showText("Extremadura");
												contenido.newLine();}
											File file = new File("D:/destino2.pdf");
						
					
						
					}
					
			

				} catch (IOException | MyException e1) {

					JOptionPane.showMessageDialog(null, e1.getMessage());

					e1.printStackTrace();
				}
			}
		});
		Abrirfichero.setFont(new Font("Tahoma", Font.BOLD, 16));
		Abrirfichero.setBounds(175, 81, 203, 30);
		contentPane.add(Abrirfichero);

		Gentxt = new JButton("Generar TXT");
		Gentxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
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
