  package Examen2;

import java.awt.Desktop;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

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
	JLabel lresul;
	static String path;
	public static JFileChooser archivo;
	public static BufferedReader br;
	public static int contadorex=0;
	public static int contadorba=0;
	public static int contadorca=0;
	public static int contadorar=0;
	public static int contadorhu=0;
	public static int contadorza=0;
	public static int contadorte=0;
	public static int contadorproductos=0;

	String pri;
	static ArrayList<String> provincia = new ArrayList<String>();
	static ArrayList<String> año = new ArrayList<String>();
	static ArrayList<String> ccaa = new ArrayList<String>();
	static ArrayList<String> añoduplis = new ArrayList<String>();
	static ArrayList<String> provinciaduplis = new ArrayList<String>();
	static ArrayList<String> ccaaduplis = new ArrayList<String>();
	private static String provincias[], ccaas[], anos[];


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
				
				provincia.add(String.valueOf(cadena.charAt(0)));
				año.add(String.valueOf(cadena.charAt(cadena.length()-3)) + cadena.charAt(cadena.length()-2));
				ccaa.add(String.valueOf(cadena.charAt(cadena.length()-1)));
				contadorproductos++;
				
				
			if(cadena.contains("E")) {
				contadorex++;
				if(cadena.contains("B")){
					contadorba++;
				}else if(cadena.contains("C")) {					
					contadorca++;
				}
			}else if(cadena.contains("A")) {
				contadorar++;
				if(cadena.contains("Z")){
					contadorza++;
				}else if(cadena.contains("H")) {					
					contadorhu++;
				}else if(cadena.contains("T")) {					
					contadorte++;
				}
			}}
		
			
				for (String pro : provincia) {
					if(!provinciaduplis.contains(pro)) {
						provinciaduplis.add(pro);
					}			
				}
				for (String ano : año) {
					if(!añoduplis.contains(ano)){
						añoduplis.add(ano);
					}
					
				}
				for (String ca : ccaa) {
					if(!ccaaduplis.contains(ca)) {
						ccaaduplis.add(ca);
					}
				}
				
				provincias = provinciaduplis.toArray(new String[0]);
				Arrays.sort(provincias);

				ccaas = ccaaduplis.toArray(new String[0]);
				Arrays.sort(ccaas);

				anos = añoduplis.toArray(new String[0]);
				Arrays.sort(anos);
				
				for (int i = 0; i < ccaas.length; i++) {
					System.out.println(provincias[i]);
				System.out.println(ccaas[i]);
				System.out.println(anos[i]);
				}
				System.out.println(contadorex);
				System.out.println(contadorba);
				System.out.println(contadorca);
				System.out.println(contadorar);
				System.out.println(contadorza);
				System.out.println(contadorhu);
				System.out.println(contadorte);
				
			br.close();	
			
			
			
			
			

		} else if (r == JFileChooser.CANCEL_OPTION) { // Si se cancela
			JOptionPane.showMessageDialog(null, "Ha cancelado su selección");
		} else {
			JOptionPane.showMessageDialog(null, "Error en la selección");
		}
		}


	public Examen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 353);
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
		
		lresul = new JLabel("");
		lresul.setHorizontalAlignment(SwingConstants.CENTER);
		lresul.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lresul.setBounds(68, 267, 414, 36);
		contentPane.add(lresul);

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
		Gentxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					 pri = provincia.toString();
					if (pri.equals("[]")) {
						lresul.setText("Debe abrir primero el Fichero");
					}else {
						BufferedWriter p = new BufferedWriter(new FileWriter("D:/provincias.txt", false));
						BufferedWriter c = new BufferedWriter(new FileWriter("D:/comunidades.txt", false));
						BufferedWriter a = new BufferedWriter(new FileWriter("D:/anos.txt", false));
						BufferedWriter t = new BufferedWriter(new FileWriter("D:/total_pro.txt", false));
					
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
					
					for (int j = 0; j < ccaas.length; j++) {
						if(ccaas[j].equals("E")){
							ccaas[j]="Extremadua";
						}else if(ccaas[j].equals("A")) {
							ccaas[j]="Aragon";
						}
					}
					
					p.write("Provincias\n----------\n");
					for (int i = 0; i < provincias.length; i++) {
						p.write(provincias[i]+ "\n");
					}
					c.write("CCAA\n----\n");
					for (int i = 0; i < ccaas.length; i++) {
						c.write(ccaas[i] + "\n");
					}
					a.write("Años\n----\n");
					for (int i = 0; i < anos.length; i++) {
						a.write(anos[i] + "\n");
					}
					t.write("Total productos: " + contadorproductos + "\n\nTotal productos Extremadura: "
							+ contadorex + "\n\tProductos Badajoz: " + contadorba + "\n\tProductos Cáceres: "
							+ contadorca + "\n\nTotal productos Aragón: " + contadorar + "\n\tProductos Huesca: "
							+ contadorhu + "\n\tProductos Zaragoza: " + contadorza + "\n\tProductos Teruel: "
							+ contadorte);
					
					p.close();
					c.close();
					a.close();
					t.close();
					lresul.setText("Ficheros generados");
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		Gentxt.setFont(new Font("Tahoma", Font.BOLD, 16));
		Gentxt.setBounds(33, 148, 203, 30);
		contentPane.add(Gentxt);

		abrirfichs = new JButton("Abrir todos los ficheros\r\n");
		abrirfichs.addActionListener(new ActionListener() {
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
		abrirfichs.setFont(new Font("Tahoma", Font.BOLD, 16));
		abrirfichs.setBounds(154, 219, 235, 30);
		contentPane.add(abrirfichs);

		Genpdf = new JButton("Generar PDF");
		Genpdf.setFont(new Font("Tahoma", Font.BOLD, 16));
		Genpdf.setBounds(340, 148, 203, 30);
		contentPane.add(Genpdf);
		
		JLabel lresul_1 = new JLabel("");
		lresul_1.setHorizontalAlignment(SwingConstants.CENTER);
		lresul_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lresul_1.setBounds(58, 225, 414, 36);
		contentPane.add(lresul_1);
	}
}
