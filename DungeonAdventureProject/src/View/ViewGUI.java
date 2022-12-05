package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
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

/**
 * A Class that builds a GUI and uses it to prompt and collect input from the user.
 * 
 * @author Trevor Henry, Riley Stevenson, and Colton Wickens
 * @version 1.0
 */
public class ViewGUI extends JFrame {

	/**
	 * Serialization ID
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
    /**
     * JTextArea for the user to be prompted on
     */
    private final JTextArea myTextArea;
    /**
     * JTextField for the User to specify input after being prompted
     */
    private JTextField myTextField;
    /**
     * JScrollPane that is the container for the JTextArea so you can scroll and view current and past prompts
     */
    private final JScrollPane myScroll;
    
    /**
     * Constructor for the ViewGUI
     */
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
    
    /**
     * Getter for the JPanel for the North side
     * @return JPanel1
     */
    public JPanel getPanel1() {
    	return myJPanel1;
    }
    
    /**
     * Getter for the JPanel for the CENTER
     * @return JPanel2
     */
    public JPanel getPanel2() {
    	return myJPanel2;
    }
    
    /**
     * Getter for the JPanel for the SOUTH side
     * @return JPanel3
     */
    public JPanel getPanel3() {
    	return myJPanel3;
    }
    
    /**
     * Getter for the JPanel for the WEST side
     * @return JPanel4
     */
    public JPanel getPanel4() {
    	return myJPanel4;
    }
    
    /**
     * Getter for the JPanel for the EAST side
     * @return JPanel5
     */
    public JPanel getPanel5() {
    	return myJPanel5;
    }
    
    /**
     * Getter for the JTextField for user input
     * @return JTextField
     */
    public JTextField getTextField() {
    	return myTextField;
    }
    
    /**
     * Getter for JTextArea for the prompts to the user
     * @return JPanel1
     */
    public JTextArea getTextArea() {
    	return myTextArea;
    }
    
    /**
     * Getter for the JScrollPane for the container of the JTextArea
     * @return JPanel1
     */
    public JScrollPane getScrollPane() {
    	return myScroll;
    }
    
    /**
     * Resets the Text of the JTextField
     */
    public void resetText() {
    	myTextField.setText("");
    }
    /**
     * Removes the JTextField from the JPanel3 and creates adds a new one
     */
    public void resetTextField() {
    	myTextField = new JTextField(20);
    	resetPanel3();
    	getPanel3().add(getTextField());;
        getPanel3().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
    /**
     * Adds text to the textarea panel by taking any theString object and displaying it
     * @param theString text that should be put to the JTextArea
     */
    public void addTexttoTextArea(final String theString) {
    	getTextArea().append("\n" + theString);
    	getTextArea().validate();
    	JScrollBar vertical = getScrollPane().getVerticalScrollBar();
    	vertical.setValue(vertical.getMaximum());
    }
  /**
   * Makes JScrollPane scroll to bottom after text is added 
   */
    public void updateTextArea() {
    	getScrollPane().validate();
    	JScrollBar vertical = getScrollPane().getVerticalScrollBar();
    	vertical.setValue(vertical.getMaximum());
    }
    
   /**
   * Resets JPanel1 by removing all contents  
   */
    public void resetPanel1() {
    	myJPanel1.removeAll();
    	EventQueue.invokeLater(() -> {
    	    myJPanel1.updateUI();
    	});
    	pack();
    }
    
    /**
     * Resets JPanel2 by removing all contents  
     */
    public void resetPanel2() {
    	myJPanel2.removeAll();
    	EventQueue.invokeLater(() -> {
    	    myJPanel2.updateUI();
    	});
    	pack();
    }
    
    /**
     * Resets JPanel3 by removing all contents  
     */
    public void resetPanel3() {
    	myJPanel3.removeAll();
    	EventQueue.invokeLater(() -> {
    	    myJPanel3.updateUI();
    	});
    	pack();
    }
    
    /**
     * Resets JPanel4 by removing all contents  
     */
    public void resetPanel4() {
    	myJPanel4.removeAll();
    	EventQueue.invokeLater(() -> {
    	    myJPanel4.updateUI();
    	});
    	pack();
    }
    
    /**
     * Resets JPanel5 by removing all contents  
     */
    public void resetPanel5() {
    	myJPanel5.removeAll();
    	EventQueue.invokeLater(() -> {
    	    myJPanel5.updateUI();
    	});
    	pack();
    }
    
    /**
     * Resets All JPanels by removing all contents  
     */
    public void resetAllPanels() {
    	resetPanel1();
    	resetPanel2();
    	resetPanel3();
    	resetPanel4();
    	resetPanel5();
    	pack();
    }
    
    /**
     * Setup for the JFrame by adding all the needed content for the start of the game to it
     */
    public void start() {
    	String currDirectory = System.getProperty("user.dir");
    	String image = currDirectory + "\\images\\Dunicon.jfif";
    	ImageIcon imageIcon = new ImageIcon(image);
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setIconImage(imageIcon.getImage());
		setPreferredSize(new Dimension(screenSize.width * 1 / 3, screenSize.height * 1 / 3));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        
        
        getPanel1().setLayout(new FlowLayout());
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
    
  /**
   * Displays the how to play text to the JTextArea 
   */
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
    
    /**
     * The main menu for the game, prompts the user to create a new save file or load a saved file
     * @return a boolean, true if user picks create and false for load
     * @throws InterruptedException
     */
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
	 * @return Hero object that the user has created
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
	 * @return Hero object that the user has created
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
	
	/**
	 * Takes in how many rows the user wants to be in the dungeon and stores it in the rows variable	
	 * @return the user chosen  number of rows that the dungeon will have
	 * @throws InterruptedException
	 */
	public int displayRows() throws InterruptedException {
		int rows;
		addTexttoTextArea("How many rows do you want the dungeon to be: ");
    	pack();
		
		rows = makeJTextFieldHoldForInt(getTextField());
		resetText();
		return rows;
	}
	
	/**
	 * Takes in how many columns the user wants to be in the dungeon and stores it in the cols variable	
	 * @return the user chosen number of columns the dungeon will have
	 * @throws InterruptedException
	 */	
	public int displayColumns() throws InterruptedException {
		int cols;
		addTexttoTextArea("How many columns do you want the dungeon to be: ");
    	pack();
		
		cols = makeJTextFieldHoldForInt(getTextField());
		resetTextField();
		return cols;
	}
	
	/**
	 * Display's the save game files that exist and prompts the user to enter a save game until they have entered a valid one.
	 * @return A string that represents the file the user wants to load
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
	 * @param theHero object
	 */
	public void displayCurrRoom(final Hero theHero) {
		StringBuilder sb = new StringBuilder();
		sb.append("Current Room:\n");
		sb.append(theHero.getCurrRoom().toString());
		addTexttoTextArea(sb.toString());
	}
	
	/**
	 * Displays the given text to the specified panel by creating a JLabel with the text and adding it to the specified Panel
	 * @param theString text to be displayed
	 * @param thePanel
	 */
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
	 * The options for the hero to attack. The user can enter either "normal" or "special" for the attacks. If neither are entered, then the user will 
	 * see the "please enter valid attack option" and be prompted to enter another option
	 * @return a string representation of the attack that the user has specified
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
	 * Displays the options menu with the options "move", "inventory", and "save" and after the user specifies which option they want directs them to the prompt they want
	 * 
	 * @param theHero object
	 * @param theDungeon object
	 * @return a boolean, false if user entered move and true if user entered save
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
	 * Displays the current inventory of items the Hero has. User is prompted if they would like to use the a item or go back to options menu.
	 * 
	 * @param theHero object
	 * @param theDungeon object
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
	 * Prompts user for a file name for the save and gets file name.
	 * @return String representation of the name of the save file the user specified
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
	 * User has the option to overwrite file if they have entered a save file that already exists
	 * @return yes or no based of if the user wanted to overwrite the current file or not
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
	 * @param theRoom object the Hero is in
	 * @return  a string representing the direction the user specified they wanted to move
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
	 * Prompts the user until a valid move option for the current room is entered.
	 * 
	 * @param theRoom object the Hero is in
	 * @param theOptions a String array of the only move options available for the current room
	 * @return direction string of the direction the user specifies to move
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
	
	/**
	 * Creates a JButton with a .png in it of the monster specified and adds it to JPanel1
	 * @param theType of monster that is currently in the room
	 */
	public void displayMonster(final String theType) {
	    if(theType.equalsIgnoreCase("ogre")) {
	         getPanel1().add(createMonsterPic("ogre")); }
	    else if(theType.equalsIgnoreCase("skeleton")){
	         getPanel1().add(createMonsterPic("skeleton"));
	    }
	    else if(theType.equalsIgnoreCase("gremlin")){
	         getPanel1().add(createMonsterPic("gremlin"));
	    }

	    pack();

	}
	
	/**
	* Creates the monster picture based off of the monster in the dungeon room
	* @param theString the type of monster in the current room
	* @return JButton containing the .png of the monster in the room
	*/
	private static JButton createMonsterPic(final String theString) {
		JButton b = new JButton();

		String currDirectory = System.getProperty("user.dir");
		String image = currDirectory + "\\images\\" + theString + ".png";
		ImageIcon pic = new ImageIcon(image);
		Image icon = pic.getImage();
		Image real = icon.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
		pic = new ImageIcon(real);
		b.setIcon(pic);

		return b;
	}
	
	/**
	 * Creates a JButton with a .png in it of the Hero specified and adds it to JPanel1
	 * @param theType of Hero that the user selected
	 */
	public void displayHero(final String heroType) {
        if(heroType.equalsIgnoreCase("warrior")) {
             getPanel1().add(createHeroPic("warrior")); }
        else if(heroType.equalsIgnoreCase("priestess")){
             getPanel1().add(createHeroPic("priestess"));
        }
        else if(heroType.equalsIgnoreCase("thief")){
             getPanel1().add(createHeroPic("thief"));
        }
        else if(heroType.equalsIgnoreCase("berserker")){
             getPanel1().add(createHeroPic("berserker"));
        }

        pack();

    }
	
	/**
	* Creates a JButton with  picture of the Hero the user has selected
	* @param theString the type of Hero the user selected
	* @return JButton containing the .png of the Hero the user selected
	*/
	private static JButton createHeroPic(final String theString) {
		JButton b = new JButton();

		String currDirectory = System.getProperty("user.dir");
		String image = currDirectory + "\\images\\" + theString + ".png";
		ImageIcon pic = new ImageIcon(image);
		Image icon = pic.getImage();
		Image real = icon.getScaledInstance(75, 75, java.awt.Image.SCALE_SMOOTH);
		pic = new ImageIcon(real);
		b.setIcon(pic);

		return b;
	}
	    
	    

	/**
	 * User can type whether they would like to quit the game or restart the game
	 * 
	 * @return true if they wish to continue or false if they wish to quit
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
	
	 /**
	  * Makes the program wait for user to enter a String response in the JTextField
	  * @param theTextField that we wish to get input from the user from
	  * @return the string the user entered in to the JTextField
	  * @throws InterruptedException
	  */
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
    
    /**
	  * Makes the program wait for user to enter a Integer response in the JTextField
	  * @param theTextField that we wish to get input from the user from
	  * @return the Int the user entered in to the JTextField
	  * @throws InterruptedException
	  */
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
