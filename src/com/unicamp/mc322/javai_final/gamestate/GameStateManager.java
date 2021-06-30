package com.unicamp.mc322.javai_final.gamestate;

import java.util.List;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.models.ModelRegistry;
import com.unicamp.mc322.javai_final.display.Screen;
import com.unicamp.mc322.javai_final.player.Player;

public class GameStateManager {
	private GameState currentState;
	private Player[] players;
	int currentPlayerIndex;
	
	GameState initState;
	GameState summonState;
	GameState attackState;
	GameState defendState;
	GameState roundEndState;
	
	public GameStateManager() {
		players = new Player[2];
		currentPlayerIndex = 0;
		
		initState = new InitState(this);
//		summonState = new SummonState(this);
//		attackState = new AttackState(this);
//		defendState = new DefendState(this);
		
		currentState = initState;
	}
	
	public void init() {
		for(int i = 0; i < players.length; i++) {
			players[i] = new Player();
		}
		
//		players[0].getFieldCards()[1] = new Card(ModelRegistry.PORO);
//		players[0].getFieldCards()[2] = new Card(ModelRegistry.PORO);
//		players[0].getFieldCards()[4] = new Card(ModelRegistry.DEFENDER);
//		
//		players[1].getFieldCards()[2] = new Card(ModelRegistry.DEFENDER);
//		players[1].getFieldCards()[3] = new Card(ModelRegistry.DEFENDER);
//		players[1].getFieldCards()[5] = new Card(ModelRegistry.PORO);
	
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
	
	Player getOpponentPlayer() {
		return players[(currentPlayerIndex + 1) % 2];
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
				s.drawStringLeftAnchored(20 + 3, 100 - 8, "Mana (" + p.getMana() + "):");
				s.drawProgressBarLeftAnchored(20 + 3, 102, p.getMana(), 10);
				
				s.drawProgressBarLeftAnchored(20 + 3, 100 + 20, p.getSpellMana(), 3);
				
				s.drawNexus(5, 20 + 3, p.getNexusHealth());
			} else {
				s.drawStringLeftAnchored(20 - 5, 100 - 8, "Mana (" + p.getMana() + "):");
				s.drawProgressBarLeftAnchored(20 - 5, 102, p.getMana(), 10);
				
				//s.drawStringLeftAnchored(20 - 5, 100 + 5, "Spell Mana (" + p.getSpellMana() + "):");
				s.drawProgressBarLeftAnchored(20 - 5, 100 + 20, p.getSpellMana(), 3);
				
				s.drawNexus(5, 20 - 5, p.getNexusHealth());
			}
		}
		
		currentState.onRender(s);
	}

}
