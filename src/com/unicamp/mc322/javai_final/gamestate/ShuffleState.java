package com.unicamp.mc322.javai_final.gamestate;

import com.unicamp.mc322.javai_final.player.Player;

public class ShuffleState extends GameState {

	public ShuffleState(GameStateManager manager) {
		super(manager);
	}
	
	@Override
	public void onStateLoad() {
		for(Player p : getManager().players) {
			
		}
		getManager().setState(new SwapCardState(getManager()));
	}

}
