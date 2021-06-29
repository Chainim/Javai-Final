package com.unicamp.mc322.javai_final.gamestate;

import java.util.List;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.display.Screen;
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
	
	public void draw(Screen s) {
		
		//FIXME: Essa eh uma string de depuracao, lembrar de remover ao final do projeto
		s.drawStringRightAnchored(16, 120, "Estado atual: " + currentState.getClass().getSimpleName());
		
		
		final int xoffset = 18;
		
		for(int i = 0; i < players.length; i++) {
			List<Card> playerCards = players[i].getHandCards();
			for(int j = 0; j < playerCards.size(); j++) {
				if(i == 0)
					s.drawCard(xoffset + j * 15, 40 - 10, playerCards.get(j).getModel());
				else
					s.drawCard(xoffset + j * 15, 0, playerCards.get(j).getModel());
			}
		}
	}
}
