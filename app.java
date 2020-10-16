import java.util.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
class clients
{
    int client_id;
    String name;
    String address;
    String suburb;
    String state;
    int postcode;
    clients(int client_id,String name,String address,String suburb,String state,int postcode)
    {
        this.client_id=client_id;
        this.name=name;
        this.address=address;
        this.suburb=suburb;
        this.state=state;
        this.postcode=postcode;
    }
}

class properties
{
    int property_id;
    String address;
    String suburb;
    String state;
    int postcode;
    int rent;
    double fee;
    int client_id;
    properties(int property_id,String address,String suburb,String state,int postcode,int rent,double fee,int client_id)
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

class expenses
{
    int property_id;
    String expense;
    int cost;
    String date;
    expenses(int property_id,String expense,int cost,String date)
    {
        this.property_id=property_id;
        this.expense=expense;
        this.cost=cost;
        this.date=date;
    }
}

class rents
{
    int property_id;
    int rent_amt;
    String date;
    rents(int property_id,int rent_amt,String date)
    {
        this.property_id=property_id;
        this.rent_amt=rent_amt;
        this.date=date;
    }
}
class PropertyReport
{
    HashMap<Integer,clients> cl=new HashMap<>();
    void read_file(File f)
    {
        try(Scanner sc=new Scanner(f,StandardCharsets.UTF_8.name()))
        {
            while(sc.hasNextLine())
            {
                String temp[]=sc.nextLine().split(",");
                int client_id=Integer.parseInt(temp[0]);
                String name=temp[1];
                String address=temp[2];
                String suburb=temp[3];
                String state=temp[4];
                int postcode=Integer.parseInt(temp[5]);
                cl.put(client_id,new clients(client_id, name, address, suburb, state, postcode));
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        //System.out.println(cl.get(1).name);
    }
}
class app
{
    public static void main(String[] args) {
        
        PropertyReport pr=new PropertyReport();
        pr.read_file(new File("clients.txt"));
        
    }
}