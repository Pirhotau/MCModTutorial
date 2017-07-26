package com.Pirhotau.ModTutorial.Energy;

public class EnergyProvider extends EnergyHandler {

	public EnergyProvider(int capacity, int maxExtract) {
		super(capacity, 0, maxExtract);
	}
	
	/**
	 * Returns true if it is possible to create a certain amount of energy
	 * (Internal use only)
	 * 
	 * @param energy
	 * @return
	 */
	public boolean canCreateEnergy(int energy) {
		return this.energy + energy <= this.capacity;
	}
	
	/**
	 * Tries to create a certain amount of energy.
	 * Return true if the energy was created successfully, else false.
	 * (Internal use only)
	 * 
	 * @param energy
	 * @return
	 */
	public boolean createEnergy(int energy) {
		if(this.canCreateEnergy(energy)) {
			this.energy += energy;
			return true;
		} else return false;
	}
}
