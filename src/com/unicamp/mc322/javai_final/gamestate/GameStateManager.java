package com.unicamp.mc322.javai_final.gamestate;

import com.unicamp.mc322.javai_final.player.Player;

public class GameStateManager {
	private GameState currentState;
	Player[] players;
	
	public GameStateManager() {
		players = new Player[2];
		
		currentState = new ShuffleState(this);
		currentState.onStateLoad();
	}
	
	public void setState(GameState state) {
		currentState.onStateUnload();
		currentState = state;
		currentState.onStateLoad();
	}
	
	public void update() {
		currentState.update();
	}
}
