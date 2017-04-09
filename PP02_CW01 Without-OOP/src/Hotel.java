//Programming Principles 02 CW-1
//Dilum Maduranga De Silva
//IIT-2016142
//Uow-16266371

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hotel{
	
	//to set a name to the text file
	String textFileName = null;
	
	//created a scanner object to get user inputs
	private Scanner input = new Scanner(System.in);
	
	//declaration of an array called hotelRoomArray for 10 rooms
	//hotelRoomArray holds 10 rows and 3 columns
	String[][] hotelRoomArray = new String[10][3];
	
	//the method to displays the selection menu and continue according to the user choice
	public void displayProgramMenu(){
		
		//obj.loadFromFile();
		
		boolean check1 = true;
		
		//this part will repeat/iterate until boolean variable check1 becomes false
		while (check1 == true) {
			
			// The selection menu of the hotel program
			
			System.out.println("\n====================== Welcome to Hotel No-OOP =======================");
			System.out.println("\n");
			System.out.println("___________-Menu-___________\n");
			
			//functions of the hotel program
			System.out.println("V: Views All rooms");
			System.out.println("A: Add customer to a room");
			System.out.println("E: Display empty rooms");
			System.out.println("D: Delete customer from room");
			System.out.println("F: Find room from customer name");
			// this will allow user to store array data into a text file
			System.out.println("S: Store program data in to file");
			//this will allow user to load the data of the program from the text file into array
			System.out.println("L: Load program data from file");
			System.out.println("O: View rooms Ordered alphabetically by name");
			
			System.out.println("X: Exit \n\n");
			System.out.print("Please enter your selection to continue : "+"\n");
			
			//catching and handling exceptions using a try catch block 
			try {
				//get user selected input
				String selectionInput = input.nextLine();
				
				// to make sure user has inputed a valid selection (validation)
				if (selectionInput.length() == 1) {
					// getting only the first character of the user input and assigning
					//it to the variable called userInput
					char userInput = selectionInput.charAt(0);
					
					//both upper case and lower case are consider in a selection
					switch (userInput) {
					case 'v':
					case 'V':
						System.out.println("****View Rooms****\n"); //display the user selected function of the program
						viewRooms(); //perform the function according to the user selection
						break; //breaking the flow to avoid continuing other cases
						
					case 'a':
					case 'A':
						System.out.println("****Add Customer****\n");
						addCustomer();
						break;
						
					case 'e':
					case 'E':
						System.out.println("****Display Empty Rooms****\n");
						displayEmptyRooms();
						break;
						
					case 'd':
					case 'D':
						System.out.println("****Delete Customer****\n");
						deleteCustomer();
						break;
						
					case 'f':
					case 'F':
						System.out.println("****Find room by name****\n");
						findRoomByName();
						break;
						
					case 's':
					case 'S':
						storeInFile();
						break;
						
					case 'l':
					case 'L':
						loadFromFile();
						break;
						
					case 'o':
					case 'O':
						System.out.println("****Sorting rooms by name****\n");
						alphabaticSort();
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
						
					default: //set a message to inform invalid inputs 
						System.out.println("Invalid input");
						break;
					}
				} 
				//false block of the 'if' condition which used to validate the user input
				else { 
					System.out.println("!!! Invalid Input. Input should contain only one character !!!");
				}

			} catch (Exception e) { 
				System.out.println();
				e.printStackTrace();
			}

		}
	}
	//initializing hotelRoomArray
	public void creatingArray() {
		for (int i = 0; i < hotelRoomArray.length; i++) {
			hotelRoomArray[i][0]=""+i;
			hotelRoomArray[i][1]="empty";
			hotelRoomArray[i][2]="empty";
		}
	}

	// method to display room status
	public void viewRooms() {
		for (int i = 0; i < hotelRoomArray.length; i++) {
			if (hotelRoomArray[i][1].equals("empty")) {
				System.out.println("Room " + (i + 1) + " is empty");
			} else {
				System.out.println("Room " + (i + 1) + " is occupied by " + hotelRoomArray[i][1] + " "
						+ hotelRoomArray[i][2]);
			}
		}
	}

	// method to validate the room number
	public int rooomNoValidator() {
		int roomNum = 0;
		boolean check = true;
		while (check == true) {
			try {
				System.out.print("Enter room number (1-10): ");
				roomNum = Integer.parseInt(input.nextLine());
				System.out.println();
				if (roomNum > 0 && roomNum < hotelRoomArray.length) {
					check = false;
				} else {
					System.out.println("There are only 10 rooms, room number should be between 1-10");
				}
			} catch (Exception e1) {
				System.out.println("Please enter a valid room number");
			}
		}
		return roomNum;
	}

	// method to validate the user entered name (first name , last name)
		public String nameValidator(String namePart) {
			String name = "0";
			boolean check2 = true;
			Pattern p = Pattern.compile("[a-zA-Z]+$"); 

			while (check2 == true) {
				try {
					System.out.print("Enter Customer's " + namePart + " name:");
					name = input.nextLine();
					System.out.println();
					Matcher m = p.matcher(name);
					if (m.matches()) {
						check2 = false;
					} else {
						System.out.println("Please enter a valid " + namePart + " name");
					}

				} catch (Exception e2) {
					// e2.printStackTrace();
					System.out.println("Please enter a valid " + namePart + " name");
				}
			}
			return name;
		}

	// method to add a customer to a room
	public void addCustomer() {
		int roomNum = rooomNoValidator();
		if (hotelRoomArray[roomNum - 1][1].equalsIgnoreCase("empty")) {
			String fName = nameValidator("first");
			String lName = nameValidator("last");
			hotelRoomArray[roomNum - 1][1]=fName;
			hotelRoomArray[roomNum - 1][2]=lName;
			System.out.println("Customer successfully added to room " + roomNum);
		} else {
			System.err.print("A customer already occupied in this room\n");
			addCustomer();
		}
	}

	// method to display the empty rooms
	public void displayEmptyRooms() {
		for (int i = 0; i < hotelRoomArray.length; i++) {
			if (hotelRoomArray[i][1].equalsIgnoreCase("empty"))
				System.out.println("room " + (i + 1) + " is empty");
		}
	}

	// method to remove a customer from a room
	public void deleteCustomer() {
		int roomNum = rooomNoValidator();
		if (hotelRoomArray[roomNum - 1][1].equals("empty")) {
			System.out.println("Room " + (roomNum) + " is already empty");
		} else {
			hotelRoomArray[roomNum - 1][1]="empty";
			hotelRoomArray[roomNum - 1][2]="empty";
			System.out.println("Successfully deleted Customer from Room " + (roomNum));
		}
	}

	// method to find a room by a given name
	public void findRoomByName() {
		String fName = nameValidator("first");
		String lName = nameValidator("last");
		String tempName = fName + lName;
		boolean found = false;
		for (int i = 0; i < hotelRoomArray.length; i++) {

			if ((hotelRoomArray[i][1] + hotelRoomArray[i][2]).equalsIgnoreCase(tempName)) {
				found = true;
				System.out.println("Customer " + fName + " " + lName + " is occupied in room " + (i + 1));
			} else {
			}
		}
		if (found == false) {
			System.out.println("!!! No match found !!!");
		} else {
		}
	}

	// storing room details in a .txt file according to the current date 
	// and the current date will be the default file name.
	public void storeInFile() {
		
		try {
			System.out.println("Do you want to give a file name? ");
			System.out.println("or else file will be saved by default\n");
			System.out.println("press 'Y' to enter the name manually or press 'N' to skip ");
			String ignoreDefault = input.nextLine();
			
			if (ignoreDefault.equalsIgnoreCase("y")){
				System.out.print("Please enter the file name: ");
				textFileName = input.nextLine();
				System.out.print("File has been saved as "+textFileName);
			
			}else if (ignoreDefault.equalsIgnoreCase("n")){
				System.out.println("Current date has been saved as the file name (default)");
				//creating a object from calendar class and assigning it to fileName variable
				textFileName = new SimpleDateFormat("yyyy_MM_dd").format(Calendar.getInstance().getTime());
			}
			
			//file name will be saved as the current value of fileName variable
			PrintWriter hotelFiles = new PrintWriter(textFileName);
			for (int i = 0; i < hotelRoomArray.length; i++) {
				hotelFiles.println((i + 1) + "\t" + hotelRoomArray[i][1] + "\t" + hotelRoomArray[i][2]);
			}
			hotelFiles.close();
			System.out.println("\nSuccessfully saved data to the file..........\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// loading room details from text file
	public void loadFromFile() {
		try {
			FileReader freader = new FileReader(textFileName);
			Scanner sc = new Scanner(freader);
			int count = 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] data = line.split("\t");
				hotelRoomArray[count][1]=data[1];
				hotelRoomArray[count][2]=data[2];
				count += 1;
			}
			freader.close();
			sc.close();
			System.out.println("Successfully loaded data from file");
		} catch (IOException e) {
			System.err.print("!!! File not found !!!"+"\n");
		}
	}

	public void alphabaticSort() {
		String[][] sortArray=new String[10][3];
		sortArray=hotelRoomArray;
		for (int i = 0; i < sortArray.length- 1; i++) {
			boolean isSorted=true;
			for (int j = 0; j < (sortArray.length - 1) - i; j++) {
				// comparing first name of customers in the array list
				if ((sortArray[j][1]).compareToIgnoreCase(sortArray[j+1][1]) > 0) {
					String temp1 = sortArray[j][1];
					sortArray[j][1]=sortArray[j+1][1];
					sortArray[j+1][1]=temp1;

					String temp2 = sortArray[j][2];
					sortArray[j][2]=sortArray[j+1][2];
					sortArray[j+1][2]=temp2;
					
					String temp3 = sortArray[j][0];
					sortArray[j][0]=sortArray[j+1][0];
					sortArray[j+1][0]=temp3;
					
					isSorted=false;

				} else if ((sortArray[j][1]).compareToIgnoreCase(sortArray[j+1][1]) == 0) {
					// comparing last name of customers in the array list if
					// the first names are same
					if ((sortArray[j][2]).compareToIgnoreCase(sortArray[j+1][2]) > 0) {
						String temp1 = sortArray[j][1];
						sortArray[j][1]=sortArray[j+1][1];
						sortArray[j+1][1]=temp1;

						String temp2 = sortArray[j][2];
						sortArray[j][2]=sortArray[j+1][2];
						sortArray[j+1][2]=temp2;
						
						String temp3 = sortArray[j][0];
						sortArray[j][0]=sortArray[j+1][0];
						sortArray[j+1][0]=temp3;
						
						isSorted=false;
					} else {
					}
				} else {
				}
			}
			if(isSorted) break;
		}
		System.out.println("Rooms Ordered alphabetically by name");
		System.out.println("Room number Customer Name");
		for (int i = 0; i < sortArray.length; i++) {
			if(!(hotelRoomArray[i][1].equalsIgnoreCase("empty"))){
				System.out.println(
						"     " + (Integer.parseInt(hotelRoomArray[i][0])+1) + "         " + hotelRoomArray[i][1] + " " + hotelRoomArray[i][2]);
			}else{}
		}
	}
}
