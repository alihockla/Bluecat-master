import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Ali Hockla
 *
 */

public class ConfiguredClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if ( args.length != 1 )
		{
			System.out.println( "Usage:" );
			System.out.println( "   java ConfiguredClientTest <port number>" );
			return;
		}
		
		int port_number= Integer.valueOf( args[0] );		
		try
		{
			// input the message from standard input
			BufferedReader reader= new BufferedReader( 
					 new InputStreamReader(System.in) );
			//System.out.print( "Enter message: " );
			String message= reader.readLine();
			
			ConfiguredClientSocket client= new ConfiguredClientSocket(port_number);
			System.out.println( "Transmitting message: '" + message + "'..." );
			client.transmit(message);
			System.out.println( "Done...exiting..." );
		}
		catch ( Exception e )
		{
			System.out.println( e.getMessage() );
		}
	}

}
