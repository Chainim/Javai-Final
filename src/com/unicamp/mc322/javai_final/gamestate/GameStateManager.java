package com.unicamp.mc322.javai_final.gamestate;

import java.util.List;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.models.ModelRegistry;
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
			Player p = players[i];
			List<Card> playerCards = p.getHandCards();
			for(int j = 0; j < playerCards.size(); j++) {
				if(i == 0)
					s.drawCard(xoffset + j * 15, 40 - 10, playerCards.get(j).getModel());
				else
					s.drawCard(xoffset + j * 15, 0, playerCards.get(j).getModel());
			}
			for(int j = 0; j < 6; j++) {
				int yPos;
				if(i == 0)
					yPos = 20;
				else
					yPos = 20 - 5;
				s.drawBox(yPos, xoffset + 10 + j * 8, 5, 7);
				if(p.getFieldCards()[j] != null) {
					Card c = p.getFieldCards()[j];
					s.drawStringLeftAnchored(yPos + 1, xoffset + 11 + j * 8, Integer.toString(c.getDamage()));
					s.drawStringRightAnchored(yPos + 1, xoffset + 10 + (j + 1) * 8 - 3, Integer.toString(c.getHealth()));
				}
					
			}
			
			if(i == 0) {
				s.drawStringLeftAnchored(20 - 5, 100 - 8, "Mana (" + p.getMana() + "):");
				s.drawProgressBarLeftAnchored(20 - 5, 100, p.getMana(), 10);
			} else {
				s.drawStringLeftAnchored(20 + 3, 100 - 8, "Mana (" + p.getMana() + "):");
				s.drawProgressBarLeftAnchored(20 + 3, 100, p.getMana(), 10);
			}
		}
	}
}
