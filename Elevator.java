/* This code was created by Nicholas Batten and Bradd Bentley for the third course work
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
    public void setNumOfFloors(int a)
    {
        numOfFloors = a;
    }
    
    //returns the total number of floors
    public int getNumofFloors()
    {
    	return numOfFloors;
    }
    
        //receives the highest floor of the building from the Building class
    public void setTopFloor(int a)
    {
        topFloor = a;
    }
    
    //returns the highest floor of the building
    public int getTopFloor()
    {
    	return topFloor;
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
    
    public boolean getTopFloorReached()
    {
    	return topFloorReached;
    }
    
    //Returns the direction that the elevator is travelling in
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
    
    //Receives an integer from the building class and move the elevator to the required floor. Keeps a count of the number of floors visited
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