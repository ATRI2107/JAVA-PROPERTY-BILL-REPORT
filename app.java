import java.util.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
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
    HashMap<Integer,properties> prop2=new HashMap<>();
    void record_rent_collection(HashMap<Integer,clients> cl,HashMap<Integer,ArrayList<expenses>> exp,HashMap<Integer,ArrayList<properties>> prop,HashMap<Integer,ArrayList<rents>> re)
    {
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Enter the address you want to find property details for: ");
        String address_search[]=sc.nextLine().split(" ");
        System.out.println("-------------------------------------");
        System.out.println("Property ID     ||   Property Address");
        System.out.println("-------------------------------------");
        for(Map.Entry<Integer,ArrayList<properties>> m:prop.entrySet())
        {
            
            ArrayList<properties> temp=m.getValue();
            for(properties p:temp)
            {
                String check_address[]=p.address.split(" ");
                HashMap<String,Integer> index=new HashMap<>();
                for(int i=0;i<check_address.length;i++)
                {
                    index.put(check_address[i],i);
                }
                boolean check=false;
                ArrayList<Integer> index_check=new ArrayList<>();
                for(String s:address_search)
                {
                    index_check.add(index.getOrDefault(s, -1));
                }
                if(index_check.size()==1 && index_check.get(0)!=-1) check=true;
                else
                {
                    for(int j=1;j<index_check.size();j++)
                    {
                        
                        if(index_check.get(j)-index_check.get(j-1)==1) check=true;
                        else
                        {
                            check=false;
                            break;
                        }
                    }
                } 
                if(check)
                {
                    prop2.put(p.property_id,p);
                    System.out.println(p.property_id+"                  "+p.address);
                }
                
                
                
            }
        }
        System.out.println();
        System.out.print("Enter the Property Id of your choice from the given options above : ");
        int pid=sc.nextInt();
        System.out.println();
        properties p=prop2.get(pid);
        System.out.println("-------------------------------------------------------------");
        System.out.println("Property Address   Property Weekly Rent   Property Owner Name");
        System.out.println("-------------------------------------------------------------");
        System.out.println(p.address+"          "+p.rent+"                  "+cl.get(p.client_id).name);
        System.out.println();
        System.out.print("Enter the number of weeks you want to collect rent for : ");
        int weeks=sc.nextInt();
        System.out.println();
        Date d=new Date();
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-mm-dd");
        String curr_date=formatter.format(d);
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                    SUMMMARY                                                      ");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Property Address   Property Weekly Rent   Number Of Weeks     Property Owner Name        Rent Collected            Date");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(p.address+"          "+p.rent+"                  "+weeks+"                "+cl.get(p.client_id).name+"             "+(weeks*p.rent)+"                 "+curr_date);
        System.out.println();
        menu(cl, exp, prop, re);
    }
    
    void menu(HashMap<Integer,clients> cl,HashMap<Integer,ArrayList<expenses>> exp,HashMap<Integer,ArrayList<properties>> prop,HashMap<Integer,ArrayList<rents>> re)
    {
        Scanner sc=new Scanner(System.in);
        int choice;

        System.out.println("Enter your choice:");
        System.out.println();
        System.out.println("1. Record Rent Collection ");
        System.out.println("2. Record Expenses ");
        System.out.println("3. Portfolio Report ");
        System.out.println("4. Save ");
        System.out.println("5. Exit");
        System.out.println();
        choice=sc.nextInt();
        System.out.println();
        switch(choice)
        {
            case 1:
                record_rent_collection(cl, exp, prop, re);
            break;

        }
    }
}
class app
{
    public static void main(String[] args) {

        File f;
        f=new File("clients.txt");
        PropertyReport pr=new PropertyReport();
        HashMap<Integer,clients> cl=new HashMap<>();
        HashMap<Integer,ArrayList<expenses>> exp=new HashMap<>();
        HashMap<Integer,ArrayList<properties>> prop=new HashMap<>();
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
                if(prop.containsKey(client_id))
                {
                    prop.get(client_id).add(new properties(property_id, address, suburb, state, postcode, rent, fee, client_id));
                }
                else 
                {
                    ArrayList<properties> al=new ArrayList<>();
                    al.add(new properties(property_id, address, suburb, state, postcode, rent, fee, client_id));
                    prop.put(client_id,al);
                }
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
       pr.menu(cl, exp, prop, re);
    }
}