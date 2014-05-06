package swing;
//import Grafico;
//import ManejadorClicsRaton;

import fractal.Grafico;
import hoja.Afines;
import hoja.HelechoBransley;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import basic.Complejo;
import basic.Punto;
import conjunto.Conjunto;
import conjunto.ConjuntoMandelbrot;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.NumberFormat;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import paleta.Paleta;
import paleta.ColoreoFuego;
import paleta.ColoreoRGB;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;

/**
 * 
 */

/**
 * @author hernan
 *
 */
public class Fractales extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblImagen;
	private JMenuBar menuBar;
	private JMenu mnArchivo;
	private JMenu mnAyuda;
	private JPanel panelOpciones;
	private JScrollPane scrollPaneOpciones;
	private JSpinner spinXMin;
	private JSpinner spinXMax;
	private JSpinner spinYMin;
	private JSpinner spinYMax;
	private JButton btnGraficar;
	private Grafico g;

	private MouseHandler mouseHandler;
	private JLabel lblRangoEjeX;
	private JLabel lblRangoEjeY;
	private JLabel lblA;
	private JLabel lblA_1;
	private JCheckBox chckbxMover;
	private JCheckBox chckbxZoom;
	private JCheckBox chckbxThread;
	private JLabel lblPuntoactual;
	private JLabel lblTiempoprocesamiento;
	private JSpinner spinThreads;
	private JPanel panelProcesamiento;
	private JPanel panelCoordenadas;
	private JPanel panelAcciones;
	private JLabel lblThreads;
	private JCheckBox chckbxAceleracinDeReclculo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fractales frame = new Fractales();
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
	public Fractales() {
		setTitle("Fractales");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 20, 875, 675);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);

		mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{403, 230, 0};
		gbl_contentPane.rowHeights = new int[] {579, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0};
		contentPane.setLayout(gbl_contentPane);

		lblImagen = new JLabel("");
		GridBagConstraints gbc_lblImagen = new GridBagConstraints();
		gbc_lblImagen.fill = GridBagConstraints.VERTICAL;
		gbc_lblImagen.insets = new Insets(0, 0, 5, 5);
		gbc_lblImagen.gridx = 0;
		gbc_lblImagen.gridy = 0;
		contentPane.add(lblImagen, gbc_lblImagen);
		mouseHandler = new MouseHandler(this);
		lblImagen.addMouseListener(mouseHandler);
		lblImagen.addMouseMotionListener(mouseHandler);

		scrollPaneOpciones = new JScrollPane();
		GridBagConstraints gbc_scrollPaneOpciones = new GridBagConstraints();
		gbc_scrollPaneOpciones.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneOpciones.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneOpciones.gridx = 1;
		gbc_scrollPaneOpciones.gridy = 0;
		contentPane.add(scrollPaneOpciones, gbc_scrollPaneOpciones);

		initFractalComplejos();
		
		agregarPanelOpciones();
		graficar();
		
		
//		Afines.cargarAfines();
//		HelechoBransley helecho = new HelechoBransley(this);
//		helecho.calcular();
//		helecho.graficar(lblImagen);


		//		Complejo c = new Complejo(0.3, 0.5);

		// Personalizacion
		//		Funcion f = new FuncionPolinomica(2, new Complejo(0.279, 0));
		//		Funcion f = new FuncionPolinomica(3, new Complejo(0.4, 0));
		//		Funcion f = new FuncionPolinomica(3, new Complejo(-1, 0));
		//		Funcion f = new FuncionPolinomica(3, new Complejo(0.3, 0.6));

		//		Fraccion c1 = new Fraccion(3, 3);
		//		Fraccion c2 = new Fraccion(6, 6);
		//		
		//		if(c1.equals(c2)){
		//			System.out.println("Son iguales");
		//		}

		//		Coloreo coloreo = new ColoreoMonocromatico();
		//		Coloreo coloreo = new ColoreoModulo();
		//		Coloreo coloreo = new ColoreoFranja();
		//		Paleta coloreo = new ColoreoRGB();
		//		Coloreo coloreo = new ColoreoLinea(45, 5);
		//		Coloreo coloreo = new ColoreoFuego();

		//		Conjunto conjunto = new ConjuntoMandelbrot(2);
		//		Conjunto conjunto = new ConjuntoJulia(f);
		//		g = new Grafico(this, conjunto, coloreo);

		// Grafico de Julia (primero)
		//		g.calcular();
		//		g.graficar(lblImagen);
//		Afines.cargarAfines();
//		HelechoBransley helecho = new HelechoBransley(this);
//
//		helecho.calcular();
//		//		helecho.verificar();
//		helecho.graficar(lblImagen);
//
//		initFractalComplejos();
//		graficar();
	}

	private void initFractalComplejos() {
		double xMin = -2;
		double xMax = 2;
		double yMin = -2;
		double yMax = 2;

		int width = 600;
		int height = 598;

		g = new Grafico(this, new Complejo(xMin, yMin), new Complejo(xMax, yMax), width, height);

		g.setThreads(10);
//		g.setAceleracionMover(true);
	}

	private void agregarPanelOpciones() {
		panelOpciones = new JPanel();
		scrollPaneOpciones.setViewportView(panelOpciones);
		GridBagLayout gbl_panelOpciones = new GridBagLayout();
		gbl_panelOpciones.columnWidths = new int[]{39, 0};
		gbl_panelOpciones.rowHeights = new int[]{20, 0, 0, 0};
		gbl_panelOpciones.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelOpciones.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelOpciones.setLayout(gbl_panelOpciones);

		panelCoordenadas = new JPanel();
		panelCoordenadas.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Coordenadas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panelCoordenadas = new GridBagConstraints();
		gbc_panelCoordenadas.fill = GridBagConstraints.BOTH;
		gbc_panelCoordenadas.insets = new Insets(5, 5, 5, 5);
		gbc_panelCoordenadas.gridx = 0;
		gbc_panelCoordenadas.gridy = 0;
		panelOpciones.add(panelCoordenadas, gbc_panelCoordenadas);
		GridBagLayout gbl_panelCoordenadas = new GridBagLayout();
		gbl_panelCoordenadas.columnWidths = new int[]{39, 30, 24, 0};
		gbl_panelCoordenadas.rowHeights = new int[]{20, 0, 0, 0, 0, 0};
		gbl_panelCoordenadas.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelCoordenadas.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCoordenadas.setLayout(gbl_panelCoordenadas);

		lblRangoEjeX = new JLabel("Rango eje Real:");
		GridBagConstraints gbc_lblRangoEjeX = new GridBagConstraints();
		gbc_lblRangoEjeX.anchor = GridBagConstraints.WEST;
		gbc_lblRangoEjeX.gridwidth = 3;
		gbc_lblRangoEjeX.insets = new Insets(5, 5, 5, 5);
		gbc_lblRangoEjeX.gridx = 0;
		gbc_lblRangoEjeX.gridy = 0;
		panelCoordenadas.add(lblRangoEjeX, gbc_lblRangoEjeX);

		spinXMin = new JSpinner();
		GridBagConstraints gbc_spinXMin = new GridBagConstraints();
		gbc_spinXMin.anchor = GridBagConstraints.NORTH;
		gbc_spinXMin.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinXMin.insets = new Insets(5, 5, 5, 5);
		gbc_spinXMin.gridx = 0;
		gbc_spinXMin.gridy = 1;
		panelCoordenadas.add(spinXMin, gbc_spinXMin);
		spinXMin.setModel(new SpinnerNumberModel(-1.0, -2.0, 2.0, 0.01));

		lblA = new JLabel("a");
		GridBagConstraints gbc_lblA = new GridBagConstraints();
		gbc_lblA.insets = new Insets(5, 0, 5, 0);
		gbc_lblA.gridx = 1;
		gbc_lblA.gridy = 1;
		panelCoordenadas.add(lblA, gbc_lblA);

		spinXMax = new JSpinner();
		GridBagConstraints gbc_spinXMax = new GridBagConstraints();
		gbc_spinXMax.insets = new Insets(5, 5, 5, 5);
		gbc_spinXMax.anchor = GridBagConstraints.NORTH;
		gbc_spinXMax.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinXMax.gridx = 2;
		gbc_spinXMax.gridy = 1;
		panelCoordenadas.add(spinXMax, gbc_spinXMax);
		spinXMax.setModel(new SpinnerNumberModel(1.0, -2.0, 2.0, 0.01));

		lblRangoEjeY = new JLabel("Rango eje Imaginario:");
		GridBagConstraints gbc_lblRangoEjeY = new GridBagConstraints();
		gbc_lblRangoEjeY.anchor = GridBagConstraints.WEST;
		gbc_lblRangoEjeY.gridwidth = 3;
		gbc_lblRangoEjeY.insets = new Insets(5, 5, 5, 5);
		gbc_lblRangoEjeY.gridx = 0;
		gbc_lblRangoEjeY.gridy = 2;
		panelCoordenadas.add(lblRangoEjeY, gbc_lblRangoEjeY);

		spinYMin = new JSpinner();
		GridBagConstraints gbc_spinYMin = new GridBagConstraints();
		gbc_spinYMin.anchor = GridBagConstraints.NORTH;
		gbc_spinYMin.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinYMin.insets = new Insets(5, 5, 5, 5);
		gbc_spinYMin.gridx = 0;
		gbc_spinYMin.gridy = 3;
		panelCoordenadas.add(spinYMin, gbc_spinYMin);
		spinYMin.setModel(new SpinnerNumberModel(-2.0, -2.0, 2.0, 0.01));

		lblA_1 = new JLabel("a");
		GridBagConstraints gbc_lblA_1 = new GridBagConstraints();
		gbc_lblA_1.insets = new Insets(5, 0, 5, 0);
		gbc_lblA_1.gridx = 1;
		gbc_lblA_1.gridy = 3;
		panelCoordenadas.add(lblA_1, gbc_lblA_1);

		spinYMax = new JSpinner();
		GridBagConstraints gbc_spinYMax = new GridBagConstraints();
		gbc_spinYMax.anchor = GridBagConstraints.NORTH;
		gbc_spinYMax.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinYMax.insets = new Insets(5, 5, 5, 5);
		gbc_spinYMax.gridx = 2;
		gbc_spinYMax.gridy = 3;
		panelCoordenadas.add(spinYMax, gbc_spinYMax);
		spinYMax.setModel(new SpinnerNumberModel(2.0, -2.0, 2.0, 0.01));

		btnGraficar = new JButton("Graficar");
		GridBagConstraints gbc_btnGraficar = new GridBagConstraints();
		gbc_btnGraficar.gridwidth = 3;
		gbc_btnGraficar.insets = new Insets(10, 0, 5, 0);
		gbc_btnGraficar.gridx = 0;
		gbc_btnGraficar.gridy = 4;
		panelCoordenadas.add(btnGraficar, gbc_btnGraficar);
		btnGraficar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				graficar();
			}
		});

		panelAcciones = new JPanel();
		panelAcciones.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Acciones", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panelAcciones = new GridBagConstraints();
		gbc_panelAcciones.fill = GridBagConstraints.BOTH;
		gbc_panelAcciones.insets = new Insets(5, 5, 5, 5);
		gbc_panelAcciones.gridx = 0;
		gbc_panelAcciones.gridy = 1;
		panelOpciones.add(panelAcciones, gbc_panelAcciones);
		GridBagLayout gbl_panelAcciones = new GridBagLayout();
		gbl_panelAcciones.columnWidths = new int[]{39, 0};
		gbl_panelAcciones.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelAcciones.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelAcciones.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelAcciones.setLayout(gbl_panelAcciones);

		chckbxMover = new JCheckBox("Mover");
		GridBagConstraints gbc_chckbxMover = new GridBagConstraints();
		gbc_chckbxMover.anchor = GridBagConstraints.WEST;
		gbc_chckbxMover.insets = new Insets(5, 15, 5, 0);
		gbc_chckbxMover.gridx = 0;
		gbc_chckbxMover.gridy = 0;
		panelAcciones.add(chckbxMover, gbc_chckbxMover);

		chckbxZoom = new JCheckBox("Zoom");
		GridBagConstraints gbc_chckbxZoom = new GridBagConstraints();
		gbc_chckbxZoom.anchor = GridBagConstraints.WEST;
		gbc_chckbxZoom.insets = new Insets(5, 15, 5, 0);
		gbc_chckbxZoom.gridx = 0;
		gbc_chckbxZoom.gridy = 1;
		panelAcciones.add(chckbxZoom, gbc_chckbxZoom);

		lblPuntoactual = new JLabel("PuntoActual");
		GridBagConstraints gbc_lblPuntoactual = new GridBagConstraints();
		gbc_lblPuntoactual.gridx = 0;
		gbc_lblPuntoactual.gridy = 2;
		panelAcciones.add(lblPuntoactual, gbc_lblPuntoactual);
		chckbxZoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxMover.isSelected() && chckbxZoom.isSelected()){
					chckbxMover.setSelected(false);
					mouseHandler.setMover(false);
				}

				mouseHandler.setZoom(chckbxZoom.isSelected());
			}
		});
		chckbxMover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxMover.isSelected() && chckbxZoom.isSelected()){
					chckbxZoom.setSelected(false);
					mouseHandler.setZoom(false);
				}

				mouseHandler.setMover(chckbxMover.isSelected());
			}
		});

		panelProcesamiento = new JPanel();
		panelProcesamiento.setBorder(new TitledBorder(null, "Procesamiento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelProcesamiento = new GridBagConstraints();
		gbc_panelProcesamiento.insets = new Insets(5, 5, 5, 5);
		gbc_panelProcesamiento.fill = GridBagConstraints.BOTH;
		gbc_panelProcesamiento.gridx = 0;
		gbc_panelProcesamiento.gridy = 2;
		panelOpciones.add(panelProcesamiento, gbc_panelProcesamiento);
		GridBagLayout gbl_panelProcesamiento = new GridBagLayout();
		gbl_panelProcesamiento.columnWidths = new int[]{39, 24, 0};
		gbl_panelProcesamiento.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelProcesamiento.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelProcesamiento.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelProcesamiento.setLayout(gbl_panelProcesamiento);

		chckbxThread = new JCheckBox("Aceleraci\u00F3n por Threads");
		GridBagConstraints gbc_chckbxThread = new GridBagConstraints();
		gbc_chckbxThread.anchor = GridBagConstraints.WEST;
		gbc_chckbxThread.gridwidth = 2;
		gbc_chckbxThread.insets = new Insets(5, 15, 5, 0);
		gbc_chckbxThread.gridx = 0;
		gbc_chckbxThread.gridy = 0;
		panelProcesamiento.add(chckbxThread, gbc_chckbxThread);
		chckbxThread.setSelected(true);

		spinThreads = new JSpinner();
		GridBagConstraints gbc_spinThreads = new GridBagConstraints();
		gbc_spinThreads.anchor = GridBagConstraints.WEST;
		gbc_spinThreads.insets = new Insets(5, 15, 5, 5);
		gbc_spinThreads.gridx = 0;
		gbc_spinThreads.gridy = 1;
		panelProcesamiento.add(spinThreads, gbc_spinThreads);
		spinThreads.setModel(new SpinnerNumberModel(10, 1, 50, 1));

		lblThreads = new JLabel("Threads");
		GridBagConstraints gbc_lblThreads = new GridBagConstraints();
		gbc_lblThreads.anchor = GridBagConstraints.WEST;
		gbc_lblThreads.insets = new Insets(5, 5, 5, 0);
		gbc_lblThreads.gridx = 1;
		gbc_lblThreads.gridy = 1;
		panelProcesamiento.add(lblThreads, gbc_lblThreads);

		chckbxAceleracinDeReclculo = new JCheckBox("Aceleraci\u00F3n de rec\u00E1lculo");
		chckbxAceleracinDeReclculo.setEnabled(false);
		chckbxAceleracinDeReclculo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
//				g.setAceleracionMover(chckbxAceleracinDeReclculo.isSelected());
			}
		});
		chckbxAceleracinDeReclculo.setSelected(true);
		GridBagConstraints gbc_chckbxAceleracinDeReclculo = new GridBagConstraints();
		gbc_chckbxAceleracinDeReclculo.anchor = GridBagConstraints.WEST;
		gbc_chckbxAceleracinDeReclculo.gridwidth = 2;
		gbc_chckbxAceleracinDeReclculo.insets = new Insets(5, 15, 5, 5);
		gbc_chckbxAceleracinDeReclculo.gridx = 0;
		gbc_chckbxAceleracinDeReclculo.gridy = 2;
		panelProcesamiento.add(chckbxAceleracinDeReclculo, gbc_chckbxAceleracinDeReclculo);

		lblTiempoprocesamiento = new JLabel();
		GridBagConstraints gbc_lblTiempoprocesamiento = new GridBagConstraints();
		gbc_lblTiempoprocesamiento.gridwidth = 2;
		gbc_lblTiempoprocesamiento.insets = new Insets(5, 5, 0, 0);
		gbc_lblTiempoprocesamiento.gridx = 0;
		gbc_lblTiempoprocesamiento.gridy = 3;
		panelProcesamiento.add(lblTiempoprocesamiento, gbc_lblTiempoprocesamiento);
		spinThreads.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(chckbxThread.isSelected()){
					g.setThreads((int) spinThreads.getValue());					
				}
			}
		});
		chckbxThread.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Active");
				if (chckbxThread.isSelected()){
					spinThreads.setEnabled(true);
					g.setThreads((int) spinThreads.getValue());					
				}else{
					spinThreads.setEnabled(false);
					g.setThreads(1);
				}
			}
		});
	}

	// Grafico mediante coordenadas
	protected void graficar() {
		double xMin = (double) spinXMin.getValue();
		double xMax = (double) spinXMax.getValue();
		double yMin = (double) spinYMin.getValue();
		double yMax = (double) spinYMax.getValue();

		g.setRango(xMin, xMax, yMin, yMax);		
		actualizarInterfaz();
	}

	// Grafico mediante accion Mover (MouseMove)
	public void mover(int f, int c){
		g.mover(f, c);
		actualizarInterfaz();
	}

	// Grafico mediante accion Zoom (MousePressed y MouseRelease)
	public void zoom(int fMin, int fMax, int cMin, int cMax){
		g.zoom(fMin, fMax, cMin, cMax);		
		actualizarInterfaz();
	}

	private void actualizarInterfaz() {
		// Renderizar imagen
		g.graficar(lblImagen);

		// Rango de ejes
		spinXMin.setValue(g.getxMin());
		spinXMax.setValue(g.getxMax());
		spinYMin.setValue(g.getyMin());
		spinYMax.setValue(g.getyMax());

		// Tiempo de procesamiento
		lblTiempoprocesamiento.setText("Tiempo de Procesamiento: " + g.getTiempoProcesamiento() + "ms");	

	}

	public void setPosicionActual(int f, int c) {
		double x = g.getCtoX(c);
		double y = g.getFtoY(f);

		lblPuntoactual.setText("(" + NumberFormat.getNumberInstance().format(x) + ", " + NumberFormat.getNumberInstance().format(y) + ")");
	}

}