
public class Destination{
	private final int id;
	private final String city;
	private final String country;
	
	public Destination(int id,String city,String country){
		this.id=id;
		this.city=city;
		this.country=country;
	}
	
	public int getId(){
		return id;
	}
	
	public String getCity(){
		return city;
	}
	
	public String getCountry(){
		return country;
	}
	
	
	@Override
	public String toString(){
		return "id: "+id+"\ncity: "+city+"\ncountry: "+country;
	}
}