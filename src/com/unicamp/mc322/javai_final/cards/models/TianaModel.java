package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.MinionCardModel;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;
import com.unicamp.mc322.javai_final.gamestate.InputListener;
import com.unicamp.mc322.javai_final.gamestate.SummonState;

public class TianaModel extends MinionCardModel {
	TianaModel() {
		super("card.tiana.name", "card.tiana.desc", 8, 7, 7);
	}
	
	@Override
	public void onDraw(Card c) {
		// Uma das cartas sumonadas ataca o nexus inimigo
		GameStateManager manager = GameStateManager.getInstance();
		
		SummonState g = (SummonState)manager.getCurrentState();
		g.addListener(new InputListener() {
			@Override
			public void onInput(String input) {
				int index;
				try {
					index = Integer.parseInt(input);
				} catch (NumberFormatException e)  {
					System.err.println("Invalid input");
					onSummon(c);
					return;
				}
				
				if(index > 5 || manager.getCurrentPlayer().getFieldCards()[index] == null) {
					System.err.println("Invalid input");
					onSummon(c);
					return;
				}
				
				Card attacking = manager.getCurrentPlayer().getFieldCards()[index];
				manager.getOpponentPlayer().takeNexusDamage(attacking.getDamage());
			}
		});
	}
}
