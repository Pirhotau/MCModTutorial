package com.Pirhotau.ModTutorial.Energy;

public class EnergyConnection extends EnergyHandler {
	
	public EnergyConnection(int transferRate) {
		super(transferRate*10, transferRate, transferRate);
	}
	
}
