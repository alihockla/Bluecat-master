/**
 * @author Ali Hockla
 *
 */

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BluecatServerSocket {
	int port_number;
	ServerSocket server_socket;
	Socket client_socket;

	/**
	 * 
	 */
	public BluecatServerSocket( int port ) throws IOException {
		port_number= port;
		server_socket= new ServerSocket( port_number );
	}
	
	public String listen() throws IOException
	{
		client_socket= server_socket.accept();
		String input_line;
		BufferedReader reader= new BufferedReader( 
				 new InputStreamReader(client_socket.getInputStream()) );
		PrintWriter output= new PrintWriter( client_socket.getOutputStream(), true );
		
		while ((input_line= reader.readLine()) != null) {

			System.out.println( "Received from client: " );
			System.out.println( "   " + input_line );
			output.println(input_line);
			System.out.println("is closed? " + client_socket.isClosed());
	}
			System.out.println("That's all folks! GOODBYE...");
			client_socket.close();
			client_socket.shutdownInput();
			
			return input_line;

	}
	
	public void listenAndTransferFileAtOnce(String fileToRead) throws IOException
	{
		client_socket= server_socket.accept();
		String file_line, file_contents = "";
		String input_line;
		BufferedReader reader= new BufferedReader( 
				 new InputStreamReader(client_socket.getInputStream()) );
		PrintWriter output= new PrintWriter( client_socket.getOutputStream(), true );
		
		// read in the file
		File file = new File(fileToRead);
		BufferedReader file_reader = new BufferedReader(new FileReader(file));
		System.out.println("Reading from file: " + file.toString());
		while ((file_line = file_reader.readLine()) != null) {	
			file_contents += file_line + " ";
		}

		
		while ((input_line= reader.readLine()) != null) {
		
			System.out.println( "Received from client: " );
			System.out.println( "   " + input_line );
			output.println(input_line + " -> A gift for you: -> " + file_contents);
	}
			System.out.println("That's all folks! GOODBYE...");
//			client_socket.close();
			client_socket.shutdownInput();
			
	}
	public void listenAndTransferFileAtOnceAndLog(String fileToRead, String fileToWrite) throws IOException
	{
		client_socket= server_socket.accept();
		String file_line, file_contents = "", write_data = "";
		String input_line;
		BufferedReader reader= new BufferedReader( 
				 new InputStreamReader(client_socket.getInputStream()) );
		PrintWriter output= new PrintWriter( client_socket.getOutputStream(), true );
		
		// read in the file
		File file = new File(fileToRead);
		BufferedReader file_reader = new BufferedReader(new FileReader(file));
		System.out.println("Reading from file: " + file.toString());
		while ((file_line = file_reader.readLine()) != null) {	
			file_contents += file_line + " ";
		}

		while ((input_line= reader.readLine()) != null) {
		
			System.out.println( "Received from client: " );
			System.out.println( "   " + input_line );
			write_data += input_line + " ";
			output.println(input_line + " -> And A Gift From Me To You: -> " + file_contents);
	}
		// write out to file
		FileWriter fileWriter = new FileWriter(fileToWrite);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write("Data Received: ->" + write_data + " Data Sent: ->" + file_contents);
		System.out.println("Writing to file: " + fileWriter.toString());
		bufferedWriter.close();
		
			System.out.println("That's all folks! GOODBYE...");
//			client_socket.close();
			client_socket.shutdownInput();
			
	}

	
	// *** ONLY WORKS WITH CONFIGURED CLIENT SOCKET :( *** \\
	public void listenAndTransferFileLineByLine(String filename) throws IOException {
		
		client_socket= server_socket.accept();
		String file_line = "", file_contents = "";
		String input_line;
		BufferedReader reader= new BufferedReader( 
				 new InputStreamReader(client_socket.getInputStream()) );
		PrintWriter output= new PrintWriter( client_socket.getOutputStream(), true );
		
		// read in the file
		File file = new File(filename);
		BufferedReader file_reader = new BufferedReader(new FileReader(file));
		System.out.println("Reading from file: " + file.toString());

		while ((input_line= reader.readLine()) != null) {
			System.out.println( "Received from client: " );
			System.out.println( "   " + input_line );
			client_socket.shutdownInput();
			while ((file_line = file_reader.readLine()) != null) {
			file_contents = file_line + " ";
			output.println(input_line + " -> A gift for you: -> " + file_contents);
			}
		}
			System.out.println("That's all folks! GOODBYE...");
//			client_socket.close();
			
	}
	// *** ONLY WORKS WITH CONFIGURED CLIENT SOCKET :( *** \\
	public void listenAndTransferFileLineByLineAndLog(String fileToRead, String fileToWrite) throws IOException
	{
		client_socket= server_socket.accept();
		String file_line, file_contents = "", write_data = "";
		String input_line;
		BufferedReader reader= new BufferedReader( 
				 new InputStreamReader(client_socket.getInputStream()) );
		PrintWriter output= new PrintWriter( client_socket.getOutputStream(), true );
		
		// read in the file
		File file = new File(fileToRead);
		BufferedReader file_reader = new BufferedReader(new FileReader(file));
		System.out.println("Reading from file: " + file.toString());
//		while ((file_line = file_reader.readLine()) != null) {	
//			file_contents += file_line + " ";
//		}

		while ((input_line= reader.readLine()) != null) {
			System.out.println( "Received from client: " );
			System.out.println( "   " + input_line );
			write_data += input_line + " ";
			client_socket.shutdownInput();
			while ((file_line = file_reader.readLine()) != null) {
			file_contents = file_line + " ";
			output.println(input_line + " -> A gift for you: -> " + file_contents);
			write_data += file_contents + " ";
			}
		}
		
		// write out to file
		FileWriter fileWriter = new FileWriter(fileToWrite);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write("Data Received and Sent ->" + write_data);
		System.out.println("Writing to file: " + fileWriter.toString());
		bufferedWriter.close();
		
			System.out.println("That's all folks! GOODBYE...");
			client_socket.close();
//			client_socket.shutdownInput();
			
	}

	

} // end class
