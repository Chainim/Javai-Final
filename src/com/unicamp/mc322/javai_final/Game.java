package com.unicamp.mc322.javai_final;

import com.unicamp.mc322.javai_final.display.Screen;
import com.unicamp.mc322.javai_final.display.TextScreen;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;

public class Game {
	
	private GameStateManager stateManager;
	private boolean running;
	
	public Game() {
		 stateManager = new GameStateManager();
	}
	
	public void start() {
		running = true;
		
		stateManager.init();
		
		loop();
	}
	
	public void loop() {
		while(running) {
			stateManager.update();
		}
	}
	
	public static void main(String[] args) {
		//Game g = new Game();
		//g.start();
		//Screen d = new TextScreen();
		//for(int i = 0; i < 6; i++)
		//	d.drawCard(30 + 14 * i, 5);
		//d.drawNexus(0, 0);
		//d.render();
	}
}
