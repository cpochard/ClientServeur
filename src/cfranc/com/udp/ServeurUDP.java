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
			//On cr�� un socket qui �coute sur le port 4567
			socketServeur = new DatagramSocket(4567);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		//Tant que l'appli tourne
		while (true) {
			//On cr�� un tableau avec 1024 cellules qui ne contient que des bytes
			byte[] receiveData = new byte[1024];
			//Car les data � envoyer doivent �tre de m�me taille que celle qu'on veut recevoir
			byte[] sendData = new byte[1024];
			//On cr�� un paquet qui sera envoy� sur le r�seau
			DatagramPacket receivePacket = new DatagramPacket(receiveData, 1024);
			try {
				//dans ma communication, je vais recevoir le paquet de donn�es � recevoir
				socketServeur.receive(receivePacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
			//Je cr�� une variable de chaine de caract�re que je construit � partir du paquet re�u et je r�cup�re ses donn�es
			String line = new String(receivePacket.getData());
			
			System.out.println(line);
			//On cr�� un objet qui repr�sente une adresse IP
			//On r�cup�re l'adresse IP de qui le paquet a �t� re�u
			InetAddress adresseIP = receivePacket.getAddress();
			//Je r�cup�re le port du paquet re�u
			int port = receivePacket.getPort();
			//On cr�� une variable pour tout mettre en majuscule
			String upperCaseLine = line.toUpperCase();
			//On r�cup�re la chaine de caract�res en majuscule et on la r�injecte dans un tableau de byte
			sendData = upperCaseLine.getBytes();
			//On cr�� un nouveau paquet � envoyer qui contient nos donn�es, la taille, l'adresse IP et le port
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
