import java.io.FileWriter;
import java.io.PrintWriter;

public class GenerateBootScript implements itemGenerationADT
{
	private static xSetWacom set = new xSetWacom();
	private FileWriter fw;
	private PrintWriter pw;
	private xSetWacom xsw;
	private String homeCommand = "echo \"$HOME\"";
	private String home;
	
	public GenerateBootScript()
	{
		
	}
	
	public void gen( String devID, String output, String outputNum )
	{
		try
		{
			home = set.getHome( homeCommand );
			fw = new FileWriter( home + "/.config/wacom-conf/wacomBootconfig", false );
			pw = new PrintWriter( fw, true ); //autoflush enabled
			
			pw.print( "#!/bin/bash \n" 
					+ "\n"
					+ "xsetwacom set " + devID + " \"MapToOutput\" " + output + outputNum + "\n" );
			
			pw.close();
			fw.close();
		}
		catch ( Exception e )
		{
			System.out.println( e );
			// To do: Have it write the following to a log.
		}
	}
	
	public void rm()
	{
		
	}
}