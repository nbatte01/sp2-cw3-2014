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
    
    public void setFloors(int a)
    {
        numOfFloors = a;
    }
    
    public static int returnFloors()
    {
        return numOfFloors;
    }
    
    public void resetElevator()
    {
        current = 0;
        numOfFloors = 0;
        floorCount = 0;
        topFloor = 0;
        topFloorReached = false;
    }
    
    public int currentFloor()
    {
        if(current == topFloor)
        {
            topFloorReached = true;
        }
        return current;
    }
    
    public void setTopfloor(int a)
    {
        topFloor = a;
    }
    
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
    
    public void move(int a)//send this method an int of the nunber of floors to change ie -1, -2 or plus two. If the user only sends a 0 then floors do not move
    {
        if(a > 0) //go up a floor(s)
        {
            current += a;
            //System.out.println("The elevator has moved up to level: " + current);
            floorCount += 1;
        }
        else if(a < 0)//go down a floor(s)
        {
            current += a;
            //System.out.println("The elevator has moved down to level: " + current);
            floorCount += 1;
        }
        else 
        {
            //System.out.println("The elevator has not moved");
        }
    }
    
    public int returnFloorCount()
    {
        return floorCount;
    }

}