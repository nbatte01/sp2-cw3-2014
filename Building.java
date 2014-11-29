import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Building
{
    public static int totalFloors;
    public static int totalCustomers;
    public static ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
    static Elevator n = new Elevator();
    public static void main(String[]args)
    {
        System.out.print("\f");
        Scanner in = new Scanner(System.in);
        
        System.out.print("Please enter the number of floors: ");
        String floors = in.next();
        
        if(intValidation(floors))//needs to be adapted to a while loop to allow users to have multiple goes of data entry
        {
            totalFloors = (Integer.valueOf(floors));
            System.out.print("Please enter the number of customers: ");
            String customers = in.next();
            
            if(intValidation(customers))
            {
                n.setFloors(totalFloors);
                totalCustomers = (Integer.valueOf(customers));
                Customer x = new Customer();
                list = x.customerList(totalCustomers);//customer class will return a arraylist that contain ID number, current floor, destination floor and whether or not the customer is still in the lift
                business();
            }
            else
            {
                System.out.println("Please ensure that you only enter an int");
            }
        }
        else
        {
            System.out.println("Pleas enesure that you only enter ints");
        }
        
        //prints out the final arraylist
        System.out.println();
        System.out.println("the final arraylist");
        for(int i = 0 ; i < totalCustomers ; i++)
        {
            System.out.println(list.get(i));
        }
    }
    
    public static boolean intValidation(String a) //validates a string to ensure it is a int
    {
        boolean result = false;
        if(a.matches("[0-9]+") && a.length() > 0)
        {
            result = true;
        }
        return result;
    }
    
    public static void business()
    {
        System.out.println("The highest current floor: "+highestCurrent());
        System.out.println("The highest destination floor: " + highestDestination());
        System.out.println();
        System.out.println("Start of calculations");
        Scanner in = new Scanner(System.in);
        int upTravellers = 0;
        //counts the number of up travellers
        for(int i = 0 ; i < totalCustomers ; i++)
        {
            int b = (Integer) list.get(i).get(1);
            int c = (Integer) list.get(i).get(2);
            if(b<c)
            {
                upTravellers = upTravellers + 1;
            }
        }
        
        
        while(upTravellers > 0)
        {
            System.out.println("proceed?");
            String proceed = in.next();
            if(proceed.equals("Y"))
            {
                //prints the array list
                System.out.println();
                System.out.println("The current floor on the elevator class is: " + n.currentFloor());
                for(int i = 0 ; i < totalCustomers ; i++)
                {
                    System.out.println(list.get(i));
                }
    
                //if the customers current floor matches the elevator current floor 
                for(int i = 0 ; i <totalCustomers ; i++)
                {
                    int b = (Integer) list.get(i).get(1);
                    int c = (Integer) list.get(i).get(2);
                    //checks that the current floor of the customers matche sthat of the elvator 
                    //Checks that the destination floor of the customer is higher than their current floor thus travelling up
                    //chevck that they are current not in the elevator
                    if((b == n.currentFloor()) && (b<c) && (list.get(i).get(3)==false))
                    {
                        list = Customer.inElevator(i);
                        System.out.println("CUSTOMER CURRENT FLOOR MATCHES THE CURRENT FLOOR");
                    }
                }
                
                //if the customers destination floor matches the current floor
                for(int i = 0 ; i <totalCustomers ; i++)
                {
                    int b = (Integer) list.get(i).get(1);//current
                    int c = (Integer) list.get(i).get(2);//destination
                    //checks that the current floor of the customers matche sthat of the elvator 
                    //Checks that the destination floor of the customer is higher than their current floor thus travelling up
                    //chevck that they are current not in the elevator
                    if((c == n.currentFloor()) && (list.get(i).get(3)==true) && (list.get(i).get(4)==false))
                    {
                        list = Customer.OutElevator(i);
                        System.out.println("CUSTOMER destination FLOOR MATCHES THE CURRENT FLOOR");
                        upTravellers -= 1;
                        
                    }
                }
                
                System.out.println("the next floor that the elevator will travel to is: " + closestFloorUp());
                //changes the floor of the elevator
                n.move(closestFloorUp() - n.currentFloor());
                System.out.println("Up travellers: " + upTravellers);
            }
        }
    }
    
    
    
    //find the highest current floor //WORKING
    public static int highestCurrent()
    {
        int highestCurrent = 0;
        for(int i = 0; i < totalCustomers ; i++)
        {
            int a = (Integer) list.get(i).get(1);
            if(a > highestCurrent)
            {
                highestCurrent = a;
            }
        }
        return highestCurrent;
    }
    
    //find the highest destination floor //WORKING
    public static int highestDestination()
    {
        int highestDestination = 0;
        for(int i = 0 ; i< totalCustomers ; i++)
        {
            int b = (Integer) list.get(i).get(2);
            if(b > highestDestination)
            {
                highestDestination = b;
            }
        }
        return highestDestination;
    }
    
    //find closest floor compared to the current floor when travelling up //NOT WORKING
    public static int closestFloorUp()
    {
        int nextUpFloor = n.currentFloor();
        int highestUpCurrent = 0;
        //finds the highest customer current floor for customers that wish to travel up
        for(int i = 0; i < totalCustomers ; i++)
        {
            int a = (Integer) list.get(i).get(1);
            int b = (Integer) list.get(i).get(2);
            if((a > highestUpCurrent)&&(list.get(i).get(5).equals("UP")))
            {
                highestUpCurrent = a;
            }
        }
        
        if(n.currentFloor() == highestUpCurrent)
        {
            System.out.println("The highest up floor has been reached");
            outerloop:
            for(int i = n.currentFloor() ; i < highestDestination() ; i++)
            {
                for(int x = 0 ; x < totalCustomers ; x++)
                {
                    if((list.get(x).get(3)==true)&&(list.get(x).get(5).equals("UP")))
                    {
                        int c = (Integer) list.get(x).get(2);
                        if((c == i)&&(c > n.currentFloor()))
                        {
                            nextUpFloor = i;
                            break outerloop;
                        }
                    }
                }
            }
        }
        else
        {
            outerloop:
            for(int i = n.currentFloor() ; i < highestDestination() ; i++)
            {
                for(int x = 0 ; x < totalCustomers ; x++)
                {
                    int c = (Integer) list.get(x).get(1);
                    if((c == i)&&(c > n.currentFloor()))
                    {
                        nextUpFloor = i;
                        break outerloop;
                    }
                }
            }
        }
        return nextUpFloor;
    }
    
    //find the closest floor compared to the current floor when travelling down
    public static void closestFloorDown()
    {
        
    }
    
    public static int getFloors()
    {
        return totalFloors;
    }
}