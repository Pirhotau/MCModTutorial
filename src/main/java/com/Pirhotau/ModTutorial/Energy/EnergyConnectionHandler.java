package com.Pirhotau.ModTutorial.Energy;

public class EnergyConnectionHandler extends EnergyHandler {
	
	public EnergyConnectionHandler(int transferRate) {
		super(transferRate*10, transferRate, transferRate);
	}
	
}
