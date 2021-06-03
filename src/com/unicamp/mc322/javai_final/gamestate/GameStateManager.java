package com.unicamp.mc322.javai_final.gamestate;

import com.unicamp.mc322.javai_final.player.Player;

public class GameStateManager {
	private GameState currentState;
	private Player[] players;
	private int currentPlayerIndex;
	
	public GameStateManager() {
		players = new Player[2];
		currentPlayerIndex = 0;
		
		currentState = new InitState(this);
	}
	
	public void init() {
		for(int i = 0; i < players.length; i++) {
			players[i] = new Player();
		}
	
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
	
	Player[] getPlayers() {
		return players;
	}
	
	Player getCurrentPlayer() {
		return players[currentPlayerIndex];
	}
	
	void advancePlayer() {
		currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
	}
	
	public void onInput(String input) {
		currentState.onInput(input);
	}
}
