import java.util.InputMismatchException;

public class Validation{
			
		protected static String getValidatedInput(String prompt){
			System.out.println(prompt);
			while(true){
				String input=Main.scanner.nextLine();
				if(input.equalsIgnoreCase("exit"))return null;
				if(!input.trim().isEmpty())return input;
			}
		}
		
		protected static String getValidFullName(){
			String fullName;
			System.out.println("Enter your full name:");
			
			while(true){
				fullName=Main.scanner.nextLine();
				if(!fullName.trim().isEmpty()){
					break;
				}
				System.out.println("This field can't be empty! Try again.");
			}
			
			return fullName;
		}
		
		protected static int getValidDestinationId(String prompt){
			System.out.println(prompt);
			while(true){
				try{
					int id=Main.scanner.nextInt();
					Main.scanner.nextLine();
					if(destinationExists(id))return id;
					else System.out.println("This ID doesn't exist.");
				}
				catch(InputMismatchException e){
					System.out.println("Invalid input. Please try again.");
					Main.scanner.nextLine();
				}
			}
		}

		protected static boolean destinationExists(int id){
			return CommandLine.destinations.stream().anyMatch(destination->destination.getId()==id);
		}
}