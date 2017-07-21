package com.Pirhotau.Debug;

public class Debug {
	private static final Debug INSTANCE = new Debug();
	
	private DebugWindow window;
	private DebugData data;
	
	private Debug() {
		data = new DebugData();
		window = new DebugWindow(data);
		
		window.setVisible(true);
	}
	
	private static Debug getInstance() {
		return INSTANCE;
	}
	
	private DebugWindow getWindow() {
		return window;
	}
	
	private DebugData getData() {
		return data;
	}
	
	public static void setVisible(boolean b) {
		Debug.getInstance().getWindow().setVisible(b);
	}
	
	public static void addEntry(String key, String name) {
		Debug.getInstance().getData().addEntry(key, name);
	}
	
	public static void setValue(String key, String value) {
		Debug.getInstance().getData().setValue(key, value);
	}
}
