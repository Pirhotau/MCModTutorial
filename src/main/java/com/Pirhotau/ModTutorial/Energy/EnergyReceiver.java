package com.Pirhotau.ModTutorial.Energy;

public class EnergyReceiver extends EnergyHandler {
	
	public EnergyReceiver(int capacity, int maxReceive) {
		super(capacity, maxReceive, 0);
	}
	
	/**
	 * If it is possible to consume a certain amount of energy
	 * (internal use only)
	 * 
	 * @param energy
	 * @return
	 */
	public boolean canConsume(int energy) {
		return energy <= this.energy;
	}
	
	/**
	 * Tries to consume a certain amount of energy.
	 * If this energy can be consumed, returns true, else false.
	 * (Internal use only)
	 * 
	 * @param energy
	 * @return
	 */
	public boolean consume(int energy) {
		if(this.canConsume(energy)) {
			this.energy -= energy;
			return true;
		} else return false;
	}
}
