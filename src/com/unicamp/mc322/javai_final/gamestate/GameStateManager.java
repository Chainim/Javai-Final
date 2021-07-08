package com.unicamp.mc322.javai_final.gamestate;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.models.ModelRegistry;
import com.unicamp.mc322.javai_final.display.InterfaceScreen;
import com.unicamp.mc322.javai_final.display.Screen;
import com.unicamp.mc322.javai_final.player.Player;

public class GameStateManager {
	
	private static GameStateManager instance;
	
	private GameState currentState;
	private Player[] players;
	int currentPlayerIndex;
	
	InitState initState;
	SummonState summonState;
	AttackState attackState;
	DefendState defendState;
	RoundEndState roundEndState;
	
	public GameStateManager() {
		instance = this;
		
		players = new Player[2];
		currentPlayerIndex = 0;
		
		initState = new InitState(this);
		summonState = new SummonState(this);
		attackState = new AttackState(this);
		defendState = new DefendState(this);
		roundEndState = new RoundEndState(this);
		
		currentState = initState;
	}
	
	public void init() {
		for(int i = 0; i < players.length; i++) {
			if(i == 0)
				players[i] = new Player(false);
			else
				players[i] = new Player(true);
		}
		
		//TODO: Lembrar de tirar esse trecho de codigo
		players[0].addMana();
		players[0].addMana();
		players[0].addMana();
		players[0].addMana();
		players[0].addMana();
		players[0].addMana();
		players[1].addMana();
		players[1].addMana();
		players[1].addMana();
		players[1].addMana();
		players[1].addMana();
		players[1].addMana();
		
		players[0].getFieldCards()[1] = new Card(ModelRegistry.PORO);
		players[0].getFieldCards()[1].setOwner(players[0]);
		players[0].getFieldCards()[2] = new Card(ModelRegistry.PORO);
		players[0].getFieldCards()[2].setOwner(players[0]);
		players[0].getFieldCards()[4] = new Card(ModelRegistry.DEFENDER);
		players[0].getFieldCards()[4].setOwner(players[0]);
		
		players[1].getFieldCards()[2] = new Card(ModelRegistry.DEFENDER);
		players[1].getFieldCards()[2].setOwner(players[1]);
		players[1].getFieldCards()[3] = new Card(ModelRegistry.DEFENDER);
		players[1].getFieldCards()[3].setOwner(players[1]);
		players[1].getFieldCards()[5] = new Card(ModelRegistry.PORO);
		players[1].getFieldCards()[5].setOwner(players[1]);
	
		currentState.onStateLoad();
	}
	
	public GameState getCurrentState() {
		return currentState;
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
	
	public Player getCurrentPlayer() {
		return players[currentPlayerIndex];
	}
	
	public Player getOpponentPlayer() {
		return players[(currentPlayerIndex + 1) % 2];
	}
	
	void advancePlayer() {
		currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
	}
	
	public void onInput(String input) {
		currentState.onInput(input);
	}
	
	public void drawInterface() {
		
	}
	
	public void draw() {
		
		//card.add(costLabel);
		//card.add(attackLabel);
		//card.add(healthLabel);
		//card.add(nameLabel);
		
		
		
		for(int i = 0;i < players.length;i++) {
			InterfaceScreen.getInterfaceScreen().getManaBars()[i].setValue(players[i].getMana());
			InterfaceScreen.getInterfaceScreen().getManaBars()[i].setString("Mana: " + players[i].getMana());
			
			for(int j = players[i].getHandCards().size();j < 10;j++) {
				JButton button = InterfaceScreen.getInterfaceScreen().getHandCards().get(j + i * 10);
				button.setVisible(false);
			}
			
			for(int j = 0;j < players[i].getHandCards().size();j++) {
				JButton button = InterfaceScreen.getInterfaceScreen().getHandCards().get(j + i * 10);
				Card c = players[i].getHandCards().get(j);
				button.setVisible(true);
				((JLabel) button.getComponent(0)).setText(Integer.toString(c.getManaCost()));
				((JLabel) button.getComponent(1)).setText(Integer.toString(c.getDamage()));
				((JLabel) button.getComponent(2)).setText(Integer.toString(c.getHealth()));
				((JLabel) button.getComponent(3)).setText(c.getLocalizedName());
			}
		}
		
		/*//FIXME: Essa eh uma string de depuracao, lembrar de remover ao final do projeto
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
				s.drawStringLeftAnchored(20 + 3, 100 - 18, "Mana (" + p.getMana() + "):");
				s.drawProgressBarLeftAnchored(20 + 3, 102 - 10, p.getMana(), 10);
				
				s.drawStringLeftAnchored(20 + 3, 100 + 5, "Spell Mana (" + p.getSpellMana() + "):");
				s.drawProgressBarLeftAnchored(20 + 3, 100 + 20, p.getSpellMana(), 3);
				
				s.drawNexus(5, 20 + 0, p.getNexusHealth());
			} else {
				s.drawStringLeftAnchored(20 - 5, 100 - 18, "Mana (" + p.getMana() + "):");
				s.drawProgressBarLeftAnchored(20 - 5, 102 - 10, p.getMana(), 10);
				
				s.drawStringLeftAnchored(20 - 5, 100 + 5, "Spell Mana (" + p.getSpellMana() + "):");
				s.drawProgressBarLeftAnchored(20 - 5, 100 + 20, p.getSpellMana(), 3);
				
				s.drawNexus(5, 20 - 5, p.getNexusHealth());
			}
		}
		
		currentState.onRender(s);*/
	}
	
	public static GameStateManager getInstance() {
		return instance;
	}

	public void doCombat(Card attacking, Card defending) {
		defending.takeDamage(attacking.getDamage());
		attacking.takeDamage(defending.getDamage());
		
		if(attacking.getHealth() <= 0) {
			attacking.onDeath();
			defending.onKill();
			this.getCurrentPlayer().getFieldCards()[attacking.getFieldIndex()] = null;
		} else {
			attacking.onAttack();			
		}
		if(defending.getHealth() <= 0) {
			defending.onDeath();
			attacking.onKill();
			this.getOpponentPlayer().getFieldCards()[defending.getFieldIndex()] = null;
		}
		
	}
}
