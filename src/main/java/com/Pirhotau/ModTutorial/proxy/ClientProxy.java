package com.Pirhotau.ModTutorial.proxy;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRender() {
		System.out.println("ClientProxy.registerRender()");
	}
}
