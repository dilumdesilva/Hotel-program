//P.Dilum De Silva
//UoW - 16266371
//IIT - 2016142

import java.io.Serializable;

public class Room implements Serializable{

	//Declaring private fields
	private int roomNo;
	private Customer customer;
	private Queue queue;

	// constructor declaration
	public Room(int roomNo) {
		// initializing private fields in the class
		this.roomNo = roomNo;
		customer = new Customer();
//		queue=new Queue(7);
		
	}
	
	// Getters and setters for private fields
	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Queue getQueue() {
		return queue;
	}
}
