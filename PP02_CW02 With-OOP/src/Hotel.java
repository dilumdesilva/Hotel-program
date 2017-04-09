//P.Dilum De Silva
//UoW - 16266371
//IIT - 2016142

import java.io.Serializable;

public class Hotel implements Serializable{
	
	//Declaring constructor
	public Hotel(){
		initializeArray();
	}
	
	//Crating a Room type array with 10 rooms
	private Room[] roomArray = new Room[10];

	//Getter and setter method for roomArray
	public Room[] getRoomArray() {
		return roomArray;
	}

	public void setRoomArray(Room[] roomArray) {
		this.roomArray = roomArray;
	}
	
	//Initialize the roomArray
	public void initializeArray(){
		for(int i=0;i<roomArray.length;i++){
			roomArray[i]=new Room(i+1);
			//System.out.println("initialized");
		}
	}
}
