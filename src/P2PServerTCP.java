/**
 * Author: Archer Reilly
 * Date: 13/Nov/2014
 * File: P2PServerTCP.java
 * Desc: an tcp implementation of the P2P server
 *
 * Produced By CSRGXTU.
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class P2PServerTCP {
  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("Usage: P2PServerTCP port");
      System.exit(1);
    }

    new P2PServerTCP(Integer.parseInt(args[0]));
  }

  public P2PServerTCP(int port) {
    try {
      ServerSocket serverSocket = new ServerSocket(port);

      // Number of client
      int clientNo = 1;

      while (true) {
        // Listen for a new connection request
        Socket socket = serverSocket.accept();


        // Find clients ip address and port
        InetAddress inetAddress = socket.getInetAddress();
        String clientIP = inetAddress.getHostAddress();
        int clientPort = socket.getPort();
        // Display client number
        System.out.println("Starting thread for client " + clientNo
          + " at " + new Date() + " IP:PORT: " + clientIP + ":" + Integer.toString(clientPort));

        // Create a new thread for the connection
        HandleAClient task = new HandleAClient(socket);

        // Start the new thread
        new Thread(task).start();

        // Increment clientNo
        clientNo++;
      }
    } catch (IOException ex) {
        System.err.println(ex);
    }
  }

  // Inner class
  // Define he thread class for handling new connectin
  class HandleAClient implements Runnable {
    private Socket socket; // a connected socket

    /** construct a thread */
    public HandleAClient(Socket socket) {
      this.socket = socket;
    }

    /** Run a thread */
    public void run() {
      try {
        // create data input and output streams
        DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
        DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
        InetAddress inetAddress = socket.getInetAddress();
        String clientIP = inetAddress.getHostAddress();
        int clientPort = socket.getPort();

        // continuouly server the client
        while (true) {
          int number = inputFromClient.readInt();
          System.out.println("Reveived from client: " + clientIP + ":" + Integer.toString(clientPort) + " " + number);
          outputToClient.writeInt(number);
          
          /*String sentence = inputFromClient.readLine();
          outputToClient.writeBytes(sentence.toUpperCase());*/
        }
      } catch (IOException e) {
        // System.err.println(e);
        try {
          socket.close();
        } catch (IOException ex) {
          //
        }
      }
    }
  }
}