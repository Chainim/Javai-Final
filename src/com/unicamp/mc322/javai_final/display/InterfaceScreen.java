package com.unicamp.mc322.javai_final.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import com.unicamp.mc322.javai_final.gamestate.GameStateManager;

public class InterfaceScreen {
	private JFrame frame;
	private List<JButton> handCards, fieldCards;
	private JProgressBar[] manaBars;
	private static InterfaceScreen instance;
	private JTextArea textArea;
	
	public InterfaceScreen(JFrame window) {
		this.frame = window;
		handCards = new ArrayList<JButton>();
		fieldCards = new ArrayList<JButton>();
		manaBars = new JProgressBar[2];
		instance = this;
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}
	
	public static InterfaceScreen getInterfaceScreen() {
		return instance;
	}
	
	public List<JButton> getHandCards(){
		return handCards;
	}
	
	public List<JButton> getFieldCards(){
		return fieldCards;
	}
	
	public JProgressBar[] getManaBars() {
		return manaBars;
	}

	public void show() {

		buildManaBar();
		buildButton();
		buildHandCards();
		buildFieldCards();
		frame.setVisible(true);
		
		//((JLabel) handCards.get(0).getComponent(1)).setText("ola");
		
		// frame.getContentPane().getComponent(1).setVisible(false);

		
		 textArea = new JTextArea(); textArea.setLocation(4,
		 frame.getHeight() - 60); textArea.setPreferredSize(new
		 Dimension(frame.getWidth() - 24, 20));
		 textArea.setSize(textArea.getPreferredSize()); frame.add(textArea);
		 
	}

	public void buildHandCards() {
		for (int j = 0; j < 2; j++) {
			JPanel cardsPanel = new JPanel();

			FlowLayout f = new FlowLayout();
			f.setHgap(6);
			cardsPanel.setLayout(f);

			for (int i = 0; i < 10; i++) {
				JButton button = buildCard(new ActionListener() {
					
					private int cardIndex;

					@Override
					public void actionPerformed(ActionEvent e) {
						System.err.println("Clicou na carta: " + cardIndex);
						GameStateManager.getInstance().onInput("hand " + Integer.toString(cardIndex));
					}

					public ActionListener setIndex(int index) {
						cardIndex = index;
						return this;
					}
				}.setIndex(i));
				
				handCards.add(button);
				cardsPanel.add(button);
			}
			cardsPanel.setSize(cardsPanel.getPreferredSize());
			cardsPanel.setLocation(0, (1 - j) * (frame.getHeight() - 60 - cardsPanel.getHeight()));

			frame.add(cardsPanel);
		}
	}

	public void buildButton() {
		JButton button = new JButton();

		ActionListener buttonEvents = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.err.println("Clicou em Done");
				GameStateManager.getInstance().onInput("done");
			}
			
		};

		button.setPreferredSize(new Dimension(100, 50));
		button.setLocation(frame.getWidth() / 2 + 150, frame.getHeight() / 2 - 55);
		button.setSize(100, 50);
		button.setContentAreaFilled(true);
		button.setBackground(Color.green);
		button.setText("Done");
		button.addActionListener(buttonEvents);

		frame.add(button);
	}

	public void buildFieldCards() {

		for (int j = 0; j < 2; j++) {
			FlowLayout f = new FlowLayout();
			JPanel field = new JPanel();
			f.setHgap(3);
			field.setLayout(f);

			for (int i = 0; i < 6; i++) {
				ActionListener buttonEvents = new ActionListener() {
					private int cardIndex;
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.err.println("Clicou em FieldCard " + cardIndex);
						GameStateManager.getInstance().onInput("field " + Integer.toString(cardIndex));
					}
					public ActionListener setIndex(int index) {
						cardIndex = index;
						return this;
					}
				}.setIndex(i);
				
				JButton p = new JButton();
				p.addActionListener(buttonEvents);
				p.setMargin(new Insets(0, 0, 0, 0));
				p.setPreferredSize(new Dimension(50, 50));
				p.setSize(p.getPreferredSize());
				p.setBackground(Color.gray);
				
				SpringLayout s = new SpringLayout();
				p.setLayout(s);

				JLabel attackLabel = new JLabel("A");
				JLabel healthLabel = new JLabel("D");


				s.putConstraint(SpringLayout.WEST, attackLabel, 1, SpringLayout.WEST, p);
				s.putConstraint(SpringLayout.SOUTH, attackLabel, 0, SpringLayout.SOUTH, p);

				s.putConstraint(SpringLayout.EAST, healthLabel, -1, SpringLayout.EAST, p);
				s.putConstraint(SpringLayout.SOUTH, healthLabel, 0, SpringLayout.SOUTH, p);
				
				p.add(attackLabel);
				p.add(healthLabel);
				
				fieldCards.add(p);
				field.add(p);
			}
		
			field.setSize(field.getPreferredSize());

			field.setLocation((frame.getWidth() - field.getWidth() - 70) / 2, frame.getHeight() / 2 - 30 + (j) * -60);
			frame.add(field);
		}

	}

	public void buildManaBar() {

		for (int j = 0; j < 2; j++) {
			JProgressBar manaBar = new JProgressBar(0, 10);
			manaBar.setLocation(frame.getWidth() - 200, frame.getHeight() / 2 + 10 + j * (-100));
			manaBar.setSize(100, 20);
			manaBar.setValue(0);
			manaBar.setString("Mana: " + 0);
			manaBar.setStringPainted(true);
			
			frame.add(manaBar);
			
			manaBars[j] = manaBar;
		}
	}
	
	public JButton buildCard(ActionListener l) {
		JButton card = new JButton();
		card.setMargin(new Insets(0, 0, 0, 0));
		card.setBackground(Color.LIGHT_GRAY);
		
		card.setPreferredSize(new Dimension(90, 150));
		card.setSize(card.getPreferredSize());
		
		card.addActionListener(l);
		
		SpringLayout s = new SpringLayout();
		card.setLayout(s);
		
		JLabel costLabel = new JLabel("C");
		JLabel attackLabel = new JLabel("A");
		JLabel healthLabel = new JLabel("D");
		
		JLabel nameLabel = new JLabel("Name");
		//System.out.println(nameLabel.getFont().getName());
		//nameLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		
		s.putConstraint(SpringLayout.WEST, costLabel, 1, SpringLayout.WEST, card);
		s.putConstraint(SpringLayout.NORTH, costLabel, 0, SpringLayout.NORTH, card);
		
		s.putConstraint(SpringLayout.WEST, attackLabel, 1, SpringLayout.WEST, card);
		s.putConstraint(SpringLayout.SOUTH, attackLabel, 0, SpringLayout.SOUTH, card);
		
		s.putConstraint(SpringLayout.EAST, healthLabel, -1, SpringLayout.EAST, card);
		s.putConstraint(SpringLayout.SOUTH, healthLabel, 0, SpringLayout.SOUTH, card);
		
		s.putConstraint(SpringLayout.HORIZONTAL_CENTER, nameLabel, 0, SpringLayout.HORIZONTAL_CENTER, card);
		s.putConstraint(SpringLayout.VERTICAL_CENTER, nameLabel, 0, SpringLayout.VERTICAL_CENTER, card);
		
		card.add(costLabel);
		card.add(attackLabel);
		card.add(healthLabel);
		card.add(nameLabel);
		
		return card;
	}
}
