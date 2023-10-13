package Correcion;

import java.awt.Desktop;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class Correcion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private static JLabel lresul;
	private static JFileChooser archivo;
	private JButton babrir;
	private JLabel lconversion;
	private JSeparator separator;
	private JButton bgenerarA;
	private JButton bgenerarB;
	private static ArrayList<String> primera = new ArrayList<String>();
	private static ArrayList<String> ultima = new ArrayList<String>();
	private static ArrayList<String> medio = new ArrayList<String>();
	private static ArrayList<String> ccaasinduplis = new ArrayList<String>();
	private static ArrayList<String> provsinduplis = new ArrayList<String>();
	private static ArrayList<String> anossinduplis = new ArrayList<String>();
	private static BufferedReader br = null;
	private static int contadorproductos = 0, contadorBAD = 0, contadorCAC = 0, contadorZAR = 0, contadorTER = 0,
			contadorHUE = 0, contadorEXT = 0, contadorARA = 0;
	private static String provincias[], ccaa[], anos[];
	private static String pri;
	private JSeparator separator_1;
	private JButton babrirficheros;

	private static void abrirArchivo() {

		archivo = new JFileChooser();
		archivo.setCurrentDirectory(new File(System.getProperty("user.home") + "/Documents"));
		archivo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		FileNameExtensionFilter filtro1 = new FileNameExtensionFilter( // Filtros
				"Textos txt", "txt");

		archivo.setFileFilter(filtro1);

		int r = archivo.showOpenDialog(null);

		if (r == JFileChooser.APPROVE_OPTION) {
			try {
				br = new BufferedReader(new FileReader(archivo.getSelectedFile().getPath()));
				String linea;
				
				//Al leer el fichero la 1ª línea, la salta y la ignora
				//por la Excepción
				try {
					new ExceptionNums(br);
				} catch (ExceptionNums e) {
					e.getMessage();
				}

				while ((linea = br.readLine()) != null) {
	/*============================================================================================================*/				
					//Extraemos la 1ª letra y convertimos a String
					primera.add(String.valueOf(linea.charAt(0)));
					//Extraemos la última letra y convertimos a String
					ultima.add(String.valueOf(linea.charAt(linea.length() - 1)));
					//Extraemos los dos últimos dígitos del año y convertimos a String
					medio.add(String.valueOf(linea.charAt(linea.length() - 3)) + linea.charAt(linea.length() - 2));
					contadorproductos++;
	/*============================================================================================================*/				
					//Contamos productos de ccaa y provincias
					if (linea.contains("E")) {
						contadorEXT++;
						if (linea.contains("B")) {
							contadorBAD++;
						} else if (linea.contains("C")) {
							contadorCAC++;
						}
					} else if (linea.contains("A")) {
						contadorARA++;
						if (linea.contains("Z")) {
							contadorZAR++;
						} else if (linea.contains("T")) {
							contadorTER++;
						} else if (linea.contains("H")) {
							contadorHUE++;
						}
					}
				}

				//Quitamos duplicados de ccaa, provincias y años
				for (String provincia : primera) {
					if (!provsinduplis.contains(provincia)) {
						provsinduplis.add(provincia);
					}
				}
				
				for (String ccaa : ultima) {
					if (!ccaasinduplis.contains(ccaa)) {
						ccaasinduplis.add(ccaa);
					}
				}
				
				for (String anos : medio) {
					if (!anossinduplis.contains(anos)) {
						anossinduplis.add(anos);
					}
				}
				
				//Ordenamos ccaa, provincias y años
				provincias = provsinduplis.toArray(new String[0]);
				Arrays.sort(provincias);

				ccaa = ccaasinduplis.toArray(new String[0]);
				Arrays.sort(ccaa);

				anos = anossinduplis.toArray(new String[0]);
				Arrays.sort(ccaa);

				br.close();
				lresul.setText("Extracción de datos completada");
			} catch (IOException e) {
				lresul.setText("Error de fichero");
			}
		} else if (r == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "Ha cancelado su selección");
		} else {
			JOptionPane.showMessageDialog(null, "Error en la selección");
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Correcion frame = new Correcion();
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
	public Correcion() {
		setTitle("Petici\u00F3n datos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lresul = new JLabel("");
		lresul.setHorizontalAlignment(SwingConstants.CENTER);
		lresul.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lresul.setBounds(10, 225, 414, 36);
		contentPane.add(lresul);

		babrir = new JButton("1\u00BA.- Abrir fichero");
		babrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lresul.setText(null);
				abrirArchivo();
			}
		});
		babrir.setFont(new Font("Tahoma", Font.BOLD, 16));
		babrir.setBounds(107, 63, 209, 36);
		contentPane.add(babrir);

		lconversion = new JLabel("Referencias de Productos");
		lconversion.setFont(new Font("Tahoma", Font.BOLD, 18));
		lconversion.setHorizontalAlignment(SwingConstants.CENTER);
		lconversion.setBounds(37, 11, 351, 23);
		contentPane.add(lconversion);

		separator = new JSeparator();
		separator.setBounds(37, 50, 351, 2);
		contentPane.add(separator);

		bgenerarA = new JButton("Generar TXT");
		bgenerarA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pri = primera.toString();
					if (pri.equals("[]")) {
						lresul.setText("Debe abrir primero el Fichero");
					} else {
						BufferedWriter p = new BufferedWriter(new FileWriter("D:/provincias.txt", false));
						BufferedWriter c = new BufferedWriter(new FileWriter("D:/comunidades.txt", false));
						BufferedWriter a = new BufferedWriter(new FileWriter("D:/anos.txt", false));
						BufferedWriter t = new BufferedWriter(new FileWriter("D:/total_pro.txt", false));
						
		/*============================================================================================================*/				//Convertimos Letras a Palabras
						for (int i = 0; i < provincias.length; i++) {
							if (provincias[i].equals("B")) {
								provincias[i] = "Badajoz";
							} else if (provincias[i].equals("C")) {
								provincias[i] = "Cáceres ";
							} else if (provincias[i].equals("Z")) {
								provincias[i] = "Zaragoza";
							} else if (provincias[i].equals("H")) {
								provincias[i] = "Huesca";
							} else if (provincias[i].equals("T")) {
								provincias[i] = "Teruel";
							}
						}
						for (int i = 0; i < ccaa.length; i++) {
							if (ccaa[i].equals("A")) {
								ccaa[i] = "Aragón";
							} else if (ccaa[i].equals("E")) {
								ccaa[i] = "Extremadura";
							}
						}
		/*============================================================================================================*/						
						//Escribimos en los ficheros
						p.write("Provincias\n----------\n");
						for (int i = 0; i < provincias.length; i++) {
							p.write(provincias[i] + "\n");
						}
						c.write("CCAA\n----\n");
						for (int i = 0; i < ccaa.length; i++) {
							c.write(ccaa[i] + "\n");
						}
						a.write("Años\n----\n");
						for (int i = 0; i < anos.length; i++) {
							a.write(anos[i] + "\n");
						}
						t.write("Total productos: " + contadorproductos + "\n\nTotal productos Extremadura: "
								+ contadorEXT + "\n\tProductos Badajoz: " + contadorBAD + "\n\tProductos Cáceres: "
								+ contadorCAC + "\n\nTotal productos Aragón: " + contadorARA + "\n\tProductos Huesca: "
								+ contadorHUE + "\n\tProductos Zaragoza: " + contadorZAR + "\n\tProductos Teruel: "
								+ contadorTER);
						p.close();
						c.close();
						a.close();
						t.close();
						lresul.setText("Ficheros generados");
					}
				} catch (Exception ex) {
					lresul.setText("El fichero no se pudo generar");
				}
			}
		});
		bgenerarA.setFont(new Font("Tahoma", Font.BOLD, 16));
		bgenerarA.setBounds(37, 133, 152, 36);
		contentPane.add(bgenerarA);

		bgenerarB = new JButton("Generar PDF");
		bgenerarB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pri.equals("[]")) {
					lresul.setText("Debe abrir primero el Fichero");
				} else {
					//Todo el contenido a generar
				}
			}
		});
		bgenerarB.setFont(new Font("Tahoma", Font.BOLD, 16));
		bgenerarB.setBounds(236, 133, 152, 36);
		contentPane.add(bgenerarB);

		separator_1 = new JSeparator();
		separator_1.setBounds(37, 110, 351, 2);
		contentPane.add(separator_1);
		
		babrirficheros = new JButton("Abrir todos los ficheros");
		babrirficheros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().open(new File("D:/provincias.txt"));
					Desktop.getDesktop().open(new File("D:/comunidades.txt"));
					Desktop.getDesktop().open(new File("D:/anos.txt"));
					Desktop.getDesktop().open(new File("D:/total_pro.txt"));
				} catch (IOException e1) {
					lresul.setText("Error al abrir los ficheros");
				}
			}
		});
		babrirficheros.setFont(new Font("Tahoma", Font.BOLD, 16));
		babrirficheros.setBounds(87, 178, 246, 36);
		contentPane.add(babrirficheros);

	}
}
