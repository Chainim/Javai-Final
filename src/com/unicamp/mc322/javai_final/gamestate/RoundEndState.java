package com.unicamp.mc322.javai_final.gamestate;

public class RoundEndState extends GameState {
	private int[] defendSelection;
	private int[] attackSelection;
	private int[] matchingSelection;
  	
	protected RoundEndState(GameStateManager manager, int[] defendSelection, int[] attackSelection, int[] matchingSelection) {
		super(manager);
		this.defendSelection = defendSelection;
		this.attackSelection = attackSelection;
		this.matchingSelection = matchingSelection;
	}

}
