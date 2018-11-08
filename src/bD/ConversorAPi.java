package bD;

import mapping.Conversor;

public class ConversorAPi extends Conversor
{
	@Override
	public String convertir(String dato)
	{
		double datoDouble = Double.parseDouble( dato );
		
		return Double.toString(datoDouble*(Math.PI/180));
		
	}
}
