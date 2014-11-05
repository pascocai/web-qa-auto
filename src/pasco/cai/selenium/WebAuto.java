package pasco.cai.selenium;


import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import pasco.cai.java.util.ReadFile;
import pasco.cai.java.util.ReadFileExcel;

import java.awt.Rectangle;
import java.io.File;

public class WebAuto extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private int browserType = 1;
	private int defaultTimeOut = 30;
	private int firstColumn = 2;
	private int firstRow = 2;
	
	SeleniumOperation seOper = null;

	private JPanel jContentPane = null;
	private int windowWidth = 800;
	private int windowHeight = 600;
	private Toolkit toolkit = getToolkit();
	private Dimension screenSize = toolkit.getScreenSize();
	File importFile = null;
	JTextArea textarea = new JTextArea();

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				
				WebAuto thisClass = new WebAuto();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	public WebAuto() {
		super();
		initialize();
	}

	private void initialize() {
		JButton buttonImport = new JButton("Import File");
		JButton buttonRun = new JButton("Run");
		JButton buttonClean = new JButton("Clean Console");
		JButton buttonClose = new JButton("Close");
		
		ButtonGroup browserTypeGroup = new ButtonGroup();
		JRadioButton browserType1 = new JRadioButton("Chrome", true);
		JRadioButton browserType2 = new JRadioButton("Firefox", false);
		JRadioButton browserType3 = new JRadioButton("IE", false);
		
		JLabel label1 = new JLabel("Number of cases :", JLabel.CENTER);
		JLabel label2 = new JLabel("Number of fields :", JLabel.CENTER);
		JLabel label3 = new JLabel("first data column :", JLabel.CENTER);
		JLabel label4 = new JLabel("first data row :", JLabel.CENTER);
		
		JLabel labelTimeOut = new JLabel("Timeout(s) :", JLabel.CENTER);
	    

		final JTextField textTimeOut = new JTextField();
		
		final JTextField tf1 = new JTextField();
		final JTextField tf2 = new JTextField();
		final JTextField tf3 = new JTextField();
		final JTextField tf4 = new JTextField();
		
		JScrollPane scrollingResult = new JScrollPane(textarea);
		
		Font font = new Font("宋体", Font.PLAIN, 16);

		this.setTitle("Automatic Web QA");
		this.setContentPane(getJContentPane());
		this.setSize(new Dimension(this.windowWidth, this.windowHeight));
		this.setLocation(screenSize.width / 2 - getWidth() / 2,
				screenSize.height / 2 - getHeight() / 2);

		JMenuBar menubar = new JMenuBar();

		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);

		JMenuItem fileOpen = new JMenuItem("Open");
		fileOpen.setMnemonic(KeyEvent.VK_O);
		fileOpen.setToolTipText("Open file");
		fileOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFileChooser fileopen = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("xls files", "xls");
				fileopen.addChoosableFileFilter(filter);
				int ret = fileopen.showDialog(null, "Open file");
				if (ret == JFileChooser.APPROVE_OPTION) {
					importFile = fileopen.getSelectedFile();
					textarea.insert("importFile:\n"+importFile.getPath()+"\n", textarea.getText().length());
				}
			}
		});

		JMenuItem fileClose = new JMenuItem("Close");
		fileClose.setMnemonic(KeyEvent.VK_C);
		fileClose.setToolTipText("Exit application");
		fileClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		file.add(fileOpen);
		file.add(fileClose);
		menubar.add(file);
		setJMenuBar(menubar);
		
		buttonImport.setBounds(new Rectangle(30, 15, 120, 30));
		buttonImport.setToolTipText("import file");
		buttonImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFileChooser fileopen = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("xls files", "xls");
				fileopen.addChoosableFileFilter(filter);
				int ret = fileopen.showDialog(null, "Open file");
				if (ret == JFileChooser.APPROVE_OPTION) {
					importFile = fileopen.getSelectedFile();
					textarea.insert("import file:\n"+importFile.getPath()+"\n", textarea.getText().length());
				}
			}
		});
		
		buttonRun.setBounds(new Rectangle(230, 15, 80, 30));
		buttonRun.setToolTipText("Run");
		buttonRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(importFile==null||importFile.equals("")) {
					textarea.insert("Please select a file.\n", textarea.getText().length());
					jContentPane.paintImmediately(jContentPane.getBounds());
					return;
				}
				if(!textTimeOut.getText().equals("")) {
					defaultTimeOut = Integer.parseInt(textTimeOut.getText().trim());
				}
				if(tf1.getText().equals("")) {
					textarea.insert("Please enter number of cases.\n", textarea.getText().length());
					jContentPane.paintImmediately(jContentPane.getBounds());
					return;
				}
				if(tf2.getText().equals("")) {
					textarea.insert("Please enter number of fields.\n", textarea.getText().length());
					jContentPane.paintImmediately(jContentPane.getBounds());
					return;
				}
				if(!tf3.getText().equals("")) {
					firstColumn = Integer.parseInt(tf3.getText().trim());
				}
				if(!tf4.getText().equals("")) {
					firstRow = Integer.parseInt(tf4.getText().trim());
				}
				
				ReadFile readFile = null;
				String fileFullName = importFile.getName();
				String fileExtName = fileFullName.substring(fileFullName.lastIndexOf(".")+1);
					
				if(fileExtName.equals("xls")) {
					runWithExcel(readFile, Integer.parseInt(tf1.getText()), Integer.parseInt(tf2.getText()));
				} else {
					textarea.insert("Import file type is invalid.\n", textarea.getText().length());
					jContentPane.paintImmediately(jContentPane.getBounds());
				}
			}
		});

		buttonClean.setBounds(new Rectangle(505, 15, 120, 30));
		buttonClean.setToolTipText("Clean Console");
		buttonClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				textarea.setText(null);
			}
		});
		
		buttonClose.setBounds(new Rectangle(675, 15, 80, 30));
		buttonClose.setToolTipText("Close");
		buttonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(seOper!=null){
					seOper.QuitDriver();
					seOper = null;
				}
				System.exit(0);
			}
		});
		
		browserType1.setBounds(new Rectangle(30, 60, 80, 30));
		browserType1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(((JRadioButton) event.getSource()).getText().equals("Chrome"))
					browserType = 1;
			}
		});
		browserTypeGroup.add(browserType1);
		
		browserType2.setBounds(new Rectangle(120, 60, 80, 30));
		browserType2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(((JRadioButton) event.getSource()).getText().equals("Firefox"))
					browserType = 2;
			}
		});
		browserTypeGroup.add(browserType2);
		
		browserType3.setBounds(new Rectangle(210, 60, 60, 30));
		browserType3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(((JRadioButton) event.getSource()).getText().equals("IE"))
					browserType = 3;
			}
		});
		browserTypeGroup.add(browserType3);
		
		labelTimeOut.setBounds(260, 60, 140, 30);
		textTimeOut.setBounds(375, 60, 40, 30);
		textTimeOut.setText(Integer.toString(defaultTimeOut));
		
		label1.setBounds(10, 100, 140, 30);
		tf1.setBounds(140, 100, 40, 30);
		label2.setBounds(210, 100, 140, 30);
		tf2.setBounds(340, 100, 40, 30);
		label3.setBounds(400, 100, 140, 30);
		tf3.setBounds(530, 100, 40, 30);
		tf3.setText(Integer.toString(firstColumn));
		label4.setBounds(570, 100, 140, 30);
		tf4.setBounds(690, 100, 40, 30);
		tf4.setText(Integer.toString(firstRow));
				
		textarea.setBounds(30, 150, 730, 360);
		textarea.setEnabled(false);
		textarea.setDisabledTextColor(Color.BLACK);
		textarea.setFont(font);
		
		scrollingResult.setBounds(30, 150, 730, 360);
		jContentPane.add(buttonImport);
		jContentPane.add(buttonRun);
		jContentPane.add(buttonClean);
		jContentPane.add(buttonClose);
		
		jContentPane.add(browserType1);
		jContentPane.add(browserType2);
		jContentPane.add(browserType3);
		

		jContentPane.add(labelTimeOut);
		jContentPane.add(textTimeOut);
		
		jContentPane.add(label1);
		jContentPane.add(tf1);
		jContentPane.add(label2);
		jContentPane.add(tf2);
		jContentPane.add(label3);
		jContentPane.add(tf3);
		jContentPane.add(label4);
		jContentPane.add(tf4);
		
		jContentPane.add(scrollingResult);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
		}
		return jContentPane;
	}
	
	private void runWithExcel(ReadFile rf, int numberOfCases, int numberOfFields) {
		rf = new ReadFileExcel();
		ReadFileExcel rfe = (ReadFileExcel) rf;
		rfe.openExcel(importFile, 0);
		seOper = new SeleniumOperation(browserType, defaultTimeOut);
		int fieldTypes[] = new int[numberOfFields];
		String fieldIds[] = new String[numberOfFields];
		String fieldValues[] = new String[numberOfFields];
		
		for(int caseIndex=0;caseIndex<numberOfCases;caseIndex++) {
			textarea.insert("========== Runing case "+(caseIndex+1)+" ==========\n", textarea.getText().length());
			jContentPane.paintImmediately(jContentPane.getBounds());
			for(int fieldIndex=0;fieldIndex<numberOfFields;fieldIndex++) {
				if(rfe.getCell(caseIndex+firstColumn-1, fieldIndex*3+1).equals(""))
					fieldTypes[fieldIndex] = -1;
				else {
					fieldTypes[fieldIndex] = Integer.parseInt(rfe.getCell(caseIndex+firstColumn-1, fieldIndex*3+firstRow-1));
					fieldIds[fieldIndex] = rfe.getCell(caseIndex+firstColumn-1, fieldIndex*3+firstRow);
					fieldValues[fieldIndex] = rfe.getCell(caseIndex+firstColumn-1, fieldIndex*3+firstRow+1);
				}
			}
			for(int fieldIndex=0;fieldIndex<numberOfFields;fieldIndex++) {
				textarea.insert("          ==== Runing field "+(fieldIndex+1)+" ====\n", textarea.getText().length());
				jContentPane.paintImmediately(jContentPane.getBounds());
				System.out.println(fieldTypes[fieldIndex] + " " + fieldIds[fieldIndex] + " " +fieldValues[fieldIndex]);
				if(fieldTypes[fieldIndex]>=0)
					seOper.run(fieldTypes[fieldIndex], fieldIds[fieldIndex], fieldValues[fieldIndex]);
				textarea.insert("          ==== Field "+(fieldIndex+1)+" is finished ====\n", textarea.getText().length());
				jContentPane.paintImmediately(jContentPane.getBounds());
				
			}
			textarea.insert("========== Case "+(caseIndex+1)+" is finished ==========\n", textarea.getText().length());
			jContentPane.paintImmediately(jContentPane.getBounds());
		}
		seOper.QuitDriver();
		rfe = null;
	}
}

