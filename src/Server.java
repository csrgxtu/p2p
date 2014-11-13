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

public class Server extends Thread {
  private int listenPort = 1234;
  private DatagramSocket serverSocket;

  public Server(int port) {
    listenPort = port;
    try {
      serverSocket = new DatagramSocket(listenPort);
    } catch (SocketException e) {
      e.printStackTrace();
      System.exit(1);
    }
    System.out.println("Debug: Server Listening On " + listenPort);
  }

  public void run() {
    try {
      while (true) {
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);

        serverSocket.receive(receivePacket);
        String sentence = new String(receivePacket.getData());
        InetAddress IPAddress = receivePacket.getAddress();
        int port = receivePacket.getPort();
        System.out.println("Client Socket: " + IPAddress.toString() + Integer.toString(port));
        System.out.println("Received: " + sentence);

        try {
          Thread.sleep(5000);
        } catch (InterruptedException ex) {
          System.out.println("InterruptedException");
        }

        String capitalizedSentence = sentence.toUpperCase();
        sendData = capitalizedSentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
        serverSocket.send(sendPacket);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.out.println("Usage: Server port");
      System.exit(1);
    }
    int port = Integer.parseInt(args[0]);

    // try {
      Thread t = new Server(port);
      t.start();
    // } catch (IOException e) {
    //   e.printStackTrace();
    // }
  }
  /*public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.out.println("Usage: Server port");
      System.exit(1);
    }
    int listenPort = Integer.parseInt(args[0]);
    DatagramSocket serverSocket = new DatagramSocket(listenPort);

    while (true) {
      byte[] receiveData = new byte[1024];
      byte[] sendData = new byte[1024];
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      serverSocket.receive(receivePacket);
      String sentence = new String(receivePacket.getData());
      System.out.println("Client Socket: " + receivePacket.getAddress().toString() + ":" + Integer.toString(receivePacket.getPort()));
      System.out.println("Received: " + sentence);

      InetAddress IPAddress = receivePacket.getAddress();
      int port = receivePacket.getPort();
      String capitalizedSentence = sentence.toUpperCase();
      sendData = capitalizedSentence.getBytes();
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
      serverSocket.send(sendPacket);
    }*/
}
