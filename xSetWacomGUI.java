import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class xSetWacomGUI extends JFrame //implements ActionListener, ItemListener
{	
	xSetWacom set = new xSetWacom();
	
	// input comboboxes
	private JComboBox jcbDeviceID, jcbOutput, jcbOutputNum;
	
	// tabs and panel for specific settings.
	private JPanel jpTab1 = new JPanel();
	private JPanel jpTab2 = new JPanel();
	private JTabbedPane jtpGUI = new JTabbedPane();
	
	// xsetwacom mode radiobuttons
	private JRadioButton absolute, relative;
	private ButtonGroup AOrR;
	
	// checkboxes for removal of config files if no longer desired
	private JCheckBox jcbBootConfRemoval, jcbPermanentConfRemoval;
	
	//button to execute command
	private JButton btnApply, btnApplyConfig, btnGenBoot, btnGenPermanentConf;
	      
	public xSetWacomGUI()
	{
		super( "xsetWacom stylus mapper" ); // call the window this.
		
		///---------------------------------------------------
		/// 	Everything below goes in jpTab1
		///---------------------------------------------------
		jpTab1.setLayout( new GridLayout( 3, 2, 20, 20 ) ); // 3 rows, 2 columns, 20,20 padding
		//jpTab1.setLayout( new GridLayout( 4, 2, 20, 20 ) ); // <- layout to use if the JRadioButtons were to be implemented.
		jpTab1.add( new JLabel( "Stylus ID: " ) );
		
		String[] StylusID = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20" };
		jcbDeviceID = new JComboBox( StylusID );
		jpTab1.add( jcbDeviceID );

	    jpTab1.add( new JLabel( "Map to: " ) ); // add JLabel stating "Map to: "
	      
	    String[] MapToOptions = { "LVDS", "VGA", "HDMI", "DP", "DVI", "CRT", "DFP", "HEAD-" }; 
	    jcbOutput = new JComboBox( MapToOptions );  
	    jpTab1.add( jcbOutput ); 
	      
	    btnApply = new JButton( "Apply" );
	    jpTab1.add( btnApply );
	    btnApply.addActionListener(		         
	  		 new ActionListener()
		         {
	  			 	 public void actionPerformed( ActionEvent ae )
	    			 {
	    				try 
	    				{
							set.execute( ( String ) jcbDeviceID.getSelectedItem(), ( String ) jcbOutput.getSelectedItem(), ( String ) jcbOutputNum.getSelectedItem() );
						} 
	    				catch (IOException | InterruptedException e) 
						{
							e.printStackTrace();
						}
	    			 }
		         }
		     
	    	);
	      
		String[] OutputNum = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		jcbOutputNum = new JComboBox( OutputNum );
		jpTab1.add( jcbOutputNum );
	      
	    /*absolute = new JRadioButton( "absolute", true );
	    relative = new JRadioButton( "relative" );
	    jpTab1.add( absolute );
	    jpTab1.add( relative );
	    AOrR = new ButtonGroup();
	    AOrR.add( absolute );
	    AOrR.add( relative );
	    absolute.addItemListener( this );
	    relative.addItemListener( this );*/
		
		///---------------------------------------------------
		/// 		Everything below goes in jpTab2
		///---------------------------------------------------
	      
		jpTab2.setLayout( new GridLayout( 4, 2, 20, 20 ) ); // 4 rows, 2 columns, 20,20 padding
		jpTab2.add( new JLabel( "Boot: " ) );
		
		btnGenBoot = new JButton( "Gen" );
		jpTab2.add( btnGenBoot );
		btnGenBoot.addActionListener(		         
		  		 new ActionListener()
			         {
		  			 	 public void actionPerformed( ActionEvent ae )
		    			 {	    				
		  			 		GenerateBootScript gbs = new GenerateBootScript();
							 gbs.gen( ( String ) jcbDeviceID.getSelectedItem(), ( String ) jcbOutput.getSelectedItem(), ( String ) jcbOutputNum.getSelectedItem() );
		    			 }
			         }
		    	);
		
		jpTab2.add( new JLabel( "Make permanent: " ) );
		
		btnGenPermanentConf = new JButton( "Gen" );
		jpTab2.add( btnGenPermanentConf );
		btnGenPermanentConf.addActionListener(		         
		  		 new ActionListener()
		         {
	  			 	 public void actionPerformed( ActionEvent ae )
	    			 {	    				
	  			 		GenerateDaemonScript gds = new GenerateDaemonScript();
						 gds.gen( ( String ) jcbDeviceID.getSelectedItem(), ( String ) jcbOutput.getSelectedItem(), ( String ) jcbOutputNum.getSelectedItem() );
	    			 }
		         }
	    	);
		
		jcbBootConfRemoval = new JCheckBox( "remove boot" );
		jpTab2.add( jcbBootConfRemoval );
		
		jcbPermanentConfRemoval = new JCheckBox( "remove perm" );
		jpTab2.add( jcbPermanentConfRemoval );
		
		btnApplyConfig = new JButton( "Apply" );
		jpTab2.add( btnApplyConfig );
		
		jpTab2.add( new JLabel( "testst" ) );
		
		///---------------------------------------------------
		/// 		Adds the Panels to the actual JFrame.
		///---------------------------------------------------
		jtpGUI.add( "Map", jpTab1 );
		jtpGUI.add( "Config", jpTab2 );
		add( jtpGUI );
	      
		///---------------------------------------------------
		///	 	Standard way of setting up a JFrame.
		///---------------------------------------------------
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setSize( 400, 170 );
		//setSize( 400, 200 ); // <- resolution for JRadioButtons.
		setVisible( true );
	 }
		
		///---------------------------------------------------
		///	 	itemEvent for radiobuttons ( not working yet )
		///---------------------------------------------------
	   /*public void itemStateChanged( ItemEvent ie )
	   {
		   xSetWacom set = new xSetWacom();
		   if ( ie.getSource() == absolute )
		   {
			   try 
			   {
				   set.xSetMode( "Absolute" );
			   } 
			   catch ( IOException | InterruptedException e ) 
			   {
				   e.printStackTrace();
			   }
		   }
		   else
		   {
			   try
			   {
				   set.xSetMode( "Relative" );
			   }
			   catch ( IOException | InterruptedException e )
			   {
				   e.printStackTrace();
			   }
		   }
	   }*/
	
	   public static void main( String[] args )
	   {
	      xSetWacomGUI xSWGUI = new xSetWacomGUI();
	      /*String test = "echo \"$HOME\"";
	      try 
	      {
			System.out.println( set.getHome( test ) );
		  } 
	      catch (IOException | InterruptedException e) 
	      {
			e.printStackTrace();
		  }*/
	   } // end main
	}
