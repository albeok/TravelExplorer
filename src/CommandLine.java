import java.util.ArrayList;
import java.util.Iterator;

public class CommandLine{
	private static int idDestination=0;
	private static int idReservation=0;
	private static ArrayList<Destination>destinations=new ArrayList<>();
	private static ArrayList<Reservation>reservations=new ArrayList<>();
	
	public static void initCommandLine(){
		System.out.println(""
			+ "to close the app, press 0\n"
			+ "to add destinations, press 1\n"
			+ "to get all the destination, press 2\n"
			+ "to delete a destination, press 3\n"
			+ "to add a reservation, press 4\n"
			+ "to get all the reservation for a specific destination, press 5\n"
			+ "to retrieve the total number of reservations, press 6"
		);
		
		int number=Main.scanner.nextInt();
		Main.scanner.nextLine();
		
		switch(number){
		case 0:
			System.out.println("Closing the app...");
			Main.scanner.close();
			System.out.println("App closed.");
			break;
		case 1:
			CommandLine.addDestination();
			break;
		case 2:
			CommandLine.getDestination();
			break;
		case 3:
			if(destinations.isEmpty()){
				System.out.println("There's nothing to delete!");
				CommandLine.initCommandLine();
				break;
			}
			CommandLine.deleteDestination();
			break;
		case 4:
			if(destinations.isEmpty()){
				System.out.println("There's no destination to reserve!");
				CommandLine.initCommandLine();
				break;
			}
			CommandLine.addReservation();
			break;
		case 5:
			if(reservations.isEmpty()){
				System.out.println("No reservations found!");
				CommandLine.initCommandLine();
				break;
			}
			CommandLine.getReservations();
			break;
		case 6:
			CommandLine.calculateTotalReservations();
			break;
		default:
			System.out.println("Not a valid number");
			CommandLine.initCommandLine();
			break;
		}
	}
	
	public static void addDestination(){
		System.out.println("Create a new destination! type 'exit' to exit");
		while(true){
			System.out.println("Insert the city");
			String city=Main.scanner.nextLine();
			if(city.trim().isEmpty()){
				System.out.println("City cannot be empty. Please enter a valid city.");
		        continue;
			}
			if(city.equalsIgnoreCase("exit"))break;
			System.out.println("Insert the country");		
			String country=Main.scanner.nextLine();
			if(country.trim().isEmpty()){
				System.out.println("Country cannot be empty. Please enter a valid country.");
		        continue;
			}
			System.out.println("Creating the new destination...");
			Destination d=new Destination(idDestination++,city,country);
			System.out.println("Destination added successfully!");
			destinations.add(d);				
		}
		CommandLine.initCommandLine();
	}
	
	public static void addReservation(){
		System.out.println("Id of the destination");
		int id=Main.scanner.nextInt();
		Main.scanner.nextLine();
		boolean found=false;
		for(Destination destination:destinations){
			if(destination.getId()==id) {
				found=true;
				break;
			}
		}
		if(!found){
			System.out.println("This id doesn't exist!");
			CommandLine.addReservation();
			return;	
		}
		System.out.println("Your fullname, please");		
		while(true){
			String fullName=Main.scanner.nextLine();
			if(fullName.trim().isEmpty()){
				System.out.println("This field can't be empty! Try again.");
				continue;
			}
			Reservation r=new Reservation(idReservation++,id,fullName);
			reservations.add(r);
			System.out.println("Reservation added successfully!");
			CommandLine.initCommandLine();
			break;
		}
	}
	
	public static void deleteDestination(){
		System.out.println("Insert the id of the destination to delete");
		int id=Main.scanner.nextInt();
		Main.scanner.nextLine();
		boolean found=false;
		for(Destination destination:destinations){
			if(destination.getId()==id){
				found=true;
				break;
			}
		}
		if(!found){
			System.out.println("This id doesn't exist!");
			CommandLine.deleteDestination();
			return;
		}

		Iterator<Destination>iteratorD=destinations.iterator();
		while(iteratorD.hasNext()){
			Destination destination=iteratorD.next();
			if(destination.getId()==id){
				iteratorD.remove();
				break;
			}
		}
		Iterator<Reservation>iteratorR=reservations.iterator();
		while(iteratorR.hasNext()){
			Reservation reservation=iteratorR.next();
			if(reservation.getDestinationId()==id)iteratorR.remove();
		}
		System.out.println("Destination deleted!");
		CommandLine.initCommandLine();
	}
	
	public static void getDestination(){
		for(Destination destination: destinations){
			System.out.println(destination.toString());
		}
		CommandLine.initCommandLine();
	}
	
	public static void getReservations(){
		System.out.println("destination id");
		int id=Main.scanner.nextInt();
		Main.scanner.nextLine();
		System.out.println("Reservations of destination with id "+id);
		boolean found=false;
		for(Reservation reservation:reservations){
			if(reservation.getDestinationId()==id){
				found=true;
				System.out.println(reservation.toString());
			}
		}
		if(!found)System.out.println("No reservation found for id "+id);
		CommandLine.initCommandLine();
	}
	
	public static void calculateTotalReservations(){
		System.out.println("Number of reservations: "+reservations.size());
		CommandLine.initCommandLine();
	}
}
