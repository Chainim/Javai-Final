package com.unicamp.mc322.javai_final;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.unicamp.mc322.javai_final.cards.models.ModelRegistry;
import com.unicamp.mc322.javai_final.display.InterfaceScreen;
import com.unicamp.mc322.javai_final.gamestate.DefendState;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;
import com.unicamp.mc322.javai_final.lang.Lang;
import com.unicamp.mc322.javai_final.lang.Localizer;

public class Game {

	private GameStateManager stateManager;
	private boolean running;

	private JFrame window;
	private JTextArea textArea;
	private InterfaceScreen iScreen;

	public Game() {
		stateManager = new GameStateManager();
		
		// interface grafica
		window = new JFrame("Game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(new Dimension(960, 720));
		window.setResizable(false);
		window.setLayout(null);
		
		iScreen = new InterfaceScreen(window);
		
	}

	public void start() {
		Localizer.localizerInit(Lang.PT_BR);
		ModelRegistry.initModels();

		running = true;

		stateManager.init();

		window.setVisible(true);
		
		iScreen.show();
		textArea = iScreen.getTextArea();
		
		loop();
	}

	void readInput() {
		if((stateManager.getCurrentPlayer().isAI() && !(stateManager.getCurrentState() instanceof DefendState)) || (stateManager.getOpponentPlayer().isAI() && stateManager.getCurrentState() instanceof DefendState)) {
			stateManager.onInput("souia");
			return;
		} 
		if(textArea.getText().endsWith("\n")) {
			String tex = textArea.getText();
			textArea.setText("");
			stateManager.onInput(tex.substring(0, tex.length() - 1));
		}
	}

	public void loop() {
		
		while(running) {

			readInput();
			stateManager.update();
			stateManager.draw();
			
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.start();

	}
}
