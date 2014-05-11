package swing;

import grafico.Grafico;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import afin.GraficoAfines;
import basic.Complejo;
import conjunto.ConjuntoJulia;
import conjunto.ConjuntoMandelbrot;
import conjunto.GraficoConjuntos;

public class Fractales extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private PanelImagen lblImagen;
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
	private JPanel panelGrafico;
	private JComboBox comboFractal;

	private int width;

	private int height;

	private GraficoConjuntos gConjuntos;
	private GraficoAfines gAfines;
	private Grafico grafico;
	
	private JCheckBox chckbxMovimInteractivo;
	private JCheckBox chckbxAnimarTransicion;

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

		initMenu();
		initPanelImagen();
		initPanelOpciones();

		width = 600;
		height = 600;		
		Grafico.setFrame(this, width, height);
		
		initFractales();

//      Menu -> Exportar, etc.
//		Agregar otros fractales
//		Cantidad de puntos a pintar en el afin
//		5) Go back
//		6) Barra de progreso
	    
//		grafico = gAfines;
		grafico = gConjuntos;
		actualizarInterfaz();
	}

	private void initFractales() {		
		gConjuntos = new GraficoConjuntos();
		gConjuntos.setConjunto(ConjuntoJulia.SAMPLE_6);
		gConjuntos.setThreads(10);
		gConjuntos.setRango(-2, 2, -2, 2);
		
		gAfines = GraficoAfines.SIERPINSKI;
		gAfines.setRango(-400, 400, 0, 400);
				
//		// Conjuntos de Julia:
//		int grado = 2;
//		double real = -0.391;
//		double imag = 0.587;
//		Funcion f = new FuncionPolinomica(grado, new Complejo(real, imag));
//		g.setConjunto(new ConjuntoJulia(f));
		
//		// Conjuntos Mandelbrot:
//		int exp = 2;
//		g.setConjunto(new ConjuntoMandelbrot(exp));
		
//		Conjuntos conexos (conjuntos de Fatou) y Conjuntos no conexos (conjuntos de Cantor).

	}
	
	private void initMenu() {
		
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
		gbl_contentPane.columnWidths = new int[]{400, 230, 0};
		gbl_contentPane.rowHeights = new int[] {579, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		
	}
	
	private void initPanelImagen() {
		
		lblImagen = new PanelImagen();
		GridBagConstraints gbc_lblImagen = new GridBagConstraints();
		gbc_lblImagen.fill = GridBagConstraints.VERTICAL;
		gbc_lblImagen.insets = new Insets(0, 0, 5, 5);
		gbc_lblImagen.gridx = 0;
		gbc_lblImagen.gridy = 0;
		contentPane.add(lblImagen, gbc_lblImagen);
		mouseHandler = new MouseHandler(this);
		lblImagen.addMouseListener(mouseHandler);
		lblImagen.addMouseMotionListener(mouseHandler);
		
	}

	private void initPanelOpciones() {
		// Panel de Scroll de Opciones
		scrollPaneOpciones = new JScrollPane();
		scrollPaneOpciones.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPaneOpciones = new GridBagConstraints();
		gbc_scrollPaneOpciones.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPaneOpciones.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneOpciones.gridx = 1;
		gbc_scrollPaneOpciones.gridy = 0;
		contentPane.add(scrollPaneOpciones, gbc_scrollPaneOpciones);
		
		// Agregar Panel
		panelOpciones = new JPanel();
		scrollPaneOpciones.setViewportView(panelOpciones);
		GridBagLayout gbl_panelOpciones = new GridBagLayout();
		gbl_panelOpciones.columnWidths = new int[]{39, 0};
		gbl_panelOpciones.rowHeights = new int[]{0, 20, 0, 0, 0};
		gbl_panelOpciones.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelOpciones.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelOpciones.setLayout(gbl_panelOpciones);
		
		addPanelGrafico();

		addPanelCoordenadas();

		addPanelAcciones();
		
		addPanelProcesamiento();

//		chckbxAceleracinDeReclculo = new JCheckBox("Aceleraci\u00F3n de rec\u00E1lculo");
//		chckbxAceleracinDeReclculo.setEnabled(false);
//		chckbxAceleracinDeReclculo.addChangeListener(new ChangeListener() {
//			public void stateChanged(ChangeEvent arg0) {
////				g.setAceleracionMover(chckbxAceleracinDeReclculo.isSelected());
//			}
//		});
//		chckbxAceleracinDeReclculo.setSelected(true);
//		GridBagConstraints gbc_chckbxAceleracinDeReclculo = new GridBagConstraints();
//		gbc_chckbxAceleracinDeReclculo.anchor = GridBagConstraints.WEST;
//		gbc_chckbxAceleracinDeReclculo.gridwidth = 2;
//		gbc_chckbxAceleracinDeReclculo.insets = new Insets(5, 15, 5, 5);
//		gbc_chckbxAceleracinDeReclculo.gridx = 0;
//		gbc_chckbxAceleracinDeReclculo.gridy = 2;
//		panelProcesamiento.add(chckbxAceleracinDeReclculo, gbc_chckbxAceleracinDeReclculo);

	}

	private void addPanelGrafico() {
		panelGrafico = new JPanel();
		panelGrafico.setBorder(new TitledBorder(null, "Gr\u00E1fico", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelGrafico = new GridBagConstraints();
		gbc_panelGrafico.insets = new Insets(5, 5, 5, 0);
		gbc_panelGrafico.fill = GridBagConstraints.BOTH;
		gbc_panelGrafico.gridx = 0;
		gbc_panelGrafico.gridy = 0;
		panelOpciones.add(panelGrafico, gbc_panelGrafico);
		GridBagLayout gbl_panelGrafico = new GridBagLayout();
		gbl_panelGrafico.columnWidths = new int[]{0, 0};
		gbl_panelGrafico.rowHeights = new int[]{0, 0};
		gbl_panelGrafico.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelGrafico.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelGrafico.setLayout(gbl_panelGrafico);
		
		comboFractal = new JComboBox();
		comboFractal.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == 1){
					String st = arg0.getItem().toString();
					switch (st) {
					case "Conjunto de Mandelbrot":
						gConjuntos.setConjunto(ConjuntoMandelbrot.CUADRATICO);
						break;
					case "Disco de Siegel":
						gConjuntos.setConjunto(ConjuntoJulia.DISCO_SIEGEL);
						break;
					case "Fractal Dendrita":
						gConjuntos.setConjunto(ConjuntoJulia.DENDRITA);
						break;
					case "Conejo de Douady":
						gConjuntos.setConjunto(ConjuntoJulia.CONEJO_DOUADY);
						break;
					default:
						break;
					}
					gConjuntos.setRango(-2, 2, -2, 2);
					actualizarInterfaz();
				}
			}
		});
		comboFractal.setModel(new DefaultComboBoxModel(new String[] {"Conjunto de Mandelbrot", "Disco de Siegel", "Fractal Dendrita", "Conejo de Douady"}));
		comboFractal.setSelectedIndex(0);
		GridBagConstraints gbc_comboFractal = new GridBagConstraints();
		gbc_comboFractal.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboFractal.gridx = 0;
		gbc_comboFractal.gridy = 0;
		panelGrafico.add(comboFractal, gbc_comboFractal);
		
	}

	private void addPanelCoordenadas() {
		panelCoordenadas = new JPanel();
		panelCoordenadas.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Coordenadas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panelCoordenadas = new GridBagConstraints();
		gbc_panelCoordenadas.fill = GridBagConstraints.BOTH;
		gbc_panelCoordenadas.insets = new Insets(5, 5, 5, 0);
		gbc_panelCoordenadas.gridx = 0;
		gbc_panelCoordenadas.gridy = 1;
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
		spinXMin.setModel(new SpinnerNumberModel(-2.0, -2.0, 2.0, 0.01));

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
		spinXMax.setModel(new SpinnerNumberModel(2.0, -2.0, 2.0, 0.01));

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
		
	}

	private void addPanelAcciones() {
		// Agregar Panel
		panelAcciones = new JPanel();
		panelAcciones.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Acciones", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panelAcciones = new GridBagConstraints();
		gbc_panelAcciones.fill = GridBagConstraints.BOTH;
		gbc_panelAcciones.insets = new Insets(5, 5, 5, 0);
		gbc_panelAcciones.gridx = 0;
		gbc_panelAcciones.gridy = 2;
		panelOpciones.add(panelAcciones, gbc_panelAcciones);
		
		// Set Layout
		GridBagLayout gbl_panelAcciones = new GridBagLayout();
		gbl_panelAcciones.columnWidths = new int[]{39, 0};
		gbl_panelAcciones.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelAcciones.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelAcciones.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelAcciones.setLayout(gbl_panelAcciones);

		// CheckBox Mover
		chckbxMover = new JCheckBox("Mover");
		GridBagConstraints gbc_chckbxMover = new GridBagConstraints();
		gbc_chckbxMover.anchor = GridBagConstraints.WEST;
		gbc_chckbxMover.insets = new Insets(5, 15, 0, 0);
		gbc_chckbxMover.gridx = 0;
		gbc_chckbxMover.gridy = 0;
		panelAcciones.add(chckbxMover, gbc_chckbxMover);
		chckbxMover.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		    	// Evitar que queden seleccionados ambos
		    	if(e.getStateChange() == ItemEvent.SELECTED){
		    		chckbxZoom.setSelected(false);
		    	}
		    	mouseHandler.setMover(e.getStateChange() == ItemEvent.SELECTED);
		    	chckbxMovimInteractivo.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
		    }
		});
		
		// CheckBox Mover Interactivo
		chckbxMovimInteractivo = new JCheckBox("Movimiento Interactivo");
		chckbxMovimInteractivo.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		    	mouseHandler.setMoverInteractivo(e.getStateChange() == ItemEvent.SELECTED);
		    }
		});
		chckbxMovimInteractivo.setSelected(true);
		chckbxMovimInteractivo.setEnabled(false);
		GridBagConstraints gbc_chckbxMovimientointeractivo = new GridBagConstraints();
		gbc_chckbxMovimientointeractivo.anchor = GridBagConstraints.WEST;
		gbc_chckbxMovimientointeractivo.insets = new Insets(0, 40, 5, 0);
		gbc_chckbxMovimientointeractivo.gridx = 0;
		gbc_chckbxMovimientointeractivo.gridy = 1;
		panelAcciones.add(chckbxMovimInteractivo, gbc_chckbxMovimientointeractivo);
		
		// CheckBox Zoom
		chckbxZoom = new JCheckBox("Zoom");
		chckbxZoom.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		    	// Evitar que queden seleccionados ambos
		    	if (e.getStateChange() == ItemEvent.SELECTED){
		    		chckbxMover.setSelected(false);
		    	}
		    	mouseHandler.setZoom(e.getStateChange() == ItemEvent.SELECTED);
		    }
		});
		chckbxZoom.setSelected(true);
		GridBagConstraints gbc_chckbxZoom = new GridBagConstraints();
		gbc_chckbxZoom.anchor = GridBagConstraints.WEST;
		gbc_chckbxZoom.insets = new Insets(5, 15, 5, 0);
		gbc_chckbxZoom.gridx = 0;
		gbc_chckbxZoom.gridy = 2;
		panelAcciones.add(chckbxZoom, gbc_chckbxZoom);
		
		// Label Punto actual
		lblPuntoactual = new JLabel("PuntoActual");
		GridBagConstraints gbc_lblPuntoactual = new GridBagConstraints();
		gbc_lblPuntoactual.gridx = 0;
		gbc_lblPuntoactual.gridy = 3;
		panelAcciones.add(lblPuntoactual, gbc_lblPuntoactual);
		
	}

	private void addPanelProcesamiento() {
		// Agregar Panel
		panelProcesamiento = new JPanel();
		panelProcesamiento.setBorder(new TitledBorder(null, "Procesamiento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelProcesamiento = new GridBagConstraints();
		gbc_panelProcesamiento.insets = new Insets(5, 5, 5, 0);
		gbc_panelProcesamiento.fill = GridBagConstraints.BOTH;
		gbc_panelProcesamiento.gridx = 0;
		gbc_panelProcesamiento.gridy = 3;
		panelOpciones.add(panelProcesamiento, gbc_panelProcesamiento);
		
		// Set Layout
		GridBagLayout gbl_panelProcesamiento = new GridBagLayout();
		gbl_panelProcesamiento.columnWidths = new int[]{39, 24, 0};
		gbl_panelProcesamiento.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelProcesamiento.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelProcesamiento.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelProcesamiento.setLayout(gbl_panelProcesamiento);
		
		// CheckBox Transicion Animada
		chckbxAnimarTransicion = new JCheckBox("Animar Transicion");
		GridBagConstraints gbc_chckbxAnimarTransicion = new GridBagConstraints();
		gbc_chckbxAnimarTransicion.anchor = GridBagConstraints.WEST;
		gbc_chckbxAnimarTransicion.gridwidth = 2;
		gbc_chckbxAnimarTransicion.insets = new Insets(5, 15, 5, 5);
		gbc_chckbxAnimarTransicion.gridx = 0;
		gbc_chckbxAnimarTransicion.gridy = 0;
		panelProcesamiento.add(chckbxAnimarTransicion, gbc_chckbxAnimarTransicion);
		
		// CheckBox de aceleracion por threads
		chckbxThread = new JCheckBox("Aceleraci\u00F3n por Threads");
		GridBagConstraints gbc_chckbxThread = new GridBagConstraints();
		gbc_chckbxThread.anchor = GridBagConstraints.WEST;
		gbc_chckbxThread.gridwidth = 2;
		gbc_chckbxThread.insets = new Insets(5, 15, 5, 0);
		gbc_chckbxThread.gridx = 0;
		gbc_chckbxThread.gridy = 1;
		panelProcesamiento.add(chckbxThread, gbc_chckbxThread);
		
		// Spinner Cantidad de threads
		spinThreads = new JSpinner();
		GridBagConstraints gbc_spinThreads = new GridBagConstraints();
		gbc_spinThreads.anchor = GridBagConstraints.WEST;
		gbc_spinThreads.insets = new Insets(5, 15, 5, 5);
		gbc_spinThreads.gridx = 0;
		gbc_spinThreads.gridy = 2;
		panelProcesamiento.add(spinThreads, gbc_spinThreads);
		spinThreads.setModel(new SpinnerNumberModel(10, 1, 50, 1));
		spinThreads.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(chckbxThread.isSelected()){
					gConjuntos.setThreads((int) spinThreads.getValue());					
				}
			}
		});
		
		// Label Threads
		lblThreads = new JLabel("Threads");
		GridBagConstraints gbc_lblThreads = new GridBagConstraints();
		gbc_lblThreads.anchor = GridBagConstraints.WEST;
		gbc_lblThreads.insets = new Insets(5, 5, 5, 0);
		gbc_lblThreads.gridx = 1;
		gbc_lblThreads.gridy = 2;
		panelProcesamiento.add(lblThreads, gbc_lblThreads);
		chckbxThread.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Active");
				if (chckbxThread.isSelected()){
					spinThreads.setEnabled(true);
					gConjuntos.setThreads((int) spinThreads.getValue());					
				}else{
					spinThreads.setEnabled(false);
					gConjuntos.setThreads(1);
				}
			}
		});
		
		// Label Tiempo de procesamiento
		lblTiempoprocesamiento = new JLabel();
		lblTiempoprocesamiento.setText("TiempoProcesamiento");
		GridBagConstraints gbc_lblTiempoprocesamiento = new GridBagConstraints();
		gbc_lblTiempoprocesamiento.gridwidth = 2;
		gbc_lblTiempoprocesamiento.insets = new Insets(5, 5, 0, 0);
		gbc_lblTiempoprocesamiento.gridx = 0;
		gbc_lblTiempoprocesamiento.gridy = 4;
		panelProcesamiento.add(lblTiempoprocesamiento, gbc_lblTiempoprocesamiento);
		
		// Valores por defecto
		chckbxThread.setSelected(true);
	}

	// Grafico mediante coordenadas
	protected void graficar() {
		double xMin = (double) spinXMin.getValue();
		double xMax = (double) spinXMax.getValue();
		double yMin = (double) spinYMin.getValue();
		double yMax = (double) spinYMax.getValue();

		grafico.setRango(xMin, xMax, yMin, yMax);		
		actualizarInterfaz();
	}

	// Grafico mediante accion Mover (MouseDragged)
	public void mover(int f, int c){
		grafico.mover(f, c);
		actualizarInterfaz();
	}

	// Grafico mediante accion Zoom (MousePressed y MouseRelease)
	public void zoom(int fMin, int fMax, int cMin, int cMax){
		grafico.zoom(fMin, fMax, cMin, cMax);		
		actualizarInterfaz();
	}

	private void actualizarInterfaz(){
		boolean interpolar = ! (chckbxMover.isSelected() && chckbxMovimInteractivo.isSelected()) && chckbxAnimarTransicion.isSelected();
		
		// Renderizar imagen
		Image im = grafico.generarImagen();
		lblImagen.setImagen(im, interpolar);
//	    lblImagen.setIcon(new ImageIcon(im));
//	    lblImagen.setMaximumSize(new Dimension(width, height));

		// Rango de ejes
		spinXMin.setValue(grafico.getxMin());
		spinXMax.setValue(grafico.getxMax());
		spinYMin.setValue(grafico.getyMin());
		spinYMax.setValue(grafico.getyMax());

		// Tiempo de procesamiento
		lblTiempoprocesamiento.setText("Tiempo de Procesamiento: " + gConjuntos.getTiempoProcesamiento() + "ms");
	}

	// Actualizar posicion del puntero (MouseMove)
	public void setPosicionActual(int f, int c) {
		double x = grafico.getCtoX(c);
		double y = grafico.getFtoY(f);

		lblPuntoactual.setText("(" + NumberFormat.getNumberInstance().format(x) + ", " + NumberFormat.getNumberInstance().format(y) + ")");
	}
	
	public PanelImagen getImagen() {
		return lblImagen;
	}
	
}