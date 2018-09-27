package bD;
import mapping.Control;
import mapping.Flag;
import mapping.ProgramaComando;

@ProgramaComando
public class AudioToVideo
{	
	
	@Flag(name = "-i", control = Control.fileChooser)
	private String inputVideo;
	
	@Flag(name = "-i", control = Control.fileChooser)
	private String inputAudio;
	
	@Flag(name = "-c:v copy -c:a copy", control = Control.fileChooser)
	private String output;

	public AudioToVideo(){}

}