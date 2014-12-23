/* This code was created by Nicholas Batten and Bradd Bently for the third coursework
 * of the Software and Programming 2 module at Birkbeck, University of London (December 2014).
 */

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Building
{
    public static int totalFloors;
    public static int totalCustomers;
    public static boolean topFloorReached = false;
    public static Elevator Ele = new Elevator();
    public static Customer Cus = new Customer();
    //Asks the user to input the number of floor in the building and the number of customers. Prints the results from both strategies //WORKING
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
                Ele.setFloors(totalFloors);
                totalCustomers = (Integer.valueOf(customers));
                //requires the number of floors to be preset
                Cus.customerList(totalCustomers, totalFloors);//customer class will return a arraylist that contain ID number, current floor, destination floor and whether or not the customer is still in the lift
                
                //runs the new strategy 
                double newCount =  business2();
                
                //Resets the required variables int the customer and elevator classes to allow the default strategy to start from scratch with the original list
                Cus.resetList();
                Ele.resetElevator();
                
                //runs the default strategy
                double defaultCount =  business();

                //Display the results
                //new strategy results
                System.out.println();
                System.out.println("New strategy results");
                System.out.println("The total number of floors visited after the new strategy: " + (int)newCount);
                System.out.println("The total number of floors in the building: "+ totalFloors);
                //calculates the perentage of floors visited to allow for easier strategy performance review
                double percentageVisited = (newCount*100)/ totalFloors;
                System.out.print("Percentage of floors visited: ");
                System.out.printf("%.2f", percentageVisited);
                System.out.println("%");
                
                //Default strategy results
                System.out.println();
                System.out.println("Default strategy results");
                System.out.println("The total number of floors visited after the default strategy: " + (int)defaultCount);
                System.out.println("The total number of floors in the building: "+ totalFloors);
                //calculates the perentage of floors visited to allow for easier strategy performance review
                double percentageVisited2 = (defaultCount*100)/ totalFloors;
                System.out.print("Percentage of floors visited: ");
                System.out.printf("%.2f", percentageVisited2);
                System.out.println("%");
                
                //Print's which strategy was more efficient
                System.out.println();
                System.out.println("Final verdict:");
                if(percentageVisited2 > percentageVisited)
                {
                    System.out.println("The new strategy was more efficient than the default as it visited " + ((int)defaultCount - (int)newCount) + " less floor(s)");
                }
                else if(percentageVisited > percentageVisited2)
                {
                    System.out.println("The default strategy was more efficient than the second as it visited " + ((int)newCount - (int)defaultCount) + " less floor(s)");
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
    //receives parameter of type String and return boolean val
    public static boolean intValidation(String a) 
    {
        boolean result = false;
        if(a.matches("[0-9]+") && a.length() > 0)
        {
            result = true;
        }
        return result;
    }

    //default strategy //WORKING
    //returns data type double which is the number of floors that have been visited
    //This is the default strategy. This strategy picks up passengers that are travelling up on it's way to the highest required floor whilst letting out all 
    //passengers travelling up that arrive at their destiantion. Once the elevator is at the highest floor it proceeeds to head to the ground floor picking up
    //and dropping off all passenegrs that wish to travel in the downward direction. No downward travelling passengers enter the elevator on the way to the top
    public static double business()
    {
        Ele.setTopfloor(topFloor());

        while(Cus.totalTravellers() > 0)
        {
                
            //if the customers current floor matches the elevator current floor or destination floor
            for(int i = 0 ; i <Cus.returnOriginalCustomers() ; i++)
            {
                int b = (Integer) Cus.returnList().get(i).get(1);
                int c = (Integer) Cus.returnList().get(i).get(2);
                //can only board the elevator if the customer is travelling in the correct direction, not currently in the elevator and have not already complted their journey
                if((b == Ele.currentFloor()) && (Cus.returnList().get(i).get(5).equals(Ele.direction()) && (Cus.returnList().get(i).get(3)==false))&&(Cus.returnList().get(i).get(4)==false))
                {
                    Cus.inElevator(i);
                }
                //exit the elevatpr if the customer is currently in the elevator and have not completed their journey
                if((c == Ele.currentFloor()) && (Cus.returnList().get(i).get(3)==true) && (Cus.returnList().get(i).get(4)==false))
                {
                    Cus.OutElevator(i);
                }
            }
            
            if(Cus.returnUpTravellers() > 0)
            {
                int nextUpFloor = Ele.currentFloor();
                int nextDestinationFloor = 0;
                int nextCurrentFloor = 0;
                //counts the next current floor to travel to
                outerloop:
                for(int i = Ele.currentFloor() ; i <= highestDestination() ; i++)
                {
                    for(int x = 0 ; x < Cus.returnOriginalCustomers() ; x++)
                    {
                        if((Cus.returnList().get(x).get(5).equals("UP")&&(Cus.returnList().get(x).get(4) == false)&&(Cus.returnList().get(x).get(3)==false)))
                        {
                            int c = (Integer) Cus.returnList().get(x).get(1);
                            if((c == i)&&(c > Ele.currentFloor()))
                            {
                                nextCurrentFloor = i;
                                break outerloop;
                            }
                        }
                    }
                }
                //counts the next destination floor to travel to
                outerloop:
                for(int i = Ele.currentFloor() ; i <= highestDestination() ; i++)
                {
                    for(int x = 0 ; x < Cus.returnOriginalCustomers() ; x++)
                    {
                        if((Cus.returnList().get(x).get(5).equals("UP"))&&(Cus.returnList().get(x).get(4) == false)&&(Cus.returnList().get(x).get(3) == true))
                        {
                            int c = (Integer) Cus.returnList().get(x).get(2);
                            if((c == i)&&(c > Ele.currentFloor()))
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
                
                Ele.move(nextUpFloor-Ele.currentFloor());
            }
            //Once all up travellers have arrived at their destination the elevator will move to the highest required floor
            if((Cus.returnUpTravellers() == 0)&&(topFloorReached == false))
            {
                Ele.move(topFloor()-Ele.currentFloor());
                topFloorReached = true;
            }
            //The elevator begins to move down
            if(Cus.returnUpTravellers() == 0)
            {
                int nextCurrent = 0;
                int nextDestination = 0;
                int nextDownFloor = 0;
                
                //find the highest current floor and the highest destination floor for the down passengers who are not in the lift and have not completed their journey //WORKING
                for(int i = 0 ; i <= highestDestination() ;i++)//cycle through the floors
                {
                    for(int x = 0 ; x < Cus.returnOriginalCustomers() ; x++)//cycle through the customers each time the floor changes in the for statement above
                    {
                        if((Cus.returnList().get(x).get(5).equals("DOWN"))&&(Cus.returnList().get(x).get(3)==false)&&(Cus.returnList().get(x).get(4)==false))
                        {
                            int a = (Integer)Cus.returnList().get(x).get(1);
                            if(a > nextCurrent)
                            {
                                nextCurrent = a;
                            }
                        }
                        if((Cus.returnList().get(x).get(5).equals("DOWN"))&&(Cus.returnList().get(x).get(3)==true)&&(Cus.returnList().get(x).get(4)==false))
                        {
                            int b = (Integer)Cus.returnList().get(x).get(2);
                            if(b > nextDestination)
                            {
                                nextDestination = b;
                            }
                        }
                    }
                }
                nextDownFloor = Math.max(nextCurrent, nextDestination);    
                Ele.move(nextDownFloor-Ele.currentFloor());
            }
        }
        double result = Ele.returnFloorCount();
        return result;
    }
    
    //new strategy //WORKING
    //returns data type double which is a count of the number of floors that have been visited
    //This strategy picks all passengers up on the way to the highest required floor while dropping off any customers that wish to get out, then 
    //once at the top the elevator makes it way to the bottom letting out any remaining passengers
    public static double business2()
    {
        boolean bottomFloorReached = false;
        //travelling up picking all customers up
        while(!topFloorReached)
        {

            //if the customers current floor matches the elevator current floor or the customer's destination floors matches the current floor
            for(int i = 0 ; i <Cus.returnOriginalCustomers() ; i++)
            {
                int b = (Integer) Cus.returnList().get(i).get(1);
                int c = (Integer) Cus.returnList().get(i).get(2);
                if((b == Ele.currentFloor()) && (Cus.returnList().get(i).get(3)==false) && (Cus.returnList().get(i).get(4)==false))
                {
                    Cus.inElevator(i);
                }
                if((c == Ele.currentFloor()) && (Cus.returnList().get(i).get(3)==true) && (Cus.returnList().get(i).get(4)==false))
                {
                    Cus.OutElevator(i);
                }
            }
            int nextUpFloor = Ele.currentFloor();
            int nextDestinationFloor = 0;
            int nextCurrentFloor = 0;
            //counts the next current floor to travel to
            outerloop:
            for(int i = Ele.currentFloor() ; i <= highestCurrent() ; i++)
            {
                for(int x = 0 ; x < Cus.returnOriginalCustomers() ; x++)
                {
                    
                    if((Cus.returnList().get(x).get(4) == false)&&(Cus.returnList().get(x).get(3)==false))
                    {
                        int c = (Integer) Cus.returnList().get(x).get(1);
                        if((c == i)&&(c > Ele.currentFloor()))
                        {
                            nextCurrentFloor = i;
                            break outerloop;
                        }
                    }
                }
            }
            
            //counts the next destination floor to travel to
            outerloop:
            for(int i = Ele.currentFloor() ; i <= highestDestination() ; i++)
            {
                for(int x = 0 ; x < Cus.returnOriginalCustomers() ; x++)
                {
                    if((Cus.returnList().get(x).get(4) == false)&&(Cus.returnList().get(x).get(3) == true))
                    {
                        int c = (Integer) Cus.returnList().get(x).get(2);
                        if((c == i)&&(c > Ele.currentFloor()))
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
            
            Ele.move(nextUpFloor-Ele.currentFloor());
            
            if(topFloor() == Ele.currentFloor())
            {
                topFloorReached = true;
            }
        }
        
        //travelling down picking all customers up
        while(!bottomFloorReached)
        {
            //if the customers current floor matches the elevator current floor //Possibly store this chunk of code in the customer class
            for(int i = 0 ; i <Cus.returnOriginalCustomers() ; i++)
            {
                int b = (Integer) Cus.returnList().get(i).get(1);
                int c = (Integer) Cus.returnList().get(i).get(2);
                if((b == Ele.currentFloor()) && (Cus.returnList().get(i).get(3)==false) && (Cus.returnList().get(i).get(4)==false))
                {
                    Cus.inElevator(i);
                }
                if((c == Ele.currentFloor()) && (Cus.returnList().get(i).get(3)==true) && (Cus.returnList().get(i).get(4)==false))
                {
                    Cus.OutElevator(i);
                }
            }
            
            int nextCurrent = 0;
            int nextDestination = 0;
            int nextDownFloor = 0;
            
            //find the highest destination floor for the down passengers who are not in the lift and have not completed their journey 
            for(int i = 0 ; i <= highestDestination() ;i++)//cycle through the floors
            {
                for(int x = 0 ; x < Cus.returnOriginalCustomers() ; x++)//cycle through the customers each time the floor changes in the for statement above
                {
                    if((Cus.returnList().get(x).get(3)==true)&&(Cus.returnList().get(x).get(4)==false))
                    {
                        int b = (Integer)Cus.returnList().get(x).get(2);
                        if(b > nextDownFloor)
                        {
                            nextDownFloor = b;
                        }
                    }
                }
            }
            Ele.move(nextDownFloor-Ele.currentFloor());
            if(Cus.totalTravellers() == 0)
            {
                bottomFloorReached = true;
            }
        }
        double result = Ele.returnFloorCount();
        return result;
    }
    
    //find the highest current floor //WORKING
    public static int highestCurrent()
    {
        int highestCurrent = 0;
        for(int i = 0; i < Cus.returnOriginalCustomers() ; i++)
        {
            int a = (Integer) Cus.returnList().get(i).get(1);
            if(a > highestCurrent)
            {
                highestCurrent = a;
            }
        }
        return highestCurrent;
    }
    
    //highest floor of the builing based on the customer's current and destination floors //WORKING
    public static int topFloor()
    {
        int a = Math.max(highestCurrent(),highestDestination());
        return a;
    }
    
    //find the highest destination floor //WORKING
    public static int highestDestination()
    {
        int highestDestination = 0;
        for(int i = 0 ; i< Cus.returnOriginalCustomers() ; i++)
        {
            int b = (Integer) Cus.returnList().get(i).get(2);
            if(b > highestDestination)
            {
                highestDestination = b;
            }
        }
        return highestDestination;
    }

}