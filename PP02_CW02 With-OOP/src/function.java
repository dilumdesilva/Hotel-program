//P.Dilum De Silva
//UoW - 16266371
//IIT - 2016142

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//  Order of the functions
//	1 - Array declaration for 10 rooms
//	2 - function which display room status
//	3 - function which validates the room number
//	4 - function which validates the user entered name
//	5 - function which adds a customer to a room
//	6 - function which removes a customer from a room
//	7 - function which stores room details in a .txt file
//	8 - function which load room details from Hotel_Details.txt file
//	9 - function Displays 10 customers allocated for a particular room.

public class function implements Serializable{
	
	// creating an object from scanner class to get user inputs
	private Scanner input = new Scanner(System.in);
	
	// Array declaration for 10 rooms
	static Hotel hotelObj = new Hotel();
	static Room[] roomArray=hotelObj.getRoomArray();
	
	// function which display room status
	public void viewRooms() {
		System.out.println("---View Rooms---/n");
		for (int i = 0; i < roomArray.length; i++) {
			int no=roomArray[i].getRoomNo();
		    String fName=roomArray[i].getCustomer().getFirstName();
		    String lName=roomArray[i].getCustomer().getLastName();
			if (fName.equals("empty")) {
				System.out.println("Room " +no+ " is empty");
			} else {
				System.out.println("Room " +no+ " is occupied by " + fName + " "+ lName);
			}
		}
	}
	
	// function which validates the room number
	public int roomNoValidator() {
		int roomNum = 0;
		boolean flag = true;
		while (flag == true) {
			try {
				System.out.print("Enter room number from 1-10: ");
				roomNum = Integer.parseInt(input.nextLine());
				System.out.println();
				if (roomNum > 0 && roomNum <= roomArray.length) {
					flag = false;
				} else {
					System.out.println("Room number should be between 1-10");
				}
			} catch (Exception e1) {
				System.out.println("Please enter a valid room number");
			}
		}
		return roomNum;
	}

	// function which validates the user entered name
		public String nameValidator(String namePart) {
			String name = "0";
			boolean flag = true;
			Pattern p = Pattern.compile("[a-zA-Z]+$");

			while (flag == true) {
				try {
					System.out.print("Enter Customer's " + namePart + " name:");
					name = input.nextLine();
					System.out.println();
					Matcher m = p.matcher(name);
					if (m.matches()) {
						flag = false;
					} else {
						System.out.println("Please enter a valid " + namePart + " name");
					}
				} catch (Exception e2) {
					System.out.println("Please enter a valid " + namePart + " name");
				}
			}
			return name;
		}

	// function which adds a customer to a room
	public void addCustomer() {
		System.out.println("---Add Customer---/n");
		int roomNum = roomNoValidator();
		if (roomArray[roomNum-1].getCustomer().getFirstName().equalsIgnoreCase("empty")) {
			if(roomArray[roomNum-1].getQueue().isFull()){
				System.out.println("Queue for room "+roomNum+" is full. "
						+ "\nIf you continue oldest queue item will be deleted."
						+ "\n\nPress c to continue or any other to continue...");
				String choice = input.nextLine();
				if(choice.equalsIgnoreCase("c")){
					String fName = nameValidator("first");
					String lName = nameValidator("last");
					roomArray[roomNum-1].getCustomer().setFirstName(fName);
					roomArray[roomNum-1].getCustomer().setLastName(lName);
					System.out.println("Customer successfully added to room " + roomNum);
					String fullName=fName+" "+lName;
					roomArray[roomNum-1].getQueue().enqueue(fullName);
				}else{
					System.out.println("Customer did not added to the room "+roomNum);
				}
			}else{
				String fName = nameValidator("first");
				String lName = nameValidator("last");
				roomArray[roomNum-1].getCustomer().setFirstName(fName);
				roomArray[roomNum-1].getCustomer().setLastName(lName);
				System.out.println("Customer successfully added to room " + roomNum);
				String fullName=(fName+" "+lName);
				roomArray[roomNum-1].getQueue().enqueue(fullName);
			}
		} else {
			System.out.println("A customer already occupied in this room");
		}
	}

	// function which removes a customer from a room
	public void deleteCustomer() {
		System.out.println("---Delete Customer---/n");
		int roomNum = roomNoValidator();
		if (roomArray[roomNum-1].getCustomer().getFirstName().equals("empty")) {
			System.out.println("Room " + (roomNum) + " is already empty");
		} else {
			roomArray[roomNum-1].getCustomer().setFirstName("empty");
			roomArray[roomNum-1].getCustomer().setLastName("empty");
			System.out.println("Successfully deleted Customer from Room " + (roomNum));
		}
	}

	
	// function which stores room details in a .txt file according to the current date 
	// and the current date will be the default file name.
	public void storeInFile() {
		try {
			System.out.println("Do you want to give a file name? ");
			System.out.println("or else file will be saved by default\n");
			System.out.println("press 'Y' to enter the name manually or press 'N' to skip ");
			String fileName = input.nextLine();
			
			if(fileName.equalsIgnoreCase("y")){
				
				System.out.print("Please enter the file name: ");
				fileName = input.nextLine();
				System.out.print("File has been saved as "+ fileName);
			}
			
			else if (fileName.equalsIgnoreCase("n")){
				System.out.println("Current date has been saved as the file name (default)");
				//creating a object from calendar class and assigning it to fileName variable
				fileName = new SimpleDateFormat("yyyy_MM_dd").format(Calendar.getInstance().getTime());
			}
			
			File file = new File(fileName+".txt");
			FileOutputStream fileos = new FileOutputStream(file);
			ObjectOutputStream objectos = new ObjectOutputStream(fileos);
			objectos.writeObject(hotelObj);
			objectos.flush();
			objectos.close();
			fileos.close();
			System.out.println("/nSuccessfully saved data to the file");
			
			
		} catch (IOException e) {
			System.out.println("!!! File not found !!!");
		}
	}

	// function which load room details from Hotel_Details.txt file
	public void loadFromFile() {
		File file = new File("Hotel_Details.txt");
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			hotelObj = (Hotel)ois.readObject();
			roomArray=hotelObj.getRoomArray();
			System.out.println("Successfully loaded data from file");
			ois.close();
		} catch (Exception e) {
			System.out.println("!!! File is empty or File not found !!!");
		}
	}
	
	//function Displays 10 customers allocated for a particular room
	public void roomAllocationOrder(){
		System.out.println("---First 3 Customers---/n");
		int roomNum = roomNoValidator();
		int currentSize=roomArray[roomNum-1].getQueue().getCurrentSize();
		if(currentSize>=10){
			for(int i=0;i<10;i++){
				roomArray[roomNum-1].getQueue().dequeue();
			}
		}else if(currentSize==0){
			System.out.println("Queue is empty");
		}else{
			for(int i=0;i<currentSize;i++){
				roomArray[roomNum-1].getQueue().dequeue();
			}
		}
	}
}