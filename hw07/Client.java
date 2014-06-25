import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.Socket;
import java.io.*;
import java.util.*;

/**Class to create GUI and socket for the client in a 
*chat program.
* @author Anubhav Agarwal
* @version 1.0 April 1, 2013.
*/
public class Client extends JFrame implements ActionListener {
	private static String hostname = "localhost";
	private static String user     = "Client";
	private static int portno      = 5300;
    private Socket cSocket;
	private PrintWriter sendMessage; 
    private Scanner incomingMessage;
    private JTextArea  allMessages = new JTextArea(30, 30);
    private JTextField message     = new JTextField(30);
    private JButton send           = new JButton("Send");
    /**
    *Main method to execute the program
    *@param args    A String array containing command line arguments.
    *@param client  An instance of Client class.
    */
    public static void main(String[] args)  {
		try {
			if (args.length == 1){
				hostname = args[0];
			}
			else if (args.length == 2){
				hostname = args[0];
				portno = Integer.parseInt(args[1]);
			}
			else if (args.length == 3){
				hostname = args[0];
				portno = Integer.parseInt(args[1]);
				user = args[2];
			}
		} catch (ArrayIndexOutOfBoundsException aIndex) {}
	    Client client = new Client(hostname, portno, user);
    	client.showMessages();
    }
	/** 
	*Constructor
	*@param hostname   A string containing server name.
	*@param portnumber int containing the Server's port number.
	*@param user       String containing the user's name.	
	*/
    public Client(String hostname, int portnumber, String user) {
    	this.user = user;
        try {
        	System.out.println("Connecting to server");
            cSocket = new Socket(hostname, portnumber);
            sendMessage = new PrintWriter(cSocket.getOutputStream(), true);
            incomingMessage = new Scanner(new InputStreamReader(cSocket.getInputStream()));
        } catch (Exception ex) {
			System.out.println("Could not establish connection with the server!!!");
			System.exit(0);
		}
		setGUI();		
    }
    /**
    *Sets the GUI frame with defaults for the program.
    */
    private void setGUI(){
    	allMessages.setEditable(false);
        message.addActionListener(this);
        send.addActionListener(this);
        setTitle("MSN chat client:" + user);
        add(new JScrollPane(allMessages), BorderLayout.NORTH);
        add(message, BorderLayout.CENTER);
        add(send, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        message.requestFocusInWindow();
        setVisible(true);
        setResizable(false);
        pack();
        //Closes the cSocket, scanner and printwriter when window is closed.
        addWindowListener(
            new WindowAdapter() {
                public void windowClosing(WindowEvent evet) {
					try {
                    	sendMessage.close();
                    	incomingMessage.close();
                    	cSocket.close();      
					} catch (IOException msg) {
						System.out.println("Could not send or receive message.");
				   	} catch (NullPointerException np) {}
                }
            }
        );
    }
	/**
	*function to be executed when event is triggered in chat textfield
	*@param aev  action event 
	*/
    public void actionPerformed(ActionEvent aev) {
        sendMessage.println(user + ": " + message.getText());
		allMessages.insert(user + ": " + message.getText() + "\n", allMessages.getText().length());
        message.setText("");
        message.requestFocusInWindow();
    }
	/**
	*Display messages received from server and sent to server on chat window
	*@param mesage A string containing the next incoming message.     
	*/
    private void showMessages() {
        String mesage;
		try {
        	while (incomingMessage.hasNextLine()) {
        		mesage = incomingMessage.nextLine();
            	allMessages.insert(mesage + "\n", allMessages.getText().length());
            	allMessages.setCaretPosition(allMessages.getText().length());
	        }
    	    sendMessage.close();
	        incomingMessage.close();
			cSocket.close();
		} catch (Exception e) {}
        System.out.println("Connection is closed.");
    }
}