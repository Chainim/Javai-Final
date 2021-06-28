package com.unicamp.mc322.javai_final.display;

import javax.swing.JLabel;

public class TextScreen extends Screen{

	char[][] screenbuff;
	private JLabel textLabel;
	
	public TextScreen(JLabel label) {
		screenbuff = new char[16][120];
		for(int i = 0; i < screenbuff.length; i++)
			for(int j = 0; j < screenbuff[i].length; j++)
				screenbuff[i][j] = ' ';
		this.textLabel = label;
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
	
	public void drawNexus(int posx, int posy) {
		screenbuff[posy][posx + 2] = '_';
		screenbuff[posy][posx + 3] = '_';
		screenbuff[posy + 1][posx + 1] = '/';
		screenbuff[posy + 1][posx + 4] = '\\';
		screenbuff[posy + 2][posx] = '|';
		screenbuff[posy + 3][posx + 1] = '\\';
		screenbuff[posy + 3][posx + 2] = '_';
		screenbuff[posy + 3][posx + 3] = '_';
		screenbuff[posy + 3][posx + 4] = '/';
		screenbuff[posy + 2][posx + 5] = '|';
		screenbuff[posy + 2][posx + 2] = 'h';
		screenbuff[posy + 2][posx + 3] = 'h';
	}

	@Override
	public void render() {
		
		String text = "<html>";
		text += "<pre>";
		for(int i = 0; i < screenbuff.length; i++) {
			for(int j = 0; j < screenbuff[i].length; j++) {
				//System.out.print(screenbuff[i][j]);
				text += screenbuff[i][j];
			}
			//System.out.println();
			text += "<br/>";
		}
		text += "</pre>";
		text += "</html>";
		textLabel.setText(text);
	}



}
