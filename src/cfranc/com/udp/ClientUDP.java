package cfranc.com.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientUDP {
	public ClientUDP() {
String line = new String();
byte[] sendData = new byte [1024];
byte[] receiveData = new byte [1024];


	BufferedReader userImput = new BufferedReader(new
			InputStreamReader(System.in));
			DatagramSocket clientSocket = null;
			try {
				clientSocket = new DatagramSocket(4567);
			} catch (SocketException e3) {
				e3.printStackTrace();
			}
			InetAddress IPAd = null;
			try {
				IPAd = InetAddress.getByName("localhost");
			} catch (UnknownHostException e3) {
				e3.printStackTrace();
			}{
			BufferedReader userInput = null;
			try {
				line = userInput.readLine();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
			sendData = line.getBytes();
			DatagramPacket sendPacket = new
			DatagramPacket(sendData,sendData.length, IPAd, 4567);
			try {
				clientSocket.send(sendPacket);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			DatagramPacket receivePacket = new DatagramPacket(receiveData,
			receiveData.length);
			try {
				clientSocket.receive(receivePacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String modifiedSentence = new String(receiveData);
			System.out.println("FROM SERVER UDP:" + modifiedSentence);
			clientSocket.close();
			}
			
}
}
