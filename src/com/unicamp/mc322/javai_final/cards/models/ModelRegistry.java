package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.CardModel;

public class ModelRegistry {
	public static CardModel DEFENDER;
	public static CardModel DOUBLED_VALUE;
	public static CardModel DUELIST;
	public static CardModel GAREN;
	public static CardModel HEAD_TO_HEAD;
	public static CardModel ON_TARGET;
	public static CardModel PORO_DEFENDER;
	public static CardModel PORO;
	public static CardModel SMITE;
	public static CardModel TIANA;
	public static CardModel VANGUARD;
	
	public static void initModels() {
		DEFENDER = new DefenderModel();
		DOUBLED_VALUE = new DoubledValueModel();
		DUELIST = new DuelistModel();
		GAREN = new GarenModel();
		HEAD_TO_HEAD = new HeadToHeadModel();
		ON_TARGET = new OnTargetModel();
		PORO_DEFENDER = new PoroDefenderModel();
		PORO = new PoroModel();
		SMITE = new SmiteModel();
		TIANA = new TianaModel();
		VANGUARD = new VanguardModel();
	}
}
