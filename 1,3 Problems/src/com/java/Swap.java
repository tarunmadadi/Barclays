package com.java;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Swap {
	
	public static void main(String args[])
	{
		int a = 0,b=0;
		boolean bl;
		
	    do
		{
		try
		{
			System.out.println("ENTER FIRST INTEGER VALUE ");
			Scanner sr=new Scanner(System.in);
	        a=sr.nextInt();
	        bl=false;
	        sr=null; // Making Object to be eligible for garbage collection 
		}
		catch(InputMismatchException e)
		{
			System .out.println("INVALID INPUT");
			bl=true;
		}
			
		}
		while(bl);
		
		do
		{
		
	    try
			{
	    System.out.println("ENTER SECOND INTEGER VALUE ");
	    Scanner sr=new Scanner(System.in);
	     b=sr.nextInt();
	     bl=false;
	     sr=null; // Making Object to be eligible for garbage collection 
			}
	     catch(InputMismatchException e)
	     {
	    	 System .out.println("INVALID INPUT");
	    	 bl=true;
	     }
		}while(bl);
	     
		System.out.println("VALUES BEFORE SWAPPING:\n a="+a+"\n b="+b); 
		b=b+a;
		a=b-a;
		b=b-a;
		System.out.println("VALUES AFTER SWAPPING:\n a="+a+"\n b="+b); 
	 
		
	}
}
