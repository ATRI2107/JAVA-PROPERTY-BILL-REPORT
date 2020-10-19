package rents;
public class rents
{
    public int property_id;
    public int rent_amt;
    public String date;
    public rents(int property_id,int rent_amt,String date)
    {
        this.property_id=property_id;
        this.rent_amt=rent_amt;
        this.date=date;
    }
}