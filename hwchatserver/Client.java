import java.io.*;
import java.net.*;
import java.util.*;
 
public class Client {
  public static void main(String[] args) throws UnknownHostException, IOException{
      // Create a connection to the server socket on the server application
      InetAddress host = InetAddress.getLocalHost();
      Socket socket = new Socket(host.getHostName(), 5600); 
      
  }
}