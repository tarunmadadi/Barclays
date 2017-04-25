package com.java;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;


public class ServerStatus {
	
	static int interval;
	public static void main(String args[])
	{
		int choice=0;
		
		Thread t=new Thread( new Read());// creation of child thread
		boolean bl=true,b=true;
		while(b)
		{
		try
		{
			System.out.println("ENTER NEW INTERVAL TIME in MILLISECONDS\n");
			
			Scanner sr=new Scanner(System.in);
		  interval=Integer.parseInt(sr.next());
		  sr=null;
		  b=false;
		
		}
		catch(Exception e)
		{
			b=true;
			System.out.println("INVALID INPUT\n");	
		}
	}
		
		t.start();//starting child thread
		do
		{
			System.out.println("ENTER YOUR CHOICE FOR  INTERVAL/TERMINATION\n 1.NEW INTERVAL \t  2.MAINTAIN SAME OLD INTERVAL \t 3.TERMINATE\n");
			try
			{
				Scanner sr=new Scanner(System.in);
			  choice=sr.nextInt();
			  switch(choice)
			  {
			  case 1: System.out.println("ENTER INTERVAL TIME in MILLISECONDS\n");
			              try{
			              sr=new Scanner(System.in);
			              interval=sr.nextInt();
			              t.interrupt();
			              System.out.println("NEW INTERVAL UPDATED\n");
			              
			    		  sr=null;
			              }
			              catch(Exception e)
			      		{
			            	  System.out.println("INVALID INPUT\n");
			            	  continue;
			      		}
			              break;
			  case 2: System.out.println("MAINTAINING THE SAME INTERVAL\n");
				      break;          
			  case 3:  t.stop();
			           bl=false;
			           System.out.println("PROGRAM TERMINATED");
			           break;
			   default:System.out.println("INVALID INPUT\n");
			            continue;
			             
			  }
			  sr=null;
			
			}
			catch(Exception e)
			{
				bl=true;
				System.out.println("PLEASE ENTER VALID INPUT" );
			}
			
		}while(bl);
		
		
		
	}

}

class Read implements Runnable
{
	
	public void run()
	{
	
		Map m=new HashMap();
		m.put("0/3","NO ALERT");
		m.put("1/3","AMBER ALERT");
		m.put("2/3","RED ALERT");
		m.put("3/3","RED ALERT");
		Map status=new HashMap();
		status.put("0/3","OK");
		status.put("1/3","WARNING");
		status.put("2/3","WARNING");
		status.put("3/3","CRITICAL");
		String s;
		List l = new ArrayList();
		boolean bl=false;
		int count1=0,count2=0;
		
		 BufferedReader br=null;
		 
		do
		{
			 System.out.println("\n");
		 try {
		     
	        
	          br = new BufferedReader(new FileReader("SERVER_STATUS.txt")); // open input stream test.txt for reading purpose.
	         if( (s=br.readLine())!=null){
	        	 
	        	 
	       while((s=br.readLine())!=null)
	       {
	   count1 ++;
	    	   StringTokenizer st = new StringTokenizer(s," ");  
	    	   while (st.hasMoreTokens()) {  
	    		   l.add(st.nextToken()); 
	    		  
	    	     }  
	    	   if( ((status.get(l.get(1)).toString()) . equalsIgnoreCase(l.get(2).toString()) ))
	    	   {
	    		  }
	    	   else
	    	   {
	    		   throw new Status_ServerOfflineValuesException(); 
	    	   }
	    	 if(m.get(l.get(1))==null)
	    	 {
	    	 throw new StatusException();
	    	 }
	    	 if(!(l.get(1).equals("0/3")))
	    	 {
	    		 
	         System.out.println(m.get(l.get(1))+" FOR \""+l.get(0)+"\"");
	    	 }
	    	 else
	    	 {
	    		 count2 ++;
	    	 }
	    	 
	         l.clear();
	         
	       }
	         
	         bl=true;
	        
	        Thread.currentThread().sleep(ServerStatus.interval);
	         
	         } 
	         if(count1==count2)
	         {
	        	 System.out.println(m.get("0/3"));
	         }
	         br.close();
	        
		 }
		 catch(InterruptedException e) {
	    	  bl=true;
	         System.out.println("THREAD WITH NEW INTERVAL HAD STARTED");;
	      }
		 
	      catch(Exception e) {
	    	  bl=false;
	         e.printStackTrace();
	      }
	      }while(bl);
	}
}
class StatusException extends Exception
{
	StatusException()
	{
	super("INVALID STATUS IN A READING FILE");
	}
}
class Status_ServerOfflineValuesException extends Exception
{
	Status_ServerOfflineValuesException()
	{
	super("STATUS AND SERVERS_OFFLINE VALUES MISMATCH,PLEASE CORRECT IT");
	}
}
