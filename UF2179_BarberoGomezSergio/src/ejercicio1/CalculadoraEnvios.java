package ejercicio1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;

public class CalculadoraEnvios extends JFrame {

	private JPanel contentPane;
	private JTextField txtOrigen;
	private JTextField txtDestino;
	private final ButtonGroup grupoOrigen = new ButtonGroup();
	private final ButtonGroup grupoDestino = new ButtonGroup();
	private JRadioButton rbtOrigenNacional;
	private JRadioButton rbtOrigenExtranjero;
	private JRadioButton rbtDestinoNacional;
	private JRadioButton rbtDestinoExtranjero;
	private JComboBox comboBox;
	private JSpinner spinner;
	private String origen;
	private String destino;
	private boolean origenNacional;
	private boolean destinoNacional;
	private int tipoEnvio;
	private int peso;
	private double importe;
	private String textoEnvio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculadoraEnvios frame = new CalculadoraEnvios();
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
	public CalculadoraEnvios() {
		setTitle("Calculadora de envíos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[41.00][][45.00][38.00][grow]", "[][][grow][][grow][][][13.00][]"));

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		contentPane.add(panel, "cell 0 0 5 1,grow");

		JLabel lblNewLabel = new JLabel("Calculadora de envíos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Ciudad Origen:");
		contentPane.add(lblNewLabel_1, "cell 1 1,alignx trailing");

		txtOrigen = new JTextField();
		contentPane.add(txtOrigen, "cell 2 1 3 1,growx");
		txtOrigen.setColumns(10);

		rbtOrigenNacional = new JRadioButton("Nacional");
		rbtOrigenNacional.setSelected(true);
		grupoOrigen.add(rbtOrigenNacional);
		contentPane.add(rbtOrigenNacional, "cell 2 2 2 1,aligny top");

		rbtOrigenExtranjero = new JRadioButton("Extranjero");
		grupoOrigen.add(rbtOrigenExtranjero);
		contentPane.add(rbtOrigenExtranjero, "cell 4 2,aligny top");

		JLabel lblNewLabel_2 = new JLabel("Ciudad Destino:");
		contentPane.add(lblNewLabel_2, "cell 1 3,alignx trailing");

		txtDestino = new JTextField();
		contentPane.add(txtDestino, "cell 2 3 3 1,growx");
		txtDestino.setColumns(10);

		rbtDestinoNacional = new JRadioButton("Nacional");
		rbtDestinoNacional.setSelected(true);
		grupoDestino.add(rbtDestinoNacional);
		contentPane.add(rbtDestinoNacional, "cell 2 4 2 1,aligny top");

		rbtDestinoExtranjero = new JRadioButton("Extranjero");
		grupoDestino.add(rbtDestinoExtranjero);
		contentPane.add(rbtDestinoExtranjero, "cell 4 4,aligny top");

		JLabel lblNewLabel_3 = new JLabel("Tipo de envío:");
		contentPane.add(lblNewLabel_3, "cell 1 5,alignx left");

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Paq 10 - Antes de las 10 h",
				"Paq 14 - Antes de las 14 h", "Paq 24 - Al día siguiente" }));
		comboBox.setSelectedIndex(0);
		contentPane.add(comboBox, "cell 2 5 3 1,growx");

		JLabel lblNewLabel_4 = new JLabel("Peso:");
		contentPane.add(lblNewLabel_4, "cell 1 6");

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 40, 1));
		contentPane.add(spinner, "cell 2 6,growx");

		JLabel lblNewLabel_5 = new JLabel("Kg");
		contentPane.add(lblNewLabel_5, "cell 4 6");

		JButton btnCalcular = new JButton("Calcular Precio");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculaPrecio();
			}
		});
		contentPane.add(btnCalcular, "cell 0 8 5 1,alignx center");
	}

	protected void calculaPrecio() {
		origen = txtOrigen.getText();
		destino = txtOrigen.getText();

		if (origen == null || origen.isBlank() || destino == null || destino.isBlank()) {
			JOptionPane.showMessageDialog(contentPane, "Debe introducir un origen y un destino", "Datos no válidos",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		origenNacional = rbtOrigenNacional.isSelected();
		destinoNacional = rbtDestinoNacional.isSelected();
		tipoEnvio = comboBox.getSelectedIndex();
		textoEnvio = (String) comboBox.getSelectedItem();
		peso = (int) spinner.getValue();
		importe = 0;

		if (origenNacional && destinoNacional) {
			importe = 4;
		} else {
			importe = 7;
		}

		switch (tipoEnvio) {
		case 0:
			importe += 5;
			break;
		case 1:
			importe += 2;
			break;
		}

		importe = importe + (peso / 10) * 3.5;
		muestraResultado();
	}

	private void muestraResultado() {
		String resultado = ("Origen: " + origen + "\nDestino: " + destino + "\nTipo: " + textoEnvio + "\nPeso: " + peso
				+ " Kg" + "\nImporte: " + importe + " €");
		JOptionPane.showMessageDialog(contentPane, resultado, "Cálculo", JOptionPane.INFORMATION_MESSAGE);

	}

}
