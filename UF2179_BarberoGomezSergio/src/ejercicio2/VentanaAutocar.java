package ejercicio2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

public class VentanaAutocar extends JFrame {

	private JPanel contentPane;
	private JTextField txtMarca;
	private JTextField txtKilometros;
	private JTextField txtModelo;
	private JTable table;
	private JTextField txtMatricula;
	private JSpinner spinner;
	private List<Autocar> listaAutocares;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAutocar frame = new VentanaAutocar();
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
	public VentanaAutocar() {
		listaAutocares = new ArrayList<Autocar>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][grow][][49.00][grow]", "[][][][][][grow]"));

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		contentPane.add(panel, "cell 0 0 5 1,grow");
		panel.setLayout(new MigLayout("", "[]", "[]"));

		JLabel lblNewLabel = new JLabel("  Gestión autocares");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel, "cell 0 0");

		JLabel lblNewLabel_1 = new JLabel("Matrícula:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(lblNewLabel_1, "cell 0 1,alignx trailing");

		txtMatricula = new JTextField();
		contentPane.add(txtMatricula, "cell 1 1,growx");
		txtMatricula.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Marca:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(lblNewLabel_1_1, "cell 0 2,alignx trailing");

		txtMarca = new JTextField();
		contentPane.add(txtMarca, "cell 1 2,growx");
		txtMarca.setColumns(10);

		JLabel lblNewLabel_1_3 = new JLabel("Modelo:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(lblNewLabel_1_3, "cell 2 2,alignx trailing");

		txtModelo = new JTextField();
		contentPane.add(txtModelo, "cell 3 2 2 1,growx");
		txtModelo.setColumns(10);

		JLabel lblNewLabel_1_2 = new JLabel("Kilómetros:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(lblNewLabel_1_2, "cell 0 3,alignx trailing");

		txtKilometros = new JTextField();
		contentPane.add(txtKilometros, "cell 1 3,growx");
		txtKilometros.setColumns(10);

		JLabel lblNewLabel_1_4 = new JLabel("Plazas:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(lblNewLabel_1_4, "cell 2 3");

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(30, 1, 80, 1));
		spinner.setFont(new Font("Tahoma", Font.BOLD, 10));
		contentPane.add(spinner, "cell 3 3,growx");

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 0 4 5 1,alignx center,growy");
		panel_1.setLayout(new MigLayout("", "[][grow][][grow]", "[]"));

		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarDatos();
			}
		});
		panel_1.add(btnInsertar, "flowx,cell 1 0,alignx center");

		JButton btnMostrar = new JButton("Mostrar Datos");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDatos();
			}
		});
		panel_1.add(btnMostrar, "cell 1 0 3 1,alignx center");

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 5 5 1,grow");

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Matr\u00EDcula", "Marca", "Modelo", "Kil\u00F3metros", "Plazas" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, Integer.class,
					Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
	}

	protected void mostrarDatos() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		for (Autocar a : listaAutocares) {
			Object fila[] = { a.getMatricula(), a.getMarca(), a.getModelo(), a.getKilometros(), a.getNum_plazas() };
			modelo.addRow(fila);
		}

	}

	protected void insertarDatos() {

		try {
			String matricula = txtMatricula.getText();
			String marca = txtMarca.getText();
			String modelo = txtModelo.getText();
			int kilometros = Integer.parseInt(txtKilometros.getText());
			int plazas = (int) spinner.getValue();

			if (matricula.isBlank() || marca.isBlank() || modelo.isBlank()) {
				JOptionPane.showMessageDialog(contentPane, "Debe rellenar todos los campos", "Faltan datos",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (kilometros < 0) {
				JOptionPane.showMessageDialog(contentPane, "Los kilómetros deben ser un número positivo",
						"Dato inválido", JOptionPane.ERROR_MESSAGE);
			}

			Autocar a = new Autocar(matricula, marca, modelo, kilometros, plazas);

			if (!listaAutocares.contains(a)) {
				listaAutocares.add(a);
				JOptionPane.showMessageDialog(contentPane, "El dato se ha introducido correctamente",
						"Datos introducidos", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(contentPane, "El autocar con matrícula " + matricula + " ya existe",
						"El autocar ya existe", JOptionPane.ERROR_MESSAGE);
			}

		} catch (NumberFormatException n) {
			JOptionPane.showMessageDialog(contentPane, "Debe introducir un número válido en el campo de kilómetros",
					"Dato inválido", JOptionPane.ERROR_MESSAGE);
		}
	}

}
