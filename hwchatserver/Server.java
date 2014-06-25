import java.net.*;
import java.util.*;
import java.io.*;
 
public class Server { 
   private ServerSocket server;
   private int port = 5600;
 
   public Server() {
      try {
         server = new ServerSocket(port);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
 
   public static void main(String[] args) throws IOException  {
      Server server = new Server();
      server.connection();
   }
 
   public void connection() throws IOException {
      System.out.println("Waiting for client ...");
      try  {
         Socket socket = server.accept();
         System.out.println("Client accepted: " + socket);
         String line = "";
         Scanner dis = new Scanner(socket.getInputStream());    
         boolean done = false;
         while (!done) {   
               line = dis.nextLine();
               System.out.println(line.toString());
               done = line.equals("bye");
         }
         dis.close();                
         socket.close();
      } catch(IOException e) {  
           System.out.println(e); 
      } 
   }
}