import java.awt.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
class ServerChat extends JFrame implements Runnable,ActionListener {
	TextArea msgArea;
	Thread recieveThread;
	TextField msgText;
	Button sendButton;
	DatagramSocket ds;
	int cport=10,sport=11;
	ServerChat() throws Exception {
		msgArea=new TextArea(10,10);
		msgText=new TextField(10);
		sendButton=new Button("send");

		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(msgArea);
		add(msgText);
		add(sendButton);

		sendButton.addActionListener(this);
		setBounds(10,10,200,200);
		setVisible(true);
		ds=new DatagramSocket(sport);
		recieveThread=new Thread(this);
		recieveThread.start();
	}

	public void actionPerformed(ActionEvent e) {
		try {
			String message=msgText.getText();
			DatagramPacket dp=new DatagramPacket(message.getBytes(),message.length(),InetAddress.getLocalHost(),cport);

			ds.send(dp);
			msgArea.append("You:"+message+"\n");
		}catch(Exception e1){}
	}

	public void run() {
		byte b[]=new byte[1000];
		while(true) {
			try {
				DatagramPacket dp=new DatagramPacket(b,b.length);
				ds.receive(dp);
				String data=new String(dp.getData(),0,dp.getLength());
				msgArea.append("Client:"+data+"\n");
				}catch(Exception e){}
			}
		}
		
	public static void main(String x[]) throws Exception {
		new ServerChat();
	}
}