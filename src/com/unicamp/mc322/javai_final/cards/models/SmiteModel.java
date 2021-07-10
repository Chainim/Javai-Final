package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.SpellCardModel;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;
import com.unicamp.mc322.javai_final.gamestate.InputListener;
import com.unicamp.mc322.javai_final.gamestate.SummonState;

public class SmiteModel extends SpellCardModel {

	SmiteModel() {
		super("card.smite.name", "card.smite.desc", 8);
	}
	
	@Override
	public void onSummon(Card card) {
		//Efeito: Um aliado atacante golpeia todos os oponentes defensores
		//Como ele 'golpeia', nao eh um embate?
		
		GameStateManager manager = GameStateManager.getInstance();
		
		SummonState g = (SummonState)manager.getCurrentState();
		g.addListener(new InputListener() {
			@Override
			public void onInput(String input) {
				Card c = null;
				boolean possibleSelection = false;
				for(int i = 0;i < 6;i++) {
					if(manager.getCurrentPlayer().getFieldCards()[i] != null) {
						possibleSelection = true;							
					}
				}
				if(possibleSelection == false) {
					System.err.println("Nao existe carta para aplicar o Julgamento");
					manager.getCurrentPlayer().getHandCards().add(new Card(ModelRegistry.SMITE));
					return;
				}
				
				if(manager.getCurrentPlayer().isAI()) {
					int maxDamageCard = 0;
					for(int i = 0;i < manager.getCurrentPlayer().getFieldCards().length;i++) {
						if(manager.getCurrentPlayer().getFieldCards()[i] != null && manager.getCurrentPlayer().getFieldCards()[i].getDamage() > maxDamageCard) {
							c = manager.getCurrentPlayer().getFieldCards()[i];
							maxDamageCard = c.getDamage();
						}
					}
				} else {
					if(input.equals("done")) {
						System.err.println("Clique em alguma carta do campo para aplicar o alvo certeiro");
						onSummon(card);
						return;
					}
					String[] s = input.split(" ");
					if(Integer.parseInt(s[2]) != manager.getCurrentPlayerIndex()) {
						System.err.println("Selecione somente suas coisas");
						onSummon(card);
						return;
					} else if(s[0].equals("hand")) {
						System.err.println("Selecione alguma carta do campo");
						onSummon(card);
						return;
					} else {
						int index = Integer.parseInt(s[1]);
						if(manager.getCurrentPlayer().getFieldCards()[index] == null) {
							System.err.println("Campo vazio");
							onSummon(card);
							return;
						} else {
							c = manager.getCurrentPlayer().getFieldCards()[index];							
						}
					}
				}
				
				Card attacking = c;
				
				for(int i = 0; i < 6; i++)
					if(manager.getOpponentPlayer().getFieldCards()[i] != null) {
						Card defending = manager.getOpponentPlayer().getFieldCards()[i];
						defending.takeDamage(attacking.getDamage());
						if(defending.getHealth() <= 0) {
							defending.onDeath();
							attacking.onKill();
							manager.getOpponentPlayer().getFieldCards()[i] = null;
						}
					}
			}
		});
	}
}
