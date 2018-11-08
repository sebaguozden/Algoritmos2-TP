package bD;

import mapping.FlagValidator;

public class RotarValidatorAngulo extends FlagValidator
{

	@Override
	public boolean validar(String dato)
	{
		if (Double.parseDouble( dato ) % 90 != 0){
			return true;
		}else{
			return false;
		}
	}

}