/* This code was created by Nicholas Batten and Bradd Bently for the third coursework
 * of the Software and Programming 2 module at Birkbeck, University of London (December 2014).
 */

import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

public class Customer
{
    private int current = setCurrentFloor();
    private int destination = setDestinationFloor();
    private int currentFloor;
    private int destinationFloor;
    static int totalCustomers = 0;
    static int floors = 0;
    static int upTravellers = 0;
    static int downTravellers = 0;
    static int originalCustomers = 0;
    static int originalUpTravellers = 0;
    static int originalDownTravellers = 0;
    private int ID = 0;
    boolean present = false;
    boolean arrivied = false; 
    static ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
    static Elevator x = new Elevator();

    //Used by the Building Class. Returns the total number of  travelling customers who have not completed their journey
    public static int returnUpTravellers()
    {
        return upTravellers;
    }
    
    //Used by the Building Class. Returns the total number of downwards travelling customers who have not completed their journey
    public static int returnDownTravellers()
    {
        return downTravellers;
    }
    
    public static int returnOriginalCustomers()
    {
        return originalCustomers;
    }
    
    //Used by the Building class. Return the total number of customers who have not completed their journey
    public static int totalTravellers()
    {
        return totalCustomers;
    }
    
    //Sets a current floor for use int the arraylist
    public int setCurrentFloor()
    {
        int output = random(0, floors);
        return output;
    }
    
    //Sets a destiantion floor for use in the arraylist
    public int setDestinationFloor()
    {
        int output = random(0, floors);
        return output;
    }
    
    //Generates a random integer between the ground floor and the highest floor of the building
    public int random(int a, int b)
    {
        int answer = a + (int)(Math.random() * ((b - a) + 1));
        return answer;
    }
    
    //Returns an ID number for use in the arraylist
    public int ID()
    {
        ID++;
        return ID;
    }
    
    //receives two integers from the Building class, number of customers and number of floors, and then generates a 2D arraylist that contains a customer ID,
    //The customers current floor, The customers destination floor, whether or not the customer is currently in the lift, whether or not the customer has completed
    //their journey and the direction they are travelling in
    public void customerList(int a, int b)//this method receives a number of customers from the building class and creates an array with randomly generated current floors and destination floors
    {
        int id;
        floors = b;
        boolean onBoard = false;
        boolean journeyComplete = false;
        for(int i = 0; i < a ;i++)
        {
           list.add(new ArrayList<Object>());
           id = ID();
           currentFloor = setCurrentFloor();
           destinationFloor = setDestinationFloor();
           while((destinationFloor == currentFloor)||(destinationFloor == 13)||(currentFloor == 13))
           {
               currentFloor = setCurrentFloor();
               destinationFloor = setDestinationFloor();
           }
           list.get(i).add(id);
           list.get(i).add(currentFloor);
           list.get(i).add(destinationFloor);
           
           list.get(i).add(onBoard);
           list.get(i).add(journeyComplete);
           
           if(currentFloor < destinationFloor)
           {
               list.get(i).add("UP");
               upTravellers += 1;
           }
           else
           {
               list.get(i).add("DOWN");
               downTravellers += 1;
           }
           
        }
        totalCustomers = a;
        originalCustomers = a;
        originalUpTravellers = upTravellers;
        originalDownTravellers = downTravellers;
    }
    
    //receives an int from the Building class and adjusts the relevant customers on the list to mark them as present in the elevator
    public static void inElevator(int a)
    {
        list.get(a).set(3, true);
    }
    
    //Returns the list of customers
    public static ArrayList<ArrayList<Object>> returnList()
    {
        return list;
    }
    
    //receives an int from the Building class and adjusts the relevant customers on the list to mark them as having completed their journey
    public static void OutElevator(int a)
    {
        list.get(a).set(3, false);//when the customers steps out of the elevator at their destination floor
        list.get(a).set(4, true);//when the customer has completed their journey
        if(list.get(a).get(5).equals("UP"))
        {
            upTravellers -= 1;
        }
        else
        {
            downTravellers -= 1;
        }
        totalCustomers -= 1;
    }
    
    //resets the list to it's original state by marking all customers has having not completed their journey and 
    //resetting the variables that count the number of customers remaining
    public static void resetList()
    {
        for(int i = 0 ; i < originalCustomers ; i++)
        {
            list.get(i).set(4, false);
        }
        upTravellers = originalUpTravellers;
        downTravellers = originalDownTravellers;
        totalCustomers = originalCustomers;
    }
    
    //prints the current list //This is redundant in the final version of this assignment but is kept to aid in possible future improvements to the code
    public static void printList()
    {
        for(int i = 0 ; i < originalCustomers ; i++)
        {
            System.out.println(list.get(i));            
        }
    }

}