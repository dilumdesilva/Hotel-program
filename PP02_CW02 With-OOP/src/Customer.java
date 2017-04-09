//P.Dilum De Silva
//UoW - 16266371
//IIT - 2016142

import java.io.Serializable;

public class Customer implements Serializable{

	private String firstName;
	private String lastName;

	//Declaring Constructor
	public Customer(){
		//Initializing private fields
		firstName="empty";
		lastName="empty";
	}
	// getters and setters for the private attributes in the class
	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}

	public String getLastName() {
		return lastName;
	}
}
