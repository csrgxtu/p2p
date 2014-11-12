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
    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    DatagramSocket clientSocket = new DatagramSocket();
    InetAddress IPAddress = InetAddress.getByName("localhost");
    byte[] sendData = new byte[1024];
    byte[] receiveData = new byte[1024];
    String sentence = inFromUser.readLine();
    sendData = sentence.getBytes();
    DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddress, 9901);
    clientSocket.send(sendPacket);
    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
    clientSocket.receive(receivePacket);
    String modifiedSentence = new String(receivePacket.getData());
    System.out.println("From Server: " + modifiedSentence);
    clientSocket.close();
  }
}