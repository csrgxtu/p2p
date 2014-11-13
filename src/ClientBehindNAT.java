/**
 * Author: Archer Reilly
 * Date: 08/Nov/2014
 * File: ClientBehindNAT.java
 * Desc: client connects to the relay server
 *
 * Procued By CSRGXTU.
 */
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

public class ClientBehindNAT {
  public static void main(String[] args) {
    String serverName = args[0];
    int port = Integer.parseInt(args[1]);
  
    try {
        System.out.println("Connecting to " + serverName +
               " on port " + port);
        Socket client = new Socket(serverName, port);
        System.out.println("Just connected to "
               + client.getRemoteSocketAddress());
        OutputStream outToServer = client.getOutputStream();
        DataOutputStream out =
          new DataOutputStream(outToServer);
        out.writeUTF("Hello from "
         + client.getLocalSocketAddress());
        InputStream inFromServer = client.getInputStream();
        DataInputStream in =
          new DataInputStream(inFromServer);
        System.out.println("Debug: here");
        System.out.println("Server says " + in.readUTF());
        client.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
}