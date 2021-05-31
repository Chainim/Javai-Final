package com.unicamp.mc322.javai_final.display;

public class TextScreen extends Screen{

	char[][] screenbuff;
	
	public TextScreen() {
		screenbuff = new char[10][120];
	}
	
	@Override
	public void drawCard(int posx, int posy) {
		int cardWidth = 12;
		int cardHeight = 10;
		for(int i = 0; i < cardWidth; i++) {
			screenbuff[posy][posx + i] = '-';
			screenbuff[posy + cardHeight - 1][posx + i] = '-';
		}
		for(int i = 0; i < cardHeight; i++) {
			screenbuff[posy + i][posx + 0] = '|';
			screenbuff[posy + i][posx + cardWidth - 1] = '|';
		}
		screenbuff[posy + 0][posx + 0] = '+';
		screenbuff[posy + 0][posx + cardWidth - 1] = '+';
		screenbuff[posy + cardHeight - 1][posx + 0] = '+';
		screenbuff[posy + cardHeight - 1][posx + cardWidth - 1] = '+';
		
		screenbuff[posy + 1][posx + 1] = 'c';
		screenbuff[posy + cardHeight - 2][posx + 1] = 'a';
		screenbuff[posy + cardHeight - 2][posx + cardWidth - 2] = 'd';
		
		
		String firstName = "Card";
		int auxx = posx + (cardWidth - firstName.length()) / 2;
		for(int i = 0; i < firstName.length(); i++)
			screenbuff[posy + cardHeight - 5][auxx + i] = firstName.charAt(i);
		
		String secondName = "Name";
		auxx = posx + (cardWidth - secondName.length()) / 2;
		for(int i = 0; i < secondName.length(); i++)
			screenbuff[posy + cardHeight - 4][auxx + i] = secondName.charAt(i);
	}

	@Override
	public void render() {
		for(int i = 0; i < screenbuff.length; i++) {
			for(int j = 0; j < screenbuff[i].length; j++)
				System.out.print(screenbuff[i][j]);
			System.out.println();
		}
	}



}
