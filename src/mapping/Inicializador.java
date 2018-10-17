package mapping;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.reflections.Reflections;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

public class Inicializador
{
	
	private JFrame frmAplicaciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Inicializador window=new Inicializador();
					window.frmAplicaciones.setVisible(true);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Inicializador()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame. 
	 */
	private void initialize()
	{
		int posAncho = 100;
		int posAlto = 100;
		
		frmAplicaciones=new JFrame();
		frmAplicaciones.setTitle("Aplicaciones");
		
		Reflections ref = new Reflections(".*");
        for (final Class<?> aplicacionClass : ref.getTypesAnnotatedWith(ProgramaComando.class)) {
        	       	
        	JButton btnNewButton = new JButton (aplicacionClass.getSimpleName());
        	
        	btnNewButton.addActionListener(new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
					     			
        			int posAnchoLabel = 100;
					int posAltoLabel = 100;
					
					JDialog aplicacion = new JDialog(frmAplicaciones,true);
					aplicacion.setTitle(aplicacionClass.getSimpleName());
					
					final HashMap<Field,JComponent> jFieldMap = new HashMap<Field,JComponent>();
					final List<Field> variablesOrdenadas = new ArrayList<Field>();
					
					for(Field variable : aplicacionClass.getDeclaredFields()){
						 						
						Flag anotation=variable.getAnnotation(Flag.class);
						
						if(anotation != null){
							
							variablesOrdenadas.add(variable);
															
							JLabel variableAux = new JLabel(variable.getName().concat(":"));
							variableAux.setBounds(posAnchoLabel, posAltoLabel, 500, 250);
							variableAux.setFont(new Font("Tahoma", Font.PLAIN, 50));
							aplicacion.add(variableAux);
								
							posAnchoLabel += 600;
							
							if (anotation.control() == Control.text){
								
								JTextField textField = new JTextField();
								textField.setBounds(posAnchoLabel, posAltoLabel, 1000, 250);
								textField.setFont(new Font("Tahoma", Font.PLAIN, 50));
								aplicacion.add(textField);
							
								//textField.getText();
								
								jFieldMap.put(variable,textField);
								
							}
							
							if (anotation.control() == Control.numeric){
								
								JTextField textField = new JTextField();
								textField.addKeyListener(new KeyAdapter() {
									@Override
									public void keyTyped(KeyEvent e) {
										char validar = e.getKeyChar();
										
										if (Character.isLetter(validar)){
											
											Toolkit.getDefaultToolkit().beep();
											e.consume();
											
											JOptionPane.showMessageDialog(null, "Ingresar SOLO numeros");
										}
									}
								});
								textField.setBounds(posAnchoLabel, posAltoLabel, 1000, 250);
								textField.setFont(new Font("Tahoma", Font.PLAIN, 50));
								aplicacion.add(textField);
							
								//textField.getText();
								
								jFieldMap.put(variable,textField);
								
							}
							
							if (anotation.control() == Control.time){
								
								JTextField textField = new JTextField();
								textField.addKeyListener(new KeyAdapter() {
									@Override
									public void keyTyped(KeyEvent e) {
										char validar = e.getKeyChar();
										
										if (Character.isLetter(validar)){
											
											Toolkit.getDefaultToolkit().beep();
											e.consume();
											
											JOptionPane.showMessageDialog(null, "Ingresar SOLO horarios");
										}
									}
								});
								textField.setBounds(posAnchoLabel, posAltoLabel, 1000, 250);
								textField.setFont(new Font("Tahoma", Font.PLAIN, 50));
								aplicacion.add(textField);
							
								//textField.getText();
								
								jFieldMap.put(variable,textField);
								
							}
							
							if (anotation.control() == Control.fileChooser){
								
								JFileChooser textField = new JFileChooser();
								textField.setCurrentDirectory(new File("C:\\Users\\Seba\\Desktop"));
								textField.setBounds(posAnchoLabel, posAltoLabel, 1000, 250);
								textField.setFont(new Font("Tahoma", Font.PLAIN, 50));
								aplicacion.add(textField);
							
								//textField.getSelectedFile();
								
								jFieldMap.put(variable,textField);
							}
							
							posAltoLabel += 350;						
							posAnchoLabel = 100;
									
						}
						
					}
						
					JButton button = new JButton("Procesar");
					button.setBounds(posAnchoLabel + 900, posAltoLabel, 400, 75);
					button.setFont(new Font("Tahoma", Font.PLAIN, 75));
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							HashMap<Field,String> fieldMap = new HashMap<Field,String>();
							
							try
							{
								for( Field variableAux : jFieldMap.keySet()){
									if (jFieldMap.get(variableAux) instanceof JFileChooser) {
										fieldMap.put(variableAux, ((JFileChooser)jFieldMap.get(variableAux)).getSelectedFile().getPath() );
									}if (jFieldMap.get(variableAux) instanceof JTextField){
										fieldMap.put(variableAux, ((JTextField)jFieldMap.get(variableAux)).getText().toString() );
									}
								}
									ejecutar(aplicacionClass, fieldMap, variablesOrdenadas);
									JOptionPane.showMessageDialog(null, "Procesamiento Finalizado" );
							}
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(null, "Procesamiento Fallido" );
								//JOptionPane.showMessageDialog(null, ex.printStackTrace() ); NO SE COMO MOSTRAR EL TRACE ERROR
							}
																
						}

					});
					aplicacion.add(button);
						
					aplicacion.setBounds(0, 0, 1850, posAltoLabel + 300);
					aplicacion.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					aplicacion.getContentPane().setLayout(null);
					aplicacion.setVisible(true);
        		}
				
        	});
        	btnNewButton.setBounds(posAncho, posAlto, 500, 100);
        	btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 75));
        	frmAplicaciones.getContentPane().add(btnNewButton);
			posAlto += 200;
        }
        frmAplicaciones.setBounds(0, 0, 700, posAlto+100);
        frmAplicaciones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmAplicaciones.getContentPane().setLayout(null);
	}

	private void ejecutar(Class<?> aplicacionClass, HashMap<Field,String> fieldMap, List<Field> variablesOrdenadas) throws IOException
	{

		String peticion = "";
		
		final ProgramaComando anotacionObtenidaPrograma = aplicacionClass.getAnnotation(ProgramaComando.class);
		if (anotacionObtenidaPrograma != null) {
			peticion = peticion.concat(anotacionObtenidaPrograma.flag());
		}
		
		
		for( Field variableAux : variablesOrdenadas){
			Flag anotacionObtenidaVariable = variableAux.getAnnotation(Flag.class);
			if (anotacionObtenidaVariable != null) {
				peticion = peticion.concat(anotacionObtenidaVariable.onOpen());
				peticion = peticion.concat(anotacionObtenidaVariable.name());
				peticion = peticion.concat(anotacionObtenidaVariable.onClose());
				if (variableAux.getName().equals("output")) peticion = peticion.concat("C:\\Users\\Seba\\Desktop\\");
				peticion = peticion.concat(fieldMap.get(variableAux));
				peticion = peticion.concat(anotacionObtenidaVariable.post());
			}
		}
		
		System.out.println(peticion);
		
		Process p =	
				Runtime.getRuntime().exec(peticion);
		BufferedReader br = new BufferedReader ( new InputStreamReader ( p.getInputStream() ) );
				
		String line = "";
				
		while ( ( line = br.readLine() ) != null ){
					
			System.out.println("La linea es " + line);
				
		}
	
	}
	
}
