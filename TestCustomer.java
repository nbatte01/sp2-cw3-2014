import org.junit.Test;


public class TestCustomer {

	@Test
	public void test() {
		//create new instance of customer
		Customer cus = new Customer();
		//the two integer inputs are from the building class main where the input takes place
		cus.customerList(1, 200);
		//this checks that the correct number of customers are in the elevator before it starts moving
		if (cus.totalTravellers() != 1)
		{
			System.out.println("There is an issue with the number of customers. Review the process that loads the arraylist");
		}
		if(cus.totalTravellers() != cus.returnOriginalCustomers())
		{
			System.out.println("The number of elevator travellers does not match the original number of customers.");
		}
		//the highest destination cannot be higher than the building - 200 floors or equal to floor 13
		if(cus.highestDestination() > 200 || cus.highestDestination() == 13)
		{
			System.out.println("There is an issue with the random number generator as the highesht destination should not exceed the height of the building or equal floor 13");
		}
		//the highest current cannot be higher than the building - 200 floors or equal to floor 13
		if(cus.highestCurrent() > 200 || cus.highestCurrent() == 13)
		{
			System.out.println("There is an issue with the random number generator as the highesht destination should not exceed the height of the building or equal floor 13");
		}
		//this tests that the returnUpTravellers is being populated when the elevator is travelling UP
		if(cus.highestCurrent() < cus.highestDestination() && cus.returnUpTravellers() == 0 )
		{	
			System.out.println("There is an issue with the returnUpTravellers method.");
		}
		//this tests that the returnDownTravellers is being populated when the elevator is travelling UP
		if(cus.highestCurrent() > cus.highestDestination() && cus.returnDownTravellers() == 0 )
		{	
			System.out.println("There is an issue with the returnDownTravellers method.");
		}
		//add the customer to the elevator - as there is only one customer in this test
		cus.inElevator(1);
		//the customer gets out of the elevator - now there should be no one left
		//the outElevator methods needs to be 0 as its the first row of the arraylist
		cus.OutElevator(0);
		if(cus.totalTravellers() != 0)
		{
			System.out.println("There is an issue with the OutElevator methhod as there should be 0 travellers left");
		}
		//this tests the random method to ensure that the number created is not greater than the building
		//minimum 0 floors, maximum 100 floors
		if(cus.random(0, 100) < 0 || cus.random(0, 100) ==13 || cus.random(0, 100) > 100)
		{
			System.out.println("There is an issue with the random class that generates the floors.");
		}
		//reset array list
		cus.resetList();
		if(cus.totalTravellers() != 1)
		{
			System.out.println("There is an issue with the resetList method as the number of total travellers doesn not equal 0");
		}
		
		
	}

}
