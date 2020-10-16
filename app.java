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
    double cost;
    String date;
    expenses(int property_id,String expense,double cost,String date)
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
    

}
class app
{
    public static void main(String[] args) {

        File f;
        f=new File("clients.txt");
        HashMap<Integer,clients> cl=new HashMap<>();
        HashMap<Integer,ArrayList<expenses>> exp=new HashMap<>();
        HashMap<Integer,properties> prop=new HashMap<>();
        HashMap<Integer,ArrayList<rents>> re=new HashMap<>();

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

        f=new File("expenses.txt");
        try(Scanner sc=new Scanner(f,StandardCharsets.UTF_8.name()))
        {
            while(sc.hasNextLine())
            {
                String temp[]=sc.nextLine().split(",");
                int property_id=Integer.parseInt(temp[0]);
                String expense=temp[1];
                double cost=Double.parseDouble(temp[2]);
                String date=temp[3];
                if(exp.containsKey(property_id))
                {
                    exp.get(property_id).add(new expenses(property_id, expense, cost, date));
                }
                else
                {
                    ArrayList<expenses> al=new ArrayList<>();
                    al.add(new expenses(property_id, expense, cost, date));
                    exp.put(property_id,al);
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        f=new File("properties.txt");
        try(Scanner sc=new Scanner(f,StandardCharsets.UTF_8.name()))
        {
            while(sc.hasNextLine())
            {
                String temp[]=sc.nextLine().split(",");
                int property_id=Integer.parseInt(temp[0]);
                String address=temp[1];
                String suburb=temp[2];
                String state=temp[3];
                int postcode=Integer.parseInt(temp[4]);
                int rent=Integer.parseInt(temp[5]);
                double fee=Double.parseDouble(temp[6]);
                int client_id=Integer.parseInt(temp[7]);
                prop.put(property_id,new properties(property_id, address, suburb, state, postcode, rent, fee, client_id));
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        f=new File("rents.txt");
        try(Scanner sc=new Scanner(f,StandardCharsets.UTF_8.name()))
        {
            while(sc.hasNextLine())
            {
                String temp[]=sc.nextLine().split(",");
                int property_id=Integer.parseInt(temp[0]);
                int rent_amt=Integer.parseInt(temp[1]);
                String date=temp[2];
                if(re.containsKey(property_id))
                {
                    re.get(property_id).add(new rents(property_id, rent_amt, date));
                }
                else
                {
                    ArrayList<rents> al=new ArrayList<>();
                    al.add(new rents(property_id, rent_amt, date));
                    re.put(property_id,al);
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        System.out.println(re.get(11).get(2).date);
    }
}