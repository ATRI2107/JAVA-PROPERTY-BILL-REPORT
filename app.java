import java.util.*;

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

class app
{
    public static void main(String[] args) {
        
    }
}