package expenses;

public class expenses {
    public int property_id;
    public String expense;
    public double cost;
    public String date;
    public expenses(int property_id,String expense,double cost,String date)
    {
        this.property_id=property_id;
        this.expense=expense;
        this.cost=cost;
        this.date=date;
    }
}
