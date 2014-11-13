/**
 * Author: Archer Reilly
 * Date: 13/Nov/2014
 * File: P2PClientTCP.java
 * Desc: p2p client in tcp
 *
 * Produced By CSRGXTU.
 */
import java.io.*;
import java.net.*;

public class P2PClientTCP {
  private String serverIP;
  private int serverPort;

  public P2PClientTCP(String ip, int port) {
    serverIP = ip;
    serverPort = port;
  }

  public void run() {
    try {
      // Create a socket to connect to the server
      Socket socket = new Socket(serverIP, serverPort);

      // Create an output stream to send data to server
      DataOutputStream outputToServer = new DataOutputStream(socket.getOutputStream());
      // BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
      System.out.print("Enter a char(number)[ENTER]: ");
      BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
      int number = Integer.parseInt(bufferRead.readLine());
      // int number = 1;
      outputToServer.writeInt(number);
      // outputToServer.flush();


      // Create an input stream to receive data from server
      DataInputStream inputFromServer = new DataInputStream(socket.getInputStream());
      // int number = inputFromServer.readInt();
      System.out.println("Server Response: " + inputFromServer.readInt());

      // socket.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    if (args.length != 2) {
      System.err.println("Usage: Client serverIP serverPort");
      System.exit(1);
    }

    new P2PClientTCP(args[0], Integer.parseInt(args[1])).run();
  }
}