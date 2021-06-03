package com.unicamp.mc322.javai_final.gamestate;

public class DefendState extends GameState {
	private boolean defendSelectionConfirmed;
	private boolean matchingSelectionConfirmed;
	private int[] defendSelection;
	private int[] matchingSelection;
 	
	protected DefendState(GameStateManager manager) {
		super(manager);
	}
	
	public int[] getDefendSelection() {
		return defendSelection;
	}
	
	public int[] getMatchingSelection() {
		return matchingSelection;
	}
	
	@Override
	public void onStateLoad() {
		defendSelectionConfirmed = false;
		matchingSelectionConfirmed = false;
	}
	
	@Override
	public void update() {
		if(defendSelectionConfirmed == true && matchingSelectionConfirmed == true) {
			getManager().setState(new RoundEndState(getManager()));
		}
	}
	
	// 0, 1, 2, ...
	public void onInputMatching(String input) {
		String[] aux = input.split(", ");
		int[] indices = new int[aux.length];

		for (int i = 0; i < aux.length; i++)
			indices[i] = Integer.valueOf(aux[i]);
		
		matchingSelection = indices;
		matchingSelectionConfirmed = true;
	}
	
	// 0, 1, 2, ...
	public void onInputDefend(String input) {
		String[] aux = input.split(", ");
		int[] indices = new int[aux.length];

		for (int i = 0; i < aux.length; i++)
			indices[i] = Integer.valueOf(aux[i]);
		
		defendSelection = indices;
		defendSelectionConfirmed = true;
	}

}
