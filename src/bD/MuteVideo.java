package bD;
import mapping.Control;
import mapping.Flag;
import mapping.ProgramaComando;

@ProgramaComando
public class MuteVideo
{
	@Flag(name = "-i", control = Control.fileChooser)
	private String inputVideo;
		
	@Flag(name = "-af \"volume=enable='between(t,", control = Control.numeric)
	private String desde;
		
	@Flag(name=",", onOpen = "", onClose = ")':volume=0\"", control = Control.numeric)
	private String hasta;
	
	@Flag(onClose = "", control = Control.fileChooser)
	private String output;

	public MuteVideo(){}
	
}
