package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.Dungeon;
import Model.DungeonRoom;
import Model.Hero;
import Model.HeroFactory;
import Model.Item;


public class ViewGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * JPanel for JButtons on the NORTH part of the Frame.
     */
    private final JPanel myJPanel1;
    
    /**
     * JPanel for JButtons on the CENTER part of the Frame.
     */
    private final JPanel myJPanel2;
    
    /**
     * JPanel for JButtons on the SOUTH part of the Frame.
     */
    private final JPanel myJPanel3;
    
    /**
     * JPanel for JButtons on the East part of the Frame.
     */
    private final JPanel myJPanel4;
    
    /**
     * JPanel for JButtons on the WEST part of the Frame.
     */
    private final JPanel myJPanel5;
    
    public ViewGUI() {
    	super("Dungeon Adventure");
    	myJPanel1 = new JPanel();
        myJPanel2 = new JPanel();
        myJPanel3 = new JPanel();
        myJPanel4 = new JPanel();
        myJPanel5 = new JPanel();
    }
    
    public JPanel getPanel1() {
    	return myJPanel1;
    }
    
    public JPanel getPanel2() {
    	return myJPanel2;
    }
    
    public JPanel getPanel3() {
    	return myJPanel3;
    }
    
    public JPanel getPanel4() {
    	return myJPanel4;
    }
    
    public JPanel getPanel5() {
    	return myJPanel5;
    }
    
    public void resetPanel1() {
    	myJPanel1.removeAll();
    	EventQueue.invokeLater(() -> {
    	    myJPanel1.updateUI();
    	});
    	pack();
    }
    
    public void resetPanel2() {
    	myJPanel2.removeAll();
    	EventQueue.invokeLater(() -> {
    	    myJPanel2.updateUI();
    	});
    	pack();
    }
    
    public void resetPanel3() {
    	myJPanel3.removeAll();
    	EventQueue.invokeLater(() -> {
    	    myJPanel3.updateUI();
    	});
    	pack();
    }
    
    public void resetPanel4() {
    	myJPanel4.removeAll();
    	EventQueue.invokeLater(() -> {
    	    myJPanel4.updateUI();
    	});
    	pack();
    }
    
    public void resetPanel5() {
    	myJPanel5.removeAll();
    	EventQueue.invokeLater(() -> {
    	    myJPanel5.updateUI();
    	});
    	pack();
    }
    
    public void resetAllPanels() {
    	resetPanel1();
    	resetPanel2();
    	resetPanel3();
    	resetPanel4();
    	resetPanel5();
    	pack();
    }
    
    public void start() {
    	String currDirectory = System.getProperty("user.dir");
    	String image = currDirectory + "\\images\\Dunicon.jfif";
    	ImageIcon imageIcon = new ImageIcon(image);
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setIconImage(imageIcon.getImage());
		setPreferredSize(new Dimension(screenSize.width * 1 / 3, screenSize.height * 1 / 3));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        getPanel1().setLayout(new BoxLayout(getPanel1(), BoxLayout.PAGE_AXIS));
        getPanel2().setLayout(new BoxLayout(getPanel2(), BoxLayout.PAGE_AXIS));
        getPanel3().setLayout(new BoxLayout(getPanel3(), BoxLayout.PAGE_AXIS));
        getPanel4().setLayout(new BoxLayout(getPanel4(), BoxLayout.PAGE_AXIS));
        getPanel5().setLayout(new BoxLayout(getPanel5(), BoxLayout.PAGE_AXIS));

        
        add(getPanel1(), BorderLayout.NORTH);
        
        add(getPanel2(), BorderLayout.CENTER);

        add(getPanel3(), BorderLayout.SOUTH);
        add(getPanel4(), BorderLayout.EAST);
        add(getPanel5(), BorderLayout.WEST);
        
        pack();
        setVisible(true);
    }
    
    public void displayHowToPlay() {
    	JTextArea jta1 = new JTextArea("~~ Welcome to our Dungeon Adventure ~~\n" + 
    			"How to Play: Every time you enter a room you will be shown the items you picked up from the room or if there was a trap in the room.\n"
				+ "Then if there is a monster in the room it will begin combat with the monster. After monster is defeated you will be given two options to either move or look at inventory.\n"
				+ "As you move around the dungeon you are looking for the four pillars before you can exit (pillars: Abtraction, polymorphism, inheritance, and encapsulation).\n"
				+ "When you have found all four pillars if you make it to the exit you will have won the game. If at any point you get to 0 health or below you will have died and lose the game.");
    	JTextArea jta2 = new JTextArea("\nCombat: Based off the enemy you will be given a certain amount of attacks per round and every time your able to attack, you can either do a normal attack\n"
				+ "or a special that is unique for each class of hero, and normal and specials all have a chance to miss. After any attack that does damage to a monster they will have chance to heal.\n"
				+ "When your attacks have finished the monster will have one chance to attack you which will also have a chance to miss.");
    	JTextArea jta3 = new JTextArea("\nInventory: When you view the inventory you will be shown your health, heal/vision potions, and pillars found and you will be given the option either use an item or go back\n"
				+ "to the options menu. You are only able to use items if you have them." +
				"\nDeveloper Options: When in the options menu enter dungeon to view the whole dungeon.\n");
    	getPanel1().add(jta1);
    	getPanel1().add(jta2);
    	getPanel2().add(jta3);
    	pack();
    	
    }
    
    public boolean displayMainMenu() throws InterruptedException {
    	JLabel jlab = new JLabel("Would you like to create a new game(create) or load a save game(load):");
    	JTextField input = new JTextField(10);
    	getPanel3().add(jlab);
    	getPanel3().add(input);
    	pack();
    	
    	String answer = makeJTextFieldHoldForString(input);
    	
    	while(!(answer.equalsIgnoreCase("create") || answer.equalsIgnoreCase("load"))) {
    		resetPanel3();
    		pack();
    		jlab = new JLabel("Please enter a valid option");
    		input = new JTextField(10);
        	getPanel3().add(jlab);
        	getPanel3().add(input);
        	pack();
        	answer = makeJTextFieldHoldForString(input);
		}
    	
    	resetAllPanels();
    	return answer.equalsIgnoreCase("create");
    }
    
    /**
	 * User will enter the name of their hero, then calls the hero selection that obtains the class type they want to be.
	 * @return Hero that the user has created
     * @throws InterruptedException 
	 */
	public Hero heroName() throws InterruptedException {
		JLabel jlab = new JLabel("What do you want your hero's name to be:");
    	JTextField input = new JTextField(10);
    	getPanel1().add(jlab);
    	getPanel1().add(input);
    	pack();
		String name = makeJTextFieldHoldForString(input);
			
		return heroSelection(name);	
	}
	/**
	 * User enters the name of the class they wish to select and the Hero is created using the HeroFactory
	 * @param theName character name that the user wants to be called
	 * @return Hero that the user has created
	 * @throws InterruptedException 
	 */
	public Hero heroSelection(final String theName) throws InterruptedException {
		JLabel jlab = new JLabel("What hero class do you want to be (Warrior, Thief, berserker, or Priestess): ");
    	JTextField input = new JTextField(10);
    	getPanel1().add(jlab);
    	getPanel1().add(input);
    	pack();
		
		String heroType = makeJTextFieldHoldForString(input);
		
		
		HeroFactory hf = new HeroFactory();		
		Hero hero = null;

		boolean keepAsking = true;
		
		while(keepAsking) {
			try {
				hero = hf.createHero(heroType, theName);
				keepAsking = false;
			} catch(IllegalArgumentException e) {
				getPanel1().remove(input);
				getPanel1().remove(jlab);
	    		pack();
	    		jlab = new JLabel("Not a proper Hero type entered, try again. (Warrior, Thief, berserker, or Priestess): ");
	    		input = new JTextField(10);
	        	getPanel1().add(jlab);
	        	getPanel1().add(input);
	        	pack();
	        	heroType = makeJTextFieldHoldForString(input);
			}
		}
		return hero;
	}
	
	public int displayRows() throws InterruptedException {
		int rows;
		JLabel jlab = new JLabel("How many rows do you want the dungeon to be: ");
    	JTextField input = new JTextField(10);
    	getPanel1().add(jlab);
    	getPanel1().add(input);
    	pack();
		
		rows = makeJTextFieldHoldForInt(input);
		return rows;
	}
	
	public int displayColumns() throws InterruptedException {
		int cols;
		JLabel jlab = new JLabel("How many columns do you want the dungeon to be: ");
    	JTextField input = new JTextField(10);
    	getPanel1().add(jlab);
    	getPanel1().add(input);
    	pack();
		
		cols = makeJTextFieldHoldForInt(input);
		resetPanel1();
    	pack();
		return cols;
	}
	
	/**
	 * Display's the loaded save game files that exist and prompts the user to select a game
	 * @return save
	 * @throws InterruptedException 
	 */
	public String displayLoadSave() throws InterruptedException {
		StringBuilder sb = new StringBuilder();
		JTextArea jtext = new JTextArea();
		String currDirectory = System.getProperty("user.dir");
		String saveGameDir = currDirectory + "\\SaveGames";
		File folder = new File(saveGameDir);
		File[] listOfFiles = folder.listFiles();
		sb.append("List of save Games: \n");

		for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {
				String file = listOfFiles[i].getName();
			    sb.append(file.substring(0, file.length() - 4) + "\n");
			  } 
		}
		
		jtext.setText(sb.toString());
		getPanel1().add(jtext);
		
		JLabel jlab = new JLabel("Enter the name of a save you want to load:");
    	JTextField input = new JTextField(10);
    	getPanel1().add(jlab);
    	getPanel1().add(input);
    	pack();

		String save = makeJTextFieldHoldForString(input);
		save = saveGameDir + "\\" + save + ".ser";
		File tmpDir = new File(save);
		while(!tmpDir.exists()) {
			getPanel1().remove(input);
			getPanel1().remove(jlab);
    		pack();
    		jlab = new JLabel("Enter a valid save game: ");
    		input = new JTextField(10);
        	getPanel1().add(jlab);
        	getPanel1().add(input);
        	pack();
        	save = makeJTextFieldHoldForString(input);
			save = saveGameDir + "\\" + save + ".ser";
			tmpDir = new File(save);
		}
		
		resetAllPanels();
    	pack();
		return save;
	}
	
	/**
	 * Displays the text representation of the room the user is currently in
	 * @param theHero
	 */
	public void displayCurrRoom(final Hero theHero) {
		StringBuilder sb = new StringBuilder();
		sb.append("Current Room:\n");
		sb.append(theHero.getCurrRoom().toString());
		JTextArea jtext = new JTextArea(sb.toString());
		getPanel1().add(jtext);
	}
	
	public void displayText(final String theString, final int thePanel) {
		JLabel jlab = new JLabel(theString);
		switch(thePanel) {
			case 1: getPanel1().add(jlab);
					break;
			case 2: getPanel2().add(jlab);
					break;
			case 3: getPanel3().add(jlab);
					break;
			case 4: getPanel4().add(jlab);
					break;
			case 5: getPanel5().add(jlab);
					break;
		}
	}
	
	/**
	 * The options for the hero to attack
	 * 
	 * @return
	 * @throws InterruptedException 
	 */
	public String displayHeroAttacks() throws InterruptedException {
		JLabel jlab = new JLabel("You have a chance to attack would you like to use normal attack(enter normal) or special attack(enter special): ");
    	JTextField input = new JTextField(10);
    	getPanel2().add(jlab);
    	getPanel2().add(input);
    	pack();
    	
    	String answer = makeJTextFieldHoldForString(input);
    	
    	while(!(answer.equalsIgnoreCase("create") || answer.equalsIgnoreCase("load"))) {
    		resetPanel2();
    		pack();
    		jlab = new JLabel("Please enter valid attack option:");
    		input = new JTextField(10);
        	getPanel2().add(jlab);
        	getPanel2().add(input);
        	pack();
        	answer = makeJTextFieldHoldForString(input);
		}
    	
    	return answer;
	}
	
	/**
	 * 
	 * 
	 * @param theHero
	 * @param theDungeon
	 * @return
	 * @throws InterruptedException 
	 */
	public boolean displayOptions(final Hero theHero, final Dungeon theDungeon) throws InterruptedException {
		JLabel jlab = new JLabel("Would you like move on(move), or View inventory(inventory), or Save game and exit(save):");
    	JTextField input = new JTextField(10);
    	getPanel2().add(jlab);
    	getPanel2().add(input);
    	pack();
		
    	String choice = makeJTextFieldHoldForString(input);
    	
		while(!(choice.equalsIgnoreCase("move") || choice.equalsIgnoreCase("dungeon") || choice.equalsIgnoreCase("inventory") || choice.equalsIgnoreCase("save"))) {
			resetPanel2();
    		pack();
    		jlab = new JLabel("Not a valid option. Please enter a valid option (move, inventory, or save):");
    		input = new JTextField(10);
        	getPanel2().add(jlab);
        	getPanel2().add(input);
        	pack();
        	choice = makeJTextFieldHoldForString(input);
		}
		
		if(choice.equalsIgnoreCase("inventory")) {
			resetAllPanels();
			displayText(theHero.toString(), 1);
			resetAllPanels();
			displayInventory(theHero, theDungeon);
			return displayOptions(theHero, theDungeon);
		} else if(choice.equalsIgnoreCase("dungeon")) {
			displayText("Dungeon:\n" + theDungeon.toString(), 1);
			return displayOptions(theHero, theDungeon);
		} else if(choice.equalsIgnoreCase("move")) {
			resetAllPanels();
			return false;
		} else {
			resetAllPanels();
			return true;
		} 
	}
	
	/**
	 * Displays the current inventory of items the user has
	 * 
	 * @param theHero
	 * @param theDungeon
	 * @throws InterruptedException 
	 */
	public void displayInventory(final Hero theHero, final Dungeon theDungeon) throws InterruptedException {		
		JLabel jlab = new JLabel("Would you like to use an item(use) or go back to options(options):");
    	JTextField input = new JTextField(10);
    	getPanel2().add(jlab);
    	getPanel2().add(input);
    	pack();
		
    	String choice = makeJTextFieldHoldForString(input);
    	
		while(!(choice.equalsIgnoreCase("use") || choice.equalsIgnoreCase("options"))) {
			resetPanel2();
    		pack();
    		jlab = new JLabel("Not a valid option. Please enter a valid option (use or options)");
    		input = new JTextField(10);
        	getPanel2().add(jlab);
        	getPanel2().add(input);
        	pack();
        	choice = makeJTextFieldHoldForString(input);
		}
		
		resetPanel2();
		
		if(choice.equalsIgnoreCase("use")) {
			jlab = new JLabel("Would you like to use a heal potion(heal) or a vision potion(vision):");
	    	input = new JTextField(10);
	    	getPanel2().add(jlab);
	    	getPanel2().add(input);
	    	pack();
			
	    	String item = makeJTextFieldHoldForString(input);
	    	
			if(item.equalsIgnoreCase("heal") || item.equalsIgnoreCase("vision")) {
				Item useItem = theHero.removeItemFromInventory(item);
				if(useItem != null) {
					int heroHealth = theHero.getHitPoints();
					if(theHero.useItem(useItem)) {
						displayText("After using the vision potion you are able to see the surrounding rooms.", 2);
						displayText(theHero.displayDungeonNearHero(theDungeon), 2);
						displayInventory(theHero, theDungeon);
					} else {
						displayText("The heal potion healed you for " + (theHero.getHitPoints() - heroHealth) + " . Current health is " + theHero.getHitPoints(), 2);
						displayInventory(theHero, theDungeon);
					}
				} else {
					displayText("No item to use.", 2);
					displayInventory(theHero, theDungeon);
				}
			} else {
				displayText("\nEnter a valid item to use", 2);
				displayInventory(theHero, theDungeon);
			}
		} 
	}
	
	/**
	 * User will enter what they want their save to be named
	 * @return SaveGame
	 * @throws InterruptedException 
	 */
	public String displaySaveGame() throws InterruptedException {
		JLabel jlab = new JLabel("What do you want the name of the save to be?");
    	JTextField input = new JTextField(10);
    	getPanel2().add(jlab);
    	getPanel2().add(input);
    	pack();
		
    	return makeJTextFieldHoldForString(input);	
	}
	
	/**
	 * User has the option to overwrite file if they have entered a new name that already exists
	 * @return OverWrite
	 * @throws InterruptedException 
	 */
	public String displayOverWrite() throws InterruptedException {
		JLabel jlab = new JLabel("Would you like to overwrite this file(yes or no):");
    	JTextField input = new JTextField(10);
    	getPanel2().add(jlab);
    	getPanel2().add(input);
    	pack();
    	
    	String answer = makeJTextFieldHoldForString(input);
		
		while(!(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no"))) {
			getPanel2().remove(jlab);
			getPanel2().remove(input);
    		pack();
    		jlab = new JLabel("Not a valid input please enter (yes or no):");
    		input = new JTextField(10);
        	getPanel2().add(jlab);
        	getPanel2().add(input);
        	pack();
        	answer = makeJTextFieldHoldForString(input);
		}
		
		return answer;
	}
	
	/**
	 * Prompts the user with the only possible directions they can travel in the current room and calls the method that asks for 
	 * which direction they wish to move
	 * 
	 * @param theRoom
	 * @return getMoveOption
	 * @throws InterruptedException 
	 */
	public String displayMoveOptions(final DungeonRoom theRoom) throws InterruptedException {
		 StringBuilder sb = new StringBuilder();
		 sb.append("\nYou look around the room and see that there are doors to go");
		 String[] options = new String[4];
		 int i = 0;
		 
		 if(theRoom.isNorth()) {
			 options[i++] = "up";
			 sb.append(" Up");
			 if(theRoom.isWest() || theRoom.isEast() || theRoom.isSouth()) {
				 sb.append(" or");
			 }
		 }
		 
		 if(theRoom.isWest()) {
			 options[i++] = "left";
			 sb.append(" Left");
			 if(theRoom.isEast() || theRoom.isSouth()) {
				 sb.append(" or");
			 }
		 }
		 
		 if(theRoom.isEast()) {
			 options[i++] = "right";
			 sb.append(" Right");
			 if(theRoom.isSouth()) {
				 sb.append(" or");
			 }
		 }
		 
		 if(theRoom.isSouth()) {
			 options[i] = "down";
			 sb.append(" Down");
		 }
		 
		 displayText(sb.toString(), 1);
		 
		 return getMoveOption(theRoom, options);
	}
	
	/**
	 * Prompts the user by asking which way they would like to travel
	 * 
	 * @param theRoom
	 * @param theOptions
	 * @return direction
	 * @throws InterruptedException 
	 */
	public String getMoveOption(final DungeonRoom theRoom, final String[] theOptions) throws InterruptedException {
		JLabel jlab = new JLabel();
    	JTextField input = new JTextField(10);
    	getPanel2().add(jlab);
    	getPanel2().add(input);
    	pack();
    	
    	String direction = makeJTextFieldHoldForString(input);
		direction.toLowerCase();
		
		
		while(!(Arrays.asList(theOptions).contains(direction))) {
			getPanel2().remove(jlab);
			getPanel2().remove(input);
    		pack();
    		jlab = new JLabel("Invalid direction please enter another");
    		input = new JTextField(10);
        	getPanel2().add(jlab);
        	getPanel2().add(input);
        	pack();
        	direction = makeJTextFieldHoldForString(input);
			direction.toLowerCase();
		}
		
		resetAllPanels();
		return direction;
		
	}

    
    private static String makeJTextFieldHoldForString(final JTextField theTextField) throws InterruptedException {
    	final List<String> holder = new LinkedList<String>();
    	theTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (holder) {
                    holder.add(theTextField.getText());
                    holder.notify();
                }
            }
        });
    	
    	synchronized (holder) {

            // wait for input from field
            while (holder.isEmpty())
                holder.wait();

            String answer = holder.remove(0);
            return answer;
        }
    }
    
    private static int makeJTextFieldHoldForInt(final JTextField theTextField) throws InterruptedException {
    	final List<Integer> holder = new LinkedList<Integer>();
    	theTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (holder) {
                    holder.add(Integer.parseInt(theTextField.getText()));
                    holder.notify();
                }
            }
        });
    	
    	synchronized (holder) {

            // wait for input from field
            while (holder.isEmpty())
                holder.wait();

            int answer = holder.remove(0);
            return answer;
        }
    }

}
