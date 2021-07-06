package com.unicamp.mc322.javai_final;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import com.unicamp.mc322.javai_final.cards.models.ModelRegistry;
import com.unicamp.mc322.javai_final.display.Screen;
import com.unicamp.mc322.javai_final.display.SwingScreen;
import com.unicamp.mc322.javai_final.display.TextScreen;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;
import com.unicamp.mc322.javai_final.lang.Lang;
import com.unicamp.mc322.javai_final.lang.Localizer;

public class Game {
	
	private GameStateManager stateManager;
	private boolean running;
	
	private JFrame window;
	private JLabel label;
	private JTextArea textArea;
	private Screen screen;
	
	public Game() {
		 stateManager = new GameStateManager();
		 
		 window = new JFrame("Game");
		 window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 window.setSize(960, 720);
		 window.setResizable(false);
		 
		 label = new JLabel();
		 textArea = new JTextArea();
		 
		 window.add(label);
		 window.add(textArea, BorderLayout.SOUTH);
		 
		 window.setLocationRelativeTo(null);
		 
		 screen = new TextScreen(label);
	}
	
	public void start() {
		Localizer.localizerInit(Lang.PT_BR);
		ModelRegistry.initModels();
		
		running = true;
		
		stateManager.init();
		
		window.setVisible(true);
		
		loop();
	}
	
	void readInput() {
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
			stateManager.draw(screen);
			screen.render();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void teste() {
		JFrame frame = new JFrame("Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(960, 720));
		frame.setResizable(false);
		
		frame.setLayout(null);
		
		for(int j = 0; j < 2; j++) {
			JPanel cardsPanel = new JPanel();
			
			FlowLayout f = new FlowLayout();
			f.setHgap(6);
			cardsPanel.setLayout(f);
			
			for(int i = 0; i < 10; i++) {
				cardsPanel.add(SwingScreen.buildCard(new ActionListener() {
					
					private int cardIndex;
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("Clicou na carta: " + cardIndex);
					}
					
					public ActionListener setIndex(int index) {
						cardIndex = index;
						return this;
					}
				}.setIndex(i)));
			}
			cardsPanel.setSize(cardsPanel.getPreferredSize());
			cardsPanel.setLocation(0, (1 - j) * (frame.getHeight() - 60 - cardsPanel.getHeight()));
			
			frame.add(cardsPanel);
			
			JProgressBar manaBar = new JProgressBar(0, 10);
			manaBar.setLocation(frame.getWidth() - 200, (1 - j) * (frame.getHeight() - 40 - cardsPanel.getHeight()));
			manaBar.setSize(100, 20);
			manaBar.setValue(0);
			manaBar.setString("Mana: " + 0);
			manaBar.setStringPainted(true);
			
			frame.add(manaBar);
			
			JPanel field = buildField();
			field.setLocation((frame.getWidth() - field.getWidth() - 70) / 2, frame.getHeight() / 2 - 30 + (1 - j) * -60);
			
			frame.add(field);
		}
		
		//frame.getContentPane().getComponent(1).setVisible(false);
		
		JTextArea textArea = new JTextArea();
		textArea.setLocation(4, frame.getHeight() - 60);
		textArea.setPreferredSize(new Dimension(frame.getWidth() - 24, 20));
		textArea.setSize(textArea.getPreferredSize());
		frame.add(textArea);
		
		frame.setVisible(true);
		
		JProgressBar manaBar = (JProgressBar)frame.getContentPane().getComponent(1);
		
		for(int i = 0; i <= 10; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			manaBar.setValue(i);
			manaBar.setString("Mana: " + i);
		}
	}
	
	public static JPanel buildField() {
		FlowLayout f = new FlowLayout();
		JPanel field = new JPanel();
		f.setHgap(3);
		field.setLayout(f);
		
		for(int i = 0; i < 6; i++) {
			
			JPanel p = new JPanel();
			p.setPreferredSize(new Dimension(50, 50));
			p.setSize(p.getPreferredSize());
			p.setBackground(Color.gray);
			
			SpringLayout s = new SpringLayout();
			p.setLayout(s);
			
			JLabel attackLabel = new JLabel("A");
			JLabel healthLabel = new JLabel("D");
			
			p.add(attackLabel);
			p.add(healthLabel);
			
			s.putConstraint(SpringLayout.WEST, attackLabel, 1, SpringLayout.WEST, p);
			s.putConstraint(SpringLayout.SOUTH, attackLabel, 0, SpringLayout.SOUTH, p);
			
			s.putConstraint(SpringLayout.EAST, healthLabel, -1, SpringLayout.EAST, p);
			s.putConstraint(SpringLayout.SOUTH, healthLabel, 0, SpringLayout.SOUTH, p);
			
			field.add(p);
		}
		
		field.setSize(field.getPreferredSize());
		
		return field;
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.start();
		
		//teste();
	}
}
