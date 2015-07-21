import java.io.FileWriter;
import java.io.PrintWriter;

public class GenerateDaemonScript implements itemGenerationADT
{
	private static xSetWacom set = new xSetWacom();
	private FileWriter fw;
	private PrintWriter pw;
	private xSetWacom xsw;
	private String homeCommand = "echo \"$HOME\"";
	private String home;
	
	public GenerateDaemonScript()
	{
		
	}
	
	public void gen( String devID, String output, String outputNum )
	{
		try
		{
			home = set.getHome( homeCommand );
			fw = new FileWriter( home + "/.config/wacom-conf/wacomDaemon", false );
			pw = new PrintWriter( fw, true ); //autoflush enabled
			
			pw.print( "#!/bin/bash \n" 
					+ "\n"
					+ "while : \n"
					+ "do \n" 
					//+ "	xsetwacom set \"Wacom Graphire3 stylus\" \"MapToOutput\" DFP9 \n" 
					+ "	xsetwacom set " + devID + " \"MapToOutput\" " + output + outputNum + "\n"
					+ "	sleep 1 \n" 
					+ "done \n" );
			
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
	
	/*public static void main( String[] args ) //tests out the boot generating.
	{
		GenerateDaemonScript gds = new GenerateDaemonScript();
		gds.gen( "12", "DFP", "9" );
	}*/
}