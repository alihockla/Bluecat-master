import java.io.*;
import java.net.*;
import java.util.*;


public class Bluecat {

	public static void main(String[] args) throws IOException {
		
		String data = "";
		String newString = "";
		int port_number;
		int caseNum = 0;
		
		// Parse command line args
		if (args.length == 2 && !args[0].contains("-")) { 	 // address & port # input
			caseNum = 1;
		}
		else if (args[0].equals("-f")) { // -f <input filename>
			caseNum = 2;
		}
		else if (args[0].equals("-r")) { // -r <input filename>
			caseNum = 3;
		}
		else if (args[0].equals("-o")) { // -o <output filename>
			caseNum = 4;
		}
		else if (args[0].equals("-p")) { // -p <log filename>
			caseNum = 5;
		}
		else if (args[0].equals("-l")) { // -l [port number] . . .
			caseNum = 6;
		}
		
		switch (caseNum) { 
		
		// no arguments are input in command line
		case 0: 
			System.out.println("Usage: \n		Enter [-lfrop] <port number> and/or file name ");
			System.out.println("Arguments provided: " + args.length);
			return;
			
			// address & port # input. read from standard input and transmit the content to the other end (default)
			//Usage: java Bluecat localhost 3276
		case 1:
			// starting client
			port_number = Integer.valueOf(args[1]);		
			try
			{
				// input the message from standard input
				BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Connected...\nEnter message: ");
				String message= reader.readLine();
				// to connect to localhost
				BluecatClientSocket client = new BluecatClientSocket(args[0], port_number);
				System.out.println("Transmitting message: '" + message + "'...");
				client.transmit(message);
				System.out.println("Done...exiting...");
			}
			catch (Exception e)
			{
				System.out.println("Something went wrong: " + e.getMessage());
			}
			return;
			
			// -f input. read a specified file and transmit the entire contents to the other end
			// start server or client. Usage: java Bluecat -f count.txt 3276	or	 java Bluecat -f count.txt localhost 3276
			//							or 	  java Bluecat -f count.txt -o output.txt localhost 3276
		case 2:
			try
			{
				BluecatClientSocket client = null;
				if (args.length == 3) { 
					// starting client
					port_number = Integer.valueOf(args[2]);		
					client = new BluecatClientSocket(port_number);
					
					// read in the file
					File file = new File(args[1]);
					BufferedReader file_reader = new BufferedReader(new FileReader(file));
					System.out.println("Connected...\nReading from file: " + file.toString());
					while ((data = file_reader.readLine()) != null) {	
						System.out.println("Reading data: '" + data + "'");
						newString += data + " ";
					}
					
					System.out.println("Transmitting message: \n	'" + newString + "'");
					client.transmit(newString);
					System.out.println("Done...exiting...");
					return;
				}
				else if (args.length == 4) {
					// starting client with localhost
					port_number = Integer.valueOf(args[3]);		
					client = new BluecatClientSocket(args[2], port_number);
					
					// read in the file
					File file = new File(args[1]);
					BufferedReader file_reader = new BufferedReader(new FileReader(file));
					System.out.println("Connected...\nReading from file: " + file.toString());
					while ((data = file_reader.readLine()) != null) {	
						System.out.println("Reading data:  '" + data + "'");
						newString += data + " ";
					}
					
					System.out.println("Transmitting message: \n	'" + newString + "'");
					client.transmit(newString);
					System.out.println("Done...exiting...");
					return;
				}
				else if (args.length == 6) {
					// starting client with localhost
					port_number = Integer.valueOf(args[5]);		
					client = new BluecatClientSocket(args[4], port_number);
					
					// read in the file
					File readInfile = new File(args[1]);
					BufferedReader file_reader = new BufferedReader(new FileReader(readInfile));
					System.out.println("Connected...\nReading from file: " + readInfile.toString());
					while ((data = file_reader.readLine()) != null) {	
						System.out.println("Transmitting message: \n'" + data);
						newString += data + " ";
					}
					
					// write out to file
		            FileWriter writeOutFile = new FileWriter(args[3]);
		            BufferedWriter bufferedWriter = new BufferedWriter(writeOutFile);
					System.out.println("Received content: " + newString);
					System.out.println("Writing to file: " + writeOutFile.toString());
		            bufferedWriter.write(newString);
		            bufferedWriter.close();
					System.out.println("Done...exiting...");
					return;
				}
			
			} catch (Exception e)
			{
				System.out.println("Something went wrong: " + e.getMessage());
			}
		
			return;

			// -r input. read a specified file and transmit the entire contents to the other end, line by line.
			// start server or client. Usage: java Bluecat -r count.txt 3276	or	 java Bluecat -r count.txt localhost 3276
			//							or 	  java Bluecat -r count.txt -o output.txt localhost 3276
		case 3:
			try
			{
				BluecatClientSocket client = null;
				if (args.length == 3) { 
					// starting client
					port_number = Integer.valueOf(args[2]);		
					client = new BluecatClientSocket(port_number);
					
					// input the message from standard input
					BufferedReader reader= new BufferedReader(new FileReader(args[1]));
					//System.out.print( "Enter message: " );
					String message;
					while ((message = reader.readLine()) != null) {	
						System.out.println("Transmitting message: '" + message + "'...");
						client.transmit(message);
					}
//					client.client_socket.close();
//					client.client_socket.shutdownOutput();
					System.out.println( "Done...exiting..." );
					return;
				}
				else if (args.length == 4) {
					// starting client with localhost
					port_number = Integer.valueOf(args[3]);		
					client = new BluecatClientSocket(args[2], port_number);
					
					// read in the file
					BufferedReader reader= new BufferedReader(new FileReader(args[1]));
					//System.out.print( "Enter message: " );
					String message;
					while ((message = reader.readLine()) != null) {	
						System.out.println("Transmitting message: '" + message + "'...");
						client.transmit(message);
					}
//					client.client_socket.close();
//					client.client_socket.shutdownOutput();
					System.out.println( "Done...exiting..." );
					return;
				}
				else if (args.length == 6) {
					// starting client with localhost
					port_number = Integer.valueOf(args[5]);		
					client = new BluecatClientSocket(args[4], port_number);
					
					// read in the file
					BufferedReader reader= new BufferedReader(new FileReader(args[1]));
					//System.out.print( "Enter message: " );
					String message;
					// write out to file
					FileWriter writeOutFile = new FileWriter(args[3]);
					BufferedWriter bufferedWriter = new BufferedWriter(writeOutFile);
					while ((message = reader.readLine()) != null) {	
						System.out.println("Transmitting message: '" + message + "'...");
						client.transmit(message);
						System.out.println("Writing to file: " + writeOutFile.toString());
						bufferedWriter.write(message + "\n");
					}
					
		            bufferedWriter.close();
					System.out.println("Done...exiting...");
					return;
				}
			
			} catch (Exception e)
			{
				System.out.println("Something went wrong: " + e.getMessage());
			}
		
			return;

			
			
			// -o input. Must also be able to save received content to a specified file, either by 
			// overwriting the existing file or appending to it
			// start CLIENT only. Usage: java Bluecat -o output.txt 3276
			//				       or 	 java Bluecat -o output.txt localhost 3276
		case 4:
			try
			{
				BluecatClientSocket client = null;
				if (args.length == 3) { 
					// starting client
					port_number = Integer.valueOf(args[2]);		
					client = new BluecatClientSocket(port_number);
					
				// read in the file
				File file = new File(args[1]);
				BufferedReader file_reader = new BufferedReader(new FileReader(file));
				System.out.println("Connected...\nReading from file: " + file.toString());
				while ((data = file_reader.readLine()) != null) {	
					newString += data + " ";
				}
				// overwriting to same file
				FileWriter fileWriter = new FileWriter(file);
		        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		        System.out.println("Received content: " + newString);
				System.out.println("Writing to file: " + file.toString() + "\nTransmitting to server...");
				bufferedWriter.write(newString + "*EDITED*");
				client.transmit(newString);
		        bufferedWriter.close();
		            
				System.out.println("Done...exiting...");
				}
				
				else if (args.length == 4) {
					// starting client with localhost
					port_number = Integer.valueOf(args[3]);		
					client = new BluecatClientSocket(args[2], port_number);
					
					// read in the file
					File file = new File(args[1]);
					BufferedReader file_reader = new BufferedReader(new FileReader(file));
					System.out.println("Connected...\nReading from file: " + file.toString());
					while ((data = file_reader.readLine()) != null) {	
						newString += data + " ";
					}
					// overwriting to same file
		            FileWriter fileWriter = new FileWriter(file);
		            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
					System.out.println("Received content: " + newString);
					System.out.println("Writing to file: " + file.toString() + "\nTransmitting to server...");
					bufferedWriter.write(newString + "*EDITED*");
					client.transmit(newString);
		            bufferedWriter.close();
		        
		            System.out.println("Done...exiting...");
				}
			}
			catch (Exception e)
			{	
				System.out.println("Something went wrong: " + e.getMessage());
			}
			
			return;
				
			// -p input. When acting as a server, it must also be able to log all packets received and sent 
			// start SERVER only. Usage: java Bluecat -p output.txt 3276
		case 5:
			// start server
			
			port_number = Integer.valueOf( args[2] );
			try
			{
				BluecatServerSocket server= new BluecatServerSocket( port_number );
				System.out.println( "Listening on port " + Integer.toString( port_number ) );
				
				FileWriter fileWriter = new FileWriter(args[1]);
		        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		        bufferedWriter.write(server.listen());
		        System.out.println("Writing to file: " + fileWriter.toString());
		        bufferedWriter.close();
				
				System.out.println( "Listening concluded; All packets logged; shutting down..." );
			}
			catch ( Exception e )
			{
				System.out.println( e.getMessage() );
			}
			return;
			
			// -l input. java Bluecat -l [port number] [-frp] [option data]
			// start Server. Usage: java Bluecat -l 3276	or	java Bluecat -l 3276 -f one-liners.txt 	or 	java Bluecat -l 3276 -r one-liners.txt
			//						java Bluecat -l 3276 -r one-liners.txt -p packets.txt
		case 6:
			// start server
			newString = "";
			port_number = Integer.valueOf( args[1] );
			try
			{
				BluecatServerSocket server= new BluecatServerSocket( port_number );
				System.out.println( "Listening on port " + Integer.toString( port_number ) );
				
				if (args.length == 2) {
					server.listen();
					System.out.println( "Listening concluded; shutting down..." );
					return;
				}
				else if (args.length == 4 && args[2].equals("-f")) {
					server.listenAndTransferFileAtOnce(args[3]);
					System.out.println( "Listening concluded; File contents sent; shutting down..." );
					return;
				}
				// *** ONLY WORKS WITH CONFIGURED CLIENT SOCKET :( *** \\
				else if (args.length == 4 && args[2].equals("-r")) {
					server.listenAndTransferFileLineByLine(args[3]);
					System.out.println( "Listening concluded; File contents sent; shutting down..." );
					return;
				}
				else if (args.length == 6 && args[2].equals("-f")) {
					server.listenAndTransferFileAtOnceAndLog(args[3], args[5]);
					System.out.println( "Listening concluded; File contents sent; All data logged;  shutting down..." );
					return;
				}
				// *** ONLY WORKS WITH CONFIGURED CLIENT SOCKET :( *** \\
				else if (args.length == 6 && args[2].equals("-r")) {
					server.listenAndTransferFileLineByLineAndLog(args[3], args[5]);
					System.out.println( "Listening concluded; File contents sent; All data logged;  shutting down..." );
					return;
				}


				
			}
			catch ( Exception e )
			{
				System.out.println( e.getMessage() );
			}				
			
			return;

			
			
		}
		
	} // end main
} // end class
