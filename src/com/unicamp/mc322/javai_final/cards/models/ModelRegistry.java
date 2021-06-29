package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.CardModel;

public class ModelRegistry {
	public static CardModel DEFENDER;
	public static CardModel PORO;
	
	public static void initModels() {
		DEFENDER = new DefenderModel();
		PORO = new PoroModel();
	}
}
