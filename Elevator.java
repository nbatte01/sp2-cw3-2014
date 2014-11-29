public class Elevator
{
    private int current = 0;
    private int currentOnBoard = 0;
    private static int numOfFloors;
    
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
        return current;
    }
    
    public void direction()
    {
        
    }
    
    public void move(int a)//send this method an int of the nunber of floors to change ie -1, -2 or plus two. If the user only sends a 0 then floors do not move
    {
        if(a > 0) //go up a floor(s)
        {
            while(a > 0)
            {
                current += 1;
                System.out.println("The elevator has moved to level up one level to: " + current);
                a -= 1;
            }
        }
        else if(a < 0)//go down a floor(s)
        {
            current -=1;
            System.out.println("The elevator has moved to level: " + current);
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