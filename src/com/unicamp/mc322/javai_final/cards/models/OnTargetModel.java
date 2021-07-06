package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.SpellCardModel;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;
import com.unicamp.mc322.javai_final.gamestate.InputListener;
import com.unicamp.mc322.javai_final.gamestate.SummonState;

public class OnTargetModel extends SpellCardModel {

	OnTargetModel() {
		super("card.on_target.name", "card.on_target.desc", 1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onSummon(Card card) {
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
					onSummon(card);
					return;
				}
				
				if(index > 5 || manager.getCurrentPlayer().getFieldCards()[index] == null) {
					System.err.println("Invalid input");
					onSummon(card);
					return;
				}
				
				Card c = manager.getCurrentPlayer().getFieldCards()[index];
				
				c.setHealth(c.getHealth() + 1);
				c.setDamage(c.getDamage() + 1);
			}
		});
	}

}
