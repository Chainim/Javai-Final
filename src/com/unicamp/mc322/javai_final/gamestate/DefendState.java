package com.unicamp.mc322.javai_final.gamestate;

import java.util.ArrayList;

public class DefendState extends GameState {
	private boolean defendSelectionConfirmed;
	private boolean matchingSelectionConfirmed;
	private int[] defendSelection;
	private int[] matchingSelection;
	private int[] attackSelection;
	private ArrayList<Integer> cardsIndices; 
	
 	
	protected DefendState(GameStateManager manager, int[] attackSelection) {
		super(manager);
		this.attackSelection = attackSelection;
		cardsIndices = new ArrayList<Integer>();
	}
	
	public void setDefendSelection() {
		defendSelection = new int[cardsIndices.size()];
		
		for(int i = 0;i < defendSelection.length;i++) {
			defendSelection[i] = cardsIndices.get(i);
		}
	}
	
	public void setMatchingSelection() {
		matchingSelection = new int[cardsIndices.size()];
		
		for(int i = 0;i < matchingSelection.length;i++) {
			matchingSelection[i] = cardsIndices.get(i);
		}
	}
	
	@Override
	public void onStateLoad() {
		// lembrar de printar attackSelection
		
		defendSelectionConfirmed = false;
		matchingSelectionConfirmed = false;
	}
	
	@Override
	public void update() {
		if(defendSelectionConfirmed == true && matchingSelectionConfirmed == true) {
			getManager().setState(new RoundEndState(getManager(), defendSelection, attackSelection, matchingSelection));
		}
	}
	
	public void onInput(String input) {
		if(input.equals("done")) {
			if(defendSelectionConfirmed == true) {
				onInputMatchingSelection();
			}
			else {
				onInputDefendSelection();
			}
			return;
		}
		
		cardsIndices.add(Integer.parseInt(input) - Integer.parseInt("0"));
	}
	
	public void onInputDefendSelection() {
		if(cardsIndices.size() > attackSelection.length) {
			System.err.println("Invalid selection for defendSelection");
			return;
		}
		defendSelectionConfirmed = true;
		setDefendSelection();
		cardsIndices.clear();
	}
	
	public void onInputMatchingSelection() {
		if(cardsIndices.size() != attackSelection.length) {
			System.err.println("Invalid selection for matchingSelection");
			return;
		}
		matchingSelectionConfirmed = true;
		setMatchingSelection();
		cardsIndices.clear();
	}

}
