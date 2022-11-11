package View;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.awt.Color;
public class Window extends JFrame {

	
	public JTextArea messageText;
	public Window() {
		setTitle("Dungeon Adventure");
		setSize(1800,960);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

public static void main(String[] args)
{
	Window w = new Window();

	

}
}