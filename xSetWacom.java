import java.io.*;

public class xSetWacom
{	
	//no fields passed
	
	public xSetWacom()
	{
	}	
	
	public void execute( String stylusID, String Output, String OutputNum) throws IOException, InterruptedException
	{
		String MapToMonitor = "xsetwacom set " + stylusID + " \"MapToOutput\" " + Output + OutputNum;
		System.out.println( MapToMonitor );
		try 
		{
			runShellCommand( MapToMonitor, true );
		} 
		catch (InterruptedException | IOException e)
		{
			e.printStackTrace();
		}
	}
	
	///---------------------------------------------------
	/// 		TO DO:
	///			allow device ID to change
	///---------------------------------------------------
	public void xSetMode( String mode ) throws IOException, InterruptedException
	{
		//int idCounter = firstDeviceID;
		String setModeTo = "xsetwacom set " + 12 + " Mode " + mode;
		System.out.println( setModeTo );
		runShellCommand( setModeTo, true );
	}
	
	private static void runShellCommand( String shellCommand, boolean printShellOutput ) throws IOException, InterruptedException 
	{
		Runtime runtime = Runtime.getRuntime() ;
		Process shellProcess = runtime.exec( new String[] { "bash", "-c", shellCommand } );
		 
		shellProcess.waitFor() ;
		BufferedReader shellCommandReader = new BufferedReader( new InputStreamReader(shellProcess.getInputStream() ) ) ;
		 
		String currentLine = null;
		while ( (currentLine = shellCommandReader.readLine() ) != null )
		{
		if (printShellOutput)
		System.out.println(currentLine);
		}
	}
	
	///---------------------------------------------------
	/// 		Similar to the method above
	///			but used to simply get info about 
	///			the user's home directory.
	///---------------------------------------------------
	
	public static String getHome( String shellCommand ) throws IOException, InterruptedException 
	{
		String rv = null;
		Runtime runtime = Runtime.getRuntime() ;
		Process shellProcess = runtime.exec( new String[] { "bash", "-c", shellCommand } );
		 
		shellProcess.waitFor() ;
		BufferedReader shellCommandReader = new BufferedReader( new InputStreamReader(shellProcess.getInputStream() ) ) ;
		 
		String currentLine = null;
		while ( (currentLine = shellCommandReader.readLine() ) != null )
		{
				System.out.println( currentLine );
				rv = currentLine;
		}
		return rv;
	}
}