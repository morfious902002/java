import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

/**Class to create GUI and socket for the server in a
*chat program.
* @author Anubhav Agarwal
* @version 1.0 April 1, 2013.
*/
public class Server extends JFrame implements ActionListener {
	private static String user      = "Anubhav";
	private static int portno       = 5300;	
	private ServerSocket serverSocket;
	private Socket connSocket = null;
	private PrintWriter sendMessage; 
    private Scanner incomingMessage;
    private JTextArea  allMessages  = new JTextArea(30, 30);
    private JTextField message      = new JTextField(30);
    private JButton send            = new JButton("Send");
    /**
    *Main method to execute the program
    *@param args A String array containing command line arguments.
    */
	public static void main(String[] args) {
		try {
			if (args.length == 1){
				portno = Integer.parseInt(args[0]);
			}
			else if (args.length == 2){
				portno = Integer.parseInt(args[0]);
				user = args[1];
			}
		} catch (ArrayIndexOutOfBoundsException aIndex) {}

		Server server = new Server(portno, user);
		server.showMessages();
	}
	/** 
	*Constructor
	*@param portnumber int containing the Server's port number.
	*@param user       String containing the user's name.	
	*/
    public Server(int portnumber, String user) {
    	this.user = user;
    	portno    = portnumber;
        try {
			serverSocket = new ServerSocket(portnumber);
			System.out.println("Waiting for connection");
			connSocket = serverSocket.accept();
			System.out.println("Connection established: "+ connSocket);
			sendMessage = new PrintWriter(connSocket.getOutputStream(), true);
           	incomingMessage = new Scanner(new InputStreamReader(connSocket.getInputStream()));
        } catch (Exception ex) {
			System.out.println("Could not establish connection.");
			System.exit(0);
		}    
		setGUI();		
    }
    /**
    *Sets the GUI frame with defaults for the program.
    */
    private void setGUI (){
    	allMessages.setEditable(false);
        message.addActionListener(this);
        send.addActionListener(this);
        setTitle("MSN chat server: " + user);
        add(new JScrollPane(allMessages), BorderLayout.NORTH);
        add(message, BorderLayout.CENTER);
        add(send, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        message.requestFocusInWindow();
        setVisible(true);
        setResizable(false);
        pack();
        //Closes the connSocket, scanner and printwriter when window is closed.
		addWindowListener(
            new WindowAdapter() {
                public void windowClosing(WindowEvent evet) {
					try {
                    	sendMessage.close();
                    	incomingMessage.close();
                    	connSocket.close();      
					} catch (IOException msg) {
						System.out.println("Could not send or receive message.");
				   	}
                }
            }
        );
    }
	/**
	* gets incoming message and updates text area containing all the messages.
	* @param  event  Any action event 
	*/
    public void actionPerformed(ActionEvent event) {
        sendMessage.println(user + ": " + message.getText());
		allMessages.insert(user + ": " + message.getText() + "\n", allMessages.getText().length());
        message.setText("");
        message.requestFocusInWindow();
    }
	/**
	* Display messages received from client and sent to client on chat window
	* @param recMessage A string containing the next line from client.     
	*/
    private void showMessages() {
        String recMessage;
		try {
        	while (incomingMessage.hasNextLine()) {
				recMessage = incomingMessage.nextLine();
            	allMessages.insert(recMessage + "\n", allMessages.getText().length());
            	allMessages.setCaretPosition(allMessages.getText().length());
        	}
        	sendMessage.close();
        	incomingMessage.close();
			connSocket.close();
		} catch (Exception e) {}
        System.out.println("Connection is closed.");
    }
}