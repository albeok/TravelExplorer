
public class Reservation{
	
	private final int id;
	private final int destinationId;
	private final String customerName;
	
	public Reservation(int id,int destinationId,String customerName){
		this.id=id;
		this.destinationId=destinationId;
		this.customerName=customerName;
	}
	
	public int getDestinationId(){
		return destinationId;
	}
	
	@Override
	public String toString(){
		return ""
			+ "Reservation n. "+id+"\n"
			+ "Full Name: "+customerName;
	}
}