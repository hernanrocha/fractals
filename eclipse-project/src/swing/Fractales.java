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
		setBounds(100, 100, 620, 650);
		
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
		gbl_contentPane.rowHeights = new int[]{579, 23, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
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
		
		JButton btnAumentar = new JButton("Aumentar");
		btnAumentar.setEnabled(false);
		btnAumentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Fractal: Alfombra de Sierpinski
//				f = new FractalCompuesto(f);
//				generarImagen();
				
				// Conjunto de Mandelbrot
//				ConjuntoMandelbrot con = new ConjuntoMandelbrot();
//				System.out.println("Res " + con.iterar(new Complejo()));
				
				// Grafico de Mandelbrot
//				g.zoomIn(new Punto(1, 1));
//				g.calcular();
//				g.graficar(lblImagen);
				
//				Fraccion f1 = new Fraccion(1, 1);
//				Fraccion f2 = new Fraccion(4, 5);
//				ComplejoFraccion c1 = new ComplejoFraccion(f1, f2);
//				
//				Fraccion f3 = new Fraccion(6, 6);
//				Fraccion f4 = new Fraccion(8, 10);
//				ComplejoFraccion c2 = new ComplejoFraccion(f3, f4);
//				
//				if(c1.equals(c2)){
//					System.out.println("Son iguales");
//				}
//				
//				MatrizHash matriz = new MatrizHash();
//				
//				matriz.put(c1, 5);
//				System.out.println(matriz.get(c2));
								
			}
		});
		
		scrollPaneOpciones = new JScrollPane();
		GridBagConstraints gbc_scrollPaneOpciones = new GridBagConstraints();
		gbc_scrollPaneOpciones.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneOpciones.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneOpciones.gridx = 1;
		gbc_scrollPaneOpciones.gridy = 0;
		contentPane.add(scrollPaneOpciones, gbc_scrollPaneOpciones);
		
		agregarPanelOpciones();
		
		GridBagConstraints gbc_btnAumentar = new GridBagConstraints();
		gbc_btnAumentar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAumentar.anchor = GridBagConstraints.NORTH;
		gbc_btnAumentar.gridx = 0;
		gbc_btnAumentar.gridy = 1;
		contentPane.add(btnAumentar, gbc_btnAumentar);
		
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
		
		Conjunto conjunto = new ConjuntoMandelbrot(2);
//		Conjunto conjunto = new ConjuntoJulia(f);
//		g = new Grafico(this, conjunto, coloreo);
		
		// Grafico de Julia (primero)
//		g.calcular();
//		g.graficar(lblImagen);
		Afines.cargarAfines();
		HelechoBransley helecho = new HelechoBransley(this);
		
		helecho.calcular();
		//		helecho.verificar();
		helecho.graficar(lblImagen);

		initFractalComplejos();
	}
	
	private void initFractalComplejos() {
		double xMin = (double) spinXMin.getValue();
		double xMax = (double) spinXMax.getValue();
		double yMin = (double) spinYMin.getValue();
		double yMax = (double) spinYMax.getValue();

		int width = 600;
		int height = 598;

		g = new Grafico(this, new Complejo(xMin, yMin), new Complejo(xMax, yMax), width, height);

	}

	private void agregarPanelOpciones() {
		panelOpciones = new JPanel();
		scrollPaneOpciones.setViewportView(panelOpciones);
		GridBagLayout gbl_panelOpciones = new GridBagLayout();
		gbl_panelOpciones.columnWidths = new int[]{39, 30, 24, 0};
		gbl_panelOpciones.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelOpciones.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelOpciones.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelOpciones.setLayout(gbl_panelOpciones);
		
		lblRangoEjeX = new JLabel("Rango eje Re:");
		GridBagConstraints gbc_lblRangoEjeX = new GridBagConstraints();
		gbc_lblRangoEjeX.anchor = GridBagConstraints.WEST;
		gbc_lblRangoEjeX.gridwidth = 3;
		gbc_lblRangoEjeX.insets = new Insets(5, 5, 5, 0);
		gbc_lblRangoEjeX.gridx = 0;
		gbc_lblRangoEjeX.gridy = 0;
		panelOpciones.add(lblRangoEjeX, gbc_lblRangoEjeX);
		
		spinXMin = new JSpinner();
		spinXMin.setModel(new SpinnerNumberModel(-1.0, -2.0, 2.0, 0.01));
		GridBagConstraints gbc_spinXMin = new GridBagConstraints();
		gbc_spinXMin.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinXMin.anchor = GridBagConstraints.NORTH;
		gbc_spinXMin.insets = new Insets(5, 5, 5, 5);
		gbc_spinXMin.gridx = 0;
		gbc_spinXMin.gridy = 1;
		panelOpciones.add(spinXMin, gbc_spinXMin);
		
		lblA = new JLabel("a");
		GridBagConstraints gbc_lblA = new GridBagConstraints();
		gbc_lblA.insets = new Insets(5, 0, 5, 5);
		gbc_lblA.gridx = 1;
		gbc_lblA.gridy = 1;
		panelOpciones.add(lblA, gbc_lblA);
		
		spinXMax = new JSpinner();
		spinXMax.setModel(new SpinnerNumberModel(1.0, -2.0, 2.0, 0.01));
		GridBagConstraints gbc_spinXMax = new GridBagConstraints();
		gbc_spinXMax.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinXMax.anchor = GridBagConstraints.NORTH;
		gbc_spinXMax.insets = new Insets(5, 5, 5, 0);
		gbc_spinXMax.gridx = 2;
		gbc_spinXMax.gridy = 1;
		panelOpciones.add(spinXMax, gbc_spinXMax);
		
		lblRangoEjeY = new JLabel("Rango eje Im:");
		GridBagConstraints gbc_lblRangoEjeY = new GridBagConstraints();
		gbc_lblRangoEjeY.anchor = GridBagConstraints.WEST;
		gbc_lblRangoEjeY.gridwidth = 3;
		gbc_lblRangoEjeY.insets = new Insets(5, 5, 5, 0);
		gbc_lblRangoEjeY.gridx = 0;
		gbc_lblRangoEjeY.gridy = 2;
		panelOpciones.add(lblRangoEjeY, gbc_lblRangoEjeY);
		
		spinYMin = new JSpinner();
		spinYMin.setModel(new SpinnerNumberModel(-2.0, -2.0, 2.0, 0.01));
		GridBagConstraints gbc_spinYMin = new GridBagConstraints();
		gbc_spinYMin.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinYMin.anchor = GridBagConstraints.NORTH;
		gbc_spinYMin.insets = new Insets(5, 5, 5, 5);
		gbc_spinYMin.gridx = 0;
		gbc_spinYMin.gridy = 3;
		panelOpciones.add(spinYMin, gbc_spinYMin);
		
		lblA_1 = new JLabel("a");
		GridBagConstraints gbc_lblA_1 = new GridBagConstraints();
		gbc_lblA_1.insets = new Insets(5, 0, 5, 5);
		gbc_lblA_1.gridx = 1;
		gbc_lblA_1.gridy = 3;
		panelOpciones.add(lblA_1, gbc_lblA_1);
		
		spinYMax = new JSpinner();
		spinYMax.setModel(new SpinnerNumberModel(2.0, -2.0, 2.0, 0.01));
		GridBagConstraints gbc_spinYMax = new GridBagConstraints();
		gbc_spinYMax.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinYMax.insets = new Insets(5, 5, 5, 0);
		gbc_spinYMax.anchor = GridBagConstraints.NORTH;
		gbc_spinYMax.gridx = 2;
		gbc_spinYMax.gridy = 3;
		panelOpciones.add(spinYMax, gbc_spinYMax);		
		
		btnGraficar = new JButton("Graficar");
		btnGraficar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				graficar();
			}
		});

		chckbxMover = new JCheckBox("Mover");
		chckbxMover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxMover.isSelected() && chckbxZoom.isSelected()){
					chckbxZoom.setSelected(false);
					mouseHandler.setZoom(false);
				}

				mouseHandler.setMover(chckbxMover.isSelected());
			}
		});

		GridBagConstraints gbc_chckbxMover = new GridBagConstraints();
		gbc_chckbxMover.gridwidth = 3;
		gbc_chckbxMover.anchor = GridBagConstraints.WEST;
		gbc_chckbxMover.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxMover.gridx = 0;
		gbc_chckbxMover.gridy = 5;
		panelOpciones.add(chckbxMover, gbc_chckbxMover);
		
		chckbxZoom = new JCheckBox("Zoom");
		chckbxZoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxMover.isSelected() && chckbxZoom.isSelected()){
					chckbxMover.setSelected(false);
					mouseHandler.setMover(false);
				}
				
				mouseHandler.setZoom(chckbxZoom.isSelected());
			}
		});

		GridBagConstraints gbc_chckbxZoom = new GridBagConstraints();
		gbc_chckbxZoom.gridwidth = 3;
		gbc_chckbxZoom.anchor = GridBagConstraints.WEST;
		gbc_chckbxZoom.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxZoom.gridx = 0;
		gbc_chckbxZoom.gridy = 6;
		panelOpciones.add(chckbxZoom, gbc_chckbxZoom);
		
		lblPuntoactual = new JLabel("PuntoActual");
		GridBagConstraints gbc_lblPuntoactual = new GridBagConstraints();
		gbc_lblPuntoactual.gridwidth = 3;
		gbc_lblPuntoactual.insets = new Insets(0, 0, 5, 0);
		gbc_lblPuntoactual.gridx = 0;
		gbc_lblPuntoactual.gridy = 7;
		panelOpciones.add(lblPuntoactual, gbc_lblPuntoactual);
		GridBagConstraints gbc_btnGraficar = new GridBagConstraints();
		gbc_btnGraficar.insets = new Insets(0, 0, 5, 0);
		gbc_btnGraficar.gridwidth = 3;
		gbc_btnGraficar.gridx = 0;
		gbc_btnGraficar.gridy = 8;
		panelOpciones.add(btnGraficar, gbc_btnGraficar);
		
		chckbxThread = new JCheckBox("Aceleracion por Threads");
		chckbxThread.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g.setThreads(10);
			}
		});
		GridBagConstraints gbc_chckbxThread = new GridBagConstraints();
		gbc_chckbxThread.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxThread.anchor = GridBagConstraints.WEST;
		gbc_chckbxThread.gridwidth = 3;
		gbc_chckbxThread.gridx = 0;
		gbc_chckbxThread.gridy = 9;
		panelOpciones.add(chckbxThread, gbc_chckbxThread);
		
		lblTiempoprocesamiento = new JLabel();
		GridBagConstraints gbc_lblTiempoprocesamiento = new GridBagConstraints();
		gbc_lblTiempoprocesamiento.anchor = GridBagConstraints.WEST;
		gbc_lblTiempoprocesamiento.gridwidth = 3;
		gbc_lblTiempoprocesamiento.insets = new Insets(0, 0, 0, 5);
		gbc_lblTiempoprocesamiento.gridx = 0;
		gbc_lblTiempoprocesamiento.gridy = 10;
		panelOpciones.add(lblTiempoprocesamiento, gbc_lblTiempoprocesamiento);
	}

	protected void graficar() {
		g.calcular();
		
		actualizarInterfaz();
	}
	
	public void mover(int f, int c){
		g.mover(f, c);
		
		actualizarInterfaz();
	}
	
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
		
		lblPuntoactual.setText("(" + x + ", " + y + ")");
	}

}