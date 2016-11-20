/*****************************************
/* Name:Alba Maloff
/* Course: CIS 4020 01N
/* Semester: Fall 2016
/* Assignment: Program 11 - TieFighters Stacks
/* Date started: November 18,2016 
/* Date finished:November 19, 2016
/*
/* Description: This program demonstrates the use of stacks by creating 2 stacks and using the different commands such as push and pop.
 */
/****************************************/

import java.io.File;														//import statements
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

//Class: TieFightersStacks Class
//Description: This class consists of the main method that is used for calling the input and output method and also declaring the 
//maximum amount of tie fighters per stack and declaring the 2 stacks used.
public class TieFightersStacks {

	final static int MAXTieFighters = 10;									//Declares Final Variable
	static Stack<String> HangarA = new Stack<String>();						//Initializes Stack A
	static Stack<String> HangarB = new Stack<String>();						//Initializes Stack B
	
	/*******************************************
	/* Function: Main Method
	/* Description: It calls the 2 methods below which are the:
	 *    1. inputMethod  
	 *    2. outputMethod
	/******************************************/
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		inputMethod();
		outputMethod();
	}
	
	/*******************************************
	/* Function: inputMethod
	/* Description: This method inputs the file from TieFighters text file and loops so that while there is a Nextline
	 * it uses a split to separate every line so that the determinant code and the number of the tie fighter are split into 2 separate items.
	 * This way depending on the code it determines which method to call.
	 */
	/******************************************/
	public static void inputMethod(){
		
		File file = new File("tiefighters.txt");							//Sets up file input
		Scanner inputFile = null;											//Initializes inputFile to null
				
		try{																//try catch for the input so that if there is no file it will 
			inputFile = new Scanner (file);									//be found
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		while(inputFile.hasNextLine()){										//while loop that reads until the end of the file
			
			String Temp = inputFile.nextLine();								//temporary variable
			String parts[] = Temp.split(" ");								//declares an array to store temp line and uses the split method
																			//using the empty space as a delimiter
			if (parts[0].equals("1")){ 										//if statement that accesses the subscript 0 to find the determinant code
					push(parts[1]);											//if 1 = push method
			}
				else if (parts[0].equals("2")){								//otherwise if 2 = pop method
					pop();
				}
				else popAll();												//all else popAll method
		}
	}
		
	/*******************************************
	/* Function: push Method
	/* Description: This method checks both Stacks if they are empty and pushes a new tie fighter, if they are both full it reroutes them
	 * to the main hangar.
	 */
	/******************************************/
		public static void push(String string){
			
			if (HangarA.size() < MAXTieFighters){													//if hangar A is < 10
				HangarA.push(string);																//push tie fighter into stack
				System.out.println("Tie Fighter " + string + " has docked in Hangar A");			//statement telling what tie fighter went into what hanger
			}
			else if (HangarB.size() < MAXTieFighters){												//if hangar B is < 10
				HangarB.push(string);																//push tie fighter into stack
				System.out.println("Tie Fighter " + string + " has docked in Hangar B");			//statement telling what tie fighter went into what hanger
			}
			else
				System.out.println("Tie Fighter " + string + " has been rerouted to main hangar");	//both full it reroutes them to the main hangar
		}
		
/*******************************************
/* Function: pop Method
/* Description: This method checks both Stacks if they are NOT empty it pops a tie fighter out, if they are both full it prints a statement
* rerouting to the main hangar.
*/
/******************************************/		
		public static void pop(){

			if(!HangarA.isEmpty()){															//while Hangar A is not empty 
				String string1 = HangarA.pop();												//stores the popped tie fighters name in a string
				System.out.println("Tie Fighter " + string1 + " has left Hangar A");		//Outputs which TieFighter was left
			}
			else if (!HangarB.isEmpty()){													//while Hangar A is not empty 
				String string2 = HangarB.pop();												//stores the popped tie fighters name in a string
				System.out.println("Tie Fighter " + string2 + " has left Hangar B");		//Outputs which TieFighter was left
			}
			else 
				System.out.println("Command rerouted to main hangar");						//commands to be rerouted to hangar
			
		}
		
/*******************************************
/* Function: popAll Method
/* Description: This method checks if both Stacks are empty it prints a statement saying they are empty, if not it pops all the tie fighters to empty the stacks. 
*/
/******************************************/
		public static void popAll(){
			
			if(HangarA.isEmpty() && HangarB.isEmpty())										//checks if both stacks are empty
				System.out.println("Bay is empty, command rerouted to main hangar bay");
			
			while (!HangarA.isEmpty()){														//while Hangar A is not empty 
				String string1 = HangarA.pop();												//stores the popped tie fighters name in a string
				System.out.println("Tie Fighter " + string1 + " has left Hangar A");		//Outputs which TieFighter was left
			}
			while (!HangarB.isEmpty()){														//while Hangar B is not empty
				String string2 = HangarB.pop();												//stores the popped tie fighters name in a string
				System.out.println("Tie Fighter " + string2 + " has left Hangar B");		//Outputs which TieFighter was left
			}
		}
		
/*******************************************
/* Function: output Method
/* Description: This method declares the PrintWriter and outputs to a file the remaining contents of the two stacks.
/******************************************/	
		public static void outputMethod(){		
			
			PrintWriter outputFile = null;									//Initializes the outputFile to null
			
			try {															//try catch to catch all the exceptions that can occur
				outputFile = new PrintWriter ("hangarlog.txt");				//Prints the outputFile on hangarlog.txt
				
			} catch (FileNotFoundException e) {			
				e.printStackTrace();
			}
			
			outputFile.print("Hangar A contains Tie Fighters:");			
			
			while (!HangarA.isEmpty()){										//while hangar is not empty pop all the tie fighters left in it
				String string1 = HangarA.pop();								//stores the popped tie fighters name in a string
				outputFile.print(" " + string1);							//outputs the tie fighters left
			}
			
			outputFile.println();
			outputFile.print("Hangar B contains Tie Fighters:");
			while (!HangarB.isEmpty()){										//while hangar is not empty pop all the tie fighters left in it
				String string2 = HangarA.pop();								//stores the popped tie fighters name in a string
				outputFile.print(" " + string2);							//outputs the tie fighters left
			}
			
			outputFile.close();												//close outputfile
		}
	}