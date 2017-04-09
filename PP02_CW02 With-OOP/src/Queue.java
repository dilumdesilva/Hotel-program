//P.Dilum De Silva
//UoW - 16266371
//IIT - 2016142

import java.io.Serializable;

public class Queue implements Serializable{
	
	//Declaring the private fields
	private int front;
	private int rear;
	private int currentSize;
	private int size;
	private String arrayQ[];
	
	//Getter for current size
	public int getCurrentSize() {
		return currentSize;
	}

	//Declaring the constructor
	Queue(int inputSize){
		size=inputSize;
		arrayQ=new String[size];
		front=0;
		rear=-1;
		currentSize=0;
	}
	
	//Checks whether the queue is empty
	public boolean isEmpty(){
		return (currentSize==0);
	}
	
	//Checks whether the queue is full
	public boolean isFull(){
		return (currentSize==size);
	}
	
	//Gets the top item of the queue
	public String peek(){
        if(isEmpty()){
            return null;
        }else{
        return arrayQ[front];
        }
    }
	
	//Adding an item to the queue
	public void enqueue(String name){
		++rear;
		if(isFull()){
			System.out.print("The oldest item ");
			dequeue();
			System.out.println("is deleted");
		}else{}
		if(rear==size){
			rear=0;
		}
		arrayQ[rear]=name;	
		currentSize++;
		System.out.println("Customer "+name+" successfully added to the queue");
	}
	
	//Removing an item from the queue
	public void dequeue(){
		if(isEmpty()){
			System.out.println("Queue is empty");
		}
		else{
			front++;
			if(front==size){
				System.out.println(arrayQ[front-1]);
				front=0;
			}else{
				System.out.println(arrayQ[front-1]);
			}
			currentSize--;
		}	
	}
}
