import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

public class Customer
{
    private int current = setCurrentFloor();
    private int destination = setDestinationFloor();
    private int currentFloor;
    private int destinationFloor;
    int totalCustomers = 0;
    private int ID = 0;
    boolean present = false;
    boolean arrivied = false; 
    static ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
    
    public int currentFloor()
    {
        return current;
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
        int floors = Elevator.returnFloors();
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
        return totalCustomers();
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
           }
           else
           {
               list.get(i).add("DOWN");
           }
           
        }
        totalCustomers = a;
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
        return list;
    }
    
    public static ArrayList<ArrayList<Object>> returnList()
    {
        return list;
    }
    
    public void finished()
    {
         //exercise has finished 
    }
}