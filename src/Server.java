/**
 * Author: Archer Reilly
 * Date: 10/Nov/2014
 * File: Server.java
 * Desc: the relay server handles connection and tell each other
 * the NAT's ip:port between two client.
 *
 * Produced By CSRGXTU.
 */
import java.io.*;
import java.net.*;

public class Server {

  public static void main(String[] args) throws Exception {
    int listenPort = 9901;
    DatagramSocket serverSocket = new DatagramSocket(listenPort);
    byte[] receiveData = new byte[1024];
    byte[] sendData = new byte[1024];

    while (true) {
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      serverSocket.receive(receivePacket);
      String sentence = new String(receivePacket.getData());
      System.out.println("Received: " + sentence);

      InetAddress IPAddress = receivePacket.getAddress();
      int port = receivePacket.getPort();
      String capitalizedSentence = sentence.toUpperCase();
      sendData = capitalizedSentence.getBytes();
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
      serverSocket.send(sendPacket);
    }
  }
}