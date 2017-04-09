//P.Dilum De Silva
//UoW - 16266371
//IIT - 2016142

import java.io.Serializable;
import java.util.Scanner;

public class Controller implements Serializable{
	
	public void displayProgramMenu() {
		// creating a function object
		function hotelControl = new function();
		
		Scanner input = new Scanner(System.in);
		//obj.loadFromFile();
		boolean repeatFlag = true;
		while (repeatFlag == true) {
			
			// The welcome message
			
			System.out.println("\n====================== Welcome to Hotel OOP =======================");
			System.out.println("\n");
			System.out.println("___________-Menu-___________\n");
			
			//functions of the hotel program

			System.out.println("V: Views All rooms");
			System.out.println("A: Add a customer to a room");
			System.out.println("D: Delete a customer from room");
			System.out.println("S: Store program array data into a plain text file");
			System.out.println("L: Load program data back from the file into the array");
			System.out.println("Z: Display the names of the customers who have been allocated to a room");
			System.out.println("");
			
			System.out.println("X: Exit \n\n");
			System.out.print("Please enter your selection to continue : "+"\n");
			
			try {
				String usrInput = input.nextLine();
				if (usrInput.length() == 1) {
					// Getting first character from user input
					char userInput = usrInput.charAt(0);
					// Both upper and lower case characters are 
					// allowed for a particular selection
					switch (userInput) {
					case 'v':
					case 'V':
						System.out.println("----View Rooms----\n");
						hotelControl.viewRooms();
						break;
					case 'a':
					case 'A':
						hotelControl.addCustomer();
						break;
					case 'd':
					case 'D':
						hotelControl.deleteCustomer();
						break;
					case 's':
					case 'S':
						hotelControl.storeInFile();
						break;
					case 'l':
					case 'L':
						hotelControl.loadFromFile();
						break;
					case 'z':
					case 'Z':
						hotelControl.roomAllocationOrder();
						break;
					case 'x':
					case 'X':
						//confirming whether user wants to exit or continue back with the program
						System.out.println("\nYou have selected to Exit!");
						System.err.println("Do you really want to exit?");
						System.out.println("Type 'Y'-yes | 'N'-no");
						
						String exitConfirm = input.nextLine();
						
							if (exitConfirm.equalsIgnoreCase("y")){
								System.err.println("\n\n****Program Terminated****");
								System.exit(0);
								break;	
							}else if (exitConfirm.equalsIgnoreCase("n")){
								System.out.println("");
								continue;
							}
							
					default:
						System.out.println("Invalid input");
						break;
					}
				} else {
					System.out.println("! Invalid Input. Input should contain only one character !");
				}

			} catch (Exception e) {
				System.out.println();
				e.printStackTrace();
			}
		}
	}
}
