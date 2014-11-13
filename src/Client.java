/**
 * Author: Archer Reilly
 * Date: 10/Nov/2014
 * File: Client.java
 * Desc: udp client that talks with udp server.
 *
 * Produced By CSRGXTU.
 */
import java.io.*;
import java.net.*;

public class Client {
  // private String srvAdr = "127.0.0.1";
  // private int srvPort = 9901;

  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.out.println("Usage: Client serverIP serverPort");
      System.exit(1);
    }

    String serverIP = args[0];
    int serverPort = Integer.parseInt(args[1]);
    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    DatagramSocket clientSocket = new DatagramSocket();
    InetAddress IPAddress = InetAddress.getByName(serverIP);
    byte[] sendData = new byte[1024];
    byte[] receiveData = new byte[1024];
    System.out.print("Enter a string[ENTER]: ");
    String sentence = inFromUser.readLine();
    sendData = sentence.getBytes();
    DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddress, serverPort);
    System.out.println("Debug: before sendData");
    clientSocket.send(sendPacket);
    System.out.println("Debug: after sendData");
    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
    clientSocket.receive(receivePacket);
    String modifiedSentence = new String(receivePacket.getData());
    System.out.println("From Server: " + modifiedSentence);
    clientSocket.close();
  }
}