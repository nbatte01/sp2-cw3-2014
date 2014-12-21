 import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class building
{
    public static int totalFloors;
    public static int totalCustomers;
    public static boolean topFloorReached = false;
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
                
                int firstCount =  business2();
                
                //RESETS THE CUSTOMER AND ELEVATOR CLASS
                Customer.resetList();
                n.resetElevator();
                
                int secondCount =  business();
                
                //Display the results
                System.out.println();
                System.out.println("Second strategy results");
                System.out.println("The total number of floors visited after the second strategy: " + firstCount);
                System.out.println("The total number of floors in the building: "+ totalFloors);
                //calculates the perentage of floors visited to allow for easier strategy performance review
                double percentageVisited = (firstCount*100)/totalFloors;
                System.out.println("Percentage of floors visited: " + percentageVisited + "%");
                
                System.out.println();
                System.out.println("Default strategy results");
                System.out.println("The total number of floors visited after the default strategy: " + secondCount);
                System.out.println("The total number of floors in the building: "+ totalFloors);
                //calculates the perentage of floors visited to allow for easier strategy performance review
                double percentageVisited2 = (secondCount*100)/totalFloors;
                System.out.println("Percentage of floors visited: " + percentageVisited2 + "%");
                
                System.out.println();
                System.out.println("Final verdict:");
                if(percentageVisited2 > percentageVisited)
                {
                    System.out.println("The second strategy was more efficient than the default as it visited " + (secondCount - firstCount) + " less floor(s)");
                }
                else if(percentageVisited > percentageVisited2)
                {
                    System.out.println("The default strategy was more efficient than the second as it visited " + (firstCount - secondCount) + " less floor(s)");
                }
                else
                {
                    System.out.println("Both strategies acheived the same efficiency as each other");
                }
                
                
                customersCheck = true;
            }
            else
            {
                System.out.println("Please ensure that you only enter an int");
            }
        }
    }
    
    //Validates the input string to ensure that it is a number which can be converted to an int //WORKING
    public static boolean intValidation(String a) 
    {
        boolean result = false;
        if(a.matches("[0-9]+") && a.length() > 0)
        {
            result = true;
        }
        return result;
    }
    
    public static void customerCheck()
    {
        
    }
    
    //default strategy //WORKING
    public static int business()
    {
        Scanner in = new Scanner(System.in);
        n.setTopfloor(topFloor());
        int upTravellers = Customer.returnUpTravellers();
        int downTravellers = Customer.returnDownTravellers();
        int travellers = Customer.totalTravellers();
        while(Customer.totalTravellers() > 0)
        {
                
            //if the customers current floor matches the elevator current floor or destination floor
            for(int i = 0 ; i <totalCustomers ; i++)
            {
                int b = (Integer) list.get(i).get(1);
                int c = (Integer) list.get(i).get(2);
                //can only board the elevator if the customer is travelling in the correct direction, not currently in the elevator and have not already complted their journey
                if((b == n.currentFloor()) && (list.get(i).get(5).equals(n.direction()) && (list.get(i).get(3)==false))&&(list.get(i).get(4)==false))
                {
                    list = Customer.inElevator(i);
                }
                if((c == n.currentFloor()) && (list.get(i).get(3)==true) && (list.get(i).get(4)==false))
                {
                    list = Customer.OutElevator(i);
                }
            }
            
            if(Customer.returnUpTravellers() > 0)
            {
                int nextUpFloor = n.currentFloor();
                
                int nextDestinationFloor = 0;
                int nextCurrentFloor = 0;
                //counts the next current floor to travel to
                outerloop:
                for(int i = n.currentFloor() ; i <= highestDestination() ; i++)
                {
                    for(int x = 0 ; x < totalCustomers ; x++)
                    {
                        
                        if((list.get(x).get(5).equals("UP")&&(list.get(x).get(4) == false)&&(list.get(x).get(3)==false)))
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
                        if((list.get(x).get(5).equals("UP"))&&(list.get(x).get(4) == false)&&(list.get(x).get(3) == true))
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
        
                //changes the floor of the elevator
                if(upTravellers > 0)
                {
                    n.move(nextUpFloor-n.currentFloor());
                }
                
            }
            //ONCE ALL UP TRAVELLERS HAVE ARRIVED
            if((Customer.returnUpTravellers() == 0)&&(topFloorReached == false))//moves to the top floor when there are no more custoners travelling up
            {
                n.move(topFloor()-n.currentFloor());
                topFloorReached = true;
            }
            //START THE DOWN TRAVELLER PROCESS
            if(Customer.returnUpTravellers() == 0)
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
                //Finds the highest floor
                nextDownFloor = Math.max(nextCurrent, nextDestination);    
                //changes the floor of the elevator
                n.move(nextDownFloor-n.currentFloor());
            }
        }
        int result = n.returnFloorCount();
        return result;
    }
    
    //second strategy
    public static int business2()
    {
        System.out.println();
        Scanner in = new Scanner(System.in);
        int travellers = Customer.totalTravellers();
        
        boolean bottomFloorReached = false;
      
        //travelling up picking all customers up
        while(!topFloorReached)
        {

            //if the customers current floor matches the elevator current floor // needs to be moved to a single method to reduce duplication of code
            for(int i = 0 ; i <totalCustomers ; i++)
            {
                int b = (Integer) list.get(i).get(1);
                int c = (Integer) list.get(i).get(2);
                //checks that the current floor of the customers matche sthat of the elvator 
                //Checks that the destination floor of the customer is higher than their current floor thus travelling up
                //chevck that they are current not in the elevator
                if((b == n.currentFloor()) && (list.get(i).get(3)==false) && (list.get(i).get(4)==false))
                {
                    list = Customer.inElevator(i);
                }
                if((c == n.currentFloor()) && (list.get(i).get(3)==true) && (list.get(i).get(4)==false))
                {
                    list = Customer.OutElevator(i);
                }
            }
            
            int nextUpFloor = n.currentFloor();
    
            int nextDestinationFloor = 0;
            int nextCurrentFloor = 0;
            //counts the next current floor to travel to
            outerloop:
            for(int i = n.currentFloor() ; i <= highestCurrent() ; i++)
            {
                for(int x = 0 ; x < totalCustomers ; x++)
                {
                    
                    if((list.get(x).get(4) == false)&&(list.get(x).get(3)==false))
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
                    if((list.get(x).get(4) == false)&&(list.get(x).get(3) == true))
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
    
            
            //changes the floor of the elevator depending on the strategy
            n.move(nextUpFloor-n.currentFloor());

            if(topFloor() == n.currentFloor())
            {
                topFloorReached = true;
            }
        }
        
        //travelling down picking all customers up
        while(!bottomFloorReached)
        {
            //if the customers current floor matches the elevator current floor //Possibly store this chunk of code in the customer class
            for(int i = 0 ; i <totalCustomers ; i++)
            {
                int b = (Integer) list.get(i).get(1);
                int c = (Integer) list.get(i).get(2);
                //checks that the current floor of the customers matches that of the elevator 
                //Checks that the destination floor of the customer is lower than their current floor thus travelling down
                //check that they are current not in the elevator
                if((b == n.currentFloor()) && (list.get(i).get(3)==false) && (list.get(i).get(4)==false))
                {
                    list = Customer.inElevator(i);
                }
                if((c == n.currentFloor()) && (list.get(i).get(3)==true) && (list.get(i).get(4)==false))
                {
                    list = Customer.OutElevator(i);
                }
            }
            
            int nextCurrent = 0;
            int nextDestination = 0;
            int nextDownFloor = 0;
            
            //find the highest destination floor for the down passengers who are not in the lift and have not completed their journey //UNCONFIRMED WORKING
            for(int i = 0 ; i <= highestDestination() ;i++)//cycle through the floors
            {
                for(int x = 0 ; x < totalCustomers ; x++)//cycle through the customers each time the floor changes in the for statement above
                {
                    if((list.get(x).get(3)==true)&&(list.get(x).get(4)==false))
                    {
                        int b = (Integer)list.get(x).get(2);
                        if(b > nextDownFloor)
                        {
                            nextDownFloor = b;
                        }
                    }
                }
            }
            
            n.move(nextDownFloor-n.currentFloor());
            if(Customer.totalCustomers() == 0)
            {
                bottomFloorReached = true;
            }
        }
        int result = n.returnFloorCount();
        return result;
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
    
    //highest floor of the builing based on the customer's current and destination floors
    public static int topFloor()
    {
        int a = Math.max(highestCurrent(),highestDestination());
        return a;
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
}