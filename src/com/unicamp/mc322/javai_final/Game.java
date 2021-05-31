package com.unicamp.mc322.javai_final;

import com.unicamp.mc322.javai_final.gamestate.GameStateManager;

public class Game {
	
	private boolean running;
	
	public void start() {
		running = true;
		loop();
	}
	
	public void loop() {
		while(running) {
			
		}
	}
	
	public static void main(String[] args) {
		//Game g = new Game();
		//g.start();
		GameStateManager stateManager = new GameStateManager();
		stateManager.init();
	}
}
