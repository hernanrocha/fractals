package swing;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class AcercaDe extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AcercaDe dialog = new AcercaDe();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AcercaDe() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AcercaDe.class.getResource("/icon/about.png")));
		setModal(true);
		setTitle("Acerca De");
		setResizable(false);
		setBounds(100, 100, 450, 600);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 26, 0, 0, 0, 0, 0, 23, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			JLabel lblFractalViewer = new JLabel("Fractal Viewer v2.0");
			lblFractalViewer.setFont(new Font("Tahoma", Font.BOLD, 20));
			GridBagConstraints gbc_lblFractalViewer = new GridBagConstraints();
			gbc_lblFractalViewer.anchor = GridBagConstraints.WEST;
			gbc_lblFractalViewer.insets = new Insets(10, 10, 15, 10);
			gbc_lblFractalViewer.gridx = 0;
			gbc_lblFractalViewer.gridy = 0;
			getContentPane().add(lblFractalViewer, gbc_lblFractalViewer);
		}
		{
			JLabel lblLaAplicacinPermite = new JLabel((String) null);
			GridBagConstraints gbc_lblLaAplicacinPermite = new GridBagConstraints();
			gbc_lblLaAplicacinPermite.insets = new Insets(0, 0, 5, 0);
			gbc_lblLaAplicacinPermite.gridx = 0;
			gbc_lblLaAplicacinPermite.gridy = 1;
			getContentPane().add(lblLaAplicacinPermite, gbc_lblLaAplicacinPermite);
		}
		{
			JTextPane txtpnIntro = new JTextPane();
			txtpnIntro.setBackground(UIManager.getColor("CheckBox.light"));
			txtpnIntro.setEditable(false);
			txtpnIntro.setText("Los fractales son objetos geom\u00E9tricos cuya estructura b\u00E1sica, fragmentada o irregular, se repite a diferentes escalas.\r\nLa aplicaci\u00F3n permite visualizar diferentes tipos de fractales, tanto los relacionados con Conjuntos de Complejos, como los formados a partir de la iteraci\u00F3n de Transformaciones Afines.");
			GridBagConstraints gbc_txtpnIntro = new GridBagConstraints();
			gbc_txtpnIntro.insets = new Insets(0, 10, 10, 10);
			gbc_txtpnIntro.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtpnIntro.gridx = 0;
			gbc_txtpnIntro.gridy = 2;
			getContentPane().add(txtpnIntro, gbc_txtpnIntro);
		}
		{
			JLabel lblPaleta = new JLabel("Conjuntos de Complejos (Mandelbrot y Julia)");
			lblPaleta.setFont(new Font("Tahoma", Font.BOLD, 15));
			GridBagConstraints gbc_lblPaleta = new GridBagConstraints();
			gbc_lblPaleta.anchor = GridBagConstraints.WEST;
			gbc_lblPaleta.insets = new Insets(0, 10, 5, 10);
			gbc_lblPaleta.gridx = 0;
			gbc_lblPaleta.gridy = 3;
			getContentPane().add(lblPaleta, gbc_lblPaleta);
		}
		{
			JTextPane txtpnLaPaletaSe = new JTextPane();
			txtpnLaPaletaSe.setBackground(UIManager.getColor("CheckBox.light"));
			txtpnLaPaletaSe.setText("Los conjuntos de Julia y Mandelbrot se definen a trav\u00E9s de una funci\u00F3n R definida en el plano complejo Z. El conjunto asociado a R incluye a todos los puntos del plano complejo tales que al aplicarles un n\u00FAmero N de veces la funci\u00F3n R el resultado siempre se encuentra dentro de un determinado l\u00EDmite.");
			GridBagConstraints gbc_txtpnLaPaletaSe = new GridBagConstraints();
			gbc_txtpnLaPaletaSe.insets = new Insets(0, 10, 5, 10);
			gbc_txtpnLaPaletaSe.fill = GridBagConstraints.BOTH;
			gbc_txtpnLaPaletaSe.gridx = 0;
			gbc_txtpnLaPaletaSe.gridy = 4;
			getContentPane().add(txtpnLaPaletaSe, gbc_txtpnLaPaletaSe);
		}
		{
			JLabel lblTransformacionesAfines = new JLabel("Transformaciones Afines");
			lblTransformacionesAfines.setFont(new Font("Tahoma", Font.BOLD, 15));
			GridBagConstraints gbc_lblTransformacionesAfines = new GridBagConstraints();
			gbc_lblTransformacionesAfines.anchor = GridBagConstraints.WEST;
			gbc_lblTransformacionesAfines.insets = new Insets(0, 10, 5, 10);
			gbc_lblTransformacionesAfines.gridx = 0;
			gbc_lblTransformacionesAfines.gridy = 5;
			getContentPane().add(lblTransformacionesAfines, gbc_lblTransformacionesAfines);
		}
		{
			JTextPane txtpnLaGeneracinDe = new JTextPane();
			txtpnLaGeneracinDe.setText("La generaci\u00F3n de estos fractales proviene de un simple sistema de ecuaciones que opera en el plano a trav\u00E9s de rotaciones, traslaciones y escalados. Expresada en t\u00E9rminos num\u00E9ricos, no son m\u00E1s que los coeficientes del sistema de ecuaciones anteriormente citado. El conjunto de estos coeficientes constituye lo que se llama mapa de afinidad. ");
			txtpnLaGeneracinDe.setBackground(UIManager.getColor("CheckBox.light"));
			GridBagConstraints gbc_txtpnLaGeneracinDe = new GridBagConstraints();
			gbc_txtpnLaGeneracinDe.insets = new Insets(0, 10, 5, 10);
			gbc_txtpnLaGeneracinDe.fill = GridBagConstraints.BOTH;
			gbc_txtpnLaGeneracinDe.gridx = 0;
			gbc_txtpnLaGeneracinDe.gridy = 6;
			getContentPane().add(txtpnLaGeneracinDe, gbc_txtpnLaGeneracinDe);
		}
		{
			JLabel lblUniversidadNacionalDel = new JLabel("Universidad Nacional Del Centro");
			lblUniversidadNacionalDel.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_lblUniversidadNacionalDel = new GridBagConstraints();
			gbc_lblUniversidadNacionalDel.insets = new Insets(0, 0, 5, 0);
			gbc_lblUniversidadNacionalDel.gridx = 0;
			gbc_lblUniversidadNacionalDel.gridy = 9;
			getContentPane().add(lblUniversidadNacionalDel, gbc_lblUniversidadNacionalDel);
		}
		{
			JLabel lblDeLaProvincia = new JLabel("De La Provincia De Buenos Aires (U.N.C.P.B.A.)");
			lblDeLaProvincia.setFont(new Font("Tahoma", Font.BOLD, 14));
			GridBagConstraints gbc_lblDeLaProvincia = new GridBagConstraints();
			gbc_lblDeLaProvincia.insets = new Insets(0, 0, 5, 0);
			gbc_lblDeLaProvincia.gridx = 0;
			gbc_lblDeLaProvincia.gridy = 10;
			getContentPane().add(lblDeLaProvincia, gbc_lblDeLaProvincia);
		}
		{
			JLabel lblIngenieraDeSistemas = new JLabel("Ingenier\u00EDa de Sistemas");
			lblIngenieraDeSistemas.setFont(new Font("Tahoma", Font.BOLD, 12));
			GridBagConstraints gbc_lblIngenieraDeSistemas = new GridBagConstraints();
			gbc_lblIngenieraDeSistemas.insets = new Insets(0, 0, 5, 0);
			gbc_lblIngenieraDeSistemas.gridx = 0;
			gbc_lblIngenieraDeSistemas.gridy = 11;
			getContentPane().add(lblIngenieraDeSistemas, gbc_lblIngenieraDeSistemas);
		}
		{
			JLabel lblVisualizacinComputacionalI = new JLabel("Visualizaci\u00F3n Computacional I - 2014");
			lblVisualizacinComputacionalI.setFont(new Font("Tahoma", Font.PLAIN, 12));
			GridBagConstraints gbc_lblVisualizacinComputacionalI = new GridBagConstraints();
			gbc_lblVisualizacinComputacionalI.insets = new Insets(0, 0, 5, 0);
			gbc_lblVisualizacinComputacionalI.gridx = 0;
			gbc_lblVisualizacinComputacionalI.gridy = 12;
			getContentPane().add(lblVisualizacinComputacionalI, gbc_lblVisualizacinComputacionalI);
		}
		{
			JLabel lblAutor = new JLabel("Hern\u00E1n Gabriel Rocha");
			lblAutor.setFont(new Font("Tahoma", Font.BOLD, 15));
			GridBagConstraints gbc_lblAutor = new GridBagConstraints();
			gbc_lblAutor.insets = new Insets(0, 10, 5, 10);
			gbc_lblAutor.gridx = 0;
			gbc_lblAutor.gridy = 13;
			getContentPane().add(lblAutor, gbc_lblAutor);
		}
		{
			JButton okButton = new JButton("Aceptar");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			GridBagConstraints gbc_okButton = new GridBagConstraints();
			gbc_okButton.insets = new Insets(10, 0, 10, 0);
			gbc_okButton.anchor = GridBagConstraints.NORTH;
			gbc_okButton.gridx = 0;
			gbc_okButton.gridy = 14;
			getContentPane().add(okButton, gbc_okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
	}

}
