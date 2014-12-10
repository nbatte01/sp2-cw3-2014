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
        boolean floorsCheck = false;
        boolean customersCheck = false; 
        //Ensures that the user inputs and int which is then stored as the total number of floors
        while(floorsCheck == false)
        {
            System.out.print("Please enter the number of floors: ");
            String floors = in.next();
            if(intValidation(floors))
            {
                totalFloors = (Integer.valueOf(floors));
                floorsCheck = true;
            }
            else
            {
                System.out.println("Pleas enesure that you enter an int");
            }
        }
        //Ensures that the user inputs an int which is then stored as the total number of customers
        while(customersCheck == false)
        {
            System.out.print("Please enter the number of customers: ");
            String customers = in.next();
            if(intValidation(customers))
            {
                n.setFloors(totalFloors);
                totalCustomers = (Integer.valueOf(customers));
                Customer x = new Customer();
                list = x.customerList(totalCustomers);//customer class will return a arraylist that contain ID number, current floor, destination floor and whether or not the customer is still in the lift
                business();
                customersCheck = true;
            }
            else
            {
                System.out.println("Please ensure that you only enter an int");
            }
        }
        
        //prints out the final finished arraylist
        System.out.println();
        System.out.println("the final arraylist");
        for(int i = 0 ; i < totalCustomers ; i++)
        {
            System.out.println(list.get(i));
        }
        System.out.println();
        System.out.println("The total number of floors visited: "+n.returnFloorCount());
        System.out.println("The total number of floors in the building: "+ totalFloors);
    }
    
    //Validates the input string to ensure that it is a number which can be converted to an int
    public static boolean intValidation(String a) 
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
        int downTravellers = 0;
        //counts the number of up and down travellers
        for(int i = 0 ; i < totalCustomers ; i++)
        {
            if(list.get(i).get(5).equals("UP"))
            {
                upTravellers += 1;
            }
            else if(list.get(i).get(5).equals("DOWN"))
            {
                downTravellers += 1;
            }
        }
      
        
        while(upTravellers > 0)
        {
            System.out.println();
            System.out.println("proceed?");
            String proceed = in.next();
            if((proceed.equals("Y"))||(proceed.equals("y")))
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
                        System.out.println("Customer "+ list.get(i).get(0) + " has entered the elevator");
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
                        System.out.println("Customer "+ list.get(i).get(0) + " has arrived at their destination");
                        upTravellers -= 1;
                    }
                }
                
                System.out.println("the next floor that the elevator will travel to is: " + closestFloorUp());
                //changes the floor of the elevator
                n.move(closestFloorUp()-n.currentFloor());
                System.out.println("Up travellers: " + upTravellers);
            }
        }
        if(upTravellers == 0)
        {
            System.out.println();
            System.out.println("There are no more up travellers");
            System.out.println();
        }
        int topFloor = Math.max(highestCurrent(),highestDestination());
        System.out.println("The highest floor of the building is: "+topFloor);
        System.out.println();
        n.move(topFloor-n.currentFloor());
        System.out.println();
        System.out.println("The elevator has move to the top of the building which is floor: "+n.currentFloor());
        while(downTravellers > 0)
        {
            System.out.println();
            System.out.println("proceed?");
            String proceed = in.next();
            if((proceed.equals("Y"))||(proceed.equals("y")))
            {
                //prints the array list
                System.out.println();
                System.out.println("The current floor on the elevator class is: " + n.currentFloor());
                for(int i = 0 ; i < totalCustomers ; i++)
                {
                    System.out.println(list.get(i));
                }
                
                //if the customers current floor matches the elevator current floor //Possibly store this chunk of code in the customer class
                for(int i = 0 ; i <totalCustomers ; i++)
                {
                    int b = (Integer) list.get(i).get(1);
                    int c = (Integer) list.get(i).get(2);
                    //checks that the current floor of the customers matches that of the elevator 
                    //Checks that the destination floor of the customer is lower than their current floor thus travelling down
                    //check that they are current not in the elevator
                    if((b == n.currentFloor()) && (b>c) && (list.get(i).get(3)==false))
                    {
                        list = Customer.inElevator(i);
                        System.out.println("Customer "+ list.get(i).get(0) + " has entered the elevator");
                    }
                }
                
                //if the customers destination floor matches the current floor
                for(int i = 0 ; i <totalCustomers ; i++)
                {
                    int b = (Integer) list.get(i).get(1);//current
                    int c = (Integer) list.get(i).get(2);//destination
                    //checks that the current floor of the customers matches that of the elevator 
                    //checks that their journey is not complete
                    //check that they are in the elevator
                    if((c == n.currentFloor()) && (list.get(i).get(3)==true) && (list.get(i).get(4)==false))
                    {
                        list = Customer.OutElevator(i);
                        System.out.println("Customer "+ list.get(i).get(0) + " has arrived at their destination");
                        downTravellers -= 1;
                    }
                }
                int holder = closestFloorDown();
                System.out.println("the next floor that the elevator will travel to is: " + holder);
                //changes the floor of the elevator
                n.move(holder-n.currentFloor());
                System.out.println("Down travellers: " + downTravellers);
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
    
    //find the lowest destination
    public static int lowestDestination()
    {
        int lowestDestination = 0;
        for(int i = 0 ; i<totalCustomers ; i++)
        {
             int b = (Integer) list.get(i).get(2);
             if (b > lowestDestination)
             {
                 lowestDestination = b;
                 break;
             }
        }
        return lowestDestination;
    }
    
    //find closest floor compared to the current floor when travelling up //WORKING
    public static int closestFloorUp()
    {
        int nextUpFloor = n.currentFloor();
        int highestUpCurrent = 0;
        
        //finds the highest customer current floor for customers that wish to travel up
        for(int i = 0; i < totalCustomers ; i++)
        {
            int a = (Integer) list.get(i).get(1);
            if((a > highestUpCurrent)&&(list.get(i).get(5).equals("UP")))
            {
                highestUpCurrent = a;
            }
        }
        
        int nextDestinationFloor = 0;
        int nextCurrentFloor = 0;
        //counts the next current floor to travel to
        outerloop:
        for(int i = n.currentFloor() ; i <= highestDestination() ; i++)
        {
            for(int x = 0 ; x < totalCustomers ; x++)
            {
                
                if((list.get(x).get(5).equals("UP"))&&(list.get(x).get(4) == false))
                {
                    int c = (Integer) list.get(x).get(1);
                    if((c == i)&&(c > n.currentFloor()))
                    {
                        nextCurrentFloor = i;
                        break outerloop;
                    }
                }
            }
        }
        
        //counts the next destination floor to travel to
        outerloop:
        for(int i = n.currentFloor() ; i <= highestDestination() ; i++)
        {
            for(int x = 0 ; x < totalCustomers ; x++)
            {
                if((list.get(x).get(5).equals("UP"))&&(list.get(x).get(4) == false))
                {
                    int c = (Integer) list.get(x).get(2);
                    if((c == i)&&(c > n.currentFloor()))
                    {
                        nextDestinationFloor = i;
                        break outerloop;
                    }
                }
            }
        }
        
        //Determines which floors is closer to travel to
        if((nextCurrentFloor != 0)&&(nextDestinationFloor != 0))
        {
            nextUpFloor = Math.min(nextDestinationFloor, nextCurrentFloor);
        }
        else if((nextCurrentFloor == 0)&&(nextDestinationFloor != 0))
        {
            nextUpFloor = nextDestinationFloor;
        }
        else if((nextCurrentFloor != 0)&&(nextDestinationFloor == 0))
        {
            nextUpFloor = nextCurrentFloor;
        }

        return nextUpFloor;
    }
    
    //find the closest floor compared to the current floor when travelling down  //WORKING
    public static int closestFloorDown()
    {
        int nextCurrent = 0;
        int nextDestination = 0;
        int nextDownFloor = 0;
        
        //find the highest current floor for the down passengers who are not in the lift and have not completed their journey //WORKING
        for(int i = 0 ; i <= highestDestination() ;i++)//cycle through the floors
        {
            for(int x = 0 ; x < totalCustomers ; x++)//cycle through the customers each time the floor changes in the for statement above
            {
                if((list.get(x).get(5).equals("DOWN"))&&(list.get(x).get(3)==false)&&(list.get(x).get(4)==false))
                {
                    int a = (Integer)list.get(x).get(1);
                    if(a > nextCurrent)
                    {
                        nextCurrent = a;
                    }
                }
            }
        }
        
        //find the highest destination floor for the down passengers who are not in the lift and have not completed their journey //UNCONFIRMED WORKING
        for(int i = 0 ; i <= highestDestination() ;i++)//cycle through the floors
        {
            for(int x = 0 ; x < totalCustomers ; x++)//cycle through the customers each time the floor changes in the for statement above
            {
                if((list.get(x).get(5).equals("DOWN"))&&(list.get(x).get(3)==true)&&(list.get(x).get(4)==false))
                {
                    int b = (Integer)list.get(x).get(2);
                    if(b > nextDestination)
                    {
                        nextDestination = b;
                    }
                }
            }
        }
        
        nextDownFloor = Math.max(nextCurrent, nextDestination);    
        return nextDownFloor;
    }
    
    public static int getFloors()
    {
        return totalFloors;
    }
}