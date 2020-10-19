package clients;

public class clients {
    public int client_id;
    public String name;
    public String address;
    public String suburb;
    public String state;
    public int postcode;
    public clients(int client_id,String name,String address,String suburb,String state,int postcode)
    {
        this.client_id=client_id;
        this.name=name;
        this.address=address;
        this.suburb=suburb;
        this.state=state;
        this.postcode=postcode;
    }
}
