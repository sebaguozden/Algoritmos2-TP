package bD;

import mapping.Control;
import mapping.Flag;
import mapping.ProgramaComando;

@ProgramaComando
public class Rotar
{

	@Flag(name = "-i", control = Control.fileChooser)
	private String inputVideo;
	
	@Flag(name = "-vf \"rotate=", 
		  onOpen = " ", 
		  onClose = "", 
		  post = ":bilinear=0,format=yuv420p\" -metadata:s:v rotate=0 -codec:v libx264 -codec:a copy",
		  control = Control.numeric, 
		  validator= RotarValidatorAngulo.class, 
		  post2 =",format=yuv420p\" -metadata:s:v rotate=0 -codec:v libx264 -codec:a copy",
		  conversor = ConversorAPi.class)
	private String gradosARotar;
	
	@Flag(onClose = "", control = Control.text)
	private String output;
	
	public Rotar() {}
	
}