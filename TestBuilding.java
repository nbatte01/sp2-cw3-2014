import org.junit.Test;


public class TestBuilding {

	@Test
	public void test() {
		//tests floorValiadation - no output should occur
		if (Building.floorValidation("10") == false)
		{
			System.out.println("There is an issue with the floorValidation method when inputting an integer");
		};
		
		if(Building.floorValidation("1,000") == true)
		{
			System.out.println("There is an issue with the floorValidation method when inputting text");
		}
		if(Building.floorValidation("0")== true)
		{
			System.out.println("There is an issue with the floorValidation method when 0 is input");
		}
		if(Building.floorValidation("13")== true)
		{
			System.out.println("There is an issue with the floorValidation method when 13 is input");
		}
		
		//tests customerValidation - no output should occur
		if(Building.customersValidation("15")== false)
		{
			System.out.println("There is an issue with the customerValidation method when inputting an integer");
		}
		if(Building.customersValidation("0")== true)
		{
			System.out.println("There is an issue with the customerValidation method when 0 is input");
		}
		
		//tests floorValidationMessage - no output should occur
		if(Building.floorValidationMessage("0") != "The number of floors cannot equal 0. Please retry and enter an integer.")
		{
			System.out.println("There is an issue with the floorValidationMessage method when 0 is input");
		}
		if(Building.floorValidationMessage("13") != "The number of floors cannot equal 13. Please retry and enter an integer that is not 13.")
		{
			System.out.println("There is an issue with the floorValidationMessage method when 13 is input");
		}
		if(Building.floorValidationMessage("1,000") != "The number of floors cannot accept any any text. Please retry and enter an integer.")
		{
			System.out.println("There is an issue with the floorValidationMessage method when text is input");
		}
		
		//tests customerValidationMessage - no output should occur
		if(Building.customersValidationMessage("0") != "The number of customers cannot equal 0. Please retry and enter an integer.")
		{
			System.out.println("There is an issue with the customersValidationMessage method when 0 is input");
		}
		if(Building.customersValidationMessage("1,000") != "The number of customers cannot accept any any text. Please retry and enter an integer.")
		{
			System.out.println("There is an issue with the customersValidationMessage method when text is input");
		}
		
	}

}
