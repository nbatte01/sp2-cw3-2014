/* This code was created by Nicholas Batten and Bradd Bently for the third coursework
 * of the Software and Programming 2 module at Birkbeck, University of London (December 2014).
 */

public class Elevator
{
    private int current = 0;
    private static int numOfFloors;
    int floorCount = 0; 
    int topFloor = 0;
    boolean topFloorReached = false;
    
    //receives the total number of floors from the building class
    public void setFloors(int a)
    {
        numOfFloors = a;
    }
    
    //resets the elevator after a strategy in the Building class has been completed to allow for another to begin
    public void resetElevator()
    {
        current = 0;
        numOfFloors = 0;
        floorCount = 0;
        topFloor = 0;
        topFloorReached = false;
    }
    
    //Returns the current floor of the elevator
    public int currentFloor()
    {
        if(current == topFloor)
        {
            topFloorReached = true;
        }
        return current;
    }
    
    //receives the highest floor of the building from the Building class
    public void setTopfloor(int a)
    {
        topFloor = a;
    }
    
    //Returns the direction that the elvator is travelling in
    public String direction()
    {
        String direction = "";
        //if the top floor has been reached once
        if(topFloorReached)
        {
            direction = "DOWN";
        }
        else
        {
            direction = "UP";
        }
        return direction;
    }
    
    //Receives an int from the building class and move the elevator to the required floor. Keeps a count of the number of floors visited
    public void move(int a)
    {
        if(a > 0) //go up a floor(s)
        {
            current += a;
            floorCount += 1;
        }
        else if(a < 0)//go down a floor(s)
        {
            current += a;
            floorCount += 1;
        }
    }
    
    //returns the number of floors visited
    public int returnFloorCount()
    {
        return floorCount;
    }

}