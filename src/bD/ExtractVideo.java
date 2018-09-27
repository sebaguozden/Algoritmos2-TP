package bD;
import mapping.Control;
import mapping.Flag;
import mapping.ProgramaComando;

@ProgramaComando
public class ExtractVideo
{
	
	@Flag(name = "-i", control = Control.fileChooser)
	private String inputVideo;
	
	@Flag(name = "-ss", control = Control.time)
	private String desde;
	
	@Flag(name = "-to", control = Control.time)
	private String hasta;
	
	@Flag(onClose = "", control = Control.fileChooser)
	private String output;
	

	public ExtractVideo(){}
	
	public ExtractVideo(String inputVideo, String output, String desde, String hasta){
		this.inputVideo = inputVideo;
		this.output = output;
		this.desde = desde;
		this.hasta = hasta;
	}
		
}
