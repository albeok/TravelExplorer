import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;

public class CommandLine{
	private static int idDestination=0;
	private static int idReservation=0;
	protected static ArrayList<Destination>destinations=new ArrayList<>();
	protected static ArrayList<Reservation>reservations=new ArrayList<>();
	
	
	public static void initCommandLine(){
		try{
			showCommands();
			int number=Main.scanner.nextInt();
			Main.scanner.nextLine();
			execCommand(number);
		}
		catch(InputMismatchException e){
			System.out.println("Invalid input. Please enter a number.");
	        Main.scanner.nextLine();
	        initCommandLine();
		}
	}
	
	public static void addDestination(){
		while(true){
			String city=Validation.getValidatedInput("Insert the city (or type 'exit' to cancel):");
			if(city==null)break;
			String country=Validation.getValidatedInput("Insert the country (or type 'exit' to cancel):");
			if(country==null)break;
			createDestination(city,country);
		}
		initCommandLine();
	}
	
	public static void addReservation(){
		if(destinations.isEmpty()){
			System.out.println("There's no destination to reserve!");
			initCommandLine();
			return;
		}
		int id=Validation.getValidDestinationId("Select the ID of the destination:");
		String fullName=Validation.getValidFullName();
		Reservation reservation=new Reservation(idReservation++,id,fullName);
		reservations.add(reservation);
		System.out.println("Reservation added successfully!");

		initCommandLine();	
	}
	
	public static void deleteDestination(){
		if(destinations.isEmpty()){
			System.out.println("There's nothing to delete!");
			initCommandLine();
			return;
		}
		int id=Validation.getValidDestinationId("Insert the ID of the destination to delete:");
		removeReservationAndDestination(id);
		System.out.println("Destination deleted!");

		initCommandLine();			
	}
	
	public static void getDestination(){
		if(destinations.isEmpty()){
			System.out.println("No destinations found!");
		}
		else{
			destinations.forEach(destination->System.out.println(destination));
		}
		initCommandLine();
	}
	
	public static void getReservations(){
		if(reservations.isEmpty()){
			System.out.println("No reservations found!");
			initCommandLine();
			return;
		}
		int id=Validation.getValidDestinationId("Select the ID of the interested destination: ");
		System.out.println("Checking reservations for ID destination: "+id+"...");
		if(reservations.stream().noneMatch(reservation->reservation.getDestinationId()==id)){
			System.out.println("No reservation found.");
		}
		else{
			reservations.stream()
			.filter(reservation->reservation.getDestinationId()==id)
			.forEach(System.out::println);
		}
		initCommandLine();
	}
	
	public static void calculateTotalReservations(){
		System.out.println("Number of reservations: "+reservations.size());
		initCommandLine();
	}

	private static void showCommands(){
		System.out.println(""
				+ "to close the app, press 0\n"
				+ "to add destinations, press 1\n"
				+ "to get all the destination, press 2\n"
				+ "to delete a destination, press 3\n"
				+ "to add a reservation, press 4\n"
				+ "to get all the reservation for a specific destination, press 5\n"
				+ "to retrieve the total number of reservations, press 6"
				);		
	}
	
	private static void closeApp(){
		System.out.println("Closing the app...");
		Main.scanner.close();
		System.out.println("App closed.");
	}
	
	private static void execCommand(int number){
		switch(number){
			case 0 -> closeApp();
			case 1 -> addDestination();
			case 2 -> getDestination();
			case 3 -> deleteDestination();
			case 4 -> addReservation();
			case 5 -> getReservations();
			case 6 -> calculateTotalReservations();
			default -> {
				System.out.println("Not a valid number");
				initCommandLine();
			}
		}
	}
		
	private static void createDestination(String city,String country){
		System.out.println("Creating the new destination...");
		Destination d=new Destination(idDestination++,city,country);
		destinations.add(d);
		System.out.println("Destination created successfully!");
	}
	
	private static void removeReservationAndDestination(int id){
		destinations.removeIf(destination->destination.getId()==id);
		reservations.removeIf(reservation->reservation.getDestinationId()==id);
	}
	
}