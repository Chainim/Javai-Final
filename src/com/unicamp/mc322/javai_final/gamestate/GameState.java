package com.unicamp.mc322.javai_final.gamestate;

import com.unicamp.mc322.javai_final.display.Screen;

public abstract class GameState {
	private GameStateManager manager;
	
	protected GameState(GameStateManager manager) {
		this.manager = manager;
	}
	
	protected GameStateManager getManager() {
		return manager;
	}
	
	public void update() {
		
	}
	
	public void onStateLoad() {
		
	}
	
	public void onStateUnload() {
		
	}
	
	public void onInput(String input) {
		
	}
	
	public void onRender(Screen s) {
		
	}
}
