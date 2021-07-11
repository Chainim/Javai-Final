package com.unicamp.mc322.javai_final.util;

import com.unicamp.mc322.javai_final.gamestate.GameStateManager;

public class InputUtils {
	public static int expectCardOnCurrentPlayerField(String input) {
		GameStateManager manager = GameStateManager.getInstance();
		if(input.equals("done")) {
			System.err.println("Clique em alguma carta do campo para aplicar o valor redobrado");
			return -1;
		}
		String[] s = input.split(" ");
		if(Integer.parseInt(s[2]) != manager.getCurrentPlayerIndex()) {
			System.err.println("Selecione somente suas coisas");
			return -1;
		} else if(s[0].equals("hand")) {
			System.err.println("Selecione alguma carta do campo");
			return -1;
		} else {
			int index = Integer.parseInt(s[1]);
			if(manager.getCurrentPlayer().getFieldCards()[index] == null) {
				System.err.println("Campo vazio");
				return -1;
			} else {
				return index;
			}
		}
	}
	
	public static int expectCardOnOpponentPlayerField(String input) {
		GameStateManager manager = GameStateManager.getInstance();
		if(input.equals("done")) {
			System.err.println("Clique em alguma carta do campo inimigo para defender");
			return -1;
		}
		String[] s = input.split(" ");
		if(Integer.parseInt(s[2]) != manager.getOpponentPlayerIndex()) {
			System.err.println("Selecione somente suas coisas");
			return -1;
		} else if(s[0].equals("hand")) {
			System.err.println("Selecione alguma carta do campo");
			return -1;
		} else {
			int index = Integer.parseInt(s[1]);
			if(manager.getOpponentPlayer().getFieldCards()[index] == null) {
				System.err.println("Campo vazio");
				return -1;
			} else {
				return index;
			}
		}
	}
}
