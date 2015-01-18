/**
 * @author Ali Hockla
 *
 */

import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class BluecatClientSocket {
	int port_number;
	InetAddress loopback;
	InetAddress localhost;
	Socket client_socket;
	PrintWriter output;
	BufferedReader input;

	public BluecatClientSocket(int port) throws IOException {
		port_number = port;
		loopback = InetAddress.getLoopbackAddress();
		client_socket = new Socket(loopback, port);
		output = new PrintWriter(client_socket.getOutputStream(), true);
		input = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
	}
	
	public BluecatClientSocket(String address, int port) throws IOException {
		port_number = port;
		localhost = InetAddress.getLocalHost();
		client_socket = new Socket( localhost, port );
		output = new PrintWriter(client_socket.getOutputStream(), true);
		input = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));		
	}

	
	public void transmit(String message) throws IOException
	{
		output.println(message);
		message = input.readLine();
		System.out.println("Received: " + message);		
	}

//	// *** ONLY WORKS WITH CONFIGURED SERVER SOCKET :( *** \\
//	public void transmit( String message ) throws IOException
//	{
//		output.println( message );
//		while ((message= input.readLine()) != null) {
//			System.out.println( "Received: " + message );
//
//		}
//		output.close();
//		input.close();
//		client_socket.close();
//	}

}
