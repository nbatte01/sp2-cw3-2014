public class Elevator
{
    private int current = 0;
    private int currentOnBoard = 0;
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
    
    public void registerList()
    {
        
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
            System.out.println("The elevator has moved up to level: " + current);
            floorCount += 1;
        }
        else if(a < 0)//go down a floor(s)
        {
            current += a;
            System.out.println("The elevator has moved down to level: " + current);
            floorCount += 1;
        }
        else 
        {
            System.out.println("The elevator has not moved");
        }
    }
    
    public void customerJoins()
    {
        currentOnBoard++;
    }
    
    public int returnFloorCount()
    {
        return floorCount;
    }
    
    public void customerLeaves()
    {
        if(currentOnBoard == 0)
        {
            System.out.println("There are currently no customers on board");
        }
        else
        {
            System.out.println("A customer has left the elevator");
            currentOnBoard--;
        }
           
    }
}