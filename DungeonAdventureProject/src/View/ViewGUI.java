package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
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
    
    private final JTextArea myTextArea;
    
    private JTextField myTextField;
    
    private final JScrollPane myScroll;
    
    public ViewGUI() {
    	super("Dungeon Adventure");
    	myJPanel1 = new JPanel();
        myJPanel2 = new JPanel();
        myJPanel3 = new JPanel();
        myJPanel4 = new JPanel();
        myJPanel5 = new JPanel();
        myTextArea = new JTextArea();
        myTextField = new JTextField(20);
        myScroll = new JScrollPane(myTextArea);
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
    
    public JTextField getTextField() {
    	return myTextField;
    }
    
    public JTextArea getTextArea() {
    	return myTextArea;
    }
    
    public JScrollPane getScrollPane() {
    	return myScroll;
    }
    
    public void resetText() {
    	myTextField.setText("");
    }
    
    public void resetTextField() {
    	myTextField = new JTextField(20);
    	resetPanel3();
    	getPanel3().add(getTextField());;
        getPanel3().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
    
    public void addTexttoTextArea(final String theString) {
    	getTextArea().append("\n" + theString);
    	getTextArea().validate();
    	JScrollBar vertical = getScrollPane().getVerticalScrollBar();
    	vertical.setValue(vertical.getMaximum());
    }
    
    public void updateTextArea() {
    	getScrollPane().validate();
    	JScrollBar vertical = getScrollPane().getVerticalScrollBar();
    	vertical.setValue(vertical.getMaximum());
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
        getPanel3().setLayout(new GridBagLayout());
        getPanel4().setLayout(new BoxLayout(getPanel4(), BoxLayout.PAGE_AXIS));
        getPanel5().setLayout(new BoxLayout(getPanel5(), BoxLayout.PAGE_AXIS));
        

        getPanel3().add(getTextField());;
        getPanel3().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        getTextArea().setEditable(false);
        getPanel2().add(getScrollPane());
        
        
        add(getPanel1(), BorderLayout.NORTH);
        
        add(getPanel2(), BorderLayout.CENTER);

        add(getPanel3(), BorderLayout.SOUTH);
        add(getPanel4(), BorderLayout.EAST);
        add(getPanel5(), BorderLayout.WEST);
        
        pack();
        setVisible(true);
    }
    
    public void displayHowToPlay() {
    	addTexttoTextArea("~~ Welcome to our Dungeon Adventure ~~\n" + 
    			"How to Play: Every time you enter a room you will be shown the items you picked up from the room or if there was a trap in the room.\n"
				+ "Then if there is a monster in the room it will begin combat with the monster. After monster is defeated you will be given two options to either move or look at inventory.\n"
				+ "As you move around the dungeon you are looking for the four pillars before you can exit (pillars: Abtraction, polymorphism, inheritance, and encapsulation).\n"
				+ "When you have found all four pillars if you make it to the exit you will have won the game. If at any point you get to 0 health or below you will have died and lose the game.");
    	addTexttoTextArea("\nCombat: Based off the enemy you will be given a certain amount of attacks per round and every time your able to attack, you can either do a normal attack\n"
				+ "or a special that is unique for each class of hero, and normal and specials all have a chance to miss. After any attack that does damage to a monster\n"
				+ "they will have chance to heal.When your attacks have finished the monster will have one chance to attack you which will also have a chance to miss.");
    	addTexttoTextArea("\nInventory: When you view the inventory you will be shown your health, heal/vision potions, and pillars found and you will be given the option either use an item or go back\n"
				+ "to the options menu. You are only able to use items if you have them." +
				"\nDeveloper Options: When in the options menu enter dungeon to view the whole dungeon.\n");
    	pack();
    	
    }
    
    public boolean displayMainMenu() throws InterruptedException {
    	addTexttoTextArea("Would you like to create a new game(create) or load a save game(load):");
    	pack();
    	
    	String answer = makeJTextFieldHoldForString(getTextField());
    	
    	while(!(answer.equalsIgnoreCase("create") || answer.equalsIgnoreCase("load"))) {
    		addTexttoTextArea("Please enter a valid option");
        	pack();
        	answer = makeJTextFieldHoldForString(getTextField());
		}
    	resetText();
    	return answer.equalsIgnoreCase("create");
    }
    
    /**
	 * User will enter the name of their hero, then calls the hero selection that obtains the class type they want to be.
	 * @return Hero that the user has created
     * @throws InterruptedException 
	 */
	public Hero heroName() throws InterruptedException {
		addTexttoTextArea("What do you want your hero's name to be:");
    	pack();
		String name = makeJTextFieldHoldForString(getTextField());
		resetText();
		return heroSelection(name);	
	}
	/**
	 * User enters the name of the class they wish to select and the Hero is created using the HeroFactory
	 * @param theName character name that the user wants to be called
	 * @return Hero that the user has created
	 * @throws InterruptedException 
	 */
	public Hero heroSelection(final String theName) throws InterruptedException {
		addTexttoTextArea("What hero class do you want to be (Warrior, Thief, berserker, or Priestess): ");
    	pack();
		
		String heroType = makeJTextFieldHoldForString(getTextField());
		
		
		HeroFactory hf = new HeroFactory();		
		Hero hero = null;

		boolean keepAsking = true;
		
		while(keepAsking) {
			try {
				hero = hf.createHero(heroType, theName);
				keepAsking = false;
			} catch(IllegalArgumentException e) {
	    		addTexttoTextArea("Not a proper Hero type entered, try again. (Warrior, Thief, berserker, or Priestess): ");
	        	pack();
	        	heroType = makeJTextFieldHoldForString(getTextField());
			}
		}
		resetText();
		return hero;
	}
	
	public int displayRows() throws InterruptedException {
		int rows;
		addTexttoTextArea("How many rows do you want the dungeon to be: ");
    	pack();
		
		rows = makeJTextFieldHoldForInt(getTextField());
		resetText();
		return rows;
	}
	
	public int displayColumns() throws InterruptedException {
		int cols;
		addTexttoTextArea("How many columns do you want the dungeon to be: ");
    	pack();
		
		cols = makeJTextFieldHoldForInt(getTextField());
		resetTextField();
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
		
		addTexttoTextArea("Enter the name of a save you want to load:");
    	pack();

		String save = makeJTextFieldHoldForString(getTextField());
		save = saveGameDir + "\\" + save + ".ser";
		File tmpDir = new File(save);
		while(!tmpDir.exists()) {
			addTexttoTextArea("Enter a valid save game: ");
        	pack();
        	save = makeJTextFieldHoldForString(getTextField());
			save = saveGameDir + "\\" + save + ".ser";
			tmpDir = new File(save);
		}
		resetPanel1();
		resetText();
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
		addTexttoTextArea(sb.toString());
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
		addTexttoTextArea("You have a chance to attack would you like to use normal attack(enter normal) or special attack(enter special): ");
    	pack();
    	
    	String answer = makeJTextFieldHoldForString(getTextField());
    	
    	while(!(answer.equalsIgnoreCase("normal") || answer.equalsIgnoreCase("special"))) {
    		addTexttoTextArea("Please enter valid attack option:");
        	pack();
        	answer = makeJTextFieldHoldForString(getTextField());
		}
    	resetText();
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
		addTexttoTextArea("Would you like move on(move), or View inventory(inventory), or Save game and exit(save):");
    	pack();
		
    	String choice = makeJTextFieldHoldForString(getTextField());
    	
		while(!(choice.equalsIgnoreCase("move") || choice.equalsIgnoreCase("dungeon") || choice.equalsIgnoreCase("inventory") || choice.equalsIgnoreCase("save"))) {
    		addTexttoTextArea("Not a valid option. Please enter a valid option (move, inventory, or save):");
        	pack();
        	choice = makeJTextFieldHoldForString(getTextField());
		}
		resetText();
		
		if(choice.equalsIgnoreCase("inventory")) {
			addTexttoTextArea(theHero.toString());
			displayInventory(theHero, theDungeon);
			resetPanel1();
			return displayOptions(theHero, theDungeon);
		} else if(choice.equalsIgnoreCase("dungeon")) {
			addTexttoTextArea("Dungeon:\n" + theDungeon.toString());
			return displayOptions(theHero, theDungeon);
		} else if(choice.equalsIgnoreCase("move")) {
			return false;
		} else {
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
		addTexttoTextArea("Would you like to use an item(use) or go back to options(options):");
    	pack();
		
    	String choice = makeJTextFieldHoldForString(getTextField());
    	
		while(!(choice.equalsIgnoreCase("use") || choice.equalsIgnoreCase("options"))) {
    		addTexttoTextArea("Not a valid option. Please enter a valid option (use or options)");
        	pack();
        	choice = makeJTextFieldHoldForString(getTextField());
		}
		
		resetText();
		if(choice.equalsIgnoreCase("use")) {
			addTexttoTextArea("Would you like to use a heal potion(heal) or a vision potion(vision):");
			
	    	String item = makeJTextFieldHoldForString(getTextField());
	    	resetText();
			if(item.equalsIgnoreCase("heal") || item.equalsIgnoreCase("vision")) {
				Item useItem = theHero.removeItemFromInventory(item);
				if(useItem != null) {
					int heroHealth = theHero.getHitPoints();
					if(theHero.useItem(useItem)) {
						addTexttoTextArea("After using the vision potion you are able to see the surrounding rooms.");
						addTexttoTextArea(theHero.displayDungeonNearHero(theDungeon));
						displayInventory(theHero, theDungeon);
					} else {
						addTexttoTextArea("The heal potion healed you for " + (theHero.getHitPoints() - heroHealth) + " . Current health is " + theHero.getHitPoints());
						displayInventory(theHero, theDungeon);
					}
				} else {
					addTexttoTextArea("No item to use.");
					displayInventory(theHero, theDungeon);
				}
			} else {
				addTexttoTextArea("\nEnter a valid item to use");
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
		addTexttoTextArea("What do you want the name of the save to be?");
    	pack();
    	String choice = makeJTextFieldHoldForString(getTextField());
    	resetText();
    	return choice;
	}
	
	/**
	 * User has the option to overwrite file if they have entered a new name that already exists
	 * @return OverWrite
	 * @throws InterruptedException 
	 */
	public String displayOverWrite() throws InterruptedException {
		addTexttoTextArea("Would you like to overwrite this file(yes or no):");
    	pack();
    	
    	String answer = makeJTextFieldHoldForString(getTextField());
		
		while(!(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no"))) {
			addTexttoTextArea("Not a valid input please enter (yes or no):");
        	pack();
        	answer = makeJTextFieldHoldForString(getTextField());
		}
		resetText();
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
		 
		 addTexttoTextArea(sb.toString());
		 
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
    	
    	String direction = makeJTextFieldHoldForString(getTextField());
		direction.toLowerCase();
		
		
		while(!(Arrays.asList(theOptions).contains(direction))) {
    		addTexttoTextArea("Invalid direction please enter another");
        	pack();
        	direction = makeJTextFieldHoldForString(getTextField());
			direction.toLowerCase();
		}
		resetText();
		return direction;
		
	}
	
	public void displayMonster(final String theType) {
		if(theType.equalsIgnoreCase("ogre")) {
	    	getPanel1().add(createMonsterPic("ogre"));
		}
	}
	
	private static JButton createMonsterPic(final String theString) {
		String currDirectory = System.getProperty("user.dir");
    	String image = currDirectory + "\\images\\" + theString + ".png";
    	JButton button = new JButton();
    	
    	//button.setIcon();
    	
    	return button;
	}

	/**
	 * User can type whether they would like to quit the game or restart the game
	 * 
	 * @return true or false
	 * @throws InterruptedException 
	 */
	public boolean displayKeepPlayingOptions() throws InterruptedException {
		addTexttoTextArea("Do you want to restart(restart) or would you like to quit(quit)?");
		String answer = makeJTextFieldHoldForString(getTextField());
		
		while(!(answer.equalsIgnoreCase("restart") || answer.equalsIgnoreCase("quit"))) {
			addTexttoTextArea("Not a valid option please enter a new one");
			answer = makeJTextFieldHoldForString(getTextField());
		}
		
		resetText();
		return answer.equalsIgnoreCase("restart");
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
            theTextField.setText("");
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
