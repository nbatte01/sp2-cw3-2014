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

    
    public static int returnUpTravellers()
    {
        return upTravellers;
    }
    
    public static int returnDownTravellers()
    {
        return downTravellers;
    }
    
    public static int totalTravellers()
    {
        return totalCustomers;
    }
    
    public int destinationFloor()
    {
        return destination;
    }
    
    public int setCurrentFloor()
    {
        int output = random();
        return output;
    }
    
    public int setDestinationFloor()
    {
        int output = random();
        return output;
    }
    
    public int random()
    {
        int floors = x.returnFloors();
        Random rn = new Random();
        int answer = rn.nextInt(floors);      
        return answer;
    }
    
    public int ID()
    {
        ID++;
        return ID;
    }
    
    public static int totalCustomers()
    {
        return totalCustomers;
    }
    
    public ArrayList<ArrayList<Object>> customerList(int a)//this method receives a number of customers from the building class and creates an array with randomly generated current floors and destination floors
    {
        int id;
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
        return list;
    }
    //receives an int from the Building class and adjusts the relevant customers on the list then returns this list for the building class to play with
    public static ArrayList<ArrayList<Object>> inElevator(int a)
    {
        list.get(a).set(3, true);
        return list;
    }
    
    //when the customer arrives at their destination floor
    public static ArrayList<ArrayList<Object>> OutElevator(int a)
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
        return list;
    }
    
    //resets the list to it's original state
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
    
    //prints the current list
    public static void printList()
    {
        for(int i = 0 ; i < originalCustomers ; i++)
        {
            System.out.println(list.get(i));            
        }
    }
    
    public static String finished()
    {
         //exercise has finished 
         String output = "All customers have arrived at their destination. The execise has finished";
         return output;
    }
}