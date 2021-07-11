package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.SpellCardModel;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;
import com.unicamp.mc322.javai_final.gamestate.InputListener;
import com.unicamp.mc322.javai_final.gamestate.SummonState;

public class HeadToHeadModel extends SpellCardModel {

	HeadToHeadModel() {
		super("card.head_to_head.name", "card.head_to_head.desc", 2);
	}

	@Override
	public void onSummon(Card card) {
		GameStateManager manager = GameStateManager.getInstance();

		SummonState g = (SummonState) manager.getCurrentState();
		
		if(!manager.getCurrentPlayer().hasFieldCards() || !manager.getOpponentPlayer().hasFieldCards()) {
			System.err.println("Sem cartas para fazer o mano a mano");
			manager.getCurrentPlayer().getHandCards().add(new Card(ModelRegistry.HEAD_TO_HEAD));
			return;
		}
		
		Card attacking = null;
		Card defending = null;
		if(manager.getCurrentPlayer().isAI()) {
			for(int i = 0;i < manager.getCurrentPlayer().getFieldCards().length;i++) {
				for(int j = 0;j < manager.getOpponentPlayer().getFieldCards().length;j++) {
					if(manager.getCurrentPlayer().getFieldCards()[i] != null && manager.getOpponentPlayer().getFieldCards()[j] != null) {
						attacking = manager.getCurrentPlayer().getFieldCards()[i]; 
						defending = manager.getOpponentPlayer().getFieldCards()[j];
						manager.doCombat(attacking, defending);
						return;
					}
				}
			}
		}
		
		g.addListener(new InputListener() {
			@Override
			public boolean onInput(String input) {
				
				if(input.equals("done")) {
					System.err.println("Clique em alguma carta do campo para atacar");
					return false;
				}
				String[] s = input.split(" ");
				if(Integer.parseInt(s[2]) != manager.getCurrentPlayerIndex()) {
					System.err.println("Selecione somente suas coisas");
					return false;
				} else if(s[0].equals("hand")) {
					System.err.println("Selecione alguma carta do campo");
					return false;
				} else {
					int index = Integer.parseInt(s[1]);
					if(manager.getCurrentPlayer().getFieldCards()[index] == null) {
						System.err.println("Campo vazio");
						return false;
					} else {
						g.attacking1v1 = manager.getCurrentPlayer().getFieldCards()[index];
						System.out.println("ADICIONEI O ATACANTE NO STATE");
						return true;
					}
				}
			}
		});
		
		g.addListener(new InputListener() {
			@Override
			public boolean onInput(String input) {
				
				if(input.equals("done")) {
					System.err.println("Clique em alguma carta do campo inimigo para defender");
					return false;
				}
				String[] s = input.split(" ");
				if(Integer.parseInt(s[2]) != manager.getOpponentPlayerIndex()) {
					System.err.println("Selecione somente suas coisas");
					return false;
				} else if(s[0].equals("hand")) {
					System.err.println("Selecione alguma carta do campo");
					return false;
				} else {
					int index = Integer.parseInt(s[1]);
					if(manager.getOpponentPlayer().getFieldCards()[index] == null) {
						System.err.println("Campo vazio");
						return false;
					} else {
						g.defending1v1 = manager.getOpponentPlayer().getFieldCards()[index];
						System.out.println("ADICIONEI O DEFENSOR NO STATE");
						return true;
					}
				}
			}		
		});
	}
}
