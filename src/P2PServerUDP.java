/**
 * Author: Archer Reilly
 * Date: 13/Nov/2014
 * File: P2PServerUDP.java
 * Desc: multithreaded udp p2p relay server
 *
 * Produced By CSRGXTU.
 */
import java.io.*;
import java.net.*;

public class P2PServerUDP {
  private DatagramSocket serverSocket;

  public P2PServerUDP(int port) {
    try {
      serverSocket = new DatagramSocket(port);
    } catch (SocketException e) {
      e.printStackTrace();
      System.exit(1);
    }
    System.out.println("INFO: server start listening on " + port + "...");
  }

  public void run() {
    try {
      while (true) {
        
      }
    } catch (IOException e) {

    }
  }
}