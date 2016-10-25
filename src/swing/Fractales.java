package swing;

import filtro.FiltroArchivoPNG;
import grafico.Grafico;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import afin.GraficoAfines;
import conjunto.ConjuntoJulia;
import conjunto.ConjuntoMandelbrot;
import conjunto.GraficoConjuntos;
import java.awt.Toolkit;

public class Fractales extends JFrame {

	private static final long serialVersionUID = 1L;
	
	// Ventana Principal
	private JPanel contentPane;
	
	// Menu Bar
	private JMenuBar menuBar;
	private JMenu mnArchivo;
	private JMenu mnAyuda;
	private JMenuItem mntmGuardar;
	private JMenuItem mntmSalir;
	private JSeparator separator;
	private JMenuItem mntmAcercaDe;
	
	// Panel Imagen
	private PanelImagen lblImagen;
	
	// Panel Opciones
	private JPanel panelOpciones;
	private JScrollPane scrollPaneOpciones;
	
	// Panel Opciones - Panel Fractal
	private JPanel panelFractal;
	private JComboBox<String> comboTipo;
	private JComboBox<String> comboFractal;
	private DefaultComboBoxModel<String> modeloFractalesComplejos;
	private DefaultComboBoxModel<String> modeloFractalesAfines;
	private JSlider sliderProfundidad;
	private GridBagConstraints gbc_sliderProfundidad;
	
	// Panel Opciones - Panel Coordenadas
	private JPanel panelCoordenadas;
	private JLabel lblRangoEjeX;
	private JLabel lblRangoEjeY;
	private JLabel lblA;
	private JLabel lblA_1;
	private JSpinner spinXMin;
	private JSpinner spinXMax;
	private JSpinner spinYMin;
	private JSpinner spinYMax;
	private JPanel panel;
	private JButton btnGraficar;
	private JButton btnRestablecer;
	
	// Panel Opciones - Panel Acciones
	private JPanel panelAcciones;
	private JCheckBox chckbxMover;
	private JCheckBox chckbxMovimInteractivo;
	private JCheckBox chckbxZoom;
	private JLabel lblPuntoactual;
	
	// Panel Opciones - Panel Procesamiento
	private JPanel panelProcesamiento;
	private JCheckBox chckbxAnimarTransicion;
	private JLabel lblThreads;
	private JSpinner spinThreads;
	private JCheckBox chckbxThread;
	private JProgressBar progressCalculo;
	private JLabel lblTiempoprocesamiento;
	
	// Variables
	private Grafico grafico;
	private GraficoConjuntos gConjuntos;
	private GraficoAfines gAfines;
	private MouseHandler mouseHandler;
	
	private int filasProcesadas = 0;
	private boolean cargado;
	private int width = 600;
	private int height = 600;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Fractales.class.getResource("/icon/icon.png")));
		// Iniciar Ventana
		setTitle("Fractal Viewer 2.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 10, 900, 710);

		// Iniciar Paneles
		initMenu();
		initPanelImagen();
		initPanelOpciones();
		
		// Inicializar fractales
		Grafico.setFrame(this, width, height);
		
		gConjuntos = new GraficoConjuntos();
		gConjuntos.setThreads(20);
		
		actualizarFractal();
	}
	
	private void initMenu() {
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		mntmGuardar = new JMenuItem("Guardar Como");
		mntmGuardar.setIcon(new ImageIcon(Fractales.class.getResource("/icon/save.png")));
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarImagenComo();
			}
		});
		mnArchivo.add(mntmGuardar);
		
		separator = new JSeparator();
		mnArchivo.add(separator);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.setIcon(new ImageIcon(Fractales.class.getResource("/icon/close.png")));
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		mnArchivo.add(mntmSalir);

		mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		mntmAcercaDe = new JMenuItem("Acerca De");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AcercaDe acercaDe = new AcercaDe();
				acercaDe.setVisible(true);
			}
		});
		mntmAcercaDe.setIcon(new ImageIcon(Fractales.class.getResource("/icon/about.png")));
		mnAyuda.add(mntmAcercaDe);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{400, 230, 0};
		gbl_contentPane.rowHeights = new int[] {579};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0};
		contentPane.setLayout(gbl_contentPane);
		
	}
	
	private void initPanelImagen() {
		
		lblImagen = new PanelImagen();
		GridBagConstraints gbc_lblImagen = new GridBagConstraints();
		gbc_lblImagen.fill = GridBagConstraints.BOTH;
		gbc_lblImagen.insets = new Insets(0, 0, 0, 5);
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
		gbl_panelOpciones.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelOpciones.setLayout(gbl_panelOpciones);
		
		addPanelGrafico();

		addPanelCoordenadas();

		addPanelAcciones();
		
		addPanelProcesamiento();

	}

	private void addPanelGrafico() {
		panelFractal = new JPanel();
		panelFractal.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Fractal", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panelFractal = new GridBagConstraints();
		gbc_panelFractal.insets = new Insets(5, 5, 5, 0);
		gbc_panelFractal.fill = GridBagConstraints.BOTH;
		gbc_panelFractal.gridx = 0;
		gbc_panelFractal.gridy = 0;
		panelOpciones.add(panelFractal, gbc_panelFractal);
		GridBagLayout gbl_panelFractal = new GridBagLayout();
		gbl_panelFractal.columnWidths = new int[]{0, 0};
		gbl_panelFractal.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelFractal.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelFractal.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelFractal.setLayout(gbl_panelFractal);
		
		comboFractal = new JComboBox<String>();
		comboFractal.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent item) {
				if (item.getStateChange() == ItemEvent.SELECTED){
					actualizarFractal();
				}
			}
		});
		
		comboTipo = new JComboBox<String>();
		comboTipo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent item) {
				if (item.getStateChange() == ItemEvent.SELECTED){
					mostrarListaFractales((String) item.getItem());
				}
			}
		});
		comboTipo.setModel(new DefaultComboBoxModel<String>(new String[] {"Conjuntos de Complejos", "Transformaciones Afines"}));
		comboTipo.setSelectedIndex(0);
		GridBagConstraints gbc_comboTipo = new GridBagConstraints();
		gbc_comboTipo.insets = new Insets(10, 10, 5, 10);
		gbc_comboTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboTipo.gridx = 0;
		gbc_comboTipo.gridy = 0;
		panelFractal.add(comboTipo, gbc_comboTipo);
		modeloFractalesComplejos = new DefaultComboBoxModel<String>(new String[] {"Conjunto de Mandelbrot (2)", "Conjunto de Mandelbrot (5)", "Conjunto de Mandelbrot (10)", "Disco de Siegel", "Fractal Dendrita", "Conejo de Douady", "Sample 1", "Sample 2", "Sample 3"});
		modeloFractalesAfines = new DefaultComboBoxModel<String>(new String[] {"Alfombra de Sierpinski", "Helecho de Bransley"});
		comboFractal.setModel(modeloFractalesComplejos);
		comboFractal.setSelectedIndex(0);
		GridBagConstraints gbc_comboFractal = new GridBagConstraints();
		gbc_comboFractal.insets = new Insets(5, 10, 10, 10);
		gbc_comboFractal.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboFractal.gridx = 0;
		gbc_comboFractal.gridy = 1;
		panelFractal.add(comboFractal, gbc_comboFractal);
		
		sliderProfundidad = new JSlider();
		sliderProfundidad.setMinimum(1);
		sliderProfundidad.setValue(1);
		sliderProfundidad.setMaximum(10);
		sliderProfundidad.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int prof = sliderProfundidad.getValue();
				GraficoAfines.setProfundidad(prof);
				grafico.calcular();
			}
		});
		gbc_sliderProfundidad = new GridBagConstraints();
		gbc_sliderProfundidad.gridx = 0;
		gbc_sliderProfundidad.gridy = 2;
		
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
		gbc_lblRangoEjeX.insets = new Insets(5, 5, 5, 0);
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
		gbc_lblA.insets = new Insets(5, 0, 5, 5);
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
		gbc_lblRangoEjeY.insets = new Insets(5, 5, 5, 0);
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
		gbc_lblA_1.insets = new Insets(5, 0, 5, 5);
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
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;
		panelCoordenadas.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{39, 30, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		btnGraficar = new JButton("Graficar");
		GridBagConstraints gbc_btnGraficar = new GridBagConstraints();
		gbc_btnGraficar.insets = new Insets(5, 0, 5, 5);
		gbc_btnGraficar.gridx = 0;
		gbc_btnGraficar.gridy = 0;
		panel.add(btnGraficar, gbc_btnGraficar);
		
		btnRestablecer = new JButton("Restablecer");
		btnRestablecer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizarFractal();
			}
		});
		GridBagConstraints gbc_btnRestablecer = new GridBagConstraints();
		gbc_btnRestablecer.insets = new Insets(5, 5, 5, 0);
		gbc_btnRestablecer.gridx = 1;
		gbc_btnRestablecer.gridy = 0;
		panel.add(btnRestablecer, gbc_btnRestablecer);
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
		    	actualizarCursor();
		    }
		});
		
		// CheckBox Mover Interactivo
		chckbxMovimInteractivo = new JCheckBox("Movimiento Interactivo");
		chckbxMovimInteractivo.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		    	mouseHandler.setMoverInteractivo(e.getStateChange() == ItemEvent.SELECTED);
		    	actualizarCursor();
		    }
		});
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
		    	actualizarCursor();
		    }
		});
		GridBagConstraints gbc_chckbxZoom = new GridBagConstraints();
		gbc_chckbxZoom.anchor = GridBagConstraints.WEST;
		gbc_chckbxZoom.insets = new Insets(5, 15, 5, 0);
		gbc_chckbxZoom.gridx = 0;
		gbc_chckbxZoom.gridy = 2;
		panelAcciones.add(chckbxZoom, gbc_chckbxZoom);
		
		// Label Punto actual
		lblPuntoactual = new JLabel("(0, 0)");
		GridBagConstraints gbc_lblPuntoactual = new GridBagConstraints();
		gbc_lblPuntoactual.gridx = 0;
		gbc_lblPuntoactual.gridy = 3;
		panelAcciones.add(lblPuntoactual, gbc_lblPuntoactual);
		
		// Valores iniciales
		chckbxMovimInteractivo.setSelected(true);
		chckbxMovimInteractivo.setEnabled(false);
		chckbxZoom.setSelected(true);
		
	}

	protected void actualizarCursor() {
		if(lblImagen == null){
			return;
		}
		
		if (chckbxMover.isSelected()){
			if (chckbxMovimInteractivo.isSelected()){
				lblImagen.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			}else{
				lblImagen.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));				
			}
		}else if (chckbxZoom.isSelected()){
			lblImagen.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		}else{
			lblImagen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
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
		chckbxAnimarTransicion.setSelected(true);
		GridBagConstraints gbc_chckbxAnimarTransicion = new GridBagConstraints();
		gbc_chckbxAnimarTransicion.anchor = GridBagConstraints.WEST;
		gbc_chckbxAnimarTransicion.gridwidth = 2;
		gbc_chckbxAnimarTransicion.insets = new Insets(5, 15, 5, 0);
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
		spinThreads.setModel(new SpinnerNumberModel(20, 1, 50, 1));
		spinThreads.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(chckbxThread.isSelected()){
					gConjuntos.setThreads((Integer) spinThreads.getValue());					
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
				if (chckbxThread.isSelected()){
					spinThreads.setEnabled(true);
					gConjuntos.setThreads((Integer) spinThreads.getValue());					
				}else{
					spinThreads.setEnabled(false);
					gConjuntos.setThreads(1);
				}
			}
		});
		
		progressCalculo = new JProgressBar();
		GridBagConstraints gbc_progressCalculo = new GridBagConstraints();
		gbc_progressCalculo.gridwidth = 3;
		gbc_progressCalculo.insets = new Insets(5, 10, 5, 10);
		gbc_progressCalculo.gridx = 0;
		gbc_progressCalculo.gridy = 3;
		panelProcesamiento.add(progressCalculo, gbc_progressCalculo);
		
		// Label Tiempo de procesamiento
		lblTiempoprocesamiento = new JLabel();
		lblTiempoprocesamiento.setText("0ms");
		GridBagConstraints gbc_lblTiempoprocesamiento = new GridBagConstraints();
		gbc_lblTiempoprocesamiento.gridwidth = 2;
		gbc_lblTiempoprocesamiento.insets = new Insets(5, 5, 0, 5);
		gbc_lblTiempoprocesamiento.gridx = 0;
		gbc_lblTiempoprocesamiento.gridy = 4;
		panelProcesamiento.add(lblTiempoprocesamiento, gbc_lblTiempoprocesamiento);
		
		// Valores por defecto
		chckbxThread.setSelected(true);
	}
	
	protected void mostrarListaFractales(String tipo) {
		switch (tipo) {
		case "Conjuntos de Complejos":
			comboFractal.setModel(modeloFractalesComplejos);
			break;
		case "Transformaciones Afines":
			comboFractal.setModel(modeloFractalesAfines);
			break;
		default:
			break;
		}
		actualizarFractal();
	}

	// Actualizar fractal mediante cambios en los combo de seleccion de fractal
	protected void actualizarFractal() {
		String fractalTipo = (String) comboTipo.getSelectedItem();
		String fractal = (String) comboFractal.getSelectedItem();
		
		// Elegir fractal especifico
		switch (fractal) {
		case "Conjunto de Mandelbrot (2)":
			gConjuntos.setConjunto(ConjuntoMandelbrot.CUADRATICO);
			break;
		case "Conjunto de Mandelbrot (5)":
			gConjuntos.setConjunto(ConjuntoMandelbrot.ORDEN_5);
			break;
		case "Conjunto de Mandelbrot (10)":
			gConjuntos.setConjunto(ConjuntoMandelbrot.ORDEN_10);
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
		case "Sample 1":
			gConjuntos.setConjunto(ConjuntoJulia.SAMPLE_1);
			break;
		case "Sample 2":
			gConjuntos.setConjunto(ConjuntoJulia.SAMPLE_2);
			break;
		case "Sample 3":
			gConjuntos.setConjunto(ConjuntoJulia.SAMPLE_3);
			break;
		case "Alfombra de Sierpinski":
			gAfines = GraficoAfines.SIERPINSKI;
			sliderProfundidad.setMaximum(10);
			break;
		case "Helecho de Bransley":
			gAfines = GraficoAfines.HELECHO;
			sliderProfundidad.setMaximum(25);
			break;
		default:
			break;
		}
				
		// Elegir tipo de fractal
		switch (fractalTipo) {
		case "Conjuntos de Complejos":
			panelFractal.remove(sliderProfundidad);
			grafico = gConjuntos;
			gConjuntos.setRango(-2, 2, -2, 2);
			break;
		case "Transformaciones Afines":
			panelFractal.add(sliderProfundidad, gbc_sliderProfundidad);
			grafico = gAfines;
			gAfines.setRango(-400, 400, 100, 500);
			break;
		default:
			break;
		}
	}
	
	// Grafico mediante coordenadas
	protected void graficar() {
		double xMin = (Double) spinXMin.getValue();
		double xMax = (Double) spinXMax.getValue();
		double yMin = (Double) spinYMin.getValue();
		double yMax = (Double) spinYMax.getValue();

		grafico.setRango(xMin, xMax, yMin, yMax);
	}

	// Grafico mediante accion Mover (MouseDragged)
	public void mover(int f, int c){
		grafico.mover(f, c);
	}

	// Grafico mediante accion Zoom (MousePressed y MouseRelease)
	public void zoom(int fMin, int fMax, int cMin, int cMax){
		grafico.zoom(fMin, fMax, cMin, cMax);
	}

	public void actualizarInterfaz(){
//		System.out.println("Actualizar interfaz");
		cargado = true;
		
		boolean interpolar = ! (chckbxMover.isSelected() && chckbxMovimInteractivo.isSelected()) && chckbxAnimarTransicion.isSelected();
		
		// Renderizar imagen
		Image im = grafico.generarImagen();
//		System.out.println("Actualizar interfaz");
		lblImagen.setImagen(im, interpolar);

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
	
	// Menu Bar
	
	protected void guardarImagenComo() {
		JFileChooser fc = new JFileChooser("");

        // Mostrar la ventana para abrir archivo y recoger la respuesta
        fc.setFileFilter(new FiltroArchivoPNG());
        int respuesta = fc.showSaveDialog(null);

        // Comprobar si se ha pulsado Aceptar
        if (respuesta == JFileChooser.APPROVE_OPTION){
        	String path = fc.getSelectedFile().getAbsolutePath();
        	
        	if (! path.endsWith(".png")){
        		path += ".png";
        	}
        	
            guardarImagen(new File(path));
        }
		
	}

	private void guardarImagen(File f) {
		try {
			Image im = grafico.generarImagen();
			BufferedImage buffIm = new BufferedImage(im.getWidth(null), im.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			buffIm.getGraphics().drawImage(im, 0, 0, null);
			ImageIO.write(buffIm, "png", f);
		} catch (IOException e) {
			System.out.println("Error de escritura");
		}		
	}
	
	public void resetProgress(){
		progressCalculo.setValue(0);
		filasProcesadas = 0;
	}
	
	public void addProgress(int f){
		filasProcesadas += f;
		int val = filasProcesadas * 100 / gConjuntos.getHeight();
		progressCalculo.setValue(val);
	}

	public boolean isCargado() {
		return cargado;
	}
	
}