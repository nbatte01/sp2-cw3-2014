import org.junit.Test;


public class TestElevator {

	@Test
	public void test() {
		//This test checks that number of floors and top floor is set correctly
		Elevator elev = new Elevator();
		elev.setNumOfFloors(2);
		if(elev.getNumofFloors() != 2)
		{
			System.out.println("There is an issue with either the setNumOfFloors or getNumofFloors methods.");
		}
		elev.setTopFloor(15);
		if(elev.getTopFloor() != 15)
		{
			System.out.println("There is an issue with either the setTopFloor or getTopFloor methods.");
		}
		
		//This test checks that the elevator is reset
		elev.resetElevator();
		if(elev.getNumofFloors() != 0)
		{
			System.out.println("There is an issue with the resetElevator method.");
		}
		if(elev.getTopFloor() != 0)
		{
			System.out.println("There is an issue with the resetElevator method.");
		}
		
		//tests the move and current floor methods
		// this sets the top floor to 10
		elev.setTopFloor(10);
		//this moves the elevator 8 floors
		elev.move(8);
		if(elev.currentFloor() != 8)
		{
			System.out.println("There is an issue with either the move or currentFloor methods.");
		}
		if(elev.direction() != "UP")
		{
			System.out.println("There is an issue with the direction method.");
		}
     	//this moves the elevator 2 floors - to the top of the building
		elev.move(2);
		if(elev.currentFloor() != 10)
		{
			System.out.println("There is an issue with either the move or currentFloor methods.");
		}
		if(elev.getTopFloorReached() != true)
		{
			System.out.println("There is an issue with either the currentFloor or getTopFloorReached methods.");
		}
		//Since 2 floors have been visited the below should return 2
		if(elev.returnFloorCount() != 2)
		{
			System.out.println("There is an issue with the returnFloorCount method.");
		}

	}

}
