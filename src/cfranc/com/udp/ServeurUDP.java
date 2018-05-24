package cfranc.com.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServeurUDP {

	public static void main(String[] args) {

		DatagramSocket socketServeur = null;
		try {
			//On créé un socket qui écoute sur le port 4567
			socketServeur = new DatagramSocket(4567);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		//Tant que l'appli tourne
		while (true) {
			//On créé un tableau avec 1024 cellules qui ne contient que des bytes
			byte[] receiveData = new byte[1024];
			//Car les data à envoyer doivent être de même taille que celle qu'on veut recevoir
			byte[] sendData = new byte[1024];
			//On créé un paquet qui sera envoyé sur le réseau
			DatagramPacket receivePacket = new DatagramPacket(receiveData, 1024);
			try {
				//dans ma communication, je vais recevoir le paquet de données à recevoir
				socketServeur.receive(receivePacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
			//Je créé une variable de chaine de caractère que je construit à partir du paquet reçu et je récupère ses données
			String line = new String(receivePacket.getData());
			
			System.out.println(line);
			//On créé un objet qui représente une adresse IP
			//On récupère l'adresse IP de qui le paquet a été reçu
			InetAddress adresseIP = receivePacket.getAddress();
			//Je récupère le port du paquet reçu
			int port = receivePacket.getPort();
			//On créé une variable pour tout mettre en majuscule
			String upperCaseLine = line.toUpperCase();
			//On récupère la chaine de caractères en majuscule et on la réinjecte dans un tableau de byte
			sendData = upperCaseLine.getBytes();
			//On créé un nouveau paquet à envoyer qui contient nos données, la taille, l'adresse IP et le port
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, adresseIP, port);
			try {
				//J'envoie le paquet
				socketServeur.send(sendPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
