package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.CardModel;

public class ModelRegistry {
	public static CardModel DEFENDER;
	public static CardModel PORO;
	public static CardModel GAREN;
	public static CardModel TIANA;
	public static CardModel PORO_DEFENDER;
	public static CardModel SMITE;
	public static CardModel DOUBLED_VALUE;
	public static CardModel HEAD_TO_HEAD;
	public static CardModel VANGUARD;
	
	public static void initModels() {
		DEFENDER = new DefenderModel();
		PORO = new PoroModel();
		GAREN = new GarenModel();
		TIANA = new TianaModel();
		PORO_DEFENDER = new PoroDefenderModel();
		SMITE = new SmiteModel();
		DOUBLED_VALUE = new DoubledValueModel();
		HEAD_TO_HEAD = new HeadToHeadModel();
		VANGUARD = new VanguardModel();
	}
}
