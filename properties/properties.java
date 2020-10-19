package properties;

public class properties {
    public int property_id;
    public String address;
    public String suburb;
    public String state;
    public int postcode;
    public int rent;
    public double fee;
    public int client_id;
    public properties(int property_id,String address,String suburb,String state,int postcode,int rent,double fee,int client_id)
    {
        this.property_id=property_id;
        this.address=address;
        this.suburb=suburb;
        this.state=state;
        this.postcode=postcode;
        this.rent=rent;
        this.fee=fee;
        this.client_id=client_id;
    }
}
